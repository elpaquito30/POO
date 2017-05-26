
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
    private Motorista m;
    
        public Viatura(double velocidade, double custo, Posicao p, Motorista m){
            this.velocidade = velocidade;
            this.custo = custo;
            this.p = p.clone();
            this.m = m.clone();
        }
        
        public Viatura(){
            this(0,0, new Posicao(), new Motorista());
        }
        
        public Viatura(Viatura u){
            this.velocidade = u.getVelocidade();
            this.custo = u.getCusto();
            this.p = u.getP();
            this.m = u.getMotorista();
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
        
        public Motorista getMotorista(){
            return m;
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
        
        public void setM(Motorista m){
            this.m = m;
        }
        
        public boolean equals(Object o){
            if(this == o)
                return true;
            if ((o==null) || (this.getClass() != o.getClass()))
                return false;
            Viatura m = (Viatura) o;
                return (this.velocidade==(m.getVelocidade()) && 
               this.custo==(m.getCusto()) && 
               this.p.equals(m.getP()) &&
               this.m.equals(m.getMotorista()));
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Velocidade: ").append(velocidade).append("\n");
            sb.append("Custo: ").append(custo).append("\n");
            sb.append("Posicao: ").append(p).append("\n");
            sb.append("Motorista: ").append(m).append("\n");
            return sb.toString();
        }
        
}
