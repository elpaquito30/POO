
/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utilizador
{
    private Posicao p;
    private List<Viagem> viagens;
    
  public Cliente(){
    super();
    this.viagens = new ArrayList<Viagem>();
    this.p = new Posicao();
  }
  
  public Cliente(ArrayList<Viagem> viagens, Posicao p, String email, String nome, String password, String morada, String dataDeNascimento){
    super(email, nome, password, morada, dataDeNascimento);
    this.viagens = new ArrayList<Viagem>();
    for (Viagem v : viagens) this.viagens.add(v.clone());
    this.p = p.clone();
  }
  
  public Cliente (Cliente c){
       super(c.getEmail(), c.getNome(), c.getPassword(), c.getMorada(), c.getDataDeNascimento());
       this.p = c.getPosicao();
       c.getViagens().forEach( v ->{this.viagens.add(v.clone());}); 
  }
  
  public Posicao getPosicao(){
      return p.clone();
  }
    
  public void setPosicao(Posicao p){
      this.p = p.clone();
  }
    
 public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Cliente c = (Cliente) o;
        return super.equals(c);
 }
 
 public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Posição: \n");
    s.append(p.toString() + "\n");
    s.append ("Cliente: \n");   
    s.append(super.toString());
    return s.toString();
 }
 
  public Cliente clone(){
      return new Cliente(this);
    }
}