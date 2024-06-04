/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package weatherproj;

import java.util.ArrayList;

/**
 *
 * @author anastasia
 */
public class WeatherProj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Test getLocalita metodo
            Localita localita = GestoreAPI.getLocalita("modena");
            System.out.println("Località: " + localita.toString());

            // Test getPrevisioneAttuale metodo
            String previsioneAttuale = GestoreAPI.previsioneAttuale(localita);
            System.out.println( previsioneAttuale);

            // Test getPrevisioneOraria method
            PrevisioneOraria previsioneOraria= GestoreAPI.getPrevisioneOraria(localita);

            System.out.println( previsioneOraria.descrizione());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    

