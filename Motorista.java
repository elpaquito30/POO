import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Motorista extends Utilizador
{
    private double factor;
    private List<Avaliacao> classf;
    private List<Viagem> viagens;
    private double kms;
    private boolean disponivel;
    
    public Motorista(String email, String nome, String password, String morada, String dataDeNascimento, double factor, List<Avaliacao> classf, ArrayList<Viagem> viagens, double kms, boolean disponivel){
        super(email,nome,password,morada,dataDeNascimento);
        this.factor = factor;
        this.setClassf(classf);
        this.setViagens(viagens);
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
    
    public ArrayList<Avaliacao> getClassf(){
       return this.classf.stream()
                          .map(Avaliacao::clone)
                          .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Viagem> getViagens(){
        return this.viagens.stream()
                          .map(Viagem::clone)
                          .collect(Collectors.toCollection(ArrayList::new));
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
    
    public void setClassf( List<Avaliacao> classf){
        this.classf = classf.stream()
                      .map(Avaliacao::clone)
                      .collect(Collectors.toList());
    }
    
    public void setViagens(List<Viagem> viagens){
       this.viagens = viagens.stream()
                      .map(Viagem::clone)
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
               this.viagens==(m.getViagens()) &&
               this.kms==(m.getKms()) &&
               this.disponivel==(m.getDisponivel()));
    }
    
    public Motorista clone(){
        return new Motorista(this);
    }
    
}