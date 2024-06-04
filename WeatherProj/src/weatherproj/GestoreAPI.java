/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherproj;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anastasia
 */


public class GestoreAPI {

    private static final String ApiKey = "ca1e02f9326450e75e5025b50889031e";
    private static final String Loc_URL = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String BaseWeatherURL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String BaseForecastURL = "https://api.openweathermap.org/data/2.5/forecast";

    // Method to send a GET request
    private static HttpURLConnection sendGetRequest(String urlString) throws IOException {
        //attempt to create connection
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //set request method to get
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        //status code is 200  ok
        //check the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return conn;
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
    }
    //METHOD TO GET THE CITY API
    public static Localita getLocalita(String nomeCitta) throws Exception {
        //replace the space with + so if we insert the city  like los angeles it will take it
        nomeCitta = nomeCitta.replaceAll(" ", "+");
        // Construct the URL for the geocoding API request with the city name and API key.
        String url = String.format("%s?q=%s&appid=%s&units=metric", Loc_URL, nomeCitta, ApiKey);
        HttpURLConnection apiConnection = sendGetRequest(url);
        
        String jsonResponse = readApiResponse(apiConnection);
        
        JSONParser parser = new JSONParser();
        JSONArray locationData = (JSONArray) parser.parse(jsonResponse);

        if (locationData!= null && !locationData.isEmpty()) {

            JSONObject jsonObject = (JSONObject) locationData.get(0);
            // Extract the city name, latitude, longitude, and country code from the JSONObject.
            String nome = (String) jsonObject.get("name");
            double latitudine = (double) jsonObject.get("lat");
            double longitudine = (double) jsonObject.get("lon");
            String country_code = (String)jsonObject.get("country");
            // Create a new Localita object 
            return new Localita(nome, latitudine, longitudine,country_code);
        } else {
        
            throw new Exception("No location data found for city: " + nomeCitta);
        }
    }

    private static String readApiResponse(HttpURLConnection apiConnection) throws IOException {
        //create a string builder to storfe json data
        StringBuilder resultJSON = new StringBuilder();
        //scanner to read from the inputStream of HTTPURLconnection
        Scanner scanner = new Scanner(apiConnection.getInputStream());
        //loop through each line in the response and append it to the string Builder
        while (scanner.hasNext()) {
            resultJSON.append(scanner.nextLine());
        }
        //close the scanner to release the resources associated with it

        scanner.close();
        //return json data as a string
        return resultJSON.toString();
    }
    public static String previsioneAttuale(Localita localita) throws Exception {
        
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", BaseWeatherURL, localita.getLatitudine(), localita.getLongitudine(), ApiKey);
     
        HttpURLConnection apiConnection = sendGetRequest(url);
        
        String jsonResponse = readApiResponse(apiConnection);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
        double temperatura = (double) ((JSONObject) jsonObject.get("main")).get("temp");
        
        int umidita = ((Number) ((JSONObject) jsonObject.get("main")).get("humidity")).intValue();
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        
        JSONObject weatherCondition = (JSONObject) weatherArray.get(0);
        // Extract the weather condition description.
        String condizioniAtmosferiche = (String) weatherCondition.get("description");
        LocalDateTime dataOra = LocalDateTime.now();
        int velocitaVento = ((Number) ((JSONObject) jsonObject.get("wind")).get("speed")).intValue();
        double tempMaxGiorno = (double) ((JSONObject) jsonObject.get("main")).get("temp_max");
        double tempMinGiorno = (double) ((JSONObject) jsonObject.get("main")).get("temp_min");
        PrevisioneAttuale previsioneAttuale = new PrevisioneAttuale(temperatura, umidita, condizioniAtmosferiche, dataOra, velocitaVento, tempMaxGiorno, tempMinGiorno, localita);
        // Return a description  as a string.
        return previsioneAttuale.descrizione();
    }
    public static PrevisioneOraria getPrevisioneOraria(Localita localita) throws Exception {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric&cnt=16", BaseForecastURL, localita.getLatitudine(), localita.getLongitudine(), ApiKey);
        HttpURLConnection apiConnection = sendGetRequest(url);
        String jsonResponse = readApiResponse(apiConnection);
        JSONParser parser = new JSONParser();
        JSONObject forecastData = (JSONObject) parser.parse(jsonResponse);

        // Get the list of hourly forecasts
        JSONArray hourlyForecasts = (JSONArray) forecastData.get("list");


        PrevisioneOraria previsioneOraria = new PrevisioneOraria();
        for (Object obj : hourlyForecasts) {
            JSONObject forecast = (JSONObject) obj;
            PrevisioneMeteo previsione = new PrevisioneAttuale(); // PrevisioneAttuale is a concrete subclass

            // Parse the date and time
            String dataOraStr = (String) forecast.get("dt_txt");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dataOra = LocalDateTime.parse(dataOraStr, formatter);

            // Get the main weather data
            JSONObject mainData = (JSONObject) forecast.get("main");
            double temperatura = ((Number) mainData.get("temp")).doubleValue();
            int umidita = ((Number) mainData.get("humidity")).intValue();

            // Get the weather condition description
            JSONArray weatherArray = (JSONArray) forecast.get("weather");
            JSONObject weatherCondition = (JSONObject) weatherArray.get(0);
            String condizioniAtmosferiche = (String) weatherCondition.get("description");
            // Set the previsione attributes
            previsione.setDataOra(dataOra);
            previsione.setTemperatura(temperatura);
            previsione.setUmidita(umidita);
            previsione.setCondizioniAtmosferiche(condizioniAtmosferiche);
            // Add the previsione to the list in PrevisioneOraria
            previsioneOraria.addPrevisioneFasciaOraria(previsione);
        }

        return previsioneOraria;

    }


}
