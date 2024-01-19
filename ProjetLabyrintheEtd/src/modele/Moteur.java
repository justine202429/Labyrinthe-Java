package modele;

import javax.swing.JOptionPane;

public class Moteur extends Objet {
	private int x;
	private int y;
	private boolean aEteTrouve;

	public Moteur(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteTrouve = false;

	}

	public String getNom() {
		return "Moteur";
	}

	public int getX() {
		return this.x ;
	}

	public int getY() {
		return this.y ;
	}

	public boolean aEteTrouve() {
		return aEteTrouve;
	}

	public void setAEteTrouve(boolean aEteTrouve) {
		this.aEteTrouve = aEteTrouve;
	}

	public void action(Aventurier a) {
		if (!aEteTrouve && a.getX() == this.getX() && a.getY() == this.getY()) {
			a.ajouterObjet(this);
			setAEteTrouve(true);
			// System.out.println("L'objet "+this.getNom()+ " a été ajouté à votre
			// inventaire.");
			// Message popup
			JOptionPane.showMessageDialog(null, "Wow ! Vous avez trouvé un " + this.getNom() + " !",
					"Nouvel objet obtenu !", JOptionPane.INFORMATION_MESSAGE);
		}
	}

} /*----- Fin de la classe Moteur -----*/