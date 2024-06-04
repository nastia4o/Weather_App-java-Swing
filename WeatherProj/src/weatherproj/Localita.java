/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherproj;

/**
 *
 * @author anastasia
 */
public class Localita {
    private String nome;
    private double latitudine;
    private double longitudine;
    private String id;

    public Localita(String nome, double latitudine, double longitudine, String id) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.id=id;
    }

    public  Localita(){
        this("",0,0,"");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f, %.2f)", nome, latitudine, longitudine, id);
    }
}
