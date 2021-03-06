import java.time.LocalDate;
import java.io.Serializable;

public class Viagem implements Serializable 
{
    private Cliente cli;
    private Viatura v;
    private double distancia;
    private double tempoDeChegada;
    private double duracao;
    private double distanciaDoCliente; 
    private double custo;
    private LocalDate data;
    private Posicao fim;

public Viagem(Cliente cli, Viatura v, double tempoDeChegada,double distancia, double distanciaDoCliente,Posicao fim){
    this.cli= cli.clone();
    this.v = v.clone();
    this.distancia = distancia;
    this.tempoDeChegada = tempoDeChegada;
    this.duracao = duracao;
    this.distanciaDoCliente = distanciaDoCliente;
    this.custo = this.calculaPreco();
    this.data =LocalDate.now();
    this.fim = fim;
}

public Viagem(){
    this(new Cliente(),null,0,0,0,new Posicao());
}

public Viagem(Viagem u){
    
    this.cli=u.getCliente();
    this.v=u.getViatura();
    this.distancia=u.getDistancia();
    this.tempoDeChegada=u.getTempoDeChegada();
    this.duracao=u.getDuracao();
    this.distanciaDoCliente=u.getDistanciaDoCliente();
    this.custo=u.getCusto();
    this.fim=u.getP();
    
}

public Posicao getP(){
    return fim;
}

public Cliente getCliente(){
    return cli;
}

public Viatura getViatura(){
    return v;
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

public LocalDate getData(){

    return data;
}

public void setP(Posicao fim){
    this.fim = fim;
}

public void setCli(Cliente cli){
    this.cli = cli;
}

public void setViatura(Viatura v){
    this.v = v;
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

public double calculaPreco(){
    double tempoEstimado = this.distancia/this.v.getVelocidade();
    double tempoReal = tempoEstimado * (this.v.getFactor());
    double precoCombinado = (this.distancia + tempoEstimado) * this.v.getCusto();
    double precoReal = (this.distancia + tempoReal) * this.v.getCusto();
    if(tempoEstimado <= tempoReal * 1.25){
            return precoReal;
    }
    else return precoCombinado;
    
}

public double calDesvio(){
   double tempoEstimado = this.distancia/this.v.getVelocidade();
   double precoCombinado = (this.distancia + tempoEstimado) * this.v.getCusto();
    return (precoCombinado - this.custo);

}


 public boolean equals(Object o){
     if(this == o)
        return true;
     if ((o==null) || (this.getClass() != o.getClass()))
        return false;
     Viagem m = (Viagem) o;
     return (this.fim.equals(m.getP()) && this.cli.equals(m.getCliente()) && this.v.equals(m.getViatura()) && this.distancia==(m.getDistancia()) && 
             this.tempoDeChegada==(m.getTempoDeChegada())  && this.duracao==(m.getDuracao()) 
             && this.distanciaDoCliente==(m.getDistanciaDoCliente()) && this.custo==(m.getCusto()));
        }

public Viagem clone(){
    return new Viagem(this);
}

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Cliente: ").append(cli).append("\n");
            sb.append("Motorista: ").append(v).append("\n");
            sb.append("Distância: ").append(distancia).append("\n");
            sb.append("Distância entre o taxista e cliente: ").append(distanciaDoCliente).append("\n");
            sb.append("Tempo de chegada do taxista ao cliente: ").append(tempoDeChegada).append("\n");
            sb.append("Duração da viagem: ").append(duracao).append("\n");
            sb.append("Preço da viagem: ").append(custo).append("\n");
            sb.append("Final da viagem em: ").append(data).append("\n");
            sb.append("Posição final: ").append(fim).append("\n");
            return sb.toString();
        }
}