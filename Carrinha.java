import java.io.Serializable;
/**
 * Escreva a descrição da classe Carrinha aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Carrinha extends Viatura implements Serializable{
    
public Carrinha(double velocidade, double custo, Posicao p, Motorista m, String matricula, double factor){
        super(velocidade,custo,p,m,matricula,factor);
}

public Carrinha(){
    super();
    
}

public Carrinha(Carrinha u){
    super(u.getVelocidade(),u.getCusto(),u.getP(),u.getMotorista(),u.getMatricula(),u.getFactor());
}

public Carrinha clone(){
    return new Carrinha(this);
}
 }

