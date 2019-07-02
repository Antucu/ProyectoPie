///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

public class RecyclerCategoriaModel {

    private String catIconLink;
    private String catIconName;
    private String color;
    private String title;

    private int type;

    public RecyclerCategoriaModel(int typ, String catIconLink, String catIconName) {
        this.type=typ;
        this.catIconLink = catIconLink;
        this.catIconName = catIconName;
    }

    public RecyclerCategoriaModel(int typ, String catIconLink, String catIconName, String catTitle,String color) {
        this.type=typ;
        this.catIconLink = catIconLink;
        this.catIconName = catIconName;
        this.title=catTitle;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCatIconLink(String catIconLink) {
        this.catIconLink = catIconLink;
    }

    public void setCatIconName(String catIconName) {
        this.catIconName = catIconName;
    }

    public String getCatIconLink() {
        return catIconLink;
    }

    public String getCatIconName() {
        return catIconName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String empresa) {
        this.title = empresa;
    }
}
