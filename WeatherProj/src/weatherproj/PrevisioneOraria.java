/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherproj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author anastasia
 */
// A class representing hourly weather forecasts, extending the base weather forecast class
public class PrevisioneOraria extends PrevisioneMeteo {
    private ArrayList<PrevisioneMeteo> previsioniOrarie;
    // An index to keep track of the current forecast in the list
    private int currentIndex = 0;

    // Constructor senza parametri
    public PrevisioneOraria() {
        // Calling the parent constructor with default values
        super(0, 0, "", null);

        // Initializing the hourly forecasts list
        this.previsioniOrarie = new ArrayList<>();
    }

    // A method to add an hourly forecast to the list
    public void addPrevisioneFasciaOraria(PrevisioneMeteo previsione) {
        this.previsioniOrarie.add(previsione);
    }

    // A method to retrieve the current hourly forecast
    public PrevisioneMeteo getCurrentPrevisione() {
        // Check if the current index is within the list bounds
        if (currentIndex < previsioniOrarie.size()) {
            // Return the forecast at the current index
            return previsioniOrarie.get(currentIndex);
        }
        // Return null if the current index is out of bounds
        return null;
    }

    // A method to move to the next hourly forecast
    public void nextPrevisione() {
        // Check if the current index is not at the end of the list
        if (currentIndex < previsioniOrarie.size() - 1) {
            // Increment the current index
            currentIndex++;
        }
    }

    // A method to move to the previous hourly forecast
    public void previousPrevisione() {
        // Check if the current index is not at the beginning of the list
        if (currentIndex > 0) {
            // Decrement the current index
            currentIndex--;
        }
    }

    @Override
    public String descrizione() {
        // Initialize a StringBuilder object to build the description
        StringBuilder sb = new StringBuilder();

        // Add an HTML header to the description
        sb.append("<html>Previsioni orarie:<br>");

        // Iterate through the hourly forecasts in the list
        for (PrevisioneMeteo previsione : previsioniOrarie) {
            // Add the date and time, temperature, humidity, and weather conditions to the description
            sb.append(previsione.getDataOra())
                    .append(" - Temperatura: ")
                    .append(previsione.getTemperatura())
                    .append("&deg;C, Umidità: ")
                    .append(previsione.getUmidita())
                    .append("%<br>Condizioni: ")
                    .append(previsione.getCondizioniAtmosferiche())
                    .append("<br>");
        }

        // Add an HTML footer to the description
        sb.append("</html>");

        // Return the final description as a string
        return sb.toString();
    }
}