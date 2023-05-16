/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.interfaces;

import com.pidetucomida.pojo.Cliente;
import com.pidetucomida.pojo.Producto;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public interface DAOInterface {
    public ArrayList<Producto> devuelveProductos() throws Exception;
    public boolean insertaCliente(Cliente c) throws Exception;
    public Cliente devuelveCliente(String correo) throws Exception;
    public ArrayList<Producto> getProductosPorTipo(String tipo) throws Exception;
    public boolean insertaProducto(Producto p) throws Exception;
}
