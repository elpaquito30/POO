public class Viagem
{
    private Cliente cli;
    private Motorista mot;
    private double distancia;
    private double tempoDeChegada;
    private double duracao;
    private double distanciaDoCliente; 
    private double custo;

public Viagem(Cliente cli, Motorista mot, double distancia, double tempoDeChegada, double duracao, double distanciaDoCliente, double custo){
    this.cli= cli.clone();
    this.mot = mot.clone();
    this.istancia = distancia;
    this.tempoDeChegada = tempoDeChegada;
    this.duracao = duracao;
    this.distanciaDoCliente = distanciaDoCliente;
    this.custo = custo;
    
    
}

public Viagem(){
    this(new Cliente(),new Motorista(),0,0,0,0,0);
}

public Viagem(Viagem u){
    
    this.cli=u.getCliente();
    this.mot=u.getMotorista();
    this.distancia=u.getDistancia();
    this.tempoDeChegada=u.getTempoDeChegada();
    this.duracao=u.getDuracao();
    this.distanciaDoCliente=u.getDistanciaDoCliente();
    this.custo=u.getCusto();
    
}

public Cliente getCliente(){
    return cli;
}

public Motorista getMotorista(){
    return mot;
}

public double getDistancia(){
    return distancia; 
}

public double getTempoDeChegada() {
    return tempoDeChegada;
}

public double getDuracao(){
    return duracao;
}

public double getDistanciaDoCliente(){
    return distanciaDoCliente;
}

public double getCusto(){
    return custo;
}

public void setCli(Cliente cli){
    this.cli = cli;
}

public void setMot(Motorista mot){
    this.mot = mot;
}

public void setDistancia(double distancia){
    this.distancia = distancia;
}

public void setTempoDeChegada(double tempoDeChegada){
    this.tempoDeChegada = tempoDeChegada;
}
    
public void setDuracao(double duracao){
    this.duracao=duracao;
}

public void setDistanciaDoCliente(double distanciaDoCliente){
    this.distanciaDoCliente = distanciaDoCliente;
}

public void setCusto(double custo){
    this.custo = custo;
}

 public boolean equals(Object o){
     if(this == o)
        return true;
     if ((o==null) || (this.getClass() != o.getClass()))
        return false;
     Viagem m = (Viagem) o;
     return (this.cli.equals(m.getCli()) && this.mot.equals(m.getMot()) && this.distancia==(m.getDistancia()) && 
             this.tempoDeChegada==(m.getTempoDeChegada())  && this.duracao==(m.getDuracao()) 
             && this.distanciaDoCliente==(m.getDistanciaDoCliente()) && this.custo==(m.getCusto()));
        }

public Viagem clone(){
    return new Viagem(this);
}
}