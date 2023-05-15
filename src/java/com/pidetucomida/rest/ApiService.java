/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.rest;

import com.pidetucomida.DAOImplementation.DAOImplementation;
import com.pidetucomida.pojo.Producto;
import com.pidetucomida.pojo.Cliente;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
    public ArrayList<Producto>getProducto(){
        ArrayList<Producto> productos=null;
        Producto p= null;
        try (DAOImplementation imp=new DAOImplementation()){
            productos= imp.devuelveProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
    
    
    @Path("/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean setUser(Cliente c) {
        boolean insertado= false;
        try (DAOImplementation ui = new DAOImplementation();) {
        insertado=ui.insertaCliente(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertado;
    };
    
    
    @Path("/cliente/{correo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getProducto(@PathParam("correo") String correo){
        Cliente c= null;
        try (DAOImplementation imp=new DAOImplementation()){
            c = imp.devuelveCliente(correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    @Path("/productos/{tipo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Producto>getProductPorTipo(@PathParam("tipo") String tipo){
        ArrayList<Producto> productos=null;
        Producto p= null;
        try (DAOImplementation imp=new DAOImplementation()){
            productos= imp.getProductosPorTipo(tipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
    
}
