public class Viagem
{
    private Posicao pcliente;//cliente
    private Posicao ptaxi;// taxi
    private double distancia;
    private double tempoDeChegada;
    private double duracao;
    private double distanciaDoCliente; 
    private double custo;

public Viagem(Posicao pcliente, Posicao ptaxi, double distancia, double tempoDeChegada, double duracao, double distanciaDoCliente, double custo){
    this.pcliente= pcliente.clone();
    this.ptaxi = ptaxi.clone();
    this.distancia = distancia;
    this.tempoDeChegada = tempoDeChegada;
    this.duracao = duracao;
    this.distanciaDoCliente = distanciaDoCliente;
    this.custo = custo;
    
    
}

public Viagem(){
    this(new Posicao(),new Posicao(),0,0,0,0,0);
}

public Viagem(Viagem u){
    
    this.pcliente=u.getPcliente();
    this.ptaxi=u.getPtaxi();
    this.distancia=u.getDistancia();
    this.tempoDeChegada=u.getTempoDeChegada();
    this.duracao=u.getDuracao();
    this.distanciaDoCliente=u.getDistanciaDoCliente();
    this.custo=u.getCusto();
    
}

public Posicao getPcliente(){
    return pcliente;
}

public Posicao getPtaxi(){
    return ptaxi;
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

public void setPcliente(Posicao p){
    this.pcliente = p;
}

public void setPtaxi(Posicao p){
    this.ptaxi = p;
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
     return (this.pcliente.equals(m.getPcliente()) && this.ptaxi.equals(m.getPtaxi()) && this.distancia==(m.getDistancia()) && 
             this.tempoDeChegada==(m.getTempoDeChegada())  && this.duracao==(m.getDuracao()) 
             && this.distanciaDoCliente==(m.getDistanciaDoCliente()) && this.custo==(m.getCusto()));
        }

public Viagem clone(){
    return new Viagem(this);
}
}