import java.util.TreeMap;

public class Umer{
   
private TreeMap<String,Utilizador> utilizadores;
private TreeMap<String,Viatura> taxis;
private Utilizador userLogin;

public Umer(){
    this.utilizadores = new TreeMap<String,Utilizador>();
    this.taxis = new TreeMap<String, Viatura>();
    this.userLogin = null;
}

public void adicionaUti(Utilizador u) throws UtilizadorExistenteException{
    if(this.utilizadores.containsKey(u.getEmail())) throw new UtilizadorExistenteException("Utilizador já existe");
    this.utilizadores.put(u.getEmail(),u);
    
}

public void login(String email, String password) throws PassWordErradaException{
    if(!this.utilizadores.get(email).getPassword().equals(password)) throw new PassWordErradaException("Password errada");
    userLogin = this.utilizadores.get(email);
}
    

public void insereViatura(Viatura v) throws ViaturaExistenteException{
    if(this.taxis.containsKey(v.getMatricula())) throw new ViaturaExistenteException("Viatura dada já existe");
    this.taxis.put(v.getMatricula(),v);
    
}

//public Viagem soliciViagem(String matricula){   
    
    


public Viatura viaturaProx(){
    Cliente c = (Cliente) this.userLogin;
    TreeMap<Double,Viatura> v = new TreeMap<Double,Viatura>();
    this.taxis.values().stream().filter(t->t.getMotorista().getDisponivel()).forEach(t->{
        v.put(c.getP().distancia(t.getP()),t);
    });


  return v.get(v.firstKey());

}


//public double classifMotorista(Utilizador u, double classi){
}


    
    

