import java.io.Serializable;
import static java.lang.Math.abs ;

public class Posicao implements Serializable
{
    private double x , y ;
    
    public Posicao(double cx, double cy) {
        x = cx; 
        y = cy; 
    }
    
    public Posicao(){ 
        this(0.0,0.0); 
    }
    
    public Posicao(Posicao p){
        x=p.getX(); 
        y=p.getY();
    }
    
    public double getX(){
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setX(double x){
        this.x=x;
    }
    
    public void setY(double y){
        this.y=y;
    }   
    
    public String toString(){
        return new String("Posição = "+x+","+y);
    }
    
    public Posicao clone(){
        return new Posicao(this);
    }
   
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
       Posicao m = (Posicao) o;
       return (this.x == m.getX() && 
               this.y == m.getY());
    } 
    
    public double distancia(Posicao o){
    double distancia;
    distancia = Math.sqrt( Math.pow((this.x - o.getX()),2)+
                           Math.pow((this.y - o.getY()),2));
    return distancia;
   }
   
    
}
