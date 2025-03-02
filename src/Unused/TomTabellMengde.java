package Unused;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import adt.MengdeADT;

import java.util.Set;

public class TomTabellMengde<T> implements MengdeADT<T>{
	
	public TomTabellMengde() {
	}
	
	/**
	 * @return Om mengden er tom
	 */
	@Override
	public boolean isEmpty() {
		
	}
	
	/**
	 * @param element
	 * @return Om mengden inneholder element
	 */
	@Override
	public boolean contains(T element) {
		
	}
	
	/**
	 * @param other
	 * @return Om mengden er en delmengde av en other
	 */
	@Override
	public boolean isSubsetOf(MengdeADT<T> other) {
		
	}
	
	/**
	 * @param other
	 * @return Om mengden er lik en other
	 */
	@Override
	public boolean isEqual(MengdeADT<T> other) {
		
	}
	
	/**
	 * @param other
	 * @return Om mengden og en other er disjunkte
	 */
	@Override
	public boolean isDisjoint(MengdeADT<T> other) {
		
	}
	
	/**
	 * @param other
	 * @return Snittet av mengden og en other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	@Override
	public MengdeADT<T> intersection(MengdeADT<T> other){
		
	}
	
	/**
	 * @param other
	 * @return Unionen av mengden og en other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	@Override
	public MengdeADT<T> union(MengdeADT<T> other){
		
	}
	
	/**
	 * @param other
	 * @return Mengden minus other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	@Override
	public MengdeADT<T> difference(MengdeADT<T> other){
		
	}
	
	/**
	 * Legger til et element i mengden. Elementet skal kun legges til hvis
	 * det ikke finnes i mengden fra før.
	 * 
	 * @param element
	 */
	@Override
	public void add(T element) {
		
	}
	
	/**
	 * Legger alle elementer fra en other til i mengden. Kun elementer
	 * som ikke finnes i mengden fra før, skal legges til.
	 * 
	 * @param other
	 */
	@Override
	public void addAllFrom(MengdeADT<T> other) {
		
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
		
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@Override
	public T[] toArray() {
		
	}
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	@Override
	public T[] toArray(T[] a) {

	}
	
	/**
	 * @return Antall elementer i mengden.
	 */
	@Override	
	public int count() {
		
	}
	
	/**
	 * 
	 */
	public static <T> MengdeADT<T> createFromArray(T[] arr) {
		
	}
	
	public Set<T> toJavaSet() {
		
	}
	
	
	/**
	 * @return En streng representasjon av mengden.
	 */
	
	public String toString() {
		
	}
}