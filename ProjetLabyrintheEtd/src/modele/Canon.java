package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Canon extends Personnages {
	private int x;
	private int y;
	private boolean aEteRencontre;

	// Constructeur de la classe Canon
	public Canon(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteRencontre = false;
	}

	// Méthode pour obtenir le nom du canon
	public String getNom() {
		return "un canon";
	}

	// Méthodes pour obtenir les coordonnées du canon
	public int getX() {
		
		return this.x ;
	}

	public int getY() {
		return this.y ;
	}

	public boolean aEteTrouve() {
		return aEteRencontre;
	}

	public void setAEteTrouve(boolean aEteRencontre) {
		this.aEteRencontre = aEteRencontre;
	}

	// Méthode d'action lors de la rencontre avec le canon
	public void action(Aventurier a) {
		// Vérifie si le canon n'a pas encore été trouvé et si l'aventurier est sur la même case
		//System.out.println("Action du canon");
		if (!aEteRencontre && a.getX() == this.getX() && a.getY() == this.getY()) {
			// Faire reculer l'aventurier d'une case
			//System.out.println("Canon qui explose ! Vous reculez de deux cases");
	        // Afficher un popup avec une image et un titre
            ImageIcon imageCanon = new ImageIcon("data/canon.png"); 
            JLabel message = new JLabel("Un canon qui explose ! Vous reculez de deux cases", imageCanon, JLabel.CENTER);

            JOptionPane.showMessageDialog(null, message, "Ohé moussaillon ! Il se passe quelque chose !", JOptionPane.INFORMATION_MESSAGE);

            // Fait reculer l'aventurier de 2 cases
			a.setX(a.getXPrecedent());
			// System.out.println(a.getX());
			// System.out.println(a.getXPrecedent());
			a.setY(a.getYPrecedent() - 1);
			// System.out.println(a.getY());
			// System.out.println(a.getYPrecedent());
		}
	}
} /*----- Fin de la classe Canon -----*/
