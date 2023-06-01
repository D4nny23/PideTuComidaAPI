/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pidetucomida.pojo;

/**
 *
 * @author dev
 */
public class Cliente {

    private int idCliente;
    private String correo;
    private String pass;
    private String nombre;
    private String apellido;
    private String direccionEnvio;
    private String telefono;

    public Cliente(int idCliente, String nombre, String correo, String direccionEnvio, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.direccionEnvio = direccionEnvio;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", correo=" + correo + ", pass=" + pass + ", nombre=" + nombre + ", apellido=" + apellido + ", direccionEnvio=" + direccionEnvio + ", telefono=" + telefono + '}';
    }

    public Cliente() {
    }

    public Cliente(int idCliente, String correo, String pass, String nombre, String apellido, String direccionEnvio, String telefono) {
        this.idCliente = idCliente;
        this.correo = correo;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccionEnvio = direccionEnvio;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}
