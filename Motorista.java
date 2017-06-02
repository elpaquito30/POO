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
    private double fiabilidade;
    private double classificacao;
    private List<Avaliacao> classf;
    private double kms;
    private boolean disponivel ;

    public Motorista(String email, String nome, String password, String morada, String dataDeNascimento, double fiabilidade ,double kms, boolean disponivel){
        super(email,nome,password,morada,dataDeNascimento);
        this.fiabilidade = fiabilidade;
        this.setClassf(classf);
        this.kms = kms;
        this.disponivel = disponivel; 
    }
    
    public Motorista(){
        super();
        this.kms = 0.0;
        this.disponivel = true;
        this.fiabilidade = 0.0;
        
        
    }

      public List<Avaliacao> getClassf(){
       return this.classf.stream().map(c-> c.clone()).collect(Collectors.toList());
    }
    
    
    public Motorista(Motorista u){
        this.fiabilidade = u.getFiabilidade();
        this.classf = u.getClassf();
        this.kms = u.getKms();
        this.disponivel = u.getDisponivel();
    }
    
    public double getFiabilidade(){
        return fiabilidade;
    }
        
  
    public double getKms(){
        return kms;
    }
    
    public boolean getDisponivel(){
        return disponivel;
    }
    
    public void setFiabilidade(double fiabilidade){
        this.fiabilidade = fiabilidade;
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
    
    public void calcClassf(){

        double c = 0;
        this.classf.stream().mapToDouble(f->f.getAvaliacao()).sum();
        c=c/this.classf.size();
        this.classificacao = c;

    }
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Motorista m = (Motorista) o;
       return (this.fiabilidade==(m.getFiabilidade()) && 
               this.classf==(m.getClassf()) && 
               this.kms==(m.getKms()) &&
               this.disponivel==(m.getDisponivel()));
    }
    
    public Motorista clone(){
        return new Motorista(this);
    }
    
}