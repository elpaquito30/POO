import java.util.TreeMap;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Umer{
   
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
    


public void adicionaUti(Utilizador u) throws UtilizadorExistenteException{
    if(this.utilizadores.containsKey(u.getEmail())) throw new UtilizadorExistenteException("Utilizador já existe");
    this.utilizadores.put(u.getEmail(),u);
    
}

public void login(String email, String password) throws PassWordErradaException,SessaoIniciadaException{
    if(userLogin!=null) throw new SessaoIniciadaException("Precisa de Fechar a Sessão");
    if(!this.utilizadores.get(email).getPassword().equals(password)) throw new PassWordErradaException("Password errada");
    userLogin = this.utilizadores.get(email);
}

public void fechaSessao(){
    this.userLogin = null;
}
    

public void insereViatura(Viatura v) throws ViaturaExistenteException, SemAutorizacaoException{
    if(getTipoUtilizador()!=1)throw new SemAutorizacaoException("Sem autorização para efetuar operação");
    if(this.taxis.containsKey(v.getMatricula())) throw new ViaturaExistenteException("Viatura dada já existe");
    this.taxis.put(v.getMatricula(),v);
    
}

public void associaViatura(Motorista m, String matricula){

    this.taxis.get(matricula).setM(m);

}

public Viagem soliciViagem(String matricula,Posicao fin){  
Cliente c = (Cliente) this.userLogin; 
Viagem novo;
Viatura v = null;
Viatura taxi = null;
    //ver se a matricula dada existe no map de taxis
    if(this.taxis.containsKey(matricula)){
        //vai ao map de taxis buscar a viatura com a matricula correspondente
            v = this.taxis.get(matricula);
            //Criar a viagem
           novo = new Viagem(c, v , tempoDeChegada(v), c.getP().distancia(fin),c.getP().distancia(v.getP()), fin);
            
        }

    else{
        taxi = this.viaturaProx();
        novo = new Viagem(c, taxi , c.getP().distancia(fin), tempoDeChegada(taxi), c.getP().distancia(v.getP()),fin);
            
    }
   
    this.utilizadores.get(c.getEmail()).getViagens().add(novo);
    
    Motorista m = (Motorista) this.taxis.get(matricula).getMotorista();
    m.getViagens().add(novo);
    m.setDisponivel(false);
    
return novo;
    }
    

private double tempoDeChegada(Viatura v){
   Cliente c = (Cliente) this.userLogin;
   return c.getP().distancia(v.getP())/v.getVelocidade();
}

private Viatura viaturaProx(){
    Cliente c = (Cliente) this.userLogin;
    TreeMap<Double,Viatura> v = new TreeMap<Double,Viatura>(new ComparadorDistancia());
    this.taxis.values().stream().filter(t->t.getMotorista().getDisponivel()).forEach(t->{
        v.put(c.getP().distancia(t.getP()),t);
    });

  return v.get(v.firstKey());

}
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

public void classifMotorista(String email, double classi)throws UtilizadorEnexistenteException{
    
    if(this.utilizadores.containsKey(email)){
        Motorista m = (Motorista) this.utilizadores.get(email);
        m.calcClassf();
}else throw new UtilizadorEnexistenteException("Utilizador enexistente");
 


}



 
    
}
