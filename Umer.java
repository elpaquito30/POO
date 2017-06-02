import java.util.TreeMap;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;


public class Umer implements Serializable{
   
private TreeMap<String,Utilizador> utilizadores;
private TreeMap<String,Viatura> taxis;
private Utilizador userLogin;

public Umer(){
    this.utilizadores = new TreeMap<String,Utilizador>();
    this.taxis = new TreeMap<String, Viatura>();
    this.userLogin = null;
}

public Utilizador getUtilizador (){
    return userLogin;
  }

  public void setUtilizador(Utilizador utilizador){
    this.userLogin= utilizador;
  }

 public int getTipoUtilizador(){
    if (this.userLogin instanceof Motorista)
      return 1;
    if (this.userLogin instanceof Cliente)
      return 2;
    return 0;
  }

////////////////////////////////////////METODOS////////////////////////////////////////////////////


  /** Método que adiciona o utilizador ao TreeMap de Utilizadores
*/
public void login(String email, String password) throws PassWordErradaException,SessaoIniciadaException{
    if(userLogin!=null) throw new SessaoIniciadaException("Precisa de Fechar a Sessão");
    if(!this.utilizadores.get(email).getPassword().equals(password)) throw new PassWordErradaException("Password errada");
    userLogin = this.utilizadores.get(email);
}

public void terminarSessao(){
    this.userLogin = null;
}
    

/** Método que adiciona o utilizador ao TreeMap de Utilizadores
*/
public void adicionaUti(Utilizador u) throws UtilizadorExistenteException{
    if(this.utilizadores.containsKey(u.getEmail())) throw new UtilizadorExistenteException("Utilizador já existe");
    this.utilizadores.put(u.getEmail(),u);
    
}

/** Método que adiciona uma Viatura ao Treemap de Taxis 
*/
public void criarViaturaNova(Viatura v) throws ViaturaExistenteException, SemAutorizacaoException{
    if(getTipoUtilizador()!=1)throw new SemAutorizacaoException("Sem autorização para efetuar operação");
    if(this.taxis.containsKey(v.getMatricula())) throw new ViaturaExistenteException("Viatura dada já existe");
    this.taxis.put(v.getMatricula(),v);
    
}

/**Método que associa um Motorista a uma dada viatura
*/
public void associaViatura(Motorista m, String matricula){

    this.taxis.get(matricula).setM(m);
 
}

/**Método que solicita uma Viagem. O Cliente pode solicitar a viagem dando a matricula do taxi a ser solicitado e a posição final desta, ou apenas inserir a posição e será-lhe atribuido o taxi mais proximo consoante a Posição do Cliente
*/

public Viagem soliciViagem(String matricula,Posicao fin) throws SemAutorizacaoException{  
Cliente c = (Cliente) this.userLogin; 
Viagem novo;
Viatura v = null;
Viatura taxi = null;
    if(getTipoUtilizador()==2){
    //ver se a matricula dada existe no map de taxis
    if(matricula!=null && this.taxis.containsKey(matricula)){
        //vai ao map de taxis buscar a viatura com a matricula correspondente
            v = this.taxis.get(matricula);
            //Criar a viagem
           novo = new Viagem(c, v , tempoDeChegada(v), c.getP().distancia(fin),c.getP().distancia(v.getP()), fin);
            
        }

    else{
        taxi = this.viaturaProx();
        novo = new Viagem(c, taxi , c.getP().distancia(fin), tempoDeChegada(taxi), c.getP().distancia(v.getP() ),fin);
            
    }
   
    this.utilizadores.get(c.getEmail()).getViagens().add(novo);
    
    Motorista m = (Motorista) this.taxis.get(matricula).getMotorista();
    m.getViagens().add(novo);
    m.setDisponivel(false);
    
return novo;
    }else throw new SemAutorizacaoException("Nao tem autorização para concluir operação");
}
    
/**Método auxiliar que calcula o tempo que o Motorista demora a chegar há posição do Cliente
*/
private double tempoDeChegada(Viatura v){
   Cliente c = (Cliente) this.userLogin;
   return c.getP().distancia(v.getP())/v.getVelocidade();
}

/**Método auxiliar que retorna o taxi mais proximo em relação ao Cliente
*/
private Viatura viaturaProx(){
    Cliente c = (Cliente) this.userLogin;
    TreeMap<Double,Viatura> v = new TreeMap<Double,Viatura>(new ComparadorDistancia());
    this.taxis.values().stream().filter(t->t.getMotorista().getDisponivel()).forEach(t->{
        v.put(c.getP().distancia(t.getP()),t);
    });

  return v.get(v.firstKey());

}

/**Método que 
*/
public void finalizaViagem(String matricula){

    if(getTipoUtilizador()==1){
    Motorista m = (Motorista) userLogin;
    int tamanho = this.utilizadores.get(m.getEmail()).getViagens().size()-1;
    Viagem v = this.utilizadores.get(m.getEmail()).getViagens().get(tamanho);
    Cliente c = this.utilizadores.get(m.getEmail()).getViagens().get(tamanho).getCliente();
    Posicao f = this.utilizadores.get(m.getEmail()).getViagens().get(tamanho).getP();
    c.setPosicao(f);
    v.getViatura().setP(f);
    v.getViatura().getMotorista().setDisponivel(true);
}
}


/**Método que retorna os 10 Clientes mais gastadores
*/
public List<Cliente> top10Gastadores(){
   TreeMap<Double,Cliente> v = new TreeMap<Double,Cliente>(new ComparadorCusto());
    this.utilizadores.values().stream().filter(f -> f instanceof Cliente).map(e->(Cliente) e).forEach(t->{v.put((custoCliente(t)),t);});
    return v.values().stream().limit(10).collect(Collectors.toList());

}



public double fatViatura(LocalDate d1, LocalDate d2,String matricula)throws ViaturaInexistenteException {
        

        if(this.taxis.containsKey(matricula)) {
    return this.taxis.get(matricula).getMotorista().getViagens().stream()
                                .filter(f-> d1.isBefore(f.getData()) && f.getData().isAfter(d2))
                                .mapToDouble(i->i.getCusto()).sum();

            }else throw new ViaturaInexistenteException("Viatura não existe");
}

public double custoCliente(Cliente c){
    return c.getViagens().stream().mapToDouble(v->v.getCusto()).sum();
}

public List<Viagem> viagensEntreDatas(LocalDate d1, LocalDate d2){
  
        ArrayList<Viagem> viagem = new ArrayList<Viagem>();

        this.utilizadores.get(userLogin.getEmail()).getViagens().stream().filter(f-> d1.isBefore(f.getData()) && f.getData().isAfter(d2))
                                                                          .forEach(t-> {viagem.add(t);});

        return viagem;
  }       

public void classifMotorista(String email, double classi)throws UtilizadorInexistenteException, SemAutorizacaoException{
    
    if(getTipoUtilizador()==1){
    if(this.utilizadores.containsKey(email)){
        Motorista m = (Motorista) this.utilizadores.get(email);
        m.calcClassf();
}else throw new UtilizadorInexistenteException("Utilizador enexistente");

}else throw new SemAutorizacaoException("Não tem autorização para efetuar operação");

}

public List<Motorista> top5Desviados(){
    TreeMap<Double,Motorista> desvio = new TreeMap<Double,Motorista>(new ComparadorDesvio());
 this.utilizadores.values().stream().filter(f -> f instanceof Motorista).map(e->(Motorista) e)
                  .forEach(t->{desvio.put(t.getViagens().stream().mapToDouble(f->f.calDesvio()).sum(),t);});
return desvio.values().stream().limit(5).collect(Collectors.toList());
}



public void gravar() throws IOException { 
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("umer.data")); 
        oos.writeObject(this);
        
        oos.flush();
        oos.close();
    }
    
  
public static Umer initApp() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("umer.data"));
      
        Umer imo = (Umer) ois.readObject();
        
        ois.close();
        return imo;
    }




 
    
}
