/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.pojo;

/**
 *
 * @author dev
 */
public class Producto {
    private String ruta;
    private int idProducto;
    private String nombre;
    private int idIngrediente;
    private double precio;

    public Producto(String ruta, int idProducto, String nombre, int idIngrediente, double precio) {
        this.ruta = ruta;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.idIngrediente = idIngrediente;
        this.precio = precio;
    }
    
    public Producto(int idProducto, String nombre, int idIngrediente, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.idIngrediente = idIngrediente;
        this.precio = precio;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
}
