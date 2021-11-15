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
    
    private int idreserva;
    private int idusuario;
    private long idlibro;
    private String fechaini;
    private String fechafin;
    private boolean estado;

    public Reserva() {
    }

    public Reserva(int idreserva, int idusuario, long idlibro, String fechaini, String fechafin, boolean estado) {
        this.idreserva = idreserva;
        this.idusuario = idusuario;
        this.idlibro = idlibro;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.estado = estado;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
