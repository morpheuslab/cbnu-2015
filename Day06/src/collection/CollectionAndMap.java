package collection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public class CollectionAndMap {
	
	public void test(Collection c) {
		c.add(new String("first"));
		c.add(new Integer(3));
		c.add(new String("second"));
		c.add(new Integer(3));
		c.add(new Boolean(false));
		System.out.println(c);
	}
	
	public void test(Map m) {
		m.put("1", new String("first"));
		m.put("2", new Integer(3));
		m.put("3", new String("second"));
		m.put("4", new Integer(3));
		System.out.println(m);
	}
	
	public static void main(String[] args) throws Exception {
//		CollectionAndMap cm = new CollectionAndMap();
//		
//		Collection c = new Vector();
//		cm.test(c);
//		
//		c = new HashSet();
//		cm.test(c);
//		
//		Map m = new Hashtable();
//		cm.test(m);
//		
//		System.out.println(m.get("3"));
		
		ArrayList list = new ArrayList();
		for (int i = 0; i < 100; i ++) {
			list.add(i);
			System.out.println((i + 1) + "번째 데이터 삽입. 리스트 용량: "
					+ getCapacity(list));
		}
	}
	
	static int getCapacity(ArrayList<?> l) throws Exception {
        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(l)).length;
    }
	
}
























