/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.interfaces;

import com.pidetucomida.pojo.*;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public interface DAOInterface {

    public ArrayList<Producto> getProductos() throws Exception;

    public boolean insertaCliente(Cliente c) throws Exception;

    public Cliente devuelveCliente(String correo) throws Exception;

    public ArrayList<Producto> getProductosPorTipo(String tipo) throws Exception;

    public String insertaProducto(Producto p) throws Exception;

    public Producto getProductoPorId(int id) throws Exception;
    
    public ArrayList<Ingrediente> getIngredientesPorProductId(int id)throws Exception;

}
