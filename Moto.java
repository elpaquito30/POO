import java.io.Serializable;
/**
 * Escreva a descrição da classe Moto aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Moto extends Viatura implements Serializable {
public Moto(double velocidade, double custo, Posicao p, Motorista m, String matricula, double factor){
        super(velocidade,custo,p,m,matricula,factor);
}

public Moto(){
    super();
    
}

public Moto(Moto u){
    super(u.getVelocidade(),u.getCusto(),u.getP(),u.getMotorista(),u.getMatricula(),u.getFactor());
}

public Moto clone(){
    return new Moto(this);
}



}
