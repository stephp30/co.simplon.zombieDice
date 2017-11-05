package co.simplon.zombieDice;

import java.util.ArrayList;
import java.util.List;

public class Coupe {

	List<De> dice = new ArrayList<De>();

	Coupe() {

		// création 6 Dé Vert
		for (int i = 0; i < 6; i++) {
			De d = new De(1);
			dice.add(d);
		}
		// création 4 Dé Jaune
		for (int i = 0; i < 4; i++) {
			De d = new De(2);
			dice.add(d);
		}
		// création 6 Dé Rouge
		for (int i = 0; i < 3; i++) {
			De d = new De(3);
			dice.add(d);
		}

	}

	De piocherDe() {

		De d = new De();
		boolean resteDansLaCoupe = false;
		for (int i = 0; i < 13; i++) {

			if (dice.get(i).dansLaCoupe)
				resteDansLaCoupe = true;

		}

		if (!resteDansLaCoupe) {
			for (int i = 0; i <= 13; i++) {
				if (dice.get(i).valeur == 1)
					dice.get(i).dansLaCoupe = false;
			}
		}
		while (true) {
			int index = 1 + (int) (Math.random() * 13) - 1;
			d = dice.get(index);

			if ((d.dansLaCoupe)) {
				dice.get(index).dansLaCoupe = false;
			}
			break;
		}
		return d;
	}

}
