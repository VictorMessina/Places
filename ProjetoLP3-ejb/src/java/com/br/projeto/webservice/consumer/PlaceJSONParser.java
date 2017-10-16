package com.br.projeto.webservice.consumer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class PlaceJSONParser {
    
    public static final String API_KEY = "AIzaSyBUZvLQ17WGdJcePLxtr8Ul93Z4LOP2A8s";

    public static List<PlaceJSON> parseLista(String content) {
        List<PlaceJSON> lista = new ArrayList<>();

        JsonReader jsonReader = Json.createReader(new StringReader(content));
        JsonObject obj = jsonReader.readObject();
        jsonReader.close();

        String status = obj.getString("status");
        if (status.equalsIgnoreCase("OK")) {

            JsonArray results = obj.getJsonArray("results");

            for (int i = 0; i < results.size(); i++) {
                JsonObject p = results.getJsonObject(i);

                String address = p.getString("formatted_address");
                String name = p.getString("name");

                List<String> photos = new ArrayList<>();
                if (p.getJsonArray("photos") != null) {
                    JsonArray photosArray = p.getJsonArray("photos");
                    JsonObject pho = photosArray.getJsonObject(0);
                    String photo = pho.getString("photo_reference");
                    photos.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photo + "&key=" + API_KEY);
                }

                String id_place = p.getString("place_id");

                int price = -1;
                if (p.getJsonNumber("price_level") != null) {
                    price = p.getJsonNumber("price_level").intValue();
                }

                List<String> types = new ArrayList<>();
                JsonArray typesArray = p.getJsonArray("types");
                for (int k = 0; (k < typesArray.size()) && (typesArray.get(k) != null); k++) {
                    String type = typesArray.getString(k);
                    types.add(type);
                }

                PlaceJSON place = new PlaceJSON(id_place, address, name, photos, types, price);

                lista.add(place);
            }
        }

        return lista;
    }

    public static PlaceJSON parsePlace(String content) {
        PlaceJSON place = null;

        JsonReader jsonReader = Json.createReader(new StringReader(content));
        JsonObject obj = jsonReader.readObject();
        jsonReader.close();

        String status = obj.getString("status");
        if (status.equalsIgnoreCase("OK")) {

            JsonObject p = obj.getJsonObject("result");

            String address = p.getString("formatted_address");

            JsonObject geometry = p.getJsonObject("geometry");
            JsonObject location = geometry.getJsonObject("location");
            String latitude = location.getJsonNumber("lat").toString();
            String longitude = location.getJsonNumber("lng").toString();

            String phone_number= "Não disponível";
            if (p.getJsonString("international_phone_number") != null) {
                phone_number = p.getJsonString("international_phone_number").getString();
            }

            String name = p.getString("name");

            List<String> weekdays = new ArrayList<>();
            if (p.getJsonArray("weekday_text") != null) {
                JsonArray weekdaysArray = p.getJsonArray("weekday_text");
                for (int i = 0; i < weekdaysArray.size(); i++) {
                    String day = weekdaysArray.getString(i);
                    weekdays.add(day);
                }
            }

            List<String> photos = new ArrayList<>();
            if (p.getJsonArray("photos") != null) {
                JsonArray photosArray = p.getJsonArray("photos");
                for (int i = 0; i < photosArray.size(); i++) {
                    JsonObject pho = photosArray.getJsonObject(i);
                    String photo = pho.getString("photo_reference");
                    photos.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photo + "&key=" + API_KEY);
                }
            }

            String id_place = p.getString("place_id");

            int price = -1;
            if (p.getJsonNumber("price_level") != null) {
                price = p.getJsonNumber("price_level").intValue();
            }

            List<String> types = new ArrayList<>();
            JsonArray typesArray = p.getJsonArray("types");
            for (int k = 0; (k < typesArray.size()) && (typesArray.get(k) != null); k++) {
                String type = typesArray.getString(k);
                types.add(type);
            }
            
            String website = "Não disponível";
            if (p.getJsonString("website") != null) {
                website = p.getJsonString("website").getString();  
            }

            place = new PlaceJSON(id_place, address, phone_number, latitude, longitude, name, website, weekdays, photos, types, price);
        }

        return place;

    }

}
