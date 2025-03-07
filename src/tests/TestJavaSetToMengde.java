package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Oppgave4.*;
import adt.MengdeADT;


class TestJavaSetToMengde extends MengdeJUnitTestBase{	
	/*
	 * override makeSet method for our class
	 */	
	
	protected <T> MengdeADT<T> realMakeSet(T[] arr) {
		MengdeADT<T> set = new JavaSetToMengde<T>();
		if(arr != null) {
			for (T e : arr) {
				set.add((T) e);
			}
		}
		return set;
	}
}