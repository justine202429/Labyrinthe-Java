package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jeu.AppLabyrinthe;
import modele.Aventurier;
import modele.Labyrinthe;
import modele.Objet;
import modele.Rocher;
import modele.Tempete;

/**
 * Fenêtre de visualisation du labyrinthe.
 */
public class Vue extends JFrame {
	/*------------*/
	/* Propriétés */
	/*------------*/

	/**
	 * Référence vers le labyrinthe que la classe Vue va visualiser.
	 */
	private final Labyrinthe labyrinthe;

	/*----- Barre d'état de la fenêtre -----*/
	private final JLabel barre_etat;

	/*----- Zone de dessin -----*/
	private final Dessin dessin;

	private boolean menuOuvert = false;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Vue(int x, int y, Labyrinthe labyrinthe) {
		/*----- Lien avec le labyrinthe -----*/
		this.labyrinthe = labyrinthe;

		/*----- Paramètres de la fenêtre -----*/
		this.setTitle("Labyrinthe");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());

		/*----- Zone de dessin -----*/
		this.dessin = new Dessin(600);
		this.dessin.setFocusable(true);

		/*----- Attachement des écouteurs des évènements souris et clavier -----*/
		this.dessin.addMouseListener(this.dessin);
		this.dessin.addMouseMotionListener(this.dessin);
		this.dessin.addKeyListener(this.dessin);
		this.add(this.dessin, BorderLayout.CENTER);

		/*----- Barre d'état de la fenêtre -----*/
		this.barre_etat = new JLabel("Barre d'état");
		this.add(this.barre_etat, BorderLayout.SOUTH);

		/*----- Pour ajuster la fenêtre à son contenu et la rendre visible -----*/
		this.pack();
		this.setVisible(true);
	}

	/*----------------*/
	/* Classe interne */
	/*----------------*/

	class Dessin extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
		/*----- Propriétés de la classe interne -----*/
		int largeur;
		int taille_case;
		boolean attenteClic = false;
		boolean attenteChoix = false;

		/*----- Constructeur de la classe interne -----*/
		public Dessin(int larg) {
			/*----- Initialisation des données -----*/
			this.taille_case = larg / labyrinthe.getTaille();
			this.largeur = this.taille_case * labyrinthe.getTaille();

			/*----- Paramètre du JPanel -----*/
			this.setPreferredSize(new Dimension(this.largeur, this.largeur));
		}

		/**
		 * Méthode qui permet de dessiner ou redessinner le labyrinthe lorsque la
		 * méthode repaint() est appelée sur la classe Dessin.
		 */
		@Override
		public void paint(Graphics g) {
			/*----- On efface le dessin en entier -----*/
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.largeur, this.largeur);

			ImageIcon rocherIcone = new ImageIcon("data/rocher.png");
			Image rocherImg = rocherIcone.getImage();

			ImageIcon oceanIcone = new ImageIcon("data/ocean.png");
			Image oceanImg = oceanIcone.getImage();

			ImageIcon ileIcone = new ImageIcon("data/ile.png");
			Image ileImg = ileIcone.getImage();

			ImageIcon courantIcone = new ImageIcon("data/courant.png");
			Image courantImg = courantIcone.getImage();

			ImageIcon tempeteIcone = new ImageIcon("data/tempete.png");
			Image tempeteImg = tempeteIcone.getImage();

			ImageIcon cheminIcone = new ImageIcon("data/chemin.png");
			Image cheminImg = cheminIcone.getImage();

			// Choix image aventurier selon menu
			int bateauSelectionne = AppLabyrinthe.getBateau();
			Image pirateImg = null;
			if (bateauSelectionne == 1) {
				pirateImg = new ImageIcon("data/pirate.png").getImage();
			} else if (bateauSelectionne == 2) {
				pirateImg = new ImageIcon("data/sousmarin.png").getImage();
			} else if (bateauSelectionne == 3) {
				pirateImg = new ImageIcon("data/requin.png").getImage();
			} else {
				pirateImg = new ImageIcon("data/pirate.png").getImage();
			}

			/*----- Affichage des cases du labyrinthe -----*/
			for (int i = 0; i < labyrinthe.getTaille(); i++) {
				for (int j = 0; j < labyrinthe.getTaille(); j++) {
					/*----- Couleur de la case -----*/
					if (labyrinthe.getCase(i, j).getClassName().equals("Rocher")) {
						g.drawImage(rocherImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					} else if (labyrinthe.getCase(i, j).getClassName().equals("Ocean")) {
						g.drawImage(oceanImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					} else if (labyrinthe.getCase(i, j).getClassName().equals("Ile")) {
						g.drawImage(ileImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					} else if (labyrinthe.getCase(i, j).getClassName().equals("Courant")) {
						g.drawImage(courantImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					} else if (labyrinthe.getCase(i, j).getClassName().equals("Tempete")) {
						g.drawImage(tempeteImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					} else if (labyrinthe.getCase(i, j).getClassName().equals("Chemin")) {
						g.drawImage(cheminImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					} else {
						g.drawImage(oceanImg, j * taille_case, i * taille_case, taille_case, taille_case, null);
					}
				}
			}
			/*----- Affichage de l'aventurier -----*/
			Aventurier aventurier = labyrinthe.getAventurier();
			g.drawImage(pirateImg, taille_case * aventurier.getY() + taille_case / 4,
					taille_case * aventurier.getX() + taille_case / 4, taille_case / 2, taille_case / 2, null);
		}

		private void afficherInventaire() {
			System.out.println("Voici l'inventaire :");
			List<Objet> inventaire = labyrinthe.getAventurier().getInventaire();

			if (inventaire.isEmpty()) {
				System.out.println("L'inventaire est vide.");
			} else {
				for (Objet objet : inventaire) {
					System.out.println(objet.getNom());
				}
			}
		}

		/**
		 * Gestion des interactions souris et clavier sur le labyrinthe.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			/*----- Lecture de la position de la souris lors du clic sur l'objet Dessin -----*/
			int x = e.getX();
			int y = e.getY();

			/*----- Recherche des coordonnées de la case du labyrinthe sur laquelle le joueur a cliqué -----*/
			int ligne = y / this.taille_case;
			int colonne = x / this.taille_case;

			/*----- On regarde si l'aventurier est sur la case sur laquelle on vient de cliquer -----*/
			String msgAventurier = "";
			if (labyrinthe.getAventurier().getX() == ligne && labyrinthe.getAventurier().getY() == colonne)
				msgAventurier = "\nL'aventurier est sur cette case.";

			/*----- Etat de la case -----*/
			JOptionPane.showMessageDialog(this, "Vous avez cliqué sur la case (" + ligne + "," + colonne
					+ ").\nC'est un " + labyrinthe.getCase(ligne, colonne).getClassName() + "." + msgAventurier);

			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (attenteClic) {
				int x = e.getX() / taille_case;
				int y = e.getY() / taille_case;

				String message = "En quel type voulez-vous le changer ? \n0 = Ocean \n1 = Rocher \n2 = Ile \n3 = Courant";
				String typeStr = JOptionPane.showInputDialog(this, message);

				int type = Integer.parseInt(typeStr);
				labyrinthe.getCase(x, y).changerTypeCase(labyrinthe, x, y, type);
				repaint();

				attenteClic = false;
				// A completer (ajout personnage)
			}
		}

		private void deplacerAventurierAleatoirement() {
			System.out.println("Coordonnées actuelles : (" + labyrinthe.getAventurier().getX() + ", "
					+ labyrinthe.getAventurier().getY() + ")");

			int nvX, nvY;
			do {
				// Générer de nouvelles coordonnées aléatoires
				nvX = (int) (Math.random() * labyrinthe.getTaille());
				System.out.println("nvX" + nvX);
				nvY = (int) (Math.random() * labyrinthe.getTaille());
				System.out.println("nvY" + nvY);
			} while (labyrinthe.getCase(nvX, nvY) instanceof Rocher || labyrinthe.getCase(nvX, nvY) instanceof Tempete);

			// Déplacer l'aventurier vers les nouvelles coordonnées
			labyrinthe.getAventurier().setX(nvX);
			labyrinthe.getAventurier().setY(nvY);
			System.out.println("Nouvelles coordonnées : (" + labyrinthe.getAventurier().getX() + ", "
					+ labyrinthe.getAventurier().getY() + ")");
			JOptionPane.showMessageDialog(this, "Vous avez été mystérieusement déplacé vers une nouvelle position.");
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			barre_etat.setText("Appuyez sur H pour ouvrir le menu d'aide");
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {

			// On ouvre le menu Aide
			if (e.getKeyChar() == 'H' || e.getKeyChar() == 'h') {
				// Code pour le menu principal
				String messageMenu = "Que voulez-vous faire ? \n\nA = Modifier la carte ; \nB = Le chemin le plus court ; \nC = Approchez le bateau étrange... \n\nAppuyez sur OK et faites votre choix !";
				JOptionPane.showMessageDialog(this, messageMenu,
						"Tous les loups de mer ont besoin d'un coup de pouce de temps en temps.",
						JOptionPane.INFORMATION_MESSAGE);
				menuOuvert = true; // Menu H activé
			}
			// Si le menu H est activé, on ouvre le menu de changement de case avec 'A'
			if (e.getKeyChar() == 'A' || e.getKeyChar() == 'a') {
				String message = "Cliquez sur la case que vous souhaitez modifier.";
				JOptionPane.showMessageDialog(this, message,
						"Tous les loups de mer ont besoin d'un coup de pouce de temps en temps.",
						JOptionPane.INFORMATION_MESSAGE);
				attenteClic = true;
				attenteChoix = true;
			}

			// Menu chemin le plus court
			if (e.getKeyChar() == 'B' || e.getKeyChar() == 'b') {
				labyrinthe.trouverCheminLePlusCourt();
			}

			// Menu mystère
			if (e.getKeyChar() == 'C' || e.getKeyChar() == 'c') {
				System.out.println("Hello");
				deplacerAventurierAleatoirement();
			}

			// Inventaire à tout moment
			if (e.getKeyChar() == 'I' || e.getKeyChar() == 'i') {
				String inventaireMessage = "Voici l'inventaire :\n";
				List<Objet> inventaire = labyrinthe.getAventurier().getInventaire();

				if (inventaire.isEmpty()) {
					inventaireMessage = "L'inventaire est vide.";
				} else {
					for (Objet objet : inventaire) {
						inventaireMessage += "- " + objet.getNom() + "\n";
					}
				}
				JOptionPane.showMessageDialog(this, inventaireMessage, "Le cargo du navire.",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			labyrinthe.getAventurier().setCoordonneesPrecedentes();
			int pos;
			if (labyrinthe.getAventurier().getPv() <= 0 || labyrinthe.getAventurier().getTps() <= 0) {
				System.out.println("L'aventurier ne peut plus bouger. Points de vie ou temps épuisés.");
				return; // Sort de la méthode sans effectuer le déplacement
			}
			int xPrecedent = labyrinthe.getAventurier().getXPrecedent();
			int yPrecedent = labyrinthe.getAventurier().getYPrecedent();

			/*----- Vers le bas -----*/
			if (e.getKeyCode() == KeyEvent.VK_DOWN && labyrinthe.getAventurier().getX() < labyrinthe.getTaille() - 1) {
				pos = labyrinthe.getAventurier().getX() + 1; // Incrémentation de la position

				if (!(labyrinthe.getCase(pos, labyrinthe.getAventurier().getY()).getClassName().equals("Rocher"))) {
					if (labyrinthe.getCase(pos, labyrinthe.getAventurier().getY()).getClassName().equals("Tempete")) {
						if (labyrinthe.getAventurier().possedeObjet("Trident")) {
							labyrinthe.getAventurier().setX(pos);
							labyrinthe.getCase(labyrinthe.getAventurier().getY(), pos).changerTypeCase(labyrinthe,
									labyrinthe.getAventurier().getY(), pos, 0);
							labyrinthe.getAventurier().retirerTrident();
						} else {
							JOptionPane.showMessageDialog(null,
									"Ohé moussailon ! Il te faut le trident pour passer ici !",
									"Attention moussailon !", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						labyrinthe.getAventurier().setX(pos);
					}
				}
			}

			/*----- Vers le haut -----*/
			if (e.getKeyCode() == KeyEvent.VK_UP && labyrinthe.getAventurier().getX() > 0) {
				pos = labyrinthe.getAventurier().getX() - 1; // Décrémentation de la position

				if (!(labyrinthe.getCase(pos, labyrinthe.getAventurier().getY()).getClassName().equals("Rocher"))) {
					if (labyrinthe.getCase(pos, labyrinthe.getAventurier().getY()).getClassName().equals("Tempete")) {
						if (labyrinthe.getAventurier().possedeObjet("Trident")) {
							labyrinthe.getAventurier().setX(pos);
							labyrinthe.getCase(labyrinthe.getAventurier().getY(), pos).changerTypeCase(labyrinthe,
									labyrinthe.getAventurier().getY(), pos, 0);
							labyrinthe.getAventurier().retirerTrident();
						} else {
							JOptionPane.showMessageDialog(null,
									"Ohé moussailon ! Il te faut le trident pour passer ici !",
									"Attention moussailon !", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						labyrinthe.getAventurier().setX(pos);
					}
				}
			}

			/*----- Vers la droite -----*/
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && labyrinthe.getAventurier().getY() < labyrinthe.getTaille() - 1) {
				pos = labyrinthe.getAventurier().getY() + 1; // Incrémentation de la position

				if (!(labyrinthe.getCase(labyrinthe.getAventurier().getX(), pos).getClassName().equals("Rocher"))) {
					if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), pos).getClassName().equals("Tempete")) {
						if (labyrinthe.getAventurier().possedeObjet("Trident")) {
							labyrinthe.getAventurier().setY(pos);
							labyrinthe.getCase(pos, labyrinthe.getAventurier().getX()).changerTypeCase(labyrinthe, pos,
									labyrinthe.getAventurier().getX(), 0);
							labyrinthe.getAventurier().retirerTrident();

						} else {
							JOptionPane.showMessageDialog(null,
									"Ohé moussailon ! Il te faut le trident pour passer ici !",
									"Attention moussailon !", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						labyrinthe.getAventurier().setY(pos);
					}
				}
			}

			/*----- Vers la gauche -----*/

			if (e.getKeyCode() == KeyEvent.VK_LEFT && labyrinthe.getAventurier().getY() > 0) {
				pos = labyrinthe.getAventurier().getY() - 1; // Décrémentation de la position

				if (!(labyrinthe.getCase(labyrinthe.getAventurier().getX(), pos).getClassName().equals("Rocher"))) {
					if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), pos).getClassName().equals("Tempete")) {
						if (labyrinthe.getAventurier().possedeObjet("Trident")) {
							labyrinthe.getAventurier().setY(pos);
							labyrinthe.getCase(labyrinthe.getAventurier().getY(), pos).changerTypeCase(labyrinthe,
									labyrinthe.getAventurier().getY(), pos, 0);
							labyrinthe.getAventurier().retirerTrident();
						} else {
							JOptionPane.showMessageDialog(null,
									"Ohé moussailon ! Il te faut le trident pour passer ici !",
									"Attention moussailon !", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						labyrinthe.getAventurier().setY(pos);
					}
				}
			}
			// On déclenche l'action de la case sur laquelle est l'aventurier
			labyrinthe.actionObjet();
			labyrinthe.actionPersonnages();

			// Vérifie si l'aventurier a besoin de réparation
			System.out.println(labyrinthe.getAventurier().aBesoinReparation());
			if (labyrinthe.getAventurier().aBesoinReparation()) {
				labyrinthe.getAventurier().setABesoinReparation(!labyrinthe.getAventurier().possedeObjet("Kit de reparation"));
				labyrinthe.getAventurier().setPv(labyrinthe.getAventurier().getPv() - 5);
			} else {
				labyrinthe.getAventurier().getInventaire().remove("Kit de reparation");
			}

			afficherInventaire();
			barre_etat
					.setText("Appuyez sur H pour ouvrir le menu d'aide ----- PV : " + labyrinthe.getAventurier().getPv()
							+ "-----  Temps restant : " + labyrinthe.getAventurier().getTps());

			// On refait le dessin
			repaint();
		}

	} /*----- Fin de la classe interne Dessin -----*/
} /*----- Fin de la classe Vue -----*/
