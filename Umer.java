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
public void login(String email, String password) throws UtilizadorInexistenteException,PassWordErradaException,SessaoIniciadaException{
    if(userLogin!=null) throw new SessaoIniciadaException("Precisa de Fechar a Sessão");
    if(this.utilizadores.get(email)==null) throw new UtilizadorInexistenteException("Utilizador Inexistente");
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
public void criarViaturaNova(Viatura v) throws ViaturaExistenteException{
    if(this.taxis.containsKey(v.getMatricula())) throw new ViaturaExistenteException("Viatura dada já existe");
    this.taxis.put(v.getMatricula(),v);
    
}

/**Método que associa um Motorista a uma dada viatura
*/
public void associaViatura(Motorista m, String matricula) throws ViaturaInexistenteException{

    if(this.taxis.containsKey(matricula)){
    this.taxis.get(matricula).setM(m);
}else throw new ViaturaInexistenteException("A viatura não existe");
 
}

/**Método que solicita uma Viagem. O Cliente pode solicitar a viagem dando a matricula do taxi a ser solicitado e a posição final desta, ou apenas inserir a posição e será-lhe atribuido o taxi mais proximo consoante a Posição do Cliente
*/

public Viagem soliciViagem(String matricula,Posicao fin){
Cliente c = (Cliente) this.userLogin; 
Viagem novo;
Viatura taxi = null;

    //ver se a matricula dada existe no map de taxis
boolean aux = this.taxis.containsKey(matricula);
    if(matricula != "1" && aux){
        //vai ao map de taxis buscar a viatura com a matricula correspondente
            taxi = this.taxis.get(matricula);
            double r = tempoDeChegada(taxi);
            double d = c.getP().distancia(fin);
            //Criar a viagem
           novo = new Viagem(c, taxi , r, d,c.getP().distancia(taxi.getP()), fin);
            
        }

    else{

        taxi = viaturaProx();
        double r = tempoDeChegada(taxi);
        Posicao p = c.getP();
         double d = c.getP().distancia(fin);
       

        novo = new Viagem(c, taxi ,d, r, c.getP().distancia(taxi.getP()),fin);   
        System.out.println(taxi.getMatricula());

    }
   
    this.utilizadores.get(c.getEmail()).getViagens().add(novo);

    
    Motorista m = taxi.getMotorista();
    m.getViagens().add(novo);
    m.setDisponivel(false);
    
return novo;
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

/**Método que termina a Viagem, atualizando as posições tando do taxi como do Cliente
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
    System.out.println("Viagem Terminada");
}
}



/**Método que retorna os 10 Clientes mais gastadores
*/
public List<Cliente> top10Gastadores(){
   TreeMap<Double,Cliente> v = new TreeMap<Double,Cliente>(new ComparadorCusto());
    this.utilizadores.values().stream().filter(f -> f instanceof Cliente).map(e->(Cliente) e).forEach(t->{v.put((custoCliente(t)),t);});
    return v.values().stream().limit(10).collect(Collectors.toList());

}

/**Método auxiliar que calcula quanto um dado Cliente gastou
*/
public double custoCliente(Cliente c){
    return c.getViagens().stream().mapToDouble(v->v.getCusto()).sum();
}



/**Método que retorna o total faturado por uma dada viatura, entre datas
*/
public double fatViatura(LocalDate d1, LocalDate d2,String matricula)throws ViaturaInexistenteException {
        

        if(this.taxis.containsKey(matricula)) {
    return this.taxis.get(matricula).getMotorista().getViagens().stream()
                                .filter(f-> d1.isBefore(f.getData()) && f.getData().isAfter(d2))
                                .mapToDouble(i->i.getCusto()).sum();

            }else throw new ViaturaInexistenteException("Viatura não existe");
}




public List<Viagem> viagensEntreDatas(LocalDate d1, LocalDate d2){
  
        ArrayList<Viagem> viagem = new ArrayList<Viagem>();

        this.utilizadores.get(userLogin.getEmail()).getViagens().stream().filter(f-> d1.isBefore(f.getData()) && d2.isAfter( f.getData()))
                                                                          .forEach(t-> {viagem.add(t);});

        return viagem;
  }       

public void classifMotorista(String email, double classi)throws UtilizadorInexistenteException{
    
    if(this.utilizadores.containsKey(email)){
        Motorista m = (Motorista) this.utilizadores.get(email);
        m.calcClassf();
}else throw new UtilizadorInexistenteException("Utilizador enexistente");

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
      
        Umer ume = (Umer) ois.readObject();
        
        ois.close();
        return ume;
    }




 
    
}
