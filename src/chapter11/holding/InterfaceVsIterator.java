package chapter11.holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InterfaceVsIterator {
	public static void display(Iterator<String> it) {
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println();
	}
	
	public static void display(Collection<String> cs) {
		for(String s:cs) {
			System.out.print(s+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		List<String> petList = new ArrayList<>() ;
		petList.add("tom");
		petList.add("cat");
		petList.add("dog");
		petList.add("pig");
		petList.add("fox");
		
		Set<String> petSet = new HashSet<>(petList);
		
		Map<String, String> petMap = new HashMap<>();
		
		String[] names = "Ralph,Eric,Robin,Lacey,Sam".split(",");
		for (int i = 0; i < names.length; i++) {
			petMap.put(names[i], petList.get(i));			
		}
		
		display(petList);
		display(petSet);
		display(petList.iterator());
		display(petSet.iterator());
		System.out.println(petMap);
		System.out.println(petMap.keySet());
		display(petMap.values());
		display(petMap.values().iterator());
	}

}
