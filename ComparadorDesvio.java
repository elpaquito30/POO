import java.util.Comparator;
import java.lang.Double;
import java.io.Serializable;


public class ComparadorDesvio implements Comparator<Double>,Serializable
{
    public int compare(Double a, Double b){

	return b.compareTo(a);

	}

}
