package logic.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListImp<E extends Comparable<E>> implements Iterable<E> {

	private Node<E> head;
	private int listCount = 0;

	public LinkedListImp() {
		head = null;
		listCount = 0;
	}

	public int add(E data) {

		if (head == null) {
			head = new Node<E>(data);
			listCount++;
			return 0;
		}

		if (contains(data)) {
			return getIndex(data);
		}

		Node<E> temp = new Node<E>(data);
		Node<E> current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(temp);
		listCount++;
		return listCount - 1;
	}

	public void addSorted(E data) {

		if (head == null) {
			head = new Node<E>(data);
			return;
		}

		if (contains(data)) {
			return;
		}

		Node<E> temp = new Node<E>(data);
		Node<E> current = head;
		Node<E> prev = null;

		while (current != null) {

			// System.out.println("STEP: compare" +current.data+" "
			// + data+ "  " + current.compareTo(temp));

			int compateResult = current.compareTo(temp);

			if (compateResult == 0)
				return;

			if (compateResult > 0) {
				temp.setNext(current);

				if (prev != null) {
					prev.setNext(temp);
				} else {
					head = temp;
				}
				listCount++;
				return;
			}

			prev = current;
			current = current.getNext();
		}

		temp.setNext(null);
		prev.setNext(temp);
		listCount++;

		return;
	}

	public void add(E data, int index) {

		if (head == null) {
			head = new Node<E>(data);
			return;
		}

		Node<E> temp = new Node<E>(data);
		Node<E> current = head;
		for (int i = 1; i < index && current.getNext() != null; i++) {
			current = current.getNext();
		}
		temp.setNext(current.getNext());
		current.setNext(temp);
		listCount++;
	}

	public boolean contains(E x) {
		for (E tmp : this)
			if (tmp.equals(x))
				return true;

		return false;
	}

	public E get(int index) {
		if (index < 0)
			return null;

		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			if (current.getNext() == null)
				return null;

			current = current.getNext();
		}
		return current.getData();
	}

	public int getIndex(E x) {
		if (head == null)
			return -1;

		int index = 0;
		for (E tmp : this){
			if (tmp.equals(x)){
				return index;
			}
			index++;
		}
		
		return -1;
				
	}

	public boolean remove(Object o) {

		if (head == null)
			return false;

		Node<E> current = head;

		if (current.equals(o)) {
			head = current.getNext();
			listCount--;
			return true;
		}

		for (int i = 0; i < listCount; i++) {

			if (current.getNext() == null)
				return false;

			if (current.getNext().equals(o)) {
				current.setNext(current.getNext().getNext());
				listCount--;
				return true;
			}

			current = current.getNext();
		}
		return false;

	}

	public int size() {
		return listCount;
	}

	public String toString() {
		Node<E> current = head;
		StringBuilder output = new StringBuilder();
		while (current != null) {
			output.append("[" + current.getData().toString() + "]");
			current = current.getNext();
		}
		return output.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<E> {
		private Node<E> nextNode;

		public LinkedListIterator() {
			nextNode = head;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E res = nextNode.data;
			nextNode = nextNode.next;
			return res;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
