package modele;

public class Ocean extends Espace {

	public void action(Aventurier a) {
		System.out.println("Tu es dans un océan !");
		
		a.setPv(a.getPv() - 1);
		
		System.out.println("Besoin de cire : " + a.aBesoinCire());
        System.out.println("Rencontre sirène : " + a.aRencontreSirene());
		if (a.aBesoinCire() && a.aRencontreSirene()) {
			a.setTps(a.getTps() - 2);
		} else {
			a.setTps(a.getTps() - 1);
		}

		System.out.println("TPS : " + a.getTps());
		System.out.println("PV : " + a.getPv());
	}
} /*----- Fin de la classe Ocean -----*/
