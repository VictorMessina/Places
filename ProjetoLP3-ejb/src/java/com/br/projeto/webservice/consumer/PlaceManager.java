/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.webservice.consumer;


import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Vitória
 */
@Stateless
//pra nao precisar criar interface
@LocalBean
public class PlaceManager {
    //photo
    //https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CoQBcwAAAN9zY97j0S--C5DAQvGUjXSb61DWaKP0bBRgjSGJsUgeWN87a6EUfPl2C2CCeLTgjy82DkeCOXtGVtr0as0hJBL2kfGHc0LAqOeouBzFAt3XVEpzzxpbXQL9DVByzY3A547nGwu33W1nGwZO6Z9nibi490rbalEW1X_QYapBfgu-EhD4OeWcgmcCJj8gkgqaR_xCGhS5UvBq3yIR3Q0fk9gcARw29v9RRw&key=AIzaSyCuWZtmC03Yc4CGfjfJjl9zaAHmIhflavU

    //id place
    //https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJxXSgfDyuEmsR3X5VXGjBkFg&key=AIzaSyCuWZtmC03Yc4CGfjfJjl9zaAHmIhflavU
    public static final String API_KEY = "AIzaSyBUZvLQ17WGdJcePLxtr8Ul93Z4LOP2A8s";
    public static final String URLBASE = "https://maps.googleapis.com/maps/api/place/";
    public static final String URLEND = "&ie=utf8&oe=utf8&language=pt-BR";

    public PlaceManager() {
    }
    
    
    public String openURL(String uri){
        URL url;
        String content = null;
        
        try {
            url = new URL(uri);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
            HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
            //HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setRequestProperty("Accept-Charset", "UTF8");

            int code = con.getResponseCode();
            System.out.println("CODIGO CONNECTION: " + code);
            //200 é o codigo do sucesso, 407 por ex é erro do proxy
            if (code == 407) {
                System.out.println("Falha na autenticação do proxy.");
            } else if (code == 200) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                con.disconnect();

                content = convertToUTF8(sb.toString());
                
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(PlaceManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlaceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    } 

    public List<PlaceJSON> textSearchPlaces(String query) {
        //URL
        String uri = URLBASE + "textsearch/json?query=" + query + URLEND + "&key=" + API_KEY;        
        uri = uri.replace(" ", "+");
        System.out.println(uri);
        
        return PlaceJSONParser.parseLista(openURL(uri));
    }
    
    public PlaceJSON openPlaceById(String id_place) {
        //URL
        String uri = URLBASE + "details/json?placeid=" + id_place + URLEND +"&key=" + API_KEY;     
        System.out.println(uri);
        
        return PlaceJSONParser.parsePlace(openURL(uri));
    }
    
    
    private static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes(),"UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
    
    public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes(), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

}

