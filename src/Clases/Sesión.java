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
public class Sesión {
    
    private int idsesion;
    private int idusuario;
    private String fechaini;
    private String fechafin;

    public Sesión(int idsesion, int idusuario, String fechaini, String fechafin) {
        this.idsesion = idsesion;
        this.idusuario = idusuario;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
    }

    public int getIdsesion() {
        return idsesion;
    }

    public void setIdsesion(int idsesion) {
        this.idsesion = idsesion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getFechaini() {
        return fechaini;
    }

    public void setFechaini(String fechaini) {
        this.fechaini = fechaini;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }
    
    
    
}
