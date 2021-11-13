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
public class Reserva {
    
    private int idusuario;
    private long idlibro;
    private String fechaini;
    private String fechafin;

    public Reserva(int idusuario, long idlibro, String fechaini, String fechafin) {
        this.idusuario = idusuario;
        this.idlibro = idlibro;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
    }

    public Reserva() {
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public long getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(long idlibro) {
        this.idlibro = idlibro;
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
