import java.io.Serializable;
/**
 * Escreva a descrição da classe Carro aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

public class Carro extends Viatura implements Serializable {
    
public Carro(double velocidade, double custo, Posicao p, Motorista m, String matricula, double factor){
        super(velocidade,custo,p,m,matricula,factor);
}

public Carro(){
    super();
    
}

public Carro(Carro u){
    super(u.getVelocidade(),u.getCusto(),u.getP(),u.getMotorista(),u.getMatricula(),u.getFactor());
}

public Carro clone(){
    return new Carro(this);
}

}