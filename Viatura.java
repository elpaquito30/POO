import  java.util.Random;
import java.io.Serializable;

/**
 * Classe abstrata Viatura - escreva a descrição da classe aqui
 * 
 * @author (seu nome aqui)
 * @version (versão ou data)
 */
public abstract class Viatura implements Serializable
{
    private double velocidade;
    private double custo;
    private Posicao p;
    private Motorista m;
    private String matricula;
    private double factor;
    
        public Viatura(double velocidade, double custo, Posicao p, Motorista m, String matricula, double factor){
            this.velocidade = velocidade;
            this.custo = custo;
            this.p = p;
            this.m = m;
            this.matricula = matricula;
            this.factor = factor;
        }
        
        public Viatura(){
            this(0,0, new Posicao(), new Motorista(),"", 0);
        }
        
        public Viatura(Viatura u){
            this.velocidade = u.getVelocidade();
            this.custo = u.getCusto();
            this.p = u.getP();
            this.m = u.getMotorista();
            this.matricula = u.getMatricula();
            this.factor = u.getFactor();
            
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
        
        public String getMatricula(){
            return matricula;
        }
        
        public double getFactor(){
            return this.getFactorRandomInRange();
        }
        
        public double getFactorRandomInRange(){
            Random r = new Random();
            setFactor(r.nextDouble());
            return (double) this.factor;
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
        
        public void setMatricula(String matricula){
            this.matricula = matricula;
        }
        

        public abstract Viatura clone();
        

        public void setFactor(double factor){
            this.factor = factor;
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
               this.m.equals(m.getMotorista()) &&
               this.matricula.equals(m.getMatricula()) &&
               this.factor==(m.getFactor()));
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Velocidade: ").append(velocidade).append("\n");
            sb.append("Custo: ").append(custo).append("\n");
            sb.append("Posicao: ").append(p).append("\n");
            sb.append("Motorista: ").append(m).append("\n");
            sb.append("Matricula: ").append(matricula).append("\n");
            sb.append("Factor: ").append(factor).append("\n");
            return sb.toString();
        }
        
}
