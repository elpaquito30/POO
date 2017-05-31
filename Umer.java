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

    
    /** 
     * Verifica se o utilizador que está actualmente no sistema é um Cliente.
     */
public boolean temCli(){
        if(utilizador == null) return false;
        if(utilizador instanceof Cliente) return true;
        else return false;
    }
    
    /** 
     * Verifica se o utilizador que está actualmente no sistema é um Motorista.
     */
public boolean temMot(){
        if(utilizador == null) return false;
        if(utilizador instanceof Motorista) return true;
        else return false;
    }
    


public void adicionaUti(Utilizador u) throws UtilizadorExistenteException{
    if(this.utilizadores.containsKey(u.getEmail())) throw new UtilizadorExistenteException("Utilizador já existe");
    this.utilizadores.put(u.getEmail(),u);
    
}

public void login(String email, String password) throws PassWordErradaException,SessaoIniadaException{
    if(userLogin!=null) throw new SessaoIniadaException("Precisa de Fechar a Sessão");
    if(!this.utilizadores.get(email).getPassword().equals(password)) throw new PassWordErradaException("Password errada");
    userLogin = this.utilizadores.get(email);
}

public void fechaSessao(){
    this.userLogin = null;
}
    

public void insereViatura(Viatura v) throws ViaturaExistenteException, SemAutorizacaoException{
    if(temMot()==false)throw new SemAutorizacaoException("Sem autorização para efetuar operação");
    if(this.taxis.containsKey(v.getMatricula())) throw new ViaturaExistenteException("Viatura dada já existe");
    this.taxis.put(v.getMatricula(),v);
    
}

public Viagem soliciViagem(String matricula){   
    if(this.taxis.containsKey(matricula)){
        if(this.taxis.get(matricula).getMotorista().getDisponivel()){
            Viagem v = new Viagem()
        }
    }


    }
    


public Viatura viaturaProx(){
    Cliente c = (Cliente) this.userLogin;
    TreeMap<Double,Viatura> v = new TreeMap<Double,Viatura>(new ComparadorDistancia());
    this.taxis.entrySet().stream().filter(t->t.getValue().getMotorista().getDisponivel()).forEach(t->{
        v.put(c.getP().distancia(t.getP()),t);
    });


  return v.get(v.firstKey());

}


//public double classifMotorista(Utilizador u, double classi){
}


    
    

