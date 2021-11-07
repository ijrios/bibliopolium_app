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
    
    private int idsesion;
    private int idusuario;
    private String fechaini;
    private String fechafin;
    private Long idlibro;

    public Reserva(int idsesion, int idusuario, String fechaini, String fechafin, Long idlibro) {
        this.idsesion = idsesion;
        this.idusuario = idusuario;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.idlibro = idlibro;
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

    public Long getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(Long idlibro) {
        this.idlibro = idlibro;
    }
    
    
    
}
