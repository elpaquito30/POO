import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Motorista extends Utilizador
{
    private Random factor;
    private List<Avaliacao> classf;
    private double kms;
    private boolean disponivel;
    
    public Motorista(String email, String nome, String password, String morada, String dataDeNascimento, Random factor,double kms, boolean disponivel){
        super(email,nome,password,morada,dataDeNascimento);
        this.factor = new Random();
        this.setClassf(classf);
        this.kms = kms;
        this.disponivel = disponivel; 
    }
    
    public Motorista(){
        super();
        this.kms = 0.0;
        this.disponivel = false;
        factor = new Random();
        
        
    }
    
    public Motorista(Motorista u){
        this.factor = u.getFactor();
        this.classf = u.getClassf();
        this.kms = u.getKms();
        this.disponivel = u.getDisponivel();
    }
    
    public Random getFactor(){
        return factor;
    }
    
    public float getFactorFloat(){
        return factor.nextFloat();
    }
    
    public ArrayList<Avaliacao> getClassf(){
       return this.classf.stream()
      .map(Avaliacao::clone)
                          .collect(Collectors.toCollection(ArrayList::new));
    }
    

    public double getKms(){
        return kms;
    }
    
    public boolean getDisponivel(){
        return disponivel;
    }
    
    public void setFactor(Random factor){
        this.factor = factor;
    }
    
    public void setClassf(List<Avaliacao> classf){
        this.classf = classf.stream()
                      .map(Avaliacao::clone)
                      .collect(Collectors.toList());
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
               this.kms==(m.getKms()) &&
               this.disponivel==(m.getDisponivel()));
    }
    
    public Motorista clone(){
        return new Motorista(this);
    }
    
}