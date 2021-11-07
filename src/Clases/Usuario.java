/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Clases.Cliente;

/**
 *
 * @author jario
 */
public class Usuario {
    
    private String nombre;
    private int id;
    private String correo;
    private String pass;

     public Usuario() {
        this.nombre = "";
        this.id = 0;
        this.correo = "";
        this.pass = "";
    }

    
    public Usuario(String nombre, int id, String correo, String pass) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
