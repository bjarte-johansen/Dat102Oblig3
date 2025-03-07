package Oppgave4;

import adt.MengdeADT;
import Oppgave4.*;

class Person{
	String name;
	MengdeADT<String> hobbies;
	
	public Person(String name, String ... hobbies) {
		this.name = name;
		this.hobbies = TabellMengde.createFromArray(hobbies);
	}
}

class HobbyMatchMain{
	public static <T> double match(Person a, Person b) {
		var hA = a.hobbies;
		var hB = b.hobbies;
		
		int antallFelles = hA.intersection(hB).count();
		int antallKunHosDenEne = hA.difference(hB).count();
		int antallKunHosDenAndre = hB.difference(hA).count();
		int antallTotalt = hA.union(hB).count();
		
		if (antallTotalt == 0) {
			return 0;
		}
		
		return antallFelles - ((antallKunHosDenEne + antallKunHosDenAndre) / (double) antallTotalt);
	}
}

public class Oppgave4f {	
	/*
	 * @param i, index of person to match with others
	 */	
	public static void showScores(int i, Person personer[]) {
		System.out.printf("%s (hobbier(%d): %s)%n", personer[i].name, personer[i].hobbies.count(), personer[i].hobbies.toString());
		
		for(int j=0; j<personer.length; j++) {
			System.out.printf("\t%s vs %s: %s%n", personer[i].name, personer[j].name, HobbyMatchMain.match(personer[i], personer[j]));
		}
		
		System.out.println();
	}

	public static void main(String[] args) {
		// vi valgte 5 personer for å teste bedre
		Person personer[] = new Person[5];
		personer[0] = new Person("Leif1", "jakt", "dym", "data", "turer", "film");
		personer[1] = new Person("Kari2", "fisking", "data", "turer", "bil");
		personer[2] = new Person("Bjørn3", "film", "fisking", "data", "gym", "bil");
		personer[3] = new Person("Ole4", "film", "data");
		personer[4] = new Person("Knut5");
			
		// vis score for hver person mot alle andre
		for (int i=0; i<personer.length; i++) {
			showScores(i, personer);
		}

		
		// uni-direction-check
		var scoreFrom = HobbyMatchMain.match(personer[0], personer[1]);
		var scoreTo = HobbyMatchMain.match(personer[1], personer[0]);
				
		System.out.printf("%s vs %s: %s%n", personer[0].name, personer[1].name, HobbyMatchMain.match(personer[0], personer[1]));
		System.out.printf("%s vs %s: %s%n", personer[1].name, personer[0].name, HobbyMatchMain.match(personer[1], personer[0]));
		System.out.printf("score difference: %8.4f%n", scoreFrom - scoreTo);
		
		// self-check
		var selfScore = HobbyMatchMain.match(personer[0], personer[0]);		
		System.out.printf("self score: %8.4f%n", selfScore);		
	}

}
