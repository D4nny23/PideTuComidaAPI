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
    public int insertaProducto(Producto p) throws Exception {
        int idProducto = 0;
        String sql = "Insert into producto(img ,nombre, descripcion, precio, tipo) values(?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            Blob blob = new javax.sql.rowset.serial.SerialBlob(p.getImg());
            stm.setBlob(1, blob);
            stm.setString(2, p.getNombre());
            stm.setString(3, p.getDescripcion());
            stm.setDouble(4, p.getPrecio());
            stm.setString(5, p.getTipo());
            stm.executeUpdate();
            System.out.println("PRODUCTO INSERTADO --> " + p.toString());

            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    idProducto = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idProducto;
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
            e.printStackTrace();
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
        String sql = "INSERT INTO pedido(idCliente, comentario, formaDePago, precioTotal) VALUES(?,?,?,?);";
        try (PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, p.getIdCliente());
            stm.setString(2, p.getComentario());
            stm.setString(3, p.getFormaDePago());
            stm.setDouble(4, p.getPrecioTotal());
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
        String sql = "Insert into producto_pedido(idPedido, idProducto, cantidad) values(?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, pp.getIdPedido());
            stm.setInt(2, pp.getIdProducto());
            stm.setInt(3, pp.getCantidad());
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
        String sql = "Select idPedido, fechaPedido, comentario, formaDePago, precioTotal from pedido where finalizado = 0;";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                p = new Pedido(rs.getInt("idPedido"), rs.getString("fechaPedido"), rs.getString("comentario"), rs.getString("formaDePago"), rs.getDouble("precioTotal"));
                pedidos.add(p);
            }
        } catch (Exception e) {
        }
        return pedidos;
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

    @Override
    public ArrayList<Integer> insertaIngredientes(ArrayList<Ingrediente> al) throws Exception {
        ArrayList<Integer> idIngredientes = new ArrayList<>();
        String sqlInsert = "INSERT INTO ingrediente (nombre) VALUES (?);";
        String sqlSelect = "SELECT idIngrediente FROM ingrediente WHERE nombre = ?;";

        try (PreparedStatement stmInsert = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmSelect = con.prepareStatement(sqlSelect)) {

            for (Ingrediente i : al) {
                if (!existeIngrediente(i.getNombre())) {
                    stmInsert.setString(1, i.getNombre());
                    stmInsert.executeUpdate();

                    try (ResultSet rs = stmInsert.getGeneratedKeys()) {
                        if (rs.next()) {
                            int id = rs.getInt(1);
                            idIngredientes.add(id);
                        }
                    }
                } else {
                    stmSelect.setString(1, i.getNombre());
                    try (ResultSet rs = stmSelect.executeQuery()) {
                        if (rs.next()) {
                            int id = rs.getInt(1);
                            idIngredientes.add(id);
                        }
                    }
                }
            }
            System.out.println("ID    ->" + idIngredientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idIngredientes;
    }

    @Override
    public boolean existeIngrediente(String nombre) throws Exception {
        String sql = "SELECT COUNT(*) FROM ingrediente WHERE nombre = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, nombre);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int c = rs.getInt(1);
                    return c > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Productos_pedido> getCantidadDeProductosPorPedido(int idPedido) throws Exception {
        ArrayList<Productos_pedido> cantidades = new ArrayList<>();
        Productos_pedido pp = null;
        String sql = "SELECT cantidad, idProducto FROM producto_pedido WHERE idPedido = ?;";
        try (PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, idPedido);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int cantidad = rs.getInt("cantidad");
                    int idProducto = rs.getInt("idProducto");
                    pp = new Productos_pedido();
                    pp.setCantidad(cantidad);
                    pp.setIdProducto(idProducto);
                    cantidades.add(pp);
                    System.out.println("CANTIDAD: " + cantidades);
                }
            }

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int primaryKey = generatedKeys.getInt(1);
                    pp.setIdProducto(primaryKey);
                    cantidades.add(pp);
                }
            }

            System.out.println("Cantidades -> " + cantidades);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidades;
    }

//    @Override
//    public ArrayList<Integer> getCantidadDeProductosPorPedido(int idPedido) throws Exception {
//        ArrayList<Integer> cantidades = new ArrayList<>();
////        Productos_pedido pp = null;
//        String sql = "Select cantidad from producto_pedido where idPedido = ?;";
//        try (PreparedStatement stm = con.prepareStatement(sql)) {
//            stm.setInt(1, idPedido);
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    int cantidad = rs.getInt(1);
////                    pp = new Productos_pedido();
////                    pp.setCantidad(cantidad);
//                    cantidades.add(cantidad);
////                    System.out.println("CANTIDAD: " + pp.getIdProducto() + pp.getCantidad());
//                    System.out.println("CANTIDAD:   " + cantidades);
//                }
//            }
//            System.out.println("CAntidades -> " + cantidades);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cantidades;
//    }
    @Override
    public String insertaIngredienteAProducto(IngredienteProducto ip) throws Exception {

        String insertado = "Ingredientes no añadidos al producto " + ip.getIdProducto();
        String sql = "Insert into ingrediente_producto values(?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            for (int i = 0; i < ip.getAl().size(); i++) {
                stm.setInt(1, ip.getIdProducto());
                stm.setInt(2, ip.getAl().get(i));
                stm.executeUpdate();
            }
            insertado = "Ingredientes añadidos al producto " + ip.getIdProducto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;

    }

    @Override
    public String finalizarPedido(int idPedido) throws Exception {
        String borrado = "Pedido no borrado";
        String sql = "UPDATE pedido SET finalizado = 1 WHERE idPedido = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idPedido);
            int filasActualizadas = stm.executeUpdate();
            if (filasActualizadas > 0) {
                borrado = "Pedido borrado correctamente";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrado;
    }
}
