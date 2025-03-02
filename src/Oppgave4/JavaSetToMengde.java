package Oppgave4;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import adt.MengdeADT;

import java.util.Set;
 
public class JavaSetToMengde<T> implements MengdeADT<T>{
	protected java.util.Set<T> mStorage;
	
    @SuppressWarnings("unchecked")
    public JavaSetToMengde() {
        mStorage = new HashSet<T>();
    }


    /*
     * @return Hashcode for mengden
     */    
	public int hashCode() {
		return mStorage.hashCode();
	}
    
	
	/**
	 * @return Om mengden er tom
	 */
	@Override
	public boolean isEmpty() {
		return mStorage.isEmpty();
	}
	
	/**
	 * @param element
	 * @return Om mengden inneholder element
	 */
	@Override
	public boolean contains(T element) {
		return mStorage.contains(element);
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
		
		for(var e : mStorage) {
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
		
		for(var e : mStorage) {
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
			
		MengdeADT<T> result = new JavaSetToMengde<T>();
		
		for(var e : mStorage) {
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
		
		MengdeADT<T> result = new JavaSetToMengde<T>();
		
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
		
		MengdeADT<T> result = new JavaSetToMengde<T>();
		
		for(var e : mStorage) {
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
		mStorage.add(element);
	}
	
	/**
	 * Legger alle elementer fra en other til i mengden. Kun elementer
	 * som ikke finnes i mengden fra før, skal legges til.
	 * 
	 * @param other
	 */
	@Override
	public void addAllFrom(MengdeADT<T> other) {
		T[] tmp = other.toArray();
		for (var e : tmp) {
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
		T tmp = element;
		return mStorage.remove(element) ? tmp : null;
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		return (T[]) mStorage.toArray();
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(T[] a) {
		return (T[]) mStorage.toArray(a);
	}	
	
	/**
	 * @return Antall elementer i mengden.
	 */
	@Override	
	public int count() {
		return mStorage.size();
	}
	
	/**
	 * 
	 */
	/*
	public static <T> MengdeADT<T> createFromArray(T[] arr) {
		if(arr == null) {
			throw new NullPointerException();
		}
		
		MengdeADT<T> set = new JavaSetToMengde<>();
		for (var e : arr)
			set.add(e);
		return set;
	}
	
	public Set<T> toJavaSet() {
		return new HashSet<T>(Arrays.asList(toArray()));
	}
	*/
	
	
	/**
	 * @return En streng representasjon av mengden.
	 */
	
	public String toString() {
		return Arrays.toString(toArray());
	}
}