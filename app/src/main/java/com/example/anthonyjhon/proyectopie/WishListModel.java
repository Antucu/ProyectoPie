package com.example.anthonyjhon.proyectopie;

public class WishListModel {
    String url;
    int link;
    int freecouens;
    String title;
    String Precio;
    String Rating;
    String cuttedPrice;
    int totalRatings;
    String methodpayment;

    public WishListModel(int url, String title, String precio){
        this.link = url;
        this.title = title;
        this.Precio=precio;
    }
    public WishListModel(String url, String title, String precio) {
        this.url = url;
        this.title = title;
        this.Precio=precio;
    }

    public WishListModel(String url, int freecouens, String title, String precio, String rating, String cuttedPrice, int totalRatings, String methodpayment) {
        this.url = url;
        this.freecouens = freecouens;
        this.title = title;
        Precio = precio;
        Rating = rating;
        this.cuttedPrice = cuttedPrice;
        this.totalRatings = totalRatings;
        this.methodpayment = methodpayment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFreecouens() {
        return freecouens;
    }

    public void setFreecouens(int freecouens) {
        this.freecouens = freecouens;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getMethodpayment() {
        return methodpayment;
    }

    public void setMethodpayment(String methodpayment) {
        this.methodpayment = methodpayment;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }
}
