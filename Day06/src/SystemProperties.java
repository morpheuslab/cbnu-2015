import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;


public class SystemProperties {
	
	public static void main(String[] args) {
		
		Properties props = System.getProperties();
		
		// 키 조회
		Set keySet = props.keySet();
		TreeSet keyTreeSet = new TreeSet(keySet);
		
		Iterator keyIter = keyTreeSet.iterator();
		
		while (keyIter.hasNext()) {
			String key = (String) keyIter.next();
			String val = (String) props.get(key);
			System.out.println(key + " = " + val);
		}
		
	}
	
}






