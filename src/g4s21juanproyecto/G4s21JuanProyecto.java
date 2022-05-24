/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package g4s21juanproyecto;
import conexionDAO.conexion;
/**
 *
 * @author user
 */
public class G4s21JuanProyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new principal().setVisible(true);
        conexion conexion = new conexion();
        conexion.abrir();
    }
    
}
