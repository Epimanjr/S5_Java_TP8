/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Maxime
 */
public class Exception extends java.lang.Exception {
    
    public Exception() {
        super("Erreur: Le fichier de mot de passe ne peut pas être ouvert.");
    }
}
