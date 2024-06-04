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
public abstract class PrevisioneMeteo {
       protected double temperatura;
       protected int umidita;
       protected String condizioniAtmosferiche;
       protected LocalDateTime  dataOra;

       public PrevisioneMeteo(double temperatura, int umidita, String condizioniAtmosferiche, LocalDateTime  dataOra) {
              this.temperatura = temperatura;
              this.umidita = umidita;
              this.condizioniAtmosferiche = condizioniAtmosferiche;
              this.dataOra = dataOra;

       }

   

    public abstract String descrizione();

       public double getTemperatura() {
              return temperatura;
       }

       public void setTemperatura(double temperatura) {
              this.temperatura = temperatura;
       }

       public int getUmidita() {
              return umidita;
       }

       public void setUmidita(int umidita) {
              this.umidita = umidita;
       }

       public String getCondizioniAtmosferiche() {
              return condizioniAtmosferiche;
       }

       public void setCondizioniAtmosferiche(String condizioniAtmosferiche) {
              this.condizioniAtmosferiche = condizioniAtmosferiche;
       }

       public LocalDateTime getDataOra() {
              return dataOra;
       }

       public void setDataOra(LocalDateTime dataOra) {
              this.dataOra = dataOra;
       }

}




