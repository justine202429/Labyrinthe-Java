package modele;

public class Coffre extends Objet {
	private int x;
	private int y;
	private boolean aEteTrouve;

	public Coffre(int x, int y) {
		this.x = x;
		this.y = y;
		this.aEteTrouve = false;
	}

	public String getNom() {
		return "Coffre";
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
		}
	}
} /*----- Fin de la classe Coffre -----*/