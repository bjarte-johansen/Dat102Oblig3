package adt;

public interface MengdeADT<T> {
	
	/**
	 * @return Om mengden er tom
	 */
	boolean isEmpty();
	
	/**
	 * @param element
	 * @return Om mengden inneholder element
	 */
	boolean contains(T element);
	
	/**
	 * @param other
	 * @return Om mengden er en delmengde av en other
	 */
	boolean isSubsetOf(MengdeADT<T> other);
	
	/**
	 * @param other
	 * @return Om mengden er lik en other
	 */
	boolean isEqual(MengdeADT<T> other);
	
	/**
	 * @param other
	 * @return Om mengden og en other er disjunkte
	 */
	boolean isDisjoint(MengdeADT<T> other);
	
	/**
	 * @param other
	 * @return Snittet av mengden og en other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	MengdeADT<T> intersection(MengdeADT<T> other);
	
	/**
	 * @param other
	 * @return Unionen av mengden og en other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	MengdeADT<T> union(MengdeADT<T> other);
	
	/**
	 * @param other
	 * @return Mengden minus other.
	 *         Metoden skal ikke endre på mengden eller other,
	 *         men lage en ny mengde som blir resultatet.
	 */
	MengdeADT<T> difference(MengdeADT<T> other);
	
	/**
	 * Legger til et element i mengden. Elementet skal kun legges til hvis
	 * det ikke finnes i mengden fra før.
	 * 
	 * @param element
	 */
	void add(T element);
	
	/**
	 * Legger alle elementer fra en other til i mengden. Kun elementer
	 * som ikke finnes i mengden fra før, skal legges til.
	 * 
	 * @param other
	 */
	void addAllFrom(MengdeADT<T> other);
	
	/**
	 * Fjerner og returnerer et element fra mengden. Hvis elementet ikke finnes 
	 * i mengden, skal metoden returnere null.
	 * 
	 * @param element Et element som er lik det elementet som skal fjernes 
	 * @return Elementet som ble fjernet fra mengden, null hvis det ikke fantes.
	 */
	T remove(T element);
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	T[] toArray();
	
	/**
	 * @return En tabell av elementene i mengden. Tabellen har størrelse lik
	 *         antall elementer i mengden.
	 */
	T[] toArray(T[] a);	
	
	/**
	 * @return Antall elementer i mengden.
	 */
	int count();	
	
	
	
	/**
	 * for evt norspråklig enhetstest
	 */
	boolean erTom();
	boolean inneholder(T element);
	boolean erDelmengdeAv(MengdeADT<T> other);
    boolean erLik(MengdeADT<T> other);
	boolean erDisjunkt(MengdeADT<T> other);
	MengdeADT<T> snitt(MengdeADT<T> other);
	//MengdeADT<T> union(MengdeADT<T> other);
	MengdeADT<T> minus(MengdeADT<T> other);
	void leggTil(T element);
	void leggTilAlleFra(MengdeADT<T> other);
	T fjern(T element);
	T[] tilTabell();
	T[] tilTabell(T[] a);	
	int antallElementer();	
}