package modele;

import java.util.ArrayList;
import java.util.List;

public abstract class Case {
	// Liste des objets présents sur la case
	private List<Objet> objets = new ArrayList<>();
	// Liste des personnages présents sur la case
	private List<Personnages> personnages = new ArrayList<>();

	public final String getClassName() {
		return this.getClass().getSimpleName();
	}

	public List<Objet> getObjets() {
		return objets;
	}

	public List<Personnages> getPersonnages() {
		return personnages;
	}

	public abstract void action(Aventurier a);

	// On ajoute l'objet au labyrinthe
	public void ajouterObjet(Objet objet) {
		objets.add(objet);
		// System.out.println("Il y a cet objet dans le labyrinthe : " +
		// objet.getNom());
	}

	public void ajouterPersonnage(Personnages perso) {
		personnages.add(perso);
		// System.out.println("Il y a ce personnage dans le labyrinthe : " +
		// perso.getNom());
	}

	public void actionObjets(Aventurier aventurier) {
		for (Objet objet : objets) {
			objet.action(aventurier);
		}
		objets.clear(); // Efface les objets après les avoir utilisés
	}

	public void changerTypeCase(Labyrinthe labyrinthe, int posX, int posY, int type) {
		if (type == 0) {
			labyrinthe.setCase(posY, posX, new Ocean());
		} else if (type == 1) {
			labyrinthe.setCase(posY, posX, new Rocher());
		} else if (type == 2) {
			labyrinthe.setCase(posY, posX, new Ile());
		} else if (type == 3) {
			labyrinthe.setCase(posY, posX, new Courant());
		} else if (type == 4) {
			 labyrinthe.setCase(posY, posX, new Chemin());
		} else {
			labyrinthe.setCase(posY, posX, labyrinthe.getCase(posY, posX));
		}
	}
} /*----- Fin de la classe Case -----*/
