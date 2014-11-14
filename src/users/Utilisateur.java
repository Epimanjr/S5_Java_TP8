/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

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
     * Construit un utilisateur.
     * @param nom
     * @param motDePasse 
     */
    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }
    
    private static boolean nomEtMdpCorrect(String id, String mdp) {
        boolean res = false;
        
        return res;
    }
    
    
    
}
