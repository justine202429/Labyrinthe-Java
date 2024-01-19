package modele;

import java.util.List;

public abstract class Espace extends Case {

	public void action(Aventurier a) {
		System.out.println("Tu es dans un espace !");
		List<Objet> objets = getObjets();

		if (!objets.isEmpty()) {
			for (Objet objet : objets) {
				//System.out.println("Tu as trouvé un(e) " + objet.getNom() + " !");
			}
		}
	}

} /*----- Fin de la classe Espace -----*/
