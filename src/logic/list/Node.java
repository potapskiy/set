package logic.list;

public class Node<T extends Comparable<T>>  implements Comparable<Node<T>> {
	
	Node<T> next;
	T data;

	public Node(T _data) {
		next = null;
		data = _data;
	}

	public Node(T _data, Node<T> _next) {
		next = _next;
		data = _data;
	}

	public T getData() {
		return data;
	}

	public void setData(T _data) {
		data = _data;
	}


	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> _next) {
		next = _next;
	}
	
	public boolean equals(Object obj) {
	    return data.equals(obj);
	}

	@Override
	public int compareTo(Node<T> obj) {
		
		return data.compareTo(obj.data);
		
	}
}
