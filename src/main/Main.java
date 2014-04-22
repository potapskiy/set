package main;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import utils.Constants;
import logic.ISet;
import logic.LinkedListSet;
import logic.SetFactory;
import logic.list.LinkedListImp;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("OK");
		
		
		
		
		System.out.println(Integer.compare(1, 4));
		
		
		ISet<Integer> a = SetFactory.getSet(Constants.LIST_TYPE, Constants.INT_TYPE, 0);
		
		a.insert(5);
		a.insert(7);
		a.insert(1);
		
		
		
		ISet<Integer> b = SetFactory.getSet(Constants.LIST_TYPE, Constants.INT_TYPE, 0);
		
		b.insert(7);
		b.insert(5);
		b.insert(2);
		
		
		
		
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		
		
	}

}
