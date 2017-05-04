
/**
 * Escreva a descrição da classe Moto aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Moto extends Viatura {
public Moto(double velocidade, double custo, Posicao p){
        super(velocidade,custo,p);
}

public Moto(){
    super();
    
}

public Moto(Moto u){
    super(u.getVelocidade(),u.getCusto(),u.getP());
}

public Moto clone(){
    return new Moto(this);
}



}
