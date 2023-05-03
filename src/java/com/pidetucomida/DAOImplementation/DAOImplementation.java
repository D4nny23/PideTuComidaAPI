/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.DAOImplementation;

import com.pidetucomida.interfaces.DAOInterface;
import com.pidetucomida.pojo.Producto;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public class DAOImplementation implements DAOInterface,AutoCloseable{
    
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
        ArrayList<Producto> productos= new ArrayList<>();
        Producto p= null;
        String sql="Select idProducto, nombre, idIngrediente, precio from productos";
        try (Statement stm= con.createStatement();
            ResultSet rs= stm.executeQuery(sql);){
            while(rs.next()){
                p= new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
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
}
