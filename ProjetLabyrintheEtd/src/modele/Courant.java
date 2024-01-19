package modele;

import javax.swing.JOptionPane;

public class Courant extends Espace {

    @Override
    public void action(Aventurier a) {
        System.out.println("Tu es dans un courant !");

        // Vérifier si l'aventurier a un "moteur" dans son inventaire
        boolean possedeMoteur = false;
        for (Objet o : a.getInventaire()) {
            if (o.getNom().equals("Moteur")) {
                possedeMoteur = true;
                break;
            }
        }

        if (possedeMoteur) {
            // Choix d'utiliser ou non le moteur
            int choixMoteur = JOptionPane.showConfirmDialog(null, "Tu as un moteur ! Veux-tu l'utiliser ?",
                    "Ohé du matelot, un courant à l'horizon !", JOptionPane.YES_NO_OPTION);

            if (choixMoteur == JOptionPane.YES_OPTION) {
                // OUI = on enlève le moteur de l'inventaire + effet
                JOptionPane.showMessageDialog(null,
                        "Tu utilises ton moteur ! \nTu gères habilement le courant. \nTu perds un peu de temps mais tu progresses !",
                        null, JOptionPane.INFORMATION_MESSAGE);

                a.getInventaire().removeIf(o -> o.getNom().equals("Moteur"));
                a.setTps(a.getTps() - 1);
                a.setPv(a.getPv() - 1);
            } else if (choixMoteur == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null,
                        "Vous avez choisi de ne pas utiliser le moteur. Il reste dans votre inventaire.", null,
                        JOptionPane.INFORMATION_MESSAGE);
                a.setTps(a.getTps() - 3);
                a.setPv(a.getPv() - 2);
            }
            
        } else if (!possedeMoteur)  {
            // Si pas de moteur dans l'inventaire
            if (a.aBesoinCire() && a.aRencontreSirene()) {
                a.setTps(a.getTps() - 4);
                a.setPv(a.getPv() - 3);
            } else {
                a.setTps(a.getTps() - 3); 
                a.setPv(a.getPv() - 2);
            }
        }

        System.out.println("TPS : " + a.getTps());
        System.out.println("PV : " + a.getPv());
    }
} /*----- Fin de la classe Courant ——*/
