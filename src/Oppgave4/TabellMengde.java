package Oppgave4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import adt.MengdeADT;

import java.util.Set;

public class TabellMengde<T> implements MengdeADT<T>{
	
	protected T[] mData;
	protected int mSize;
	
	@SuppressWarnings("unchecked")
	public TabellMengde() {
		mData = (T[]) new Object[10];
		mSize = 0;
	}
	
	/**
	 * @return Om mengden er tom
	 */
	@Override
	public boolean isEmpty() {
		return count() == 0;
	}
	
	/**
	 * @param element
	 * @return Om mengden inneholder element
	 */
	@Override
	public boolean contains(T element) {
        for (int i=0; i<mSize; i++) {
        	T e = mData[i];
			if(e == null) {
				if(element == null) {
					return true;
				}
			}else if(e.equals(element)) {
				return true;
			}
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
		
		if(isEmpty()) {
			return true;
		}
		
		for (int i = 0; i < mSize; i++) {
			T e = mData[i];
			if (!other.contains(e)) {
				return false;
			}
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
		//return intersection(other).isEmpty();
		
		// iterate over all elements		
        for (int i=0; i<mSize; i++) {
        	T e = mData[i];
			if (other.contains(e)) {
				return false;
			}
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
		
		MengdeADT<T> result = new TabellMengde<T>();
		
		for (int i=0; i<mSize; i++) {
			T e = mData[i];
			if (other.contains(e)) {
				result.add(e);
			}
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
		
		MengdeADT<T> result = new TabellMengde<T>();
		
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
		if (other == null) {
			throw new NullPointerException();
		}
		
		MengdeADT<T> result = new TabellMengde<T>();
		
        for (int i=0; i<mSize; i++) {
        	T e = mData[i];
			if (!other.contains(e)) {
				result.add(e);
			}
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
		if (contains(element)) {
			return;
		}
		
		if (mSize >= mData.length) {
			mData = Arrays.copyOf(mData, mData.length * 2);
		}
		
		mData[mSize++] = element;
	}
	
	/**
	 * Legger alle elementer fra en other til i mengden. Kun elementer
	 * som ikke finnes i mengden fra før, skal legges til.
	 * 
	 * @param other
	 */
	@Override
	public void addAllFrom(MengdeADT<T> other) {
		if (other == null) {
			throw new NullPointerException();
		}
		
		T[] otherArr = other.toArray();
		for (T e : otherArr) {
			add(e);
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
		for(int i=0; i<mSize; i++) {
			if(mData[i].equals(element)) {
				T result = mData[i];
				mData[i] = mData[mSize - 1];
				mData[mSize - 1] = null;
				mSize--;
				return result;
			}
		}
		
		return null;
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@Override
	public T[] toArray() {
		return Arrays.copyOf(mData, mSize, (Class<T[]>) mData.getClass()); // Safe copy
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(T[] a) {
		return Arrays.copyOf(mData, mSize, (Class<? extends T[]>) a.getClass());
		//return Arrays.copyOf(mData, mSize, (Class<? extends T[]>) a.getClass());
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