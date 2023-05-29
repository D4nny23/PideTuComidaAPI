/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.pojo;

import java.time.LocalDateTime;

/**
 *
 * @author dev
 */
public class Pedido {
    private int id;
    private int idCliente;
    private LocalDateTime fechaPedido;
    private int finalizado;

    public Pedido(int id, int idCliente, LocalDateTime fechaPedido, int finalizado) {
        this.id = id;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.finalizado = finalizado;
    }

    public Pedido() {
    }

    public Pedido(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(int finalizado) {
        this.finalizado = finalizado;
    }
    
    
}
