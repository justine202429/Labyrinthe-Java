package modele;

import javax.swing.JOptionPane;

/**
 * Classe qui représente une zone d'espace libre.
 */
public class Reparation extends Objet {
	private int x;
	private int y;
	private boolean aEteTrouve;

	public Reparation(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteTrouve = false;

	}

	public String getNom() {
		return "Kit de reparation";
	}

	public int getX() {
		// Retournez la position X de l'objet (à adapter selon votre implémentation)
		return this.x /* position X */;
	}

	public int getY() {
		// Retournez la position Y de l'objet (à adapter selon votre implémentation)
		return this.y /* position Y */;
	}

	public boolean aEteTrouve() {
		return aEteTrouve;
	}

	public void setAEteTrouve(boolean aEteTrouve) {
		this.aEteTrouve = aEteTrouve;
	}

	/**
	 * Méthode qui définit une action / un changement sur l'aventurier.
	 */
	public void action(Aventurier a) {
		if (!aEteTrouve && a.getX() == this.getX() && a.getY() == this.getY()) {
			a.ajouterObjet(this);
			setAEteTrouve(true);
			System.out.println("L'objet " + this.getNom() + " a été ajouté à votre inventaire.");
			// Message popup
			JOptionPane.showMessageDialog(null, "Wow ! Vous avez trouvé un " + this.getNom() + " !",
					"Nouvel objet obtenu !", JOptionPane.INFORMATION_MESSAGE);

		}
	}
} /*----- Fin de la classe Reparation -----*/
