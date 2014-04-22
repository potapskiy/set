package logic;

import logic.list.LinkedListImp;


public class LinkedListSet<T extends Comparable<T>> implements ISet<T>{
	 
	 private LinkedListImp<T> a;
	 
	 
	 public LinkedListSet() {
			//this.a = LinkedListFactory.getList(dataType);
		 this.a = new LinkedListImp<T>();
	}

	@Override
	public ISet<T> union(ISet<T> b) {
		ISet<T> result = new LinkedListSet<T>();
		
		LinkedListImp<T> aList = this.a;
		LinkedListImp<T> bList = b.getList();
		
		for (T val : aList){
			result.insert(val);
		}
		
		
		for (T val : bList){
			result.insert(val);
		}
		
		return result;
		
	}

	@Override
	public ISet<T> intersection(ISet<T> b) {
		
		ISet<T> result = new LinkedListSet<T>();
		
		LinkedListImp<T> aList = this.a;
		
		for (T val : aList){
			
			if (b.member(val)) result.insert(val);
		}
		
		return result;
		
		
		
	}

	@Override
	public ISet<T> difference(ISet<T> b) {
		ISet<T> result = new LinkedListSet<T>();
		
		LinkedListImp<T> aList = this.a;
		
		for (T val : aList){
			
			if (!b.member(val)) result.insert(val);
		}
		
		return result;
		
	}

	@Override
	public ISet<T> merge(ISet<T> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean member(T x) {
		return this.a.contains(x);
	}

	@Override
	public void empty() {
		this.a = new LinkedListImp<T>();
		
	}

	@Override
	public void insert(T x) {
		this.a.add(x);
		
	}

	@Override
	public void delete(T x) {
		this.a.remove(x);
		
	}

	@Override
	public T min() {
		if (this.a.size() == 0){
			return null;
		}
		
		T min = this.a.get(0);
		
		for (T val : this.a){
			if (val.compareTo(min) < 0) min = val;
		}
		
		return min;
	}

	@Override
	public T max() {
		if (this.a.size() == 0){
			return null;
		}
		
		T max = this.a.get(0);
		
		for (T val : this.a){
			if (val.compareTo(max) > 0) max = val;
		}
		
		return max;
	}

	@Override
	public boolean equal(ISet<T> b) {
		
		LinkedListImp<T> aList = this.a;
		LinkedListImp<T> bList = b.getList();
		
		if (aList.size() != bList.size()) return false;
		
		
		for (T val: aList){
			if (!bList.contains(val))
				return false;
		}
		
		return true;
		
	}
	
	
	@Override
	public LinkedListImp<T> getList() {
		return this.a;
	}
	
	
	@Override
	public String toString(){
		return this.a.toString();
	}
	
	
}
