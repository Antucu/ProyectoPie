///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

import java.util.List;

public class HomePageModel {

    int type;

    public static final int HORIZONTAL_MODEL=1;
    public static final int HORIZONTAL_CATECORIES=2;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    //HorizontalProductLayout
    String tile;
    List<HorizontalScrollProductModel> listModelProduct;
    List<String> empresasName;

    public HomePageModel(int type, String tile, List<HorizontalScrollProductModel> listModelProduct) {
        this.type = type;
        this.tile = tile;
        this.listModelProduct = listModelProduct;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public List<HorizontalScrollProductModel> getListModelProduct() {
        return listModelProduct;
    }

    public void setListModelProduct(List<HorizontalScrollProductModel> listModelProduct) {
        this.listModelProduct = listModelProduct;
    }

    public List<String> getEmpresasName() {
        return empresasName;
    }

    public void setEmpresasName(List<String> empresasName) {
        this.empresasName = empresasName;
    }

    //HorizontalProductLayout

    //HorizontalCategoriesLayout
    String title;
    String color;
    List<HorizontalScrollProductModel> listModelCategories;
    List<String> categoriesName;

    public HomePageModel(int type, String title, String col, List<HorizontalScrollProductModel> listModelCategories, List<String> categoriesName) {
        this.type = type;
        this.title = title;
        this.color=col;
        this.listModelCategories = listModelCategories;
        this.categoriesName = categoriesName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalScrollProductModel> getListModelCategories() {
        return listModelCategories;
    }

    public void setListModelCategories(List<HorizontalScrollProductModel> listModelCategories) {
        this.listModelCategories = listModelCategories;
    }

    public List<String> getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(List<String> categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //HorizontalCategoriesLayout
}
