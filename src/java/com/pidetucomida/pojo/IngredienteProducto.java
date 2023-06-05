/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.pojo;

import java.util.ArrayList;

/**
 *
 * @author dev
 */
public class IngredienteProducto {

    private int idProducto;
    private ArrayList<Integer> al;

    public IngredienteProducto(int idProducto, ArrayList<Integer> al) {
        this.idProducto = idProducto;
        this.al = al;
    }

    public IngredienteProducto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public ArrayList<Integer> getAl() {
        return al;
    }

    public void setAl(ArrayList<Integer> al) {
        this.al = al;
    }

}
