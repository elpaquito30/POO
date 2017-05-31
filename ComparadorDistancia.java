import java.util.Comparator;
import java.io.Serializable;


public class ComparadorDistancia implements Comparator<Map.Entry<double,Viatura>>{

	public int compare(Map.Entry<double,Viatura> a, Map.Entry<double,Viatura> b){

		if(a.getKey() == b.getKey()) return 0;
		if(a.getKey() < b.getKey()) return 1;
		return -1;

	}
}