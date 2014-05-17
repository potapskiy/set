package logic;

import java.util.ArrayList;
import java.util.Arrays;

import logic.list.LinkedListImp;

public class CharVectorSet<T extends Comparable<T>> implements ISet<T> {

	private int[] characterVector;
	private int length;
	private LinkedListImp<T> set;

	public CharVectorSet(int n) {
		this.length = n;
		this.characterVector = new int[n];
		Arrays.fill(characterVector, 0);
		this.set = new LinkedListImp<T>();
	}

	public CharVectorSet(int n, LinkedListImp<T> set, int[] characterVector) {
		this.length = n;
		this.characterVector = characterVector;
		this.set = set;
	}

	@Override
	public ISet<T> union(ISet<T> b) {
		int[] bCharVect = b.getCharacterVector();

		if (characterVector.length != bCharVect.length) {
			return null;
		}

		int[] uniounSetVec = new int[length];
		for (int i = 0; i < length; i++) {
			uniounSetVec[i] = ((characterVector[i] == 1) || (bCharVect[i] == 1)) ? 1
					: 0;
		}

		return new CharVectorSet<>(length, this.set, uniounSetVec);
	}

	@Override
	public ISet<T> intersection(ISet<T> b) {
		int[] bCharVect = b.getCharacterVector();

		if (characterVector.length != bCharVect.length) {
			return null;
		}

		int[] interSetVec = new int[length];
		for (int i = 0; i < length; i++) {
			interSetVec[i] = ((characterVector[i] == 1) && (bCharVect[i] == 1)) ? 1
					: 0;
		}
		return new CharVectorSet<>(length, this.set, interSetVec);
	}

	@Override
	public ISet<T> difference(ISet<T> b) {
		int[] bCharVect = b.getCharacterVector();

		if (characterVector.length != bCharVect.length) {
			return null;
		}

		int[] diffSetVec = new int[length];
		for (int i = 0; i < length; i++) {
			diffSetVec[i] = ((characterVector[i] == 1) && (bCharVect[i] == 0)) ? 1
					: 0;
		}

		return new CharVectorSet<>(length, this.set, diffSetVec);
	}

	@Override
	public ISet<T> merge(ISet<T> b) {
		ISet<T> intersectionSet = this.intersection(b);
		if (intersectionSet.getSize() == 0) {
			return this.union(b);
		}
		return null;
	}

	@Override
	public boolean member(T x) {
		int position = this.set.getIndex(x);

		if (position < 0)
			return false;

		return (characterVector[position] == 1);
	}

	@Override
	public void empty() {
		for (int i = 0; i < length; i++) {
			characterVector[i] = 0;
		}

	}

	@Override
	public void insert(T x) {
		int position = this.set.add(x);

		try {
			this.characterVector[position] = 1;
		} catch (Exception e) {
			this.set.remove(x);
		}

	}

	@Override
	public void delete(T x) {
		int position = this.set.getIndex(x);
		System.out.println("POS: " + position);
		if (position >= 0) {
			this.characterVector[position] = 0;
		}

	}

	@Override
	public T min() {
		T elementToCompare;
		T min = null;
		int startPos = 0;
		for (int i = 0; i < length; i++) {
			if (characterVector[i] == 1) {
				min = this.set.get(i);
				startPos = i;
				break;
			}
		}

		for (int i = startPos; i < length; i++) {
			if (characterVector[i] == 1) {
				elementToCompare = this.set.get(i);
				if (elementToCompare.compareTo(min) < 0)
					min = elementToCompare;
			}
		}
		return min;
	}

	@Override
	public T max() {
		T elementToCompare;
		T max = null;
		int startPos = 0;
		for (int i = 0; i < length; i++) {
			if (characterVector[i] == 1) {
				max = this.set.get(i);
				startPos = i;
				break;
			}
		}

		for (int i = startPos; i < length; i++) {
			if (characterVector[i] == 1) {
				elementToCompare = this.set.get(i);
				if (elementToCompare.compareTo(max) > 0)
					max = elementToCompare;
			}
		}
		return max;
	}

	@Override
	public boolean equal(ISet<T> b) {

		int[] bCharVect = b.getCharacterVector();

		if (characterVector.length != bCharVect.length) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (characterVector[i] != bCharVect[i]) {
				return false;
			}
		}

		return true;
	}

	@Override
	public LinkedListImp<T> getList() {
		return this.set;
	}

	@Override
	public int[] getCharacterVector() {
		return characterVector;
	}

	@Override
	public String toString() {
//		return this.set.toString()+"\n"+vectorToString();

		StringBuilder output = new StringBuilder();
		for (int i = 0; i < length; i++) {

			if (characterVector[i] == 1)
				output.append("[" + set.get(i) + "]");
		}
		return output.toString();// +"\n"+vectorToString();
	}

	@Override
	public int getSize() {
		int size = 0;
		for (int i = 0; i < length; i++) {
			if (characterVector[i] == 1) {
				size++;
			}
		}

		return size;
	}

	@Override
	public ArrayList<Pair<T, T>> cartesianProduct(ISet<T> b) {

		ArrayList<Pair<T, T>> product = new ArrayList<Pair<T, T>>();
		int[] bCharVect = b.getCharacterVector();

		if (characterVector.length != bCharVect.length) {
			return null;
		}

		for (int i = 0; i < length; i++) {

			if (characterVector[i] == 1) {

				for (int j = 0; j < length; j++) {
					if (bCharVect[j] == 1){
						System.out.println(i + " " + j);
					product.add(new Pair(getElement(i), getElement(j)));}
				}
			}

		}

		return product;
	}

	@Override
	public String vectorToString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < length; i++) {
			output.append("[" + String.valueOf(characterVector[i]) + "]");
		}
		return output.toString();
	}

	private T getElement(int pos) {
		return set.get(pos);
	}

	@Override
	public void setList(LinkedListImp<T> set) {
		this.set = set;

	}

}
