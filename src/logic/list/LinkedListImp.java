package logic.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListImp<E> implements List<E> {

	private Node<E> previous;
	private Node<E> next;
	private Node<E> first = new Node<E>();

	private int size = 0;

	public LinkedListImp() {
		
		first.setNext(null); 
		first.setPrevious(null);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
	    for (int i = 0; i < size(); i++) {
	        if (get(i).equals(o)) return true;
	    }
	    return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(E e) {
		Node<E> node = new Node<E>(e);
		if (first == null) {
			first = node;
		} else {
			previous = first;
		}
		if (size() == 1) {
			first.setNext(node);
		}
		size++;

		return true;
	}

	@Override
	public boolean remove(Object o) {
	    Node<E> node = first;
	    for(int i=0; i<size(); i++){
	        if(node.equals(o)){
	            node.getPrevious().setNext(node.getNext());
	            return true;
	        }
	    }
	    return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public E get(int index) {
	    E element;
	    if (index >= 0 && index < size()) {
	        element = getByIndex(index).getT();
	    } else throw new IndexOutOfBoundsException();
	    return element;
	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	@Override
	public void add(int index, E element) {

	}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	private Node<E> getByIndex(int index) {
		Node<E> node = null;
		if (!isEmpty() && (index >= 0 && index < size)) {
			node = first;
			for (int i = 1; i <= index; i++) {
				node = node.getNext();
			}
		}
		return node;
	}
}