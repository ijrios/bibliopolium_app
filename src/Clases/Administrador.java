/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author jario
 */
public class Administrador extends Usuario{

   
    public Administrador(String nombre, int id, String correo, String pass) {
        super(nombre, id, correo, pass);
    }

    @Override
    public String toString() {
        return "Usuario Administrador";
    }

   
   
    
}
