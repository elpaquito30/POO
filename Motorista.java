import java.util.ArrayList;
import java.util.List;
/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Motorista extends Utilizador
{
    private double factor;
    private double classf;
    private List<Viagem> viagens;
    private double kms;
    private boolean disponivel;
    
    public Motorista(String email, String nome, String password, String morada, String dataDeNascimento, double factor, double classf, ArrayList<Viagem> viagens, double kms, boolean disponivel){
        super(email,nome,password,morada,dataDeNascimento);
        this.factor = factor;
        this.classf = classf;
        for (Viagem v : viagens) this.viagens.add(v.clone());
        this.kms = kms;
        this.disponivel = disponivel; 
    }
    
    public Motorista(){
        super();
        factor = 0;
        
    }
    
    public Motorista(Motorista u){
        this.factor = u.getFactor();
        this.classf = u.getClassf();
        this.viagens = u.getViagens();
        this.kms = u.getKms();
        this.disponivel = u.getDisponivel();
    }
    
    public double getFactor(){
        return factor;
    }
    
    public double getClassf(){
        return classf;
    }
    
    public List<Viagem> getViagens(){
        return viagens;
    }
    
    public double getKms(){
        return kms;
    }
    
    public boolean getDisponivel(){
        return disponivel;
    }
    
    public void setFactor(double factor){
        this.factor = factor;
    }
    
    public void setClassf(double classf){
        this.classf = classf;
    }
    
    public void setViagens(List<Viagem> viagens){
        this.viagens = viagens;
    }
    
    public void setKms(double kms){
        this.kms = kms;
    }
    
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }
    
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Motorista m = (Motorista) o;
       return (this.factor==(m.getFactor()) && 
               this.classf==(m.getClassf()) && 
               this.viagens==(m.getViagens()) &&
               this.kms==(m.getKms()) &&
               this.disponivel==(m.getDisponivel()));
    }
    
}