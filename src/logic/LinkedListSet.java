package logic;

import java.util.LinkedList;

public class LinkedListSet<T> implements ISet<T>{
	 
	 private LinkedList<T> a;
	 
	 
	 public LinkedListSet(int dataType) {
			//this.a = LinkedListFactory.getList(dataType);
		 this.a = new LinkedList<T>();
	}

	@Override
	public ISet<T> union(ISet<T> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISet<T> intersection(ISet<T> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISet<T> difference(ISet<T> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISet<T> merge(ISet<T> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean member(T x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void empty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(T x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equal(ISet<T> b) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
