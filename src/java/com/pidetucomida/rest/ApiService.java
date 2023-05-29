/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.rest;

import com.pidetucomida.DAOImplementation.DAOImplementation;
import com.pidetucomida.pojo.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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

    ;
    
    
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
    public String setProducto(Producto p) {
        String insertado = "No insertado";
        try (DAOImplementation ui = new DAOImplementation();) {
            insertado = ui.insertaProducto(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }

    ;
    
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
            System.out.println("IDPEDIDO:::::::::::: "+ insertado);
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
            System.out.println("PRODUCTOS_PEDIDO:::::::::::: "+ insertado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    }
}
