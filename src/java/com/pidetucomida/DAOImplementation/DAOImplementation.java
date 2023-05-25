/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.DAOImplementation;

import com.pidetucomida.interfaces.DAOInterface;
import com.pidetucomida.pojo.Cliente;
import com.pidetucomida.pojo.Ingrediente;
import com.pidetucomida.pojo.Producto;
import java.io.File;
import java.io.FileInputStream;
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
    public ArrayList<Producto> getProductos() throws Exception {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto p = null;
        String sql = "Select idProducto, nombre, descripcion, img, precio, tipo from productos";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                Blob blob = rs.getBlob(4);
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), bytes, rs.getDouble(5), rs.getString(6));
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
        String sql = "Select idProducto, nombre, descripcion, img, precio, tipo from productos where tipo ='" + tipo + "'";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                Blob blob = rs.getBlob(4);
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), bytes, rs.getDouble(5), rs.getString(6));
                productos.add(p);
            }
        } catch (Exception e) {
        }
        return productos;
    }

    @Override
    public String insertaProducto(Producto p) throws Exception {
        String insertado = "No insertado";
        String sql = "Insert into productos(img ,nombre, descripcion, precio, tipo) values(?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            insertado = p.toString();
            File fichero = new File(p.getRuta());//digo que fichero es y directorio
            byte[] buff = null;
            if (fichero.exists()) {
                FileInputStream ficheroIn = new FileInputStream(fichero);//con esto leeré el fichero para convertirlo en un array de bytes
                long bytes = fichero.length();//cojo la longitud del fichero
                buff = new byte[(int) bytes];//creo un array de bytes de la misma longitud
                int i, j = 0;//declaro variables
                System.out.println("Lo recorro y lo meto en un buffer");
                while ((i = ficheroIn.read()) != -1) {//leo el fichero y lo guardo en un arrray de bytes
                    buff[j] = (byte) i;
                    j++;
                }
            }
            Blob blob = new javax.sql.rowset.serial.SerialBlob(buff);
            stm.setBlob(1, blob);
            stm.setString(2, p.getNombre());
            stm.setString(3, p.getDescripcion());
            stm.setDouble(4, p.getPrecio());
            stm.setString(5, p.getTipo());
            stm.executeUpdate();
            System.out.println(p.toString());
            insertado = "Insertado";

        } catch (Exception e) {
            e.printStackTrace();
            insertado = e.getMessage();
        }
        return insertado;

    }

    @Override
    public Producto getProductoPorId(int id) throws Exception {
        Producto p = null;
        String sql = "Select idProducto, nombre, descripcion, img, precio, tipo from productos where idProducto =" + id;
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                Blob blob = rs.getBlob(4);
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), bytes, rs.getDouble(5), rs.getString(6));
            }
        } catch (Exception e) {
        }
        return p;
    }

    @Override
    public ArrayList<Ingrediente> getIngredientesPorProductId(int id) throws Exception {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        Ingrediente i = null;
        String sql = "select idIngrediente, nombre from ingredientes where idIngrediente in(\n"
                + "select idIngrediente from ingrediente_producto where idProducto=" + id+")";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                i= new Ingrediente(rs.getInt(1), rs.getString(2));
                ingredientes.add(i);
            }
        } catch (Exception e) {
        }
        return ingredientes;
    }

}
