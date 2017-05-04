
/**
 * Escreva a descrição da classe Carro aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Carro extends Viatura{
    
public Carro(double velocidade, double custo, Posicao p){
        super(velocidade,custo,p);
}

public Carro(){
    super();
    
}

public Carro(Carro u){
    super(u.getVelocidade(),u.getCusto(),u.getP());
}

public Carro clone(){
    return new Carro(this);
}

}
