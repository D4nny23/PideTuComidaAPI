/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.DAOImplementation;

import com.pidetucomida.interfaces.DAOInterface;
import com.pidetucomida.pojo.Cliente;
import com.pidetucomida.pojo.Producto;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public class DAOImplementation implements DAOInterface, AutoCloseable {

    Connection con = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("No se ha podido cargar el driver");
            System.exit(1);
        }
    }

    public DAOImplementation() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", "root");
    }

    @Override
    public ArrayList<Producto> devuelveProductos() throws Exception {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto p = null;
        String sql = "Select idProducto, nombre, idIngrediente, precio from productos";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
                productos.add(p);
            }
        } catch (Exception e) {
        }
        return productos;
    }

    @Override
    public void close() throws Exception {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertaCliente(Cliente c) throws Exception {
        boolean insertado = false;
        String sql = "Insert into cliente values(?,?,?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, c.getIdCliente());
            stm.setString(2, c.getCorreo());
            stm.setString(3, c.getPass());
            stm.setString(4, c.getNombre());
            stm.setString(5, c.getApellido());
            stm.setString(6, c.getDireccionEnvio());
            stm.setString(7, c.getTelefono());
            stm.executeUpdate();
            insertado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Override
    public Cliente devuelveCliente(String correo) throws Exception {
        Cliente c = null;
        String sql = "Select idCliente, correo, pass, nombre, apellido, direccionEnvio, telefono from cliente where correo = '" + correo + "'";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (Exception e) {
        }
        return c;
    }

    @Override
    public ArrayList<Producto> getProductosPorTipo(String tipo) throws Exception {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto p = null;
        String sql = "Select idProducto, nombre, idIngrediente, precio from productos where tipo ='"+tipo+"'";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
                productos.add(p);
            }
        } catch (Exception e) {
        }
        return productos;
    }

    @Override
    public void insertaProducto(Producto p) throws Exception {
        
    }
    
    
}
