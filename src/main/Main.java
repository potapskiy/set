package main;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import logic.ISet;
import logic.SetFactory;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("OK");
		
		
		List<Integer> list = new LinkedList<Integer>();
		
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		
		
//		list.remove(2);
		
		Iterator<Integer> it = list.iterator();
	    while(it.hasNext()){
	        Integer element = it.next();
	        if(element == 30){
	            it.remove();
	        }
	    }
	    
	    
		for(int i: list){
			System.out.println(i);
		}
		
		
		//ISet c = SetFactory.getSet(2,1,0);
		//c.union(b);
		
	}

}
