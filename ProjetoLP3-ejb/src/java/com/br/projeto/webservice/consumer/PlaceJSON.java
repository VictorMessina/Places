
package com.br.projeto.webservice.consumer;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 31320600
 */
public class PlaceJSON  implements Serializable{
    
    private String id_place;
    private String address;
    private String phone_number;
    private String latitude;
    private String longitude;
    private String name_place;
    private String website;
    private List<String> weekdays;
    private List<String> photos;
    private List<String> types;
    private int price;
    private int popularity; 

    public PlaceJSON() {
    }

       
    public PlaceJSON(String id_place, String address, String name_place,  List<String> photos, List<String> types, int price) {
        this.id_place = id_place;
        this.address = address;
        this.name_place = name_place;
        this.photos = photos;
        this.types = types;
        this.price = price;
    }

    public PlaceJSON(String id_place, String address, String phone_number, String latitude, String longitude, String name_place, String website, List<String> weekdays, List<String> photos, List<String> types, int price) {
        this.id_place = id_place;
        this.address = address;
        this.phone_number = phone_number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name_place = name_place;
        this.website = website;
        this.weekdays = weekdays;
        this.photos = photos;
        this.types = types;
        this.price = price;
        this.popularity = 0;
    }

    public String getId_place() {
        return id_place;
    }

    public void setId_place(String id_place) {
        this.id_place = id_place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName_place() {
        return name_place;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

  
    public List<String> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<String> weekdays) {
        this.weekdays = weekdays;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Place{" + "id_place=" + id_place + ", address=" + address + ", phone_number=" + phone_number + ", latitude=" + latitude + ", longitude=" + longitude + ", name_place=" + name_place + ", website=" + website + ", weekdays=" + weekdays + ", photos=" + photos + ", types=" + types + ", price=" + price + ", popularity=" + popularity + '}';
    }
    
    
}
