
/**
 * Escreva a descrição da classe Carrinha aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Carrinha extends Viatura {
    
public Carrinha(double velocidade, double custo, Posicao p){
        super(velocidade,custo,p);
}

public Carrinha(){
    super();
    
}

public Carrinha(Carrinha u){
    super(u.getVelocidade(),u.getCusto(),u.getP());
}

public Carrinha clone(){
    return new Carrinha(this);
}
 }

