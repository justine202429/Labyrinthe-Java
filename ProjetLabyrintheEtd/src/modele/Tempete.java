package modele;

public class Tempete extends Espace {
	private int x;
	private int y;

	public Tempete(int x, int y, Labyrinthe labyrinthe) {
		this.x = x;
		this.y = y;
	}

	public String getNom() {
		return "une tempête";
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void action(Aventurier a) {
		System.out.println("Tu es dans un tempête !");
	}
}/*----- Fin de la classe Tempete-----*/
