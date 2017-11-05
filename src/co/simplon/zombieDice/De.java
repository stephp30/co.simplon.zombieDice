package co.simplon.zombieDice;

public class De {

	int couleur; 
	int valeur; 
	boolean dansLaCoupe;

	// 1= cerveau; 2= pas; 3 = bang
	final int[] deVert = { 1, 1, 1, 2, 2, 3 };
	final int[] deJaune = { 1, 1, 2, 2, 3, 3 };
	final int[] deRouge = { 1, 2, 2, 3, 3, 3 };

	De() {
	}

	De(int c) {
		dansLaCoupe = true;
		couleur = c;
	}

	String getCouleur() {
		switch (couleur) {
		case 1:
			return "Vert";
		case 2:
			return "Jaune";
		case 3:
			return "Rouge";
		default:
			return "";
		}

	}

	String getTirageValeur() {
		switch (valeur) {
		case 1:
			return "Cerveau";
		case 2:
			return "Pas";
		case 3:
			return "Bang";
		default:
			return "";
		}

	}

	int tirageDe() {
		switch (couleur) {
		case 1:
			valeur = deVert[1 + (int) (Math.random() * 6) - 1];
			break;
		case 2:
			valeur = deJaune[1 + (int) (Math.random() * 6) - 1];
			break;
		case 3:
			valeur = deRouge[1 + (int) (Math.random() * 6) - 1];
			break;
		default:
			break;
		}
		return valeur;
	}

	boolean isCerveaux() {
		if (valeur == 1) {
			return true;
		}
		return false;

	}

	boolean isPas() {
		if (valeur == 2) {
			return true;
		}
		return false;
	}

	boolean isBang() {
		if (valeur == 3) {
			return true;
		}
		return false;
	}

}
