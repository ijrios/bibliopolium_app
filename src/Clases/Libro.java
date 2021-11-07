/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.UUID;
import javax.swing.ImageIcon;

/**
 *
 * @author jario
 */
public class Libro {
    

    private long idlibro;
    private String titulo;
    private String editorial;
    private String formato;
    private String autor;
    private String categoria;
    private long precio;
    private String idioma;
    private String sinapsis;
    private int paginas;
    private String fecha;
    private String edicion;
    private String isbn;
    private String encuadernacion;
    private ImageIcon imagen;

   
    
    public Libro(long idlibro, String titulo, String editorial, String formato, String autor, String categoria, long precio, String idioma, String sinapsis, int paginas, String fecha, String edicion, String isbn, String encuadernacion, ImageIcon imagen) {
        this.idlibro = idlibro;
        this.titulo = titulo;
        this.editorial = editorial;
        this.formato = formato;
        this.autor = autor;
        this.categoria = categoria;
        this.precio = precio;
        this.idioma = idioma;
        this.sinapsis = sinapsis;
        this.paginas = paginas;
        this.fecha = fecha;
        this.edicion = edicion;
        this.isbn = isbn;
        this.encuadernacion = encuadernacion;
        this.imagen = imagen;
    }

    public Libro() {
       
    }

    public long getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getSinapsis() {
        return sinapsis;
    }

    public void setSinapsis(String sinapsis) {
        this.sinapsis = sinapsis;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEncuadernacion() {
        return encuadernacion;
    }

    public void setEncuadernacion(String encuadernacion) {
        this.encuadernacion = encuadernacion;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    
}
