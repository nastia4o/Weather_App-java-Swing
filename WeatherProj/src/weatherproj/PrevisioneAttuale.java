/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherproj;

import java.time.LocalDateTime;

/**
 *
 * @author anastasia
 */
public class PrevisioneAttuale extends PrevisioneMeteo {
    private int velocitaVento;
    private double tempMaxGiorno;
    private double tempMinGiorno;
    private Localita citta;

    public PrevisioneAttuale(double temperatura, int umidita, String condizioniAtmosferiche, LocalDateTime dataOra,  int velocitaVento, double tempMaxGiorno, double tempMinGiorno, Localita citta ) {
        super(temperatura, umidita, condizioniAtmosferiche, dataOra);
        this.velocitaVento= velocitaVento;
        this.tempMaxGiorno = tempMaxGiorno;
        this.tempMinGiorno = tempMinGiorno;
        this.citta=new Localita();
    }
    public PrevisioneAttuale() {
        super(0, 0, "", LocalDateTime.now()); // Call to superclass constructor with default values
        this.velocitaVento = 0;
        this.tempMaxGiorno = 0.0;
        this.tempMinGiorno = 0.0;
        this.citta = new Localita();
    }


    @Override
    public String descrizione() {
            return "Data e ora " +
                    dataOra +"\n"+ " Temperatura: "
                    + temperatura + "°C," +"\n" + " Umidità: "
                    + umidita + "%, "+ "\n"+"Condizioni: "
                    + condizioniAtmosferiche;


    }
   }

  
   

    

