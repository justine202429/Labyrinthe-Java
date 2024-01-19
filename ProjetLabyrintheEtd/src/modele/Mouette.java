package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Mouette extends Personnages {
	private int x;
	private int y;
	private boolean aEteRencontre;

	public Mouette(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteRencontre = false;
	}

	public String getNom() {
		return "une mouette";
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean aEteRencontre() {
		return aEteRencontre;
	}

	public void setAEteRencontre(boolean aEteRencontre) {
		this.aEteRencontre = aEteRencontre;
	}

	public void action(Aventurier a) {
		if (!aEteRencontre && a.getX() == this.getX() && a.getY() == this.getY()) {
			setAEteRencontre(true);
			// Chargement de l'image du pirate + texte
			ImageIcon mouetteIcone = new ImageIcon("data/mouette.gif");
			JLabel label = new JLabel(mouetteIcone);

			String message = "Ohé moussailon ! Il se passe quelque chose !";
			String message2 = "Une mouette vient de te voler un objet !";

			JOptionPane.showMessageDialog(null, label, message, JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, message2, "Arf..la mer est énervée aujourd'hui...", JOptionPane.INFORMATION_MESSAGE);
			a.retirerObjetAuHasard(); // Retirer un objet au hasard de l'inventaire
		}
	}
} /*----- Fin de la classe Mouette-----*/