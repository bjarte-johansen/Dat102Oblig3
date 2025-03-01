/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Oppgave4.LenketMengde;
import Oppgave4.TabellMengde;
import adt.MengdeADT;

/**
 * 
 * Vi tester alle eller nær alle metoder, selv om det kan være noe overlap.
 *  
 * Vi har valgt å lage metoder for makeArray(...) og makeSet(...) som håndterer
 * 0 .. 6 parametre for å gjøre testene enklere å lese. Vi bytte fra å bruke
 * makeEmptyArray or makeEmptySet til makeArray() og makeSet() underveis men lot
 * pga stor arbeidsmengde vær å ettergå dette og gjøre det konsistent. 
 * 
 * Vi fant ut etterhvert at med detaljene i testene våre så dropper vi å gjøre flere
 * tester da vi allerede hadde rundet 500 linjer med kode i enhetstest. Metoder som 
 * ikke er dekket med egen funksjon er dekket under andre tester mener vi. 
 * 
 * Note: testThrowNullPointerException() sjekker at metoder som tar et sett som argument
 * kaster NullPointerException hvis argumentet er null.
 * 
 * Note: scoped variables er brukt i { statement-list } blokker for å tillate redeklarasjon
 * av variabler i samme metode / funksjon 
 * 
 * Note: Noen av testene er i en loop som kjører to ganger, eller har duplikat av add/remove
 * etc; dette er ikke feil, det er for å teste LenkedMengde bedre for å avdekke problemer
 * med remove/head/add.
 * 
 * Note: Tester er merket med {}, {a, b}, {b, c} selv om testen kan inneholde flere variabler
 * i selve testen. Det er for å vise mønster til typen test; det er ikke meningen kode må være eksakt
 * lik, feks {a,b} disjoint {b,c} kan testes med makeSet(1,2,3).disjoint(makeSet(2,3,4)).  
 * 
 * For lenketmengde så adder vi til starten av linked-list og må derfor snu array i toArray() metoden
 * ved å skrive baklengs. Dette er også grunnen til at vi i metoder som lager et nytt sett må ta
 * toArray() først og bruke dette til å iterere over elementer med for å få riktig order i nye settet. 
 * Selv om det kan sies at sett er unordered blir det i vår testenhet sammenlignet arrays og vi har
 * derfor valgt å gjøre det på denne måten.
 */

class TestLenketMengde {	
	/*
	 */	
	
	protected <T> MengdeADT<Integer> makeSet(T[] arr) {
		MengdeADT<Integer> set = new LenketMengde<Integer>();
		if(arr != null) {
			for (T e : arr) {
				set.add((Integer) e);
			}
		}
		return set;
	}
	
	protected MengdeADT<Integer> makeSet() { return makeSet(new Integer[] {}); }
	protected MengdeADT<Integer> makeSet(Integer a) { return makeSet(new Integer[] {a}); }
	protected MengdeADT<Integer> makeSet(Integer a, Integer b) { return makeSet(new Integer[] {a,b}); }
	protected MengdeADT<Integer> makeSet(Integer a, Integer b, Integer c) { return makeSet(new Integer[] {a,b,c}); }
	protected MengdeADT<Integer> makeSet(Integer a, Integer b, Integer c, Integer d) { return makeSet(new Integer[] {a,b,c,d}); }
	protected MengdeADT<Integer> makeSet(Integer a, Integer b, Integer c, Integer d, Integer e) { return makeSet(new Integer[] {a,b,c,d,e}); }
	protected MengdeADT<Integer> makeSet(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) { return makeSet(new Integer[] {a,b,c,d,e,f}); }
		
	protected Integer[] makeArray() { return new Integer[] {}; }
	protected Integer[] makeArray(Integer a) { return new Integer[] {a}; }
	protected Integer[] makeArray(Integer a, Integer b) { return new Integer[] {a,b}; }
	protected Integer[] makeArray(Integer a, Integer b, Integer c) { return new Integer[] {a,b,c}; }
	protected Integer[] makeArray(Integer a, Integer b, Integer c, Integer d) { return new Integer[] {a,b,c,d}; }
	protected Integer[] makeArray(Integer a, Integer b, Integer c, Integer d, Integer e) { return new Integer[] {a,b,c,d,e}; }
	protected Integer[] makeArray(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f) { return new Integer[] {a,b,c,d,e,f}; }
	
	protected <T> MengdeADT<Integer> makeEmptySet(){
		// older method superseeded by makesET, used early on
		return new LenketMengde<Integer>();
	}
	
	protected Integer[] makeEmptyArray() {
		// older method superseeded by makeArray, used early on
		return makeArray();
	}


	/*
	 * 
	 */

	protected Integer[] sortedCopyArray(Integer[] arr) {
		Integer[] arrCopy = arr.clone();
		Arrays.sort(arrCopy);
		return arrCopy;
	}

	
	/*
	 * 
	 */
	 
	protected void assertSortedArrayEquals(Integer[] expected, Integer[] result) {
		// we could optimize this code, but meh
		Integer[] resultArr = sortedCopyArray(result);
		Integer[] expectedArr = sortedCopyArray(expected);	
		assertArrayEquals(expectedArr, resultArr);
	}	
	
	protected void assertSetEquals(Integer[] expected, MengdeADT<Integer> result) {
		assertSortedArrayEquals(
				expected,
				result.toArray(new Integer[0])
				);
	}
	
	protected void assertSetEquals(MengdeADT<Integer> expected, MengdeADT<Integer> result) {		
		assertSortedArrayEquals(
			expected.toArray(new Integer[0]), 
			result.toArray(new Integer[0])
			);
	}		
	
	

	/**
	 * 
	 */
	
	@Test
	void testIsEmpty() {
		MengdeADT<Integer> set = makeSet();
		
		for(int i=0; i<2; i++) {
			assertTrue(set.isEmpty(), "isEmpty() failed");
			set.add(5);
			assertFalse(set.isEmpty(), "isEmpty() failed");
			set.remove(5);
		}
	}	
	
	@Test 
	public void testDisjoint(){
		{
			// {} disjoint {} = true
			MengdeADT<Integer> set1 = makeEmptySet();
			MengdeADT<Integer> set2 = makeEmptySet();		
			assertTrue(set1.isDisjoint(set2));
		}			
		
		{
			// {...} disjoint {} = true
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeEmptySet();
			assertTrue(set1.isDisjoint(set2));
		}
		
		{
			// {} disjoint {...} = true
			MengdeADT<Integer> set1 = makeEmptySet();
			MengdeADT<Integer> set2 = makeSet(1,2,3);
			assertTrue(set1.isDisjoint(set2));
		}				
		
		{
			// {a} disjoint {b} = true
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(4,5,6);		
			assertTrue(set1.isDisjoint(set2));
		}
				
		{
			// {a,b,c} disjoint {c,d,e} = false
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(3,5,6);		
			assertFalse(set1.isDisjoint(set2));
		}	
	}
	
	@Test
	void testUnion() {
		{
			// {} union {} = {}
			MengdeADT<Integer> set1 = makeEmptySet();
			MengdeADT<Integer> set2 = makeEmptySet();
			assertSetEquals(makeSet(), set1.union(set2)); 
		}	
		
		{
			// {} union {a,b} = {a,b}
			MengdeADT<Integer> set1 = makeEmptySet();
			MengdeADT<Integer> set2 = makeSet(1,2,3);		
			assertSetEquals(makeSet(1,2,3), set1.union(set2));
		}	
				
		
		{
			// {a,b} union {} = {a,b}
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeEmptySet();
			assertSetEquals(makeSet(1,2,3), set1.union(set2));
		}	
		
		{
			// {a,b} union {b,c} = {a,b,c}
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(3,4,5);		
			assertSetEquals(makeSet(1,2,3,4,5), set1.union(set2));
		}	
		
		{
			// {a,b} union {c,d} = {a,b,c,d}
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(4,5,6);					
			assertSetEquals(makeSet(1,2,3,4,5,6), set1.union(set2));
		}	
	}
	
	@Test
	void testIntersection() {
		
		{
			// {} intersect {} = {}
			MengdeADT<Integer> set1 = makeSet();
			MengdeADT<Integer> set2 = makeSet();					
			assertSetEquals(makeSet(), set1.intersection(set2));
		}
		
		{
			// {a,b} intersect {c,d} = {}
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(4,5,6);		
			assertSetEquals(makeSet(), set1.intersection(set2));
		}
		
		
		{
			// {a,b} intersect {a,b} = {a,b}
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(1,2,3);					
			assertSetEquals(makeSet(1,2,3), set1.intersection(set2));
		}
		
		{
			// {a,b,c} intersect {b,c,d} = {b,c}
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(2,3,4);					
			assertSetEquals(makeSet(2,3), set1.intersection(set2));
		}		
		
		{
			// {} intersect {a,b} = {}
			MengdeADT<Integer> set1 = makeSet();
			MengdeADT<Integer> set2 = makeSet(1,2,3);		
			assertSetEquals(makeSet(), set1.intersection(set2));
		}		
	}
	
	@Test
	void testDifference() {
		{ 
            // {} difference {} = {}
            MengdeADT<Integer> set1 = makeSet();
            MengdeADT<Integer> set2 = makeSet();		
            assertSetEquals(makeSet(), set1.difference(set2));
        }
         
        {
            // {a,b} difference {c,d} = {a,b}
            MengdeADT<Integer> set1 = makeSet(1,2,3);
            MengdeADT<Integer> set2 = makeSet(4,5,6);
              assertSetEquals(makeSet(1,2,3), set1.difference(set2));
        }
        
        {
            // {a,b} difference {a,b} = {}
            MengdeADT<Integer> set1 = makeSet(1,2,3);
            MengdeADT<Integer> set2 = makeSet(1,2,3);		
            
            assertSetEquals(makeSet(), set1.difference(set2));
        }
        
        {
            // {a,b,c} difference {b,c,d} = {a}
            MengdeADT<Integer> set1 = makeSet(1,2,3);
            MengdeADT<Integer> set2 = makeSet(2,3,4);		
            assertSetEquals(makeSet(1), set1.difference(set2));
        }		
        
        {
            // {} difference {a,b} = {}
            MengdeADT<Integer> set1 = makeSet();
            MengdeADT<Integer> set2 = makeSet(1,2,3);		            
            assertSetEquals(makeSet(), set1.difference(set2));
        }
	}

	@Test
	void testIsEqual() {
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(1,2,3);
			assertTrue(set1.isEqual(set2));
		}
		
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(1,2,3,4);
			assertFalse(set1.isEqual(set2));
		}		
		
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet();
			assertFalse(set1.isEqual(set2));
		}

		{
			MengdeADT<Integer> set1 = makeSet();
			MengdeADT<Integer> set2 = makeSet();
			assertTrue(set1.isEqual(set2));
		}
		
		{
			MengdeADT<Integer> set1 = makeSet();
			MengdeADT<Integer> set2 = makeSet(1,2,3);
			assertFalse(set1.isEqual(set2));
		}			
	}
	
	@Test
	void testIsSubsetOf() {
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(1,2,3);
			assertTrue(set1.isSubsetOf(set2));
		}
		
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet(1,2,3,4);
			assertTrue(set1.isSubsetOf(set2));
		}		
		
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3,4);
			MengdeADT<Integer> set2 = makeSet(1,2,3);
			assertFalse(set1.isSubsetOf(set2));
		}		
		
		{
			MengdeADT<Integer> set1 = makeSet(1,2,3);
			MengdeADT<Integer> set2 = makeSet();
			assertFalse(set1.isSubsetOf(set2));
		}
		
		{
			MengdeADT<Integer> set1 = makeSet();
			MengdeADT<Integer> set2 = makeSet();
			assertTrue(set1.isSubsetOf(set2));
		}
		
		{
			MengdeADT<Integer> set1 = makeSet();
			MengdeADT<Integer> set2 = makeSet(1,2,3);
			assertTrue(set1.isSubsetOf(set2));
		}			
	}
	
	@Test
	void testContains() {
		{
            MengdeADT<Integer> set = makeSet(1,2,3);
            assertTrue(set.contains(1));
            assertTrue(set.contains(2));
            assertTrue(set.contains(3));
            assertFalse(set.contains(4));
        }
        
        {
            MengdeADT<Integer> set = makeSet();
            assertFalse(set.contains(1));
            assertFalse(set.contains(2));
            assertFalse(set.contains(3));
            assertFalse(set.contains(4));
        }
        
        {
            MengdeADT<Integer> set = makeSet();
            assertFalse(set.contains(null));
            assertFalse(set.contains(0));
        }
        
        {
            MengdeADT<Integer> set = makeSet(1,2,3);
            assertFalse(set.contains(null));
        }
        
		{
            MengdeADT<Integer> set = makeSet();
            for(int i=0; i<2; i++) {
	            set.add(1);
	            assertTrue(set.contains(1));
	            set.remove(1);
	            assertFalse(set.contains(1));
            }
        }        
	}
	
	@Test
	void testImmutability() {
		MengdeADT<Integer> set1 = makeSet(1,2,3);
		MengdeADT<Integer> set2 = makeSet(3,4,5);
		
		set1.union(set2);
		set1.intersection(set2);
		set1.difference(set2);
		set1.isEqual(set2);
		set1.isDisjoint(set2);
		set1.isSubsetOf(set2);
		set1.isEmpty();		
		assertSetEquals(makeSet(1,2,3), set1);
		assertSetEquals(makeSet(3,4,5), set2);
		
		set2.union(set1);
		set2.intersection(set1);
		set2.difference(set1);
		set2.isEqual(set1);
		set2.isDisjoint(set1);
		set2.isSubsetOf(set1);
		set2.isEmpty();		
		assertSetEquals(makeSet(1,2,3), set1);
		assertSetEquals(makeSet(3,4,5), set2);	
	}
	
	@Test
	void testCount() {
		{
			MengdeADT<Integer> set = makeSet();
			assertEquals(set.count(), 0);
		}

		{
			MengdeADT<Integer> set = makeSet(1);
			assertEquals(set.count(), 1);
		}

		{
			MengdeADT<Integer> set = makeSet(1, 2);
			assertEquals(set.count(), 2);
		}

		{
			MengdeADT<Integer> set = makeSet(1, 2, 3);
			assertEquals(set.count(), 3);
		}
		
		{
			MengdeADT<Integer> set = makeSet(1, 2, 3);
			set.addAllFrom(makeSet(1, 2, 3, 4));
			assertEquals(set.count(), 4);
		}	
		
		{
			MengdeADT<Integer> set = makeSet(1, 2, 3);
			set.addAllFrom(makeSet(5));
			assertEquals(set.count(), 4);
		}	
		
		{
			MengdeADT<Integer> set = makeSet(1, 2, 3);
			set.add(5);
			assertEquals(set.count(), 4);
		}				
	}
	
	@Test
	void testAddRemove() {
		{
			// test removing in order and out of order
			// to check test LenkedMengde better
			MengdeADT<Integer> set = makeSet();
			set.add(1);
			set.add(2);
			assertEquals(set.count(), 2);
			set.remove(1);
			set.remove(2);
			assertEquals(set.count(), 0);

			set.add(1);
			set.add(2);
			assertEquals(set.count(), 2);
			set.remove(2);			
			set.remove(1);
			assertEquals(set.count(), 0);
			
		}
	}
	
	@Test
	void testAllFrom() {
		{
			MengdeADT<Integer> set = makeSet(1, 2);
			set.addAllFrom(makeSet(3, 4));
			assertEquals(set.count(), 4);			
			assertSetEquals(makeSet(1,2,3,4), set);
			
			set.addAllFrom(makeSet(3, 4, 5, 6));
			assertEquals(set.count(), 6);			
			assertSetEquals(makeSet(1,2,3,4,5,6), set);
		}			
	}
	
	@Test
	void testThrowNullPointerException() {
		MengdeADT<Integer> set = makeSet(1, 2);
        assertThrows(NullPointerException.class, () -> set.addAllFrom(null));
        assertThrows(NullPointerException.class, () -> set.isDisjoint(null));
        assertThrows(NullPointerException.class, () -> set.isEqual(null));
        assertThrows(NullPointerException.class, () -> set.intersection(null));
        assertThrows(NullPointerException.class, () -> set.union(null));
        assertThrows(NullPointerException.class, () -> set.difference(null));
	}
	
	@Test
	void testMakeSet() {
		{
            MengdeADT<Integer> set = makeSet(1, 2, 3);            
            assertSetEquals(makeSet(1,2,3), set);
        }
        
        {
            MengdeADT<Integer> set = makeSet();            
            assertSetEquals(makeSet(), set);
        }
	}
	
	@Test
	void testMakeArray() {
		{
            Integer[] arr = makeArray(1, 2, 3);
            assertArrayEquals(new Integer[] {1, 2, 3}, arr);
        }
        
        {
            Integer[] arr = makeArray();
            assertArrayEquals(new Integer[] {}, arr);
        }
    }
}