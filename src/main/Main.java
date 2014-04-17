package main;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import logic.ISet;
import logic.SetFactory;
import logic.list.LinkedListImp;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("OK");
		
		
		LinkedListImp<Integer> l = new LinkedListImp<Integer>();
		
		l.addSorted(5);
		
		l.addSorted(6);
		
		l.addSorted(7);
		
		l.addSorted(7);
		
		l.remove(6);
		l.remove(5);
		l.remove(7);
		l.remove(8);
		
		//System.out.println(Integer.compare(5, 6));
		
		for(int i: l){
			System.out.println(i);
		}
		
		System.out.println(l.toString());
		
	}

}
