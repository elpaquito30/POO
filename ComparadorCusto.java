
import java.util.Comparator;
import java.lang.Double;


public class ComparadorCusto implements Comparator<Double>
{
    public int compare(Double a, Double b){

	return b.compareTo(a);

	}

}
