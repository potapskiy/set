package logic;

import java.util.ArrayList;
import java.util.Arrays;

import logic.list.LinkedListImp;

public class CharVectorSet<T extends Comparable<T>> implements ISet<T>{
	
	private int[] characterVector;
	private int length;
	private LinkedListImp<T> set;
	
	public CharVectorSet(int n) {
		this.length = n;
		this.characterVector = new int[n];
		Arrays.fill(characterVector, 0);
		this.set = new LinkedListImp<T>();
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
		int position = this.set.add(x);
		
		if (position < 0 ) return false;
		
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
		int position = this.set.add(x);
		if (position >= 0){
			this.characterVector[position] = 0;
		}
		
	}

	@Override
	public T min() {
		T elementToCompare;
		T min = null;
		int startPos = 0;
		for (int i = 0; i < length; i++) {
			if(characterVector[i] == 1){
				min = this.set.get(i);
				startPos = i;
				break;
			}
		}
		
		
		for (int i = startPos; i < length; i++) {
			if(characterVector[i] == 1){
				elementToCompare = this.set.get(i);
				if (elementToCompare.compareTo(min) < 0) min = elementToCompare;
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
			if(characterVector[i] == 1){
				max = this.set.get(i);
				startPos = i;
				break;
			}
		}
		
		
		for (int i = startPos; i < length; i++) {
			if(characterVector[i] == 1){
				elementToCompare = this.set.get(i);
				if (elementToCompare.compareTo(max) > 0) max = elementToCompare;
			}
		}
		return max;
	}

	@Override
	public boolean equal(ISet<T> b) {

		int[] bCharVect = b.getCharacterVector();
		
		if(characterVector.length != bCharVect.length){
			return false;
		}
		
		for (int i = 0; i < length; i++) {
			if(characterVector[i] != bCharVect[i]){
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
	public int getSize() {
		return length;
	}

	@Override
	public ArrayList<Pair<T, T>> cartesianProduct(ISet<T> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vectorToString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < length; i++) {
			output.append("[" + String.valueOf(characterVector[i]) + "]");
		}
		return output.toString();
	}

	
}
