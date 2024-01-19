package jeu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import modele.Aventurier;
import modele.BarbeNoire;
import modele.Canon;
import modele.Coffre;
import modele.Courant;
import modele.Cire;
import modele.Ile;
import modele.Kraken;
import modele.Labyrinthe;
import modele.Moteur;
import modele.Mouette;
import modele.Ocean;
import modele.Pirate;
import modele.Reparation;
import modele.Rocher;
import modele.Sirène;
import modele.Tempete;
import modele.Trident;
import vue.Vue;

public class AppLabyrinthe {

	private static final String LAB_1 = "data" + File.separator + "labyrinthe_3bis.csv";
	private static int bateauChoix = 1;

	public static int getBateau() {
		return bateauChoix;
	}

	// Méthode pour charger le labyrinthe depuis un fichier CSV
	public static Labyrinthe chargeLabyrinthe(String fichier) {
		Labyrinthe laby = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier))) {
			int taille = Integer.parseInt(scanner.nextLine());
			laby = new Labyrinthe(taille);

			for (int i = 0; i < taille; i++) {
				String[] liste = scanner.nextLine().trim().split(";");

				// Gestion des espaces
				int type_case;
				for (int j = 0; j < taille; j++) {
					String[] elements = liste[j].split("-");
					type_case = Integer.parseInt(elements[0]);

					// Gestion des types de cases du labyrinthe
					if (type_case == 0) {
						laby.setCase(i, j, new Ocean());
					} else if (type_case == 1) {
						laby.setCase(i, j, new Rocher());
					} else if (type_case == 2) {
						laby.setCase(i, j, new Ile());
					} else if (type_case == 3) {
						laby.setCase(i, j, new Courant());
					} else if (type_case == 9) {
						laby.setCase(i, j, new Ocean());
						laby.setAventurier(new Aventurier(i, j, 100, 100, Aventurier.initialiserInventaire()));
					}

					// Gestion des objets et personnages sur chaque case
					for (int k = 1; k < elements.length; k++) {
						String[] contenuCase = elements[k].split("-");

						for (int l = 0; l < contenuCase.length; l++) {
							String contenu = contenuCase[l];

							// Vérification si le contenu est un chiffre ou une lettre
							boolean convertPossible = estUnChiffre(contenu);

							if (!convertPossible) {
								// Gestion des personnages spécifiques
								if (contenu.equals("A")) {
									Pirate pirate = new Pirate(i, j);
									laby.getCase(i, j).ajouterPersonnage(pirate);
								} else if (contenu.equals("B")) {
									Sirène sirène = new Sirène(i, j);
									laby.getCase(i, j).ajouterPersonnage(sirène);
								} else if (contenu.equals("C")) {
									Kraken kraken = new Kraken(i, j);
									laby.getCase(i, j).ajouterPersonnage(kraken);
								} else if (contenu.equals("D")) {
									Mouette mouette = new Mouette(i, j);
									laby.getCase(i, j).ajouterPersonnage(mouette);
								} else if (contenu.equals("X")) {
									laby.setCase(i, j, new Tempete(i, j, laby));
								} else if (contenu.equals("Z")) {
									laby.setCase(i, j, new Ocean());
									BarbeNoire barbe = new BarbeNoire(i, j);
									laby.getCase(i, j).ajouterPersonnage(barbe);
									// System.out.println("Les coordonées sont" + i + " et " + j);
									laby.setArriveeCoordonnees(i, j);
								}

							} else {
								// Gestion des objets sur la case
								if (estUnChiffre(contenu)) {
									int objet_case = Integer.parseInt(contenu);

									if (objet_case == 4) {
										Canon canon = new Canon(i, j);
										laby.getCase(i, j).ajouterPersonnage(canon);
									} else if (objet_case == 5) {
										Cire cire = new Cire(i, j);
										laby.getCase(i, j).ajouterObjet(cire);
									} else if (objet_case == 6) {
										Coffre coffre = new Coffre(i, j);
										laby.getCase(i, j).ajouterObjet(coffre);
									} else if (objet_case == 7) {
										Moteur moteur = new Moteur(i, j);
										laby.getCase(i, j).ajouterObjet(moteur);
									} else if (objet_case == 8) {
										Reparation reparation = new Reparation(i, j);
										laby.getCase(i, j).ajouterObjet(reparation);
									} else if (objet_case == 9) {
										Trident trident = new Trident(i, j);
										laby.getCase(i, j).ajouterObjet(trident);
									}
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
		}

		return laby;
	}
	
	// Méthode pour vérifier si une chaîne de caractères est un chiffre
	private static boolean estUnChiffre(String contenu) {
		return contenu != null && !contenu.isEmpty() && contenu.chars().allMatch(Character::isDigit);
	}

	public static void main(String[] s) throws InterruptedException {
		// Lancement du menu de départ
		menuDepart();
	}

	// Méthode pour afficher le menu de départ
	public static void menuDepart() {

		// Création des ImageIcons pour chaque bouton
		ImageIcon jouerIcone = new ImageIcon("data/jouer.png");
		ImageIcon quitterIcone = new ImageIcon("data/quitter.png");
		ImageIcon bateau1Icone = new ImageIcon("data/pirateanim.gif");
		ImageIcon bateau2Icone = new ImageIcon("data/sousmarinanim.gif");
		ImageIcon bateau3Icone = new ImageIcon("data/requinanim.gif");

		JLabel texte = new JLabel("Bienvenue moussailon !");
		JLabel texte2 = new JLabel("Avant de commencer ton aventure, choisis ton personnage !");

		// Boutons du menu de départ
		JButton boutonJouer = new JButton("Jouer", jouerIcone);
		JButton boutonQuitter = new JButton("Quitter", quitterIcone);
		JRadioButton boutonP1 = new JRadioButton("", bateau1Icone);
		JRadioButton boutonP2 = new JRadioButton("", bateau2Icone);
		JRadioButton boutonP3 = new JRadioButton("", bateau3Icone);

		// Groupement des boutons pour la sélection d'un seul bateau
		ButtonGroup choixBateau = new ButtonGroup();
		choixBateau.add(boutonP1);
		choixBateau.add(boutonP2);
		choixBateau.add(boutonP3);

		// Création du menu et ajout des boutons
		JPanel menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(texte);

		JPanel menu2 = new JPanel();
		menu2.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu2.add(Box.createVerticalStrut(50));
		menu2.add(boutonJouer, jouerIcone);
		menu2.add(boutonQuitter, quitterIcone);
		menu2.setOpaque(false);

		JPanel menu3 = new JPanel();
		menu3.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu3.add(boutonP1, bateau1Icone);
		menu3.add(boutonP2, bateau2Icone);
		menu3.add(boutonP3, bateau3Icone);
		menu3.add(Box.createVerticalStrut(50));
		menu3.setOpaque(false);

		JPanel menu4 = new JPanel();
		menu4.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu4.add(texte2);

		// Image de fond
		ImageIcon imageMenu = new ImageIcon("data/fond.gif");
		JLabel labelImageFond = new JLabel(imageMenu);

		labelImageFond.setLayout(new BoxLayout(labelImageFond, BoxLayout.Y_AXIS));

		labelImageFond.add(Box.createVerticalStrut(10));
		labelImageFond.add(menu, BorderLayout.CENTER);
		labelImageFond.add(Box.createVerticalStrut(10));
		labelImageFond.add(menu2, BorderLayout.CENTER);
		labelImageFond.add(Box.createVerticalStrut(10));
		labelImageFond.add(menu4, BorderLayout.CENTER);
		labelImageFond.add(Box.createVerticalStrut(10));
		labelImageFond.add(menu3, BorderLayout.CENTER);

		// Création de la fenêtre et ajout du "panneau" du menu
		JFrame fenetreMenu = new JFrame("Bienvenue matelot !");

		fenetreMenu.setSize(800, 400);
		fenetreMenu.setLayout(new BoxLayout(fenetreMenu, BoxLayout.Y_AXIS));
		fenetreMenu.setContentPane(labelImageFond);
		fenetreMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreMenu.setResizable(false);
		fenetreMenu.setLocationRelativeTo(null);

		 // Si on clique sur Jouer, on ferme le menu et on lance la Vue
		boutonJouer.addActionListener(e -> {
			// Bateau sélectionné
			if (boutonP1.isSelected()) {
				bateauChoix = 1;
			} else if (boutonP2.isSelected()) {
				bateauChoix = 2;
			} else if (boutonP3.isSelected()) {
				bateauChoix = 3;
			}
			//System.out.println("Bateau sélectionné : " + bateauChoix);

			// On lance le jeu
			Labyrinthe labyrinthe = chargeLabyrinthe(LAB_1);
			new Vue(100, 100, labyrinthe);
			fenetreMenu.dispose();

		});

		// Si on clique sur Quitter on ferme tout
		boutonQuitter.addActionListener(e -> {
			System.exit(0);
		});

		fenetreMenu.setVisible(true);
	}
}/*----- Fin de la classe AppLabyrinthe -----*/
