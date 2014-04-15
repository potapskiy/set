package logic;

public interface ISet<T> {

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

}