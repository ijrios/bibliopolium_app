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
public class Reseña {
    
    private int idreseña;
    private int idusuario;
    private long idlibro;
    private String descripcion;
    private int calificación;

    public Reseña(int idreseña, int idusuario, long idlibro, String descripcion, int calificación) {
        this.idreseña = idreseña;
        this.idusuario = idusuario;
        this.idlibro = idlibro;
        this.descripcion = descripcion;
        this.calificación = calificación;
    }

    public int getIdreseña() {
        return idreseña;
    }

    public void setIdreseña(int idreseña) {
        this.idreseña = idreseña;
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

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCalificación() {
        return calificación;
    }

    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }

        
}
