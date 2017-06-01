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
        if(this.userLogin== null) return false;
        if(this.userLogin instanceof Cliente) return true;
        else return false;
    }
    
    /** 
     * Verifica se o utilizador que está actualmente no sistema é um Motorista.
     */
public boolean temMot(){
        if(this.userLogin== null) return false;
        if(this.userLogin instanceof Motorista) return true;
        else return false;
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
    if(temMot()==false)throw new SemAutorizacaoException("Sem autorização para efetuar operação");
    if(this.taxis.containsKey(v.getMatricula())) throw new ViaturaExistenteException("Viatura dada já existe");
    this.taxis.put(v.getMatricula(),v);
    
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
           novo = new Viagem(c, v , c.getP().distancia(fin), tempoDeChegada(v),c.getP().distancia(v.getP()));
            
        }

    else{
        taxi = this.viaturaProx();
        novo = new Viagem(c, v , c.getP().distancia(fin), tempoDeChegada(taxi), c.getP().distancia(v.getP()));
            
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


//public double classifMotorista(Utilizador u, double classi){
}


    
    

