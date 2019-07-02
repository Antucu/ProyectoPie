///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

public class HorizontalScrollProductModel {
    public int icon;
    private String imgHorizontalProduct;
    private String titleImg;
    private String descripcionImg;
    private String precioImg;
    private String categoria;
    private String empresa;
    private int index;

    public HorizontalScrollProductModel(String empresa){
        this.empresa=empresa;
    }

    public HorizontalScrollProductModel(String imgHorizontalProduct, String titleImg, String descripcionImg, String precioImg) {
        this.imgHorizontalProduct = imgHorizontalProduct;
        this.titleImg = titleImg;
        this.descripcionImg = descripcionImg;
        this.precioImg = precioImg;
    }
    public HorizontalScrollProductModel(String imgHorizontalProduct, String titleImg, String descripcionImg, String precioImg, String cat,String emp, int idx) {
        this.imgHorizontalProduct = imgHorizontalProduct;
        this.titleImg = titleImg;
        this.descripcionImg = descripcionImg;
        this.precioImg = precioImg;
        this.categoria=cat;
        this.empresa=emp;
        this.index=idx;
    }
    public HorizontalScrollProductModel(int link){
        this.icon=link;
    }

    public String getImgHorizontalProduct() {
        return imgHorizontalProduct;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public String getDescripcionImg() {
        return descripcionImg;
    }

    public String getPrecioImg() {
        return precioImg;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setImgHorizontalProduct(String imgHorizontalProduct) {
        this.imgHorizontalProduct = imgHorizontalProduct;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public void setDescripcionImg(String descripcionImg) {
        this.descripcionImg = descripcionImg;
    }

    public void setPrecioImg(String precioImg) {
        this.precioImg = precioImg;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
