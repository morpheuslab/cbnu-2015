package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class CollectionInterface {
	
	public static void main(String[] args) {
		
		Collection c = new ArrayList();
		
		c.add("hello");
		c.add(111);
		
		Collection c1 = new ArrayList();
		c1.add("java");
		c1.add("hi");
		
		c.addAll(c1);
		
		Iterator iter = c.iterator();
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		ListIterator iter2 = (ListIterator) c.iterator();
		
	}
	
}























