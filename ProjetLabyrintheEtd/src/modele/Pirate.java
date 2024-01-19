package modele;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Pirate extends Personnages {
	private int x;
	private int y;
	private boolean aEteRencontre;

	public Pirate(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteRencontre = false;
	}

	public String getNom() {
		return "un pirate";
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
			a.setPv(a.getPv() - 10);

			if (!a.possedeObjet("Kit de reparation")) {
				a.setABesoinReparation(true);
				
				// Chargement de l'image du pirate + texte
				ImageIcon pirateIcone = new ImageIcon("data/pirateP.gif");
				JLabel label = new JLabel(pirateIcone);

				String message = "Ohé moussailon ! Il se passe quelque chose !";
				String message2 = "Il te faut un kit de réparation pour arrêter de perdre des points de vie !";

				JOptionPane.showMessageDialog(null, label, message, JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, message2, "Arf..la mer est énervée aujourd'hui...", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
} /*----- Fin de la classe Pirate -----*/