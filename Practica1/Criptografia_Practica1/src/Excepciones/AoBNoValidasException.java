/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Alex
 */
public class AoBNoValidasException extends Exception {
    public AoBNoValidasException() {
        super("A y B no tienen un valor válido! (4a^3+27b^2 <> 0)");
    }
    
    public AoBNoValidasException(String message) {
        super(message);
    }
}
