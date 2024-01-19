package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jeu.AppLabyrinthe;

public class BarbeNoire extends Personnages {
	private int x;
	private int y;
	private boolean aEteRencontre;

	public BarbeNoire(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteRencontre = false;
	}

	// Méthode pour obtenir le nom du personnage
	public String getNom() {
		return "Barbe Noire";
	} 

	// Méthodes pour obtenir les coordonnées du personnage
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean getAEteRencontre() {
		return aEteRencontre;
	}

	public void setAEteRencontre(boolean aEteRencontre) {
		this.aEteRencontre = aEteRencontre;
	}

	@Override
	// Méthode d'action lors de la rencontre avec Barbe Noire
	public void action(Aventurier a) {
		if (!aEteRencontre && a.getX() == this.getX() && a.getY() == this.getY()) {
			setAEteRencontre(true);

			// Chargeme des images
			ImageIcon pirateIcone = new ImageIcon("data/fin.gif");
			JLabel label = new JLabel(pirateIcone);

			ImageIcon finIcone = new ImageIcon("data/fin2.gif");
			JLabel labelFin = new JLabel(finIcone);

			String message = "Bravo moussailon !";
			JOptionPane.showMessageDialog(null, label, message, JOptionPane.INFORMATION_MESSAGE);

			// Proposer de rejouer ou d'arrêter
			String choix = JOptionPane.showInputDialog(null, "Que voulez-vous faire ?\n1. Rejouer\n2. Arrêter");

			if (choix != null && choix.equals("1")) {
				// Rejouer = réinitialiser la vue avec le menu de départ
				AppLabyrinthe.menuDepart();

			} else if (choix != null && choix.equals("2")) {
				// Arrêter = écran de fin
				String messageFin = "A la revoyure !";
				JOptionPane.showMessageDialog(null, labelFin, messageFin, JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
	}
}
/*----- Fin de la classe Barbe Noire-----*/