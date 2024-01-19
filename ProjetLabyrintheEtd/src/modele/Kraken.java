package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Kraken extends Personnages {
	private int x;
	private int y;
	private boolean aEteRencontre;

	public Kraken(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteRencontre = false;
	}

	public String getNom() {
		return "le Kraken";
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
			ImageIcon krakenIcone = new ImageIcon("data/kraken.gif");
			JLabel label = new JLabel(krakenIcone);

			String message = "Ohé moussailon ! Il se passe quelque chose !";
			String message2 = "Il te faut un kit de réparation pour arrêter de perdre des points de vie !";

			JOptionPane.showMessageDialog(null, label, message, JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, message2, "La mer nous offre ses cadeaux !", JOptionPane.INFORMATION_MESSAGE);
	        
			// Augmenter les PV de l'aventurier de 10
	        int nouveauxPV = a.getPv() + 10;
	        a.setPv(nouveauxPV);
	    }
	}
} /*----- Fin de la classe Kraken-----*/