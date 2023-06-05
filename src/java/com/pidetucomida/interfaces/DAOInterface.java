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

    public Cliente devuelveClientePorIdPedido(int idPedido) throws Exception;

    public ArrayList<Producto> getProductosPorTipo(String tipo) throws Exception;

    public ArrayList<Producto> getProductosPorIdPedido(int idPedido) throws Exception;

    public int insertaProducto(Producto p) throws Exception;

    public Producto getProductoPorId(int id) throws Exception;

    public ArrayList<Ingrediente> getIngredientesPorProductId(int id) throws Exception;

    public int insertaPedido(Pedido p) throws Exception;

    public boolean insertaProductosPedido(Productos_pedido pp) throws Exception;

    public ArrayList<Pedido> getPedidos() throws Exception;

    public String finalizarPedido(int idPedido) throws Exception;

    public ArrayList<Integer> insertaIngredientes(ArrayList<Ingrediente> ingredientes) throws Exception;

    public boolean existeIngrediente(String nombre) throws Exception;

    public ArrayList<Productos_pedido> getCantidadDeProductosPorPedido(int idPedido) throws Exception;
//    public ArrayList<Integer> getCantidadDeProductosPorPedido(int idPedido) throws Exception;

    public String insertaIngredienteAProducto(IngredienteProducto ip) throws Exception;
}
