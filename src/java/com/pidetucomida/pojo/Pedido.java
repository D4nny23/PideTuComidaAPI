/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.pojo;

/**
 *
 * @author dev
 */
public class Pedido {

    private int id;
    private int idCliente;
    private String fechaPedido;
    private int finalizado;
    private String comentario;
    private String formaDePago;
    private double precioTotal;

    public Pedido(int id, int idCliente, String fechaPedido, int finalizado, String comentario, String formaDePago) {
        this.id = id;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.finalizado = finalizado;
        this.comentario = comentario;
        this.formaDePago = formaDePago;
    }

    public Pedido() {
    }

    public Pedido(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public Pedido(int id, String fechaPedido, String comentario, String formaDePago) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.comentario = comentario;
        this.formaDePago = formaDePago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(int finalizado) {
        this.finalizado = finalizado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
