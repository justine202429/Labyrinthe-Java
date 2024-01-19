package modele;

import javax.swing.JOptionPane;

public class Ile extends Espace {

	/**
	 * Méthode qui définit une action / un changement sur l'aventurier.
	 */
	public void action(Aventurier a) {
		System.out.println("Tu es sur une île !");

		// Vérifier si l'aventurier a un "coffre" dans son inventaire
		boolean possedeCoffre = false;
		for (int i = 0; i < a.getInventaire().size() && !possedeCoffre; i++) {
			Objet o = a.getInventaire().get(i);

			if (o.getNom().equals("Coffre")) {
				possedeCoffre = true;
				// Choix d'utiliser ou non le coffre
				int choixCoffre = JOptionPane.showConfirmDialog(null, "Tu as un coffre ! Veux-tu l'utiliser ?",
						"Ohé du matelot, une île à l'horizon !", JOptionPane.YES_NO_OPTION);

				if (choixCoffre == JOptionPane.YES_OPTION) {
					// OUI = on enlève le coffre de l'inventaire + effet
					JOptionPane.showMessageDialog(null,
							"Tu utilise ton coffre ! \n\nLes habitants de l'île te laisse traverser par le fleuve. \nTu perds ton coffre mais tu es plus rapide !",
							null, JOptionPane.INFORMATION_MESSAGE);

					a.getInventaire().remove(i);
					a.setTps(a.getTps() - 1);
					a.setPv(a.getPv() - 1);

				} else if (choixCoffre == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null,
							"Vous avez choisi de ne pas utiliser le coffre. Il reste dans votre inventaire.", null,
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		}
		// Déduction de points de vie et de temps si l'aventurier ne possède pas de
		// coffre
		if (!possedeCoffre) {
			if (a.aBesoinCire() && a.aRencontreSirene()) {
				a.setTps(a.getTps() - 4);
				a.setPv(a.getPv() - 3);
			} else {
				a.setTps(a.getTps() - 2);
				a.setPv(a.getPv() - 2);
			}
		}
		System.out.println("TPS : " + a.getTps());
		System.out.println("PV : " + a.getPv());
	}
} /*----- Fin de la classe Ile -----*/
