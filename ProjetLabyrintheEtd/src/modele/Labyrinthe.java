package modele;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Labyrinthe {
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Taille du labyrinthe -----*/
	private int taille_du_labyrinthe;

	/*----- Liste des cases composants le labyrinthe -----*/
	private Case[][] cases;

	/*----- Aventurier qui doit traverser le labyrinthe -----*/
	private Aventurier aventurier;
	private int arriveeX = -1;
	private int arriveeY = -1;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Labyrinthe(int taille) {
		this.taille_du_labyrinthe = taille;
		this.cases = new Case[this.taille_du_labyrinthe][this.taille_du_labyrinthe];
	}

	/*----------*/
	/* Méthodes */
	/*----------*/

	public int getTaille() {
		return this.taille_du_labyrinthe;
	}

	public void setCase(int i, int j, Case c) {
		this.cases[i][j] = c;
	}

	public Case getCase(int i, int j) {
		return this.cases[i][j];
	}

	public Aventurier getAventurier() {
		return this.aventurier;
	}

	public void setAventurier(Aventurier aventurier) {
		this.aventurier = aventurier;
	}

	public int getArriveeX() {
		return arriveeX;
	}

	public int getArriveeY() {
		return arriveeY;
	}

	public void setArriveeCoordonnees(int x, int y) {
		arriveeX = x;
		arriveeY = y;
	}

	public void actionObjet() {
		int posX = this.aventurier.getX();
		int posY = this.aventurier.getY();
		// System.out.println("Coordonées de la case : " + posX + ", " + posY);

		// Déclenche l'action de la case
		// this.cases[posX][posY].action(this.aventurier);

		// Vérifie si la case où est l'aventurier contient un objet
		if (!this.cases[posX][posY].getObjets().isEmpty()) {
			ArrayList<Objet> objets = (ArrayList<Objet>) this.cases[posX][posY].getObjets();

			// Aventutier ramasse ou non l'objet
			for (int i = 0; i < objets.size(); i++) {
				Objet o = objets.get(i);

				// Affichage de l'image en fonction du nom de l'objet
				ImageIcon imageObjet = null;

				if ("Canon".equals(o.getNom())) {
					imageObjet = new ImageIcon("data/canon.png");
				} else if ("Coffre".equals(o.getNom())) {
					imageObjet = new ImageIcon("data/coffre.png");
				} else if ("Cire".equals(o.getNom())) {
					imageObjet = new ImageIcon("data/cire.png");
				} else if ("Moteur".equals(o.getNom())) {
					imageObjet = new ImageIcon("data/moteur.png");
				} else if ("Kit de reparation".equals(o.getNom())) {
					imageObjet = new ImageIcon("data/kit.png");
				} else if ("Trident".equals(o.getNom())) {
					imageObjet = new ImageIcon("data/trident.png");
				}

				JLabel message = new JLabel("L'objet " + o.getNom() + " a été ajouté à votre inventaire.", imageObjet,
						JLabel.CENTER);
				int choix = JOptionPane.showConfirmDialog(message, "Voulez-vous ramasser l'objet " + o.getNom() + "?",
						"Choix", JOptionPane.YES_NO_OPTION);

				// On ramasse = ajout à l'inventaire et on l'efface de la case.
				if (choix == JOptionPane.YES_OPTION) {
					this.aventurier.ajouterObjet(o);

					// Effacer les objets de la case
					this.cases[posX][posY].getObjets().clear();
					JOptionPane.showMessageDialog(null, message, "Vous avez ramassé un objet !",
							JOptionPane.INFORMATION_MESSAGE);
					// System.out.println("L'objet " + o.getNom() + " a été ajouté à votre
					// inventaire.");

					// On laisse = message mais rien ne se passe
				} else if (choix == JOptionPane.NO_OPTION) {
					// Si non, afficher un message
					JOptionPane.showMessageDialog(null, "Vous ne ramassez pas l'objet", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	public void actionPersonnages() {
		int posX = this.aventurier.getX();
		int posY = this.aventurier.getY();

		// Déclenche l'action de la case
		this.cases[posX][posY].action(this.aventurier);

		// Vérifie si la case où est l'aventurier contient un personnage
		if (!this.cases[posX][posY].getPersonnages().isEmpty()) {
			ArrayList<Personnages> personnages = (ArrayList<Personnages>) this.cases[posX][posY].getPersonnages();

			// Aventurier interagit avec le ou les personnages
			for (Personnages perso : personnages) {
				// System.out.println("Le personnage " + perso.getNom() + " apparaît");
				perso.action(this.aventurier);
			}

			// Efface le personnage de la case après l'avoir interagi
			this.cases[posX][posY].getPersonnages().clear();
		} else {
			// Pas de personnages dans la case
		}
	}

	public List<int[]> trouverCheminLePlusCourt() {
		int departX = aventurier.getX();
		int departY = aventurier.getY();

		// Récupérer les coordonnées de la case d'arrivée
		int arriveeX = getArriveeX();
		int arriveeY = getArriveeY();

		List<int[]> cheminLePlusCourt = new ArrayList<>();

		System.out.println("Coordonnées de départ : (" + departX + ", " + departY + ")");
		System.out.println("Coordonnées d'arrivée (Barbe Noire) : (" + arriveeX + ", " + arriveeY + ")");

		if (arriveeX == -1 || arriveeY == -1) {
			System.out.println("Les coordonnées de Barbe Noire n'ont pas été trouvées.");
			return cheminLePlusCourt;
		}

		if (cases[departX][departY] instanceof Rocher || cases[arriveeX][arriveeY] instanceof Rocher) {
			System.out.println("Les rochers sont infranchissables.");
			return cheminLePlusCourt;
		}

		int[][] distances = new int[taille_du_labyrinthe][taille_du_labyrinthe];
		boolean[][] visite = new boolean[taille_du_labyrinthe][taille_du_labyrinthe];

		Queue<int[]> file = new ArrayDeque<>();
		file.add(new int[] { departX, departY });
		visite[departX][departY] = true;
		distances[departX][departY] = 0;

		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Déplacements vers le haut, le bas, la
																			// gauche et la droite

		while (!file.isEmpty()) {
			int[] positionActuelle = file.poll();

			for (int[] direction : directions) {
				int nouvelX = positionActuelle[0] + direction[0];
				int nouvelY = positionActuelle[1] + direction[1];

				if (nouvelX >= 0 && nouvelX < taille_du_labyrinthe && nouvelY >= 0 && nouvelY < taille_du_labyrinthe
						&& !visite[nouvelX][nouvelY] && !(cases[nouvelX][nouvelY] instanceof Rocher)) {
					file.add(new int[] { nouvelX, nouvelY });
					visite[nouvelX][nouvelY] = true;
					distances[nouvelX][nouvelY] = distances[positionActuelle[0]][positionActuelle[1]] + 1;
				}
			}
		}

		if (!visite[arriveeX][arriveeY]) {
			System.out.println("Aucun chemin trouvé vers Barbe Noire.");
			return cheminLePlusCourt;
		}

		// Ajouter les coordonnées du chemin le plus court à la liste
		int x = arriveeX;
		int y = arriveeY;

		System.out.println("Chemin le plus court vers Barbe Noire :");

		while (x != departX || y != departY) {
			cheminLePlusCourt.add(0, new int[] { y, x });
			int distanceActuelle = distances[x][y];
			for (int[] direction : directions) {
				int nouvelX = x + direction[0];
				int nouvelY = y + direction[1];

				if (nouvelX >= 0 && nouvelX < taille_du_labyrinthe && nouvelY >= 0 && nouvelY < taille_du_labyrinthe
						&& distances[nouvelX][nouvelY] == distanceActuelle - 1) {
					x = nouvelX;
					y = nouvelY;
					break;
				}
			}
		}
		cheminLePlusCourt.remove(cheminLePlusCourt.size() - 1);
		// Changer le type de case pour chaque coordonnée du chemin
		int tailleChemin = cheminLePlusCourt.size();
		for (int i = tailleChemin - 1; i >= 0; i--) {
			int[] coordonnees = cheminLePlusCourt.get(i);
			cases[coordonnees[0]][coordonnees[1]].changerTypeCase(this, coordonnees[0], coordonnees[1], 4);
		}
		return cheminLePlusCourt;
	}
}
/*----- Fin de la classe Labyrinthe -----*/
