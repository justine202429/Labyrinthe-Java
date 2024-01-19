package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Aventurier {
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Sa position dans le labyrinthe -----*/
	private int x;
	private int y;
	private int tps;
	private int pv;
	private List<Objet> inventaire = new ArrayList<>();
	private boolean aBesoinReparation = false;
	private boolean aBesoinCire = true;
	private boolean aRencontreSirene = false;
	private int xPrecedent;
	private int yPrecedent;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Aventurier(int x, int y, int tps, int pv, List<Objet> inventaire) {
		this.x = x;
		this.y = y;
		this.tps = tps;
		this.pv = pv;
		this.inventaire = inventaire;
	}

	/*----------*/
	/* Méthodes */
	/*----------*/

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTps() {
		return this.tps;
	}

	public void setTps(int tps) {
		this.tps = tps;
	}

	public int getPv() {
		return this.pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public List<Objet> getInventaire() {
		return this.inventaire;
	}

	public void setInventaire(List<Objet> inventaire) {
		this.inventaire = inventaire;
	}
	
	public void setCoordonneesPrecedentes() {
	    xPrecedent = getX();
	    yPrecedent = getY();
	}
	
	public int getXPrecedent() {
		return this.xPrecedent ;
	}
	
	public int getYPrecedent() {
		return this.yPrecedent ;
	}
	
	public void setXPrecedent(int xPrecedent) {
	    this.xPrecedent = xPrecedent;
	}

	public void setYPrecedent(int yPrecedent) {
	    this.yPrecedent = yPrecedent;
	}
	
	public void setABesoinCire(boolean aBesoinCire) {
		this.aBesoinCire = aBesoinCire;
	}

	public void setARencontreSirene(boolean aRencontreSirene) {
		this.aRencontreSirene = aRencontreSirene;
	}
	
	public void setABesoinReparation(boolean aBesoinReparation) {
		this.aBesoinReparation = aBesoinReparation;
	}
	
	public static List<Objet> initialiserInventaire() {
		// Initialise un inventaire vide
		List<Objet> inventaireInitial = new ArrayList<>();
		return inventaireInitial;
	}

	// Ajoute l'objet à l'inventaire (affichage egalement)
	public void ajouterObjet(Objet o) {
		inventaire.add(o);
		System.out.println(
				"Wow ! Vous avez trouvé l'objet suivant : " + o.getNom() + "\nIl a été ajouté à votre inventaire.");

		System.out.println("Voici l'inventaire :");
		for (int i = 0; i < inventaire.size(); i++) {
			Objet objet = inventaire.get(i);
			System.out.println(objet.getNom());
			// System.out.println("L'objet "+o.getNom()+ " permet de (à faire)");
		}
		if (o instanceof Cire) {
			aBesoinCire = false;
			setTps(getTps() / 2);
		}
	}

	public boolean possedeObjet(String nomObjet) {
		// Vérifie si l'inventaire contient un objet avec le nom spécifié
		for (int i = 0; i < inventaire.size(); i++) {
			Objet objet = inventaire.get(i);
			if (objet.getNom().equals(nomObjet)) {
				return true;
			}
		}
		return false;
	}

	// Retire un objet au hasard de l'inventaire 
	public void retirerObjetAuHasard() {
		if (!inventaire.isEmpty()) {
			Random random = new Random();
			int indexObjet = random.nextInt(inventaire.size());
			Objet objetRetire = inventaire.remove(indexObjet);
			ImageIcon mouetteIcone = new ImageIcon("data/mouette.gif");
			JLabel label = new JLabel(mouetteIcone);

			String message = "Oh non ! La mouette a pris l'objet " + objetRetire.getNom() +" de votre inventaire.";
			JOptionPane.showMessageDialog(label, message, "Oh hé du matelot ! Il se passe quelque chose !", JOptionPane.INFORMATION_MESSAGE);
		} else {
			System.out.println("Votre inventaire est vide, la mouette ne peut rien prendre.");
		}
	}

	// Supprime l'objet "Trident" de l'inventaire
	public void retirerTrident() {
		for (int i = 0; i < inventaire.size(); i++) {
			Objet objet = inventaire.get(i);
			if (objet.getNom().equals("Trident")) {
				inventaire.remove(i);
			}
		}
		System.out.println("Le Trident a été utilisé...");
	}

	public boolean aBesoinCire() {
		return aBesoinCire;
	}

	public boolean aRencontreSirene() {
		return aRencontreSirene;
	}

	public boolean aBesoinReparation() {
		return aBesoinReparation;
	}
} /*----- Fin de la classe Aventurier -----*/
