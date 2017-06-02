import java.io.Serializable;
import java.util.Comparator;
import java.lang.Double;


public class ComparadorCusto implements Comparator<Double>, Serializable
{
    public int compare(Double a, Double b){

	return b.compareTo(a);

	}

}
