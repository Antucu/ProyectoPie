///////////////////Proyecto Pie//////////////////////

//Integrantes
//  Anthony Jhon Apaza Chambi
//  Alvaro Freed Apaza Sosa
//  Alonso Jesus Conde Sanchez
//  Jose David Fuentes Cuadros

//  V1.0

package com.example.anthonyjhon.proyectopie;

public class ProductViewPagerModel {
    private int type;
    private Integer recurso;
    private String recursoFB;

    public ProductViewPagerModel(int type, Integer recurso) {
        this.type = type;
        this.recurso = recurso;
    }

    public ProductViewPagerModel(int type, String recursoFB) {
        this.type = type;
        this.recursoFB = recursoFB;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getRecurso() {
        return recurso;
    }

    public void setRecurso(Integer recurso) {
        this.recurso = recurso;
    }

    public String getRecursoFB() {
        return recursoFB;
    }

    public void setRecursoFB(String recursoFB) {
        this.recursoFB = recursoFB;
    }
}
