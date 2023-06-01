/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.DAOImplementation;

import com.pidetucomida.interfaces.DAOInterface;
import com.pidetucomida.pojo.*;
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
        String sql = "Select idProducto, nombre, descripcion, img, precio, tipo from producto";
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
        String sql = "Select idProducto, nombre, descripcion, img, precio, tipo from producto where tipo ='" + tipo + "'";
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
        String sql = "Insert into producto(img ,nombre, descripcion, precio, tipo) values(?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            insertado = p.toString();

            Blob blob = new javax.sql.rowset.serial.SerialBlob(p.getImg());
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
        String sql = "Select idProducto, nombre, descripcion, img, precio, tipo from producto where idProducto =" + id;
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
        String sql = "select idIngrediente, nombre from ingrediente where idIngrediente in (select idIngrediente from ingrediente_producto where idProducto = " + id + ")";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                i = new Ingrediente(rs.getInt(1), rs.getString(2));
                ingredientes.add(i);
            }
        } catch (Exception e) {
        }
        return ingredientes;
    }

    @Override
    public int insertaPedido(Pedido p) throws Exception {
        int idPedido = 0;
        String sql = "INSERT INTO pedido(idCliente) VALUES(?)";
        try (PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, p.getIdCliente());
            stm.executeUpdate();

            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    idPedido = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idPedido;
    }

    @Override
    public boolean insertaProductosPedido(Productos_pedido pp) throws Exception {
        boolean insertado = false;
        String sql = "Insert into producto_pedido(idPedido, idProducto, cantidad, precio) values(?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, pp.getIdPedido());
            stm.setInt(2, pp.getIdProducto());
            stm.setInt(3, pp.getCantidad());
            stm.setDouble(4, pp.getPrecio());
            stm.executeUpdate();
            insertado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Override
    public ArrayList<Pedido> getPedidos() throws Exception {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Pedido p = null;
        String sql = "Select idPedido, fechaPedido, comentario, formaDePago from pedido;";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                p = new Pedido(rs.getInt("idPedido"), rs.getString("fechaPedido"), rs.getString("comentario"), rs.getString("formaDePago"));
                pedidos.add(p);
            }
        } catch (Exception e) {
        }
        return pedidos;
    }

    @Override
    public boolean finalizarPedido(int idPedido) throws Exception {
        boolean actualizado = false;
        String sql = "UPDATE pedido SET finalizado = 1 WHERE idPedido = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idPedido);
            int filasActualizadas = stm.executeUpdate();
            if (filasActualizadas > 0) {
                actualizado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Cliente devuelveClientePorIdPedido(int idPedido) throws Exception {
        Cliente c = null;
        String sql = "SELECT c.idCliente, c.nombre AS nombre_cliente, c.correo, c.direccionEnvio, c.telefono "
                + "FROM cliente c JOIN pedido p ON c.idCliente = p.idCliente WHERE p.idPedido = ?;";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idPedido);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente(rs.getInt("idCliente"), rs.getString("nombre_cliente"), rs.getString("correo"),
                            rs.getString("direccionEnvio"), rs.getString("telefono"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public ArrayList<Producto> getProductosPorIdPedido(int idPedido) throws Exception {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto p = null;
        String sql = "SELECT pr.nombre AS nombre_producto FROM producto pr JOIN producto_pedido pp ON pr.idProducto = pp.idProducto WHERE pp.idPedido = ?;";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idPedido);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String nombreProducto = rs.getString("nombre_producto");
                    p = new Producto();
                    p.setNombre(nombreProducto);
                    productos.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
}
