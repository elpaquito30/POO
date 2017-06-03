import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.time.LocalDate;
import java.io.Serializable;
/**
 * Classe abstrata Utilizador - escreva a descrição da classe aqui
 * 
 * @author (seu nome aqui)
 * @version (versão ou data)
 */
public abstract class Utilizador implements Serializable
{
  private String email, nome, password, morada, dataDeNascimento;
  private List<Viagem> viagens;
  
   public Utilizador(String email, String nome, String password, String morada, String dataDeNascimento){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataDeNascimento = dataDeNascimento;
        this.viagens = new ArrayList<Viagem>();
    }
    
   public Utilizador(){
       this("","","","","");
     
       
   }
   
   public Utilizador(Utilizador u){
       this.email = u.getEmail();
       this.nome = u.getNome();
       this.password = u.getPassword();
       this.morada = u.getMorada();
       this.dataDeNascimento = u.getDataDeNascimento();
       this.viagens = u.getViagens();
   }
   
   public String getEmail(){
      return email;
    }
    
   public String getNome(){
      return this.nome;
    }
    
   public String getPassword(){
      return password;
    }
    
   public String getMorada(){
      return morada;
    }
    
   public String getDataDeNascimento(){
      return dataDeNascimento;
    }
    
   public List<Viagem> getViagens(){
       return this.viagens.stream().map(e->e.clone()).collect(Collectors.toList());
}

                                                         

   public void setEmail(String email){
      this.email = email;
    }
    
   public void setNome(String nome){
      this.nome = nome;
    }
    
   public void setPassword(String password){
      this.password = password;
    }
    
   public void setMorada(String morada){
      this.morada = morada;
    }
    
   public void setDataDeNascimento(String dataDeNascimento){
      this.dataDeNascimento = dataDeNascimento;
    }
    
   public void setViagens(List<Viagem> viagens){
       this.viagens = viagens.stream()
                      .map(Viagem::clone)
                      .collect(Collectors.toList());
    }
    
   public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Utilizador m = (Utilizador) o;
       return (this.email.equals(m.getEmail()) && 
               this.nome.equals(m.getNome()) && 
               this.password.equals(m.getPassword())&& 
               this.morada.equals(m.getMorada()) && 
               this.viagens.equals(m.getViagens()) &&
               this.dataDeNascimento.equals(m.getDataDeNascimento()));
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(email).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Password: ").append(password).append("\n");
        sb.append("Morada: ").append(morada).append("\n");
        sb.append("Data de Nascimento: ").append(dataDeNascimento).append("\n");
        return sb.toString();
    }
   
}
