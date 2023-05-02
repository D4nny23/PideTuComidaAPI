/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.interfaces;

import com.pidetucomida.pojo.Producto;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public interface DAOInterface {
    public ArrayList<Producto> devuelveProductos() throws Exception;
}
