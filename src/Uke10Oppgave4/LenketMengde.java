package Uke10Oppgave4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import adt.MengdeADT;

class Node<T>{
	T data;
	Node<T> next;
	
	public Node() {
		this.data = null;
        this.next = null;
	}
	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}

public class LenketMengde<T> implements MengdeADT<T>{
	//protected Node<T> mBeforeBegin;
	//protected Node<T> mBeforeEnd;
	
	protected Node<T> mFirst;
	protected int mSize;
	
	
	public LenketMengde() {
		//mBeforeBegin = new Node<T>();
		//mBeforeEnd = new Node<T>();
        mFirst = null;
        mSize = 0;
	}
	
    /*
     * @return Hashcode for mengden
     */	
	public int hashCode() {
		int hashCode = 0;
		
		Node<T> current = mFirst;
		while(current != null) {
			if (current.data != null) {
				hashCode += current.data.hashCode();
			}
			current = current.next;
		}
		
		return hashCode;
	}
	
	/*
	protected Node<T> first(){
		return mBeforeBegin.next;
	}
	protected Node<T> last(){
		return mBeforeEnd;
	}
	*/
	
	
	/**
	 * @return Om mengden er tom
	 */
	@Override
	public boolean isEmpty() {
		return mSize == 0;
	}
	
	/**
	 * @param element
	 * @return Om mengden inneholder element
	 */
	@Override
	public boolean contains(T element) {
		Node<T> current = mFirst;
		
		while(current != null) {
			if(current.data == null) {
				if(element == null)
					return true;
			}else if(current.data.equals(element)) {
				return true;
			}

			current = current.next;
		}
		
		return false;
	}
	
	/**
	 * @param other
	 * @return Om mengden er en delmengde av en other
	 */
	@Override
	public boolean isSubsetOf(MengdeADT<T> other) {
		if (other == null) {
			throw new NullPointerException();
		}
		
		// try early exit
		if(count() > other.count())
			return false;
		
		// iterate over all elements
		Node<T> current = mFirst;
		while(current != null) {
			if (!other.contains(current.data)) {
				return false;
			}
			current = current.next;
		}
		
		return true;
	}
	
	/**
	 * @param other
	 * @return Om mengden er lik en other
	 */
	@Override
	public boolean isEqual(MengdeADT<T> other) {
		if (other == null) {
			throw new NullPointerException();
		}
		
		return (count() == other.count()) && isSubsetOf(other);			
	}
	
	/**
	 * @param other
	 * @return Om mengden og en other er disjunkte
	 */
	@Override
	public boolean isDisjoint(MengdeADT<T> other) {
		if (other == null) {
			throw new NullPointerException();
		}

		// a simpler way to do this is to check if the intersection is 
		// empty but it is not as fast, as it checks all elements
		// return intersection(other).isEmpty();		
		
		// iterate over all elements
		Node<T> current = mFirst;
		while(current != null) {
			if (other.contains(current.data)) {
				return false;
			}
			current = current.next;
		}
		
		return true;
	}
	
	/**
	 * @param other
	 * @return Snittet av mengden og en other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	@Override
	public MengdeADT<T> intersection(MengdeADT<T> other){
		if (other == null) {
			throw new NullPointerException();
		}
		
		LenketMengde<T> result = new LenketMengde<T>();
		
		// iterate over all elements
		Node<T> current = mFirst;		
		while(current != null) {
			if (other.contains(current.data)) {
				result.add(current.data);
			}
			current = current.next;
		}
		
		return result;
	}
	
	/**
	 * @param other
	 * @return Unionen av mengden og en other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	@Override
	public MengdeADT<T> union(MengdeADT<T> other){
		if (other == null) {
			throw new NullPointerException();
		} 
		
		// add all elements from both sets
		LenketMengde<T> result = new LenketMengde<T>();
		result.addAllFrom(this);
		result.addAllFrom(other);		
		return result;
	}
	
	/**
	 * @param other
	 * @return Mengden minus other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	@Override
	public MengdeADT<T> difference(MengdeADT<T> other){
		if(other == null) {
			throw new NullPointerException();
		}

		LenketMengde<T> result = new LenketMengde<T>();
		
		// iterate over all elements
		Node<T> current = mFirst;
		while(current != null) {
			if (!other.contains(current.data)) {
				result.add(current.data);
			}
			current = current.next;
		}
		
		return result;
	}
	
	/**
	 * Legger til et element i mengden. Elementet skal kun legges til hvis
	 * det ikke finnes i mengden fra før.
	 * 
	 * @param element
	 */
	@Override
	public void add(T element) {
		// adding to start is really ugly, and messes up the order
		// but we do it anyway to avoid having to iterate over all elements
		// There are ways of fixing this like using beforeBegin and beforeEnd pointers
		// but we are KISS'ing it here.
		
		if (contains(element)) {
			return;
		}
		
		Node<T> newNode = new Node<T>(element, mFirst);
		mFirst = newNode;
		mSize++;
	}
	
	/**
	 * Legger alle elementer fra en other til i mengden. Kun elementer
	 * som ikke finnes i mengden fra før, skal legges til.
	 * 
	 * @param other
	 */
	@Override
	public void addAllFrom(MengdeADT<T> other) {
		for (T element : other.toArray()) {
			add(element);
		}
	}
	
	/**
	 * Fjerner og returnerer et element fra mengden. Hvis elementet ikke finnes 
	 * i mengden, skal metoden returnere null.
	 * 
	 * @param element Et element som er lik det elementet som skal fjernes 
	 * @return Elementet som ble fjernet fra mengden, null hvis det ikke fantes.
	 */
	@Override
	public T remove(T element) {
		Node<T> current = mFirst;
		Node<T> prev = null;
		
		while(current != null) {
			if(current.data != null) {
				if(current.data.equals(element)) {
					if (prev == null) {
						// first element
						mFirst = current.next;
					} else {
						// not first element
						prev.next = current.next;
					}
					mSize--;
					return current.data;
				}
				
			}else {
				if(element == null) {
					if(prev == null) {
						// first element						
                        mFirst = current.next;
					} else {
						// not first element						
						prev.next = current.next;
					}
					mSize--;
					return null;
				}
			}
			
			prev = current;
			current = current.next;
		}
		
		return null;		
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@Override
	public T[] toArray() {
		Node<T> current = mFirst;
		
		int writeIndex = mSize;
		
		T[] result = (T[]) new Object[mSize];
		
		while(current != null) {
			result[--writeIndex] = current.data;
			current = current.next;
		}
		
		//return result;
		 return Arrays.copyOf(result, mSize, (Class<T[]>) result.getClass()); // Safe copy
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@Override
	public T[] toArray(T[] a) {
		Node<T> current = mFirst;
		
		int writeIndex = mSize;
		
		T[] result = (T[]) new Object[mSize];
		
		while(current != null) {
			result[--writeIndex] = current.data;
			current = current.next;
		}
		
		//return result;
		return Arrays.copyOf(result, mSize, (Class<T[]>) a.getClass()); // Safe copy
	}	
	
	/**
	 * @return Antall elementer i mengden.
	 */
	@Override	
	public int count() {
		return mSize;
	}
	
	
	/**
	 * 
	 */

	public static <T> MengdeADT<T> createFromArray(T[] arr) {
		if(arr == null) {
			throw new NullPointerException();
		}
		
		MengdeADT<T> set = new LenketMengde<>();
		for (var e : arr)
			set.add(e);
		return set;
	}
	
	public Set<T> toJavaSet() {
		return new HashSet<T>(Arrays.asList(toArray()));
	}
	
	
	/**
	 * @return En streng representasjon av mengden.
	 */
	
	public String toString() {
		return Arrays.toString(toArray());
	}	
}