public class Avaliacao {
    private int avaliacao;
    
    public Avaliacao(int avaliacao){
        this.avaliacao=avaliacao;
    }
    
    public Avaliacao(){
        avaliacao=0;
    }
    
    public Avaliacao(Avaliacao u){
        this.avaliacao = u.getAvaliacao();
    }
    
    public int getAvaliacao(){
        return avaliacao;
    }
    
    public void setAvaliacao(int avaliacao){
        this.avaliacao = avaliacao;
    }
    
    public Avaliacao clone(){
        return new Avaliacao(this);
    }
    
    public boolean equals(Object o){
       if(this == o)
            return true;
       if ((o==null) || (this.getClass() != o.getClass()))
            return false;
          Avaliacao m = (Avaliacao) o;
       return (this.avaliacao==(m.getAvaliacao()));
    }
}
