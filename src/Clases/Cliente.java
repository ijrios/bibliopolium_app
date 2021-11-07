/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author jario
 */
public class Cliente extends Usuario{
    
  
    private long celular;
    private String direccion;

   

    public Cliente(String nombre, int id, String correo,long celular, String pass, String direccion) {
        super(nombre, id, correo, pass);
         this.celular = celular;
         this.direccion = direccion;
    }
   
    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
   
    
}
