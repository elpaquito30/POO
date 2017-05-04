
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
  
    
  public Cliente (){
    this.viagens = new ArrayList<Viagem>();
    this.p = new Posicao();
  }
  
  public Cliente (ArrayList<Viagem> viagens, Posicao p){
    this.viagens = new ArrayList<Viagem>();
    for (Viagem v : viagens) this.viagens.add(v.clone());
    this.p = p.clone();
    
  }
  
  

}
