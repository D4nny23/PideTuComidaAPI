/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.rest;

import com.pidetucomida.DAOImplementation.DAOImplementation;
import com.pidetucomida.pojo.Producto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 *
 * @author dev
 */

@Path("productos")
public class ApiService {
    
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
    
}
