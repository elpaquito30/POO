
/**
 * Classe abstrata Viatura - escreva a descrição da classe aqui
 * 
 * @author (seu nome aqui)
 * @version (versão ou data)
 */
public abstract class Viatura
{
    private double velocidade;
    private double custo;
    private Posicao p;
    
        public Viatura(double velocidade, double custo, Posicao p){
            this.velocidade = velocidade;
            this.custo = custo;
            this.p = p.clone();
        }
        
        public Viatura(){
            this(0,0, new Posicao());
        }
        
        public Viatura(Viatura u){
            this.velocidade = u.getVelocidade();
            this.custo = u.getCusto();
            this.p = u.getP();
        }
        
        public double getVelocidade(){
            return velocidade;
        }
        
        public double getCusto(){
            return custo;
        }
        
        public Posicao getP(){
            return p;
        }
        
        public void setVelocidade(double velocidade){
            this.velocidade = velocidade;
        }
        
        public void setCusto(double custo){
            this.custo = custo;
        }
        
        public void setP(Posicao p){
            this.p = p;
        }
        
        public boolean equals(Object o){
            if(this == o)
                return true;
            if ((o==null) || (this.getClass() != o.getClass()))
                return false;
            Viatura m = (Viatura) o;
                return (this.velocidade==(m.getVelocidade()) && 
               this.custo==(m.getCusto()) && 
               this.p.equals(m.getP()));
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Velocidade: ").append(velocidade).append("\n");
            sb.append("Custo: ").append(custo).append("\n");
            sb.append("Posicao: ").append(p).append("\n");
            return sb.toString();
        }
        
}
