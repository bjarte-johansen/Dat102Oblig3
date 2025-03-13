package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Uke10Oppgave4.*;
import adt.MengdeADT;


class TestLenketMengde extends MengdeJUnitTestBase{	
	/*
	 * override makeSet method for our class
	 */	
	
	protected <T> MengdeADT<T> realMakeSet(T[] arr) {
		MengdeADT<T> set = new LenketMengde<T>();
		if(arr != null) {
			for (T e : arr) {
				set.add((T) e);
			}
		}
		return set;
	}
}