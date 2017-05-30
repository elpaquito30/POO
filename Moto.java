
/**
 * Escreva a descrição da classe Moto aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Moto extends Viatura {
public Moto(double velocidade, double custo, Posicao p, Motorista m, String matricula){
        super(velocidade,custo,p,m,matricula);
}

public Moto(){
    super();
    
}

public Moto(Moto u){
    super(u.getVelocidade(),u.getCusto(),u.getP(),u.getMotorista(),u.getMatricula());
}

public Moto clone(){
    return new Moto(this);
}



}
