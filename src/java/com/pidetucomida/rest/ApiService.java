/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.rest;

import com.pidetucomida.DAOImplementation.DAOImplementation;
import com.pidetucomida.pojo.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
@Path("/api")
public class ApiService {

    @Path("/productos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Producto> getProducto() {
        ArrayList<Producto> productos = null;
        Producto p = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            productos = imp.getProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Path("/pedidos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Pedido> getPedido() {
        ArrayList<Pedido> pedidos = null;
        Pedido p = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            pedidos = imp.getPedidos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Path("/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean setUser(Cliente c) {
        boolean insertado = false;
        try (DAOImplementation ui = new DAOImplementation();) {
            insertado = ui.insertaCliente(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Path("/cliente/{correo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getProducto(@PathParam("correo") String correo) {
        Cliente c = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            c = imp.devuelveCliente(correo);
            System.out.println(c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Path("/productos/{tipo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Producto> getProductPorTipo(@PathParam("tipo") String tipo) {
        ArrayList<Producto> productos = null;
        Producto p = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            productos = imp.getProductosPorTipo(tipo);
            System.out.println(productos.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
//{"ruta":"/home/dev/Documentos/RelacionalV1.png","nombre":"Cheeseburger","idIngrediente":1,"precio":12.0, "tipo":"Hamburguesa"}

    @Path("/productos/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int setProducto(Producto p) {
        int idProducto = 0;
        try (DAOImplementation ui = new DAOImplementation();) {
            idProducto = ui.insertaProducto(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idProducto;
    }

    @Path("/productos/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Producto getProductoPorId(@PathParam("id") int id) {
        Producto p = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            p = imp.getProductoPorId(id);
            // System.out.println("PRODUCTO POR ID:" + p.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Path("/ingredientes/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Ingrediente> geIngredientesPorIdProducto(@PathParam("id") int id) {
        ArrayList<Ingrediente> ingredientes = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            ingredientes = imp.getIngredientesPorProductId(id);
            System.out.println(ingredientes.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

    @Path("/pedido/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int setPedido(Pedido p) {
        int insertado = 0;
        try (DAOImplementation ui = new DAOImplementation();) {
            insertado = ui.insertaPedido(p);
            System.out.println("IDPEDIDO:::::::::::: " + insertado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Path("/productos_pedido/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean setProducto_Pedido(Productos_pedido pp) {
        boolean insertado = false;
        try (DAOImplementation ui = new DAOImplementation();) {
            insertado = ui.insertaProductosPedido(pp);
            System.out.println("PRODUCTOS_PEDIDO:::::::::::: " + pp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Path("/pedidos/detalles_pedido/{idPedido}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getDetallesPedido(@PathParam("idPedido") int idPedido) {
        Cliente cliente = null;
        try (DAOImplementation imp = new DAOImplementation()) {
            cliente = imp.devuelveClientePorIdPedido(idPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Path("/pedidos/detalles_pedido/{idPedido}/productos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Producto> getProductosPorIdPedido(@PathParam("idPedido") int idPedido) {
        ArrayList<Producto> productos = new ArrayList<>();
        try (DAOImplementation imp = new DAOImplementation()) {
            productos = imp.getProductosPorIdPedido(idPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Path("/ingredientesInsert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ArrayList<Integer> setIngrediente(ArrayList<Ingrediente> i) {
        ArrayList<Integer> insertado = null;
        try (DAOImplementation ui = new DAOImplementation();) {
            insertado = ui.insertaIngredientes(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Path("/ingredientesInsertEnProducto")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertaIngredienteAProducto(IngredienteProducto ip) {
        String insertado = "";
        try (DAOImplementation ui = new DAOImplementation();) {
            insertado = ui.insertaIngredienteAProducto(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    @Path("/pedidos/detalles_pedido/{idPedido}/productos/cantidad")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Productos_pedido> getCantidadDeProductosPorPedido(@PathParam("idPedido") int idPedido) {
        ArrayList<Productos_pedido> cantidades = new ArrayList<>();
        try (DAOImplementation imp = new DAOImplementation()) {
            cantidades = imp.getCantidadDeProductosPorPedido(idPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidades;
    }
//    @Path("/pedidos/detalles_pedido/{idPedido}/productos/cantidad")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<Integer> getCantidadDeProductosPorPedido(@PathParam("idPedido") int idPedido) {
//        ArrayList<Integer> cantidades = new ArrayList<>();
//        try (DAOImplementation imp = new DAOImplementation()) {
//            cantidades = imp.getCantidadDeProductosPorPedido(idPedido);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cantidades;
//    }

    @Path("/pedidos/finalizarPedido/{idPedido}")
    @POST
    public String finalizarPedido(@PathParam("idPedido") int idPedido) {
        String borrado = "";
        try (DAOImplementation imp = new DAOImplementation()) {
            borrado = imp.finalizarPedido(idPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrado;
    }
}
