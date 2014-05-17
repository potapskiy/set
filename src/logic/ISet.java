package logic;

import java.util.ArrayList;
import java.util.HashMap;

import logic.list.LinkedListImp;

public interface ISet<T extends Comparable<T>> {

	public ISet<T> union(ISet<T> b);

	public ISet<T> intersection(ISet<T> b);

	public ISet<T> difference(ISet<T> b);

	public ISet<T> merge(ISet<T> b);

	public boolean member(T x);

	public void empty();

	public void insert(T x);

	public void delete(T x);

	public T min();

	public T max();

	public boolean equal(ISet<T> b);
	
	public LinkedListImp<T> getList();
	
	public void setList(LinkedListImp<T> set);
	
	public int[] getCharacterVector();
	
	public int getSize();
	
	public ArrayList<Pair<T, T>> cartesianProduct(ISet<T> b);
	
	@Override
	public String toString();
	
	public String vectorToString();

}
