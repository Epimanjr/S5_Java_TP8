/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class Utilisateur {

    /**
     * Le nom de l'utilisateur.
     */
    private String nom;

    /**
     * Son mot de passe.
     */
    private String motDePasse;

    /**
     * Utilisateur(s) connecté(s).
     */
    public static HashMap<String, Utilisateur> connectes = new HashMap<>();

    /**
     * Construit un utilisateur.
     *
     * @param nom
     * @param motDePasse
     */
    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }

    public static boolean inscription(String id, String mdp) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("mdp.txt", true));
            pw.println(id + " " + mdp);
            pw.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean nomEtMdpCorrect(String id, String mdp) {
        boolean res = false;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("mdp.txt"));

            while (br.ready()) {
                String line = br.readLine();
                String[] lines = line.split(" ");

                if (lines.length == 1) {
                    /* Pas d'espace */
                    if (mdp.equals("") && id.equals(line)) {
                        return true;
                    }
                } else if (lines.length == 2) {
                    if (mdp.equals(lines[1]) && id.equals(lines[0])) {
                        return true;
                    }
                }
            }

            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Utilisateur getUtilisateur(String nom, String mdp) {
        if ((connectes.containsKey(nom))) {
            return (Utilisateur) (connectes.get(nom));
        } else if (nomEtMdpCorrect(nom, mdp)) {
            Utilisateur u = new Utilisateur(nom, mdp);
            connectes.put(nom, u);
            return u;
        } else {
            return null;
        }
    }

    public boolean setMdp(String ancienMdp, String nouveauMdp) {
        HashMap<String, String> tmp = new HashMap<>();

        //Lecture && stockage tempor
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("mdp.txt"));

            while (br.ready()) {
                String line = br.readLine();
                String[] lines = line.split(" ");

                if (lines.length == 1) {
                    /* Pas d'espace */
                    tmp.put(line, "");
                } else if (lines.length == 2) {
                    tmp.put(lines[0], lines[1]);
                }
            }

            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Modification du mot de passe (dans le stockage temporaire)
        tmp.replace(this.nom, nouveauMdp);

        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter("mdp.txt"));
            Set cles = tmp.keySet();
            Iterator it = cles.iterator();
            int iterateur = 0;
            while (it.hasNext()) {
                String cle = it.next().toString();
                String valeur = tmp.get(cle);
                System.out.println("Ecriture : " + cle + " " + valeur);
                pw.println(cle + " " + valeur);
            }
            pw.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void deconnexion() {
        connectes.remove(nom);
    }
    
}
