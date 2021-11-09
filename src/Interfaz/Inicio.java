/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.Administrador;
import Clases.Cliente;
import Clases.GestionDatos;
import Clases.Libro;
import Clases.Reseña;
import Clases.Usuario;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;


/**
 *
 * @author jario
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    GestionDatos s = new GestionDatos();
    Libro b = new Libro();
    ArrayList<Usuario> myuser = new ArrayList();
    ArrayList<Libro> mybook = new ArrayList();
    ArrayList<Reseña> mytop = new ArrayList();
    HashMap<Libro, Integer> map = new HashMap<>();
    ArrayList<String> lista = new ArrayList();
    ArrayList<String> categoria0 = new ArrayList();
    ArrayList<String> nomformato = new ArrayList();
    ArrayList<String> nomedicion = new ArrayList();
    ArrayList<String> nomencuadernacion = new ArrayList();
    private DefaultTableModel tmodel; // Libros
    private DefaultTableModel tmodel1; // Usuarios
    private DefaultTableModel tmodel2; //Reseñas
    private String[] nomcolusuarios = {"Nombre", "ID", "Correo", "Celular"};
    private String[] tops1 = {"Id libro", "Titulo", "Califación Total"};
    private String[] nomcolreseña = {"ID Reseña", "ID Usuario", "ID Libro", "Descripción","Calificación"};
    private String[] nomcols = {"ID", "Titulo", "Editorial", "Formato", "Autor", "Categoria", "Precio", "Idioma", "Reseña", "Número de paginas", "Fecha Publicacion", "Numero Edicion", "ISBN", "Encuadernacion"};
    private DefaultComboBoxModel cmodel;
    private DefaultComboBoxModel cmodel1;
    private DefaultComboBoxModel cmodel2;
    private DefaultComboBoxModel cmodel3;
    TableRowSorter trs;
  
    public Inicio() {
        initComponents();
        //Inicia ventana en el centro
        this.setLocationRelativeTo(null);
         // Cargamos archivos planos
        GestionDatos.cargarlibros();
        GestionDatos.cargaradministrador();
        GestionDatos.cargarclientes();
        GestionDatos.cargarreseñas();
        // Enviamos modelo de la tabla
        tmodel = new DefaultTableModel();
        tmodel1 = new DefaultTableModel();
        tmodel2 = new DefaultTableModel();
        Tabla.setModel(tmodel);
        Tabla1.setModel(tmodel1);
        Tabla2.setModel(tmodel2);
        s = new GestionDatos();
        //Se desactivan los botones para no permitir acceso
        ja.setVisible(false);
        je.setVisible(false);
        ji.setVisible(false);
        jo.setVisible(false);
        ju.setVisible(false);
        jaa.setVisible(false);
        jLabel67.setVisible(false);
        // LLenado de combobox 
        mybook = GestionDatos.getListbook();
        for (int i = 0; i < mybook.size(); i++) {
            String nomcats0 = mybook.get(i).getCategoria();
            if (nomcats0 != null && !nomcats0.trim().equals("")) {
//                    System.out.println(Arrays.toString(nomcats0));
                categoria0.add(mybook.get(i).getCategoria());
            }
        }
//        System.out.println(Arrays.toString(categoria0.toArray()));
        String[] nomformato = {"Digital", "Fisico"};
        String[] nomedicion = {"1ra", "2da", "3ra", "4ta", "5ta", "6ta", "7ma"};
        String[] nomencuadernacion = {"Tapa Dura", "Blanda Cosida", "Blanda Encolada", "Grapada", "Espiral Doble"};
        cmodel1 = new DefaultComboBoxModel(nomformato);
        cmodel2 = new DefaultComboBoxModel(nomedicion);
        cmodel3 = new DefaultComboBoxModel(nomencuadernacion);
        cmodel = new DefaultComboBoxModel(categoria0.toArray());
        //Enviamos la informacion agregada a los ComboBox
        jcategoria.setModel(cmodel);
        jcategoria1.setModel(cmodel);
        jComboBox20.setModel(cmodel);
        jformato.setModel(cmodel1);
        jformato1.setModel(cmodel1);
        jedicion1.setModel(cmodel2);
        jedicion.setModel(cmodel2);
        jencuadernacion.setModel(cmodel3);
        jencuadernacion1.setModel(cmodel3);
        
        // Obtenemos el indice de los datos filtrados para mostrar mada imagen
        Tabla.getSelectionModel().addListSelectionListener ( 
        new ListSelectionListener () { 
            public void valueChanged (ListSelectionEvent evento) { 
                int x = Tabla.getSelectedRow(); 
                if (x <0) { 
                    try {
                        Libro b = GestionDatos.getlibro(x);
                        jTextArea2.setText(b.getTitulo());
                        String im = b.getImagen().toString();
                        ImageIcon ii = new ImageIcon(System.getProperty("user.dir") + "/ImagenLibro/" + im);
                        jLabel13.setIcon(ii);
                        jTextArea1.setText(b.getSinapsis());
                    } catch (Exception e) {
                    }
                    
                } else { 
                    try {
                        int y = Tabla.convertRowIndexToModel(x);
                        Libro b = GestionDatos.getlibro(y);
                        jTextArea2.setText(b.getTitulo());
                        String im = b.getImagen().toString();
                        ImageIcon ii = new ImageIcon(System.getProperty("user.dir") + "/ImagenLibro/" + im);
                        jLabel13.setIcon(ii);
                        jTextArea1.setText(b.getSinapsis());

                    } catch (Exception e) {
                    }
                    
                } 
            } 

        } 
);
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LibrosOut = new javax.swing.JDialog();
        Ingreso1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        usu1 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        busqueda1 = new javax.swing.JTextField();
        edicion = new javax.swing.JLabel();
        isbn = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        isbn4 = new javax.swing.JLabel();
        isbn3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        LibrosIn = new javax.swing.JDialog();
        Ingreso2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        usu2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jtextautor = new javax.swing.JTextField();
        jencuadernacion = new javax.swing.JComboBox<>();
        formato = new javax.swing.JLabel();
        jformato = new javax.swing.JComboBox<>();
        paginas = new javax.swing.JLabel();
        encuadernacion = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jtextid = new javax.swing.JTextField();
        precio = new javax.swing.JLabel();
        jdate = new com.toedter.calendar.JDateChooser();
        categoria = new javax.swing.JLabel();
        jtexteditorial = new javax.swing.JTextField();
        jtextidioma = new javax.swing.JTextField();
        jtextreseña = new javax.swing.JTextField();
        idioma = new javax.swing.JLabel();
        jtexttitulo = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jedicion = new javax.swing.JComboBox<>();
        id = new javax.swing.JLabel();
        reseña = new javax.swing.JLabel();
        jtextisbn = new javax.swing.JTextField();
        jtextprecio = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        isbn1 = new javax.swing.JLabel();
        jcategoria = new javax.swing.JComboBox<>();
        fecha = new javax.swing.JLabel();
        jtextpaginas = new javax.swing.JTextField();
        edicion1 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        paginas1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        encuadernacion2 = new javax.swing.JLabel();
        img = new javax.swing.JTextField();
        LibrosUp = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jtextautor1 = new javax.swing.JTextField();
        jencuadernacion1 = new javax.swing.JComboBox<>();
        formato1 = new javax.swing.JLabel();
        jformato1 = new javax.swing.JComboBox<>();
        paginas2 = new javax.swing.JLabel();
        encuadernacion1 = new javax.swing.JLabel();
        titulo1 = new javax.swing.JLabel();
        jtextid1 = new javax.swing.JTextField();
        precio1 = new javax.swing.JLabel();
        jdate1 = new com.toedter.calendar.JDateChooser();
        categoria1 = new javax.swing.JLabel();
        jtexteditorial1 = new javax.swing.JTextField();
        jtextidioma1 = new javax.swing.JTextField();
        jtextreseña1 = new javax.swing.JTextField();
        idioma1 = new javax.swing.JLabel();
        jtexttitulo1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jedicion1 = new javax.swing.JComboBox<>();
        id1 = new javax.swing.JLabel();
        reseña1 = new javax.swing.JLabel();
        jtextisbn1 = new javax.swing.JTextField();
        jtextprecio1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        isbn2 = new javax.swing.JLabel();
        jcategoria1 = new javax.swing.JComboBox<>();
        fecha1 = new javax.swing.JLabel();
        jtextpaginas1 = new javax.swing.JTextField();
        edicion2 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        id2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Ingreso3 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        usu3 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        ClienteIn = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        idioma2 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        titulo3 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        idioma3 = new javax.swing.JLabel();
        idusu = new javax.swing.JTextField();
        titulo4 = new javax.swing.JLabel();
        titulo5 = new javax.swing.JLabel();
        dire = new javax.swing.JTextField();
        celuco = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        Ingreso4 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        usu4 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        ClienteOut = new javax.swing.JDialog();
        Ingreso6 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        usu5 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Admin = new javax.swing.JDialog();
        Ingreso5 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        usu6 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        idioma4 = new javax.swing.JLabel();
        correo1 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        name1 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        idioma5 = new javax.swing.JLabel();
        idusu1 = new javax.swing.JTextField();
        titulo7 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        Reseñas = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        descri = new javax.swing.JTextField();
        titulo2 = new javax.swing.JLabel();
        idreseña = new javax.swing.JTextField();
        idusuario = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        id3 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        puntaje = new javax.swing.JComboBox<>();
        titulo6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        idlibro1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla3 = new javax.swing.JTable();
        Ingreso7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        usu7 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel72 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jProgressBar1 = new javax.swing.JProgressBar();
        Ingreso = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        bienvenido = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ja = new javax.swing.JLabel();
        ji = new javax.swing.JLabel();
        je = new javax.swing.JLabel();
        ja2 = new javax.swing.JLabel();
        jaa = new javax.swing.JLabel();
        je2 = new javax.swing.JLabel();
        ji2 = new javax.swing.JLabel();
        jo = new javax.swing.JLabel();
        ju = new javax.swing.JLabel();
        jo2 = new javax.swing.JLabel();
        jaa2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        ju3 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        ju2 = new javax.swing.JLabel();

        LibrosOut.setUndecorated(true);
        LibrosOut.setResizable(false);
        LibrosOut.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ingreso1.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro (8).png"))); // NOI18N
        Ingreso1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, 201));

        usu1.setForeground(new java.awt.Color(153, 153, 153));
        usu1.setText("Ingrese Usuario\n");
        usu1.setBorder(null);
        Ingreso1.add(usu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 120, 36));
        Ingreso1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 170, 20));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 40, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        Ingreso1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 120, 90));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 160, 20));

        LibrosOut.getContentPane().add(Ingreso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 579));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBox20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox20ItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 130, -1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14"
            }
        ));
        Tabla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TablaFocusGained(evt);
            }
        });
        jScrollPane11.setViewportView(Tabla);

        jPanel2.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 830, 210));

        busqueda1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busqueda1KeyTyped(evt);
            }
        });
        jPanel2.add(busqueda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 210, -1));

        edicion.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        edicion.setForeground(new java.awt.Color(102, 102, 102));
        edicion.setText("Filtrar por Categoria");
        jPanel2.add(edicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, -1, -1));

        isbn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        isbn.setForeground(new java.awt.Color(102, 102, 102));
        isbn.setText("Filtrar por nombre");
        jPanel2.add(isbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 380, 50));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 254, 288, 310));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 380, 50));

        isbn4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        isbn4.setForeground(new java.awt.Color(102, 102, 102));
        isbn4.setText("Calificar libro");

        isbn3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        isbn3.setForeground(new java.awt.Color(102, 102, 102));
        isbn3.setText("Top 10");

        jButton6.setText("Review");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton6))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(isbn4)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(isbn3))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isbn4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isbn3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 480, 260, 80));

        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, -1, -1));

        LibrosOut.getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 930, 580));

        LibrosIn.setUndecorated(true);
        LibrosIn.setResizable(false);
        LibrosIn.setSize(new java.awt.Dimension(1126, 580));
        LibrosIn.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ingreso2.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro (2).png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        Ingreso2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 186, 201));

        usu2.setForeground(new java.awt.Color(153, 153, 153));
        usu2.setBorder(null);
        Ingreso2.add(usu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 156, 36));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 40, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });
        Ingreso2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 120, 90));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 270, 20));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel32.setText("Presione para guardar");
        Ingreso2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));
        Ingreso2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 270, 20));

        LibrosIn.getContentPane().add(Ingreso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 580));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jtextautor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 282, 180, -1));

        jPanel3.add(jencuadernacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 372, 180, -1));

        formato.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        formato.setForeground(new java.awt.Color(102, 102, 102));
        formato.setText("Formato");
        jPanel3.add(formato, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 232, -1, -1));

        jPanel3.add(jformato, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 232, 180, -1));

        paginas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        paginas.setForeground(new java.awt.Color(102, 102, 102));
        paginas.setText("Número de Paginas");
        jPanel3.add(paginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 182, -1, -1));

        encuadernacion.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        encuadernacion.setForeground(new java.awt.Color(102, 102, 102));
        encuadernacion.setText("Imagen");
        jPanel3.add(encuadernacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        titulo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(102, 102, 102));
        titulo.setText("Titulo");
        jPanel3.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 133, -1, -1));

        jtextid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextidKeyTyped(evt);
            }
        });
        jPanel3.add(jtextid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 83, 180, -1));

        precio.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        precio.setForeground(new java.awt.Color(102, 102, 102));
        precio.setText("Precio");
        jPanel3.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 372, -1, -1));

        jdate.setDateFormatString("YYYY/MM/dd");
        jPanel3.add(jdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 232, 180, -1));

        categoria.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        categoria.setForeground(new java.awt.Color(102, 102, 102));
        categoria.setText("Categoria");
        jPanel3.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 326, -1, -1));
        jPanel3.add(jtexteditorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 182, 180, -1));
        jPanel3.add(jtextidioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 83, 180, -1));
        jPanel3.add(jtextreseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 133, 180, -1));

        idioma.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        idioma.setForeground(new java.awt.Color(102, 102, 102));
        idioma.setText("Idioma");
        jPanel3.add(idioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 83, -1, -1));
        jPanel3.add(jtexttitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 133, 180, -1));

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("Autor");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 282, -1, -1));

        jPanel3.add(jedicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 282, 180, -1));

        id.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        id.setForeground(new java.awt.Color(102, 102, 102));
        id.setText("ID");
        jPanel3.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 83, -1, -1));

        reseña.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        reseña.setForeground(new java.awt.Color(102, 102, 102));
        reseña.setText("Sinapsis");
        jPanel3.add(reseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 133, -1, -1));
        jPanel3.add(jtextisbn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 332, 180, -1));

        jtextprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextprecioKeyTyped(evt);
            }
        });
        jPanel3.add(jtextprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 372, 180, -1));

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Editorial");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 182, -1, -1));

        isbn1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        isbn1.setForeground(new java.awt.Color(102, 102, 102));
        isbn1.setText("ISBN");
        jPanel3.add(isbn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 332, -1, -1));

        jPanel3.add(jcategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 326, 180, -1));

        fecha.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        fecha.setForeground(new java.awt.Color(102, 102, 102));
        fecha.setText("Fecha Publicación");
        jPanel3.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 232, -1, -1));

        jtextpaginas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextpaginasKeyTyped(evt);
            }
        });
        jPanel3.add(jtextpaginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 182, 180, -1));

        edicion1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        edicion1.setForeground(new java.awt.Color(102, 102, 102));
        edicion1.setText("Número Edición ");
        jPanel3.add(edicion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 282, -1, -1));
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 500, 211, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro (7).png"))); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 450, -1, -1));

        paginas1.setBackground(new java.awt.Color(255, 255, 255));
        paginas1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        paginas1.setForeground(new java.awt.Color(153, 153, 153));
        paginas1.setText("Ingrese ID para eliminar");
        jPanel3.add(paginas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, -1, -1));

        jButton5.setText("Insertar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        encuadernacion2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        encuadernacion2.setForeground(new java.awt.Color(102, 102, 102));
        encuadernacion2.setText("Encuadernación");
        jPanel3.add(encuadernacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 372, -1, -1));
        jPanel3.add(img, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 180, -1));

        LibrosIn.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 890, 580));

        LibrosUp.setUndecorated(true);
        LibrosUp.setResizable(false);
        LibrosUp.setSize(new java.awt.Dimension(1126, 580));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtextautor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextautor1ActionPerformed(evt);
            }
        });
        jPanel4.add(jtextautor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 372, 180, -1));

        jPanel4.add(jencuadernacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 462, 180, -1));

        formato1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        formato1.setForeground(new java.awt.Color(102, 102, 102));
        formato1.setText("Formato");
        jPanel4.add(formato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 322, -1, -1));

        jPanel4.add(jformato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 322, 180, -1));

        paginas2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        paginas2.setForeground(new java.awt.Color(102, 102, 102));
        paginas2.setText("Número de Paginas");
        jPanel4.add(paginas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 272, -1, -1));

        encuadernacion1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        encuadernacion1.setForeground(new java.awt.Color(102, 102, 102));
        encuadernacion1.setText("Encuadernación");
        jPanel4.add(encuadernacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 462, -1, -1));

        titulo1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo1.setForeground(new java.awt.Color(102, 102, 102));
        titulo1.setText("Titulo");
        jPanel4.add(titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 223, -1, -1));

        jtextid1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextid1KeyTyped(evt);
            }
        });
        jPanel4.add(jtextid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 173, 180, -1));

        precio1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        precio1.setForeground(new java.awt.Color(102, 102, 102));
        precio1.setText("Precio");
        jPanel4.add(precio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 462, -1, -1));

        jdate1.setDateFormatString("YYYY/MM/dd");
        jPanel4.add(jdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 322, 180, -1));

        categoria1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        categoria1.setForeground(new java.awt.Color(102, 102, 102));
        categoria1.setText("Categoria");
        jPanel4.add(categoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 416, -1, -1));
        jPanel4.add(jtexteditorial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 272, 180, -1));
        jPanel4.add(jtextidioma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 173, 180, -1));
        jPanel4.add(jtextreseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 223, 180, -1));

        idioma1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        idioma1.setForeground(new java.awt.Color(102, 102, 102));
        idioma1.setText("Idioma");
        jPanel4.add(idioma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 173, -1, -1));
        jPanel4.add(jtexttitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 223, 180, -1));

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setText("Autor");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 372, -1, -1));

        jPanel4.add(jedicion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 372, 180, -1));

        id1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        id1.setForeground(new java.awt.Color(102, 102, 102));
        id1.setText("ID");
        jPanel4.add(id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 173, -1, -1));

        reseña1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        reseña1.setForeground(new java.awt.Color(102, 102, 102));
        reseña1.setText("Sinapsis");
        jPanel4.add(reseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 223, -1, -1));
        jPanel4.add(jtextisbn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 422, 180, -1));

        jtextprecio1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextprecio1KeyTyped(evt);
            }
        });
        jPanel4.add(jtextprecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 462, 180, -1));

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("Editorial");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 272, -1, -1));

        isbn2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        isbn2.setForeground(new java.awt.Color(102, 102, 102));
        isbn2.setText("ISBN");
        jPanel4.add(isbn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 422, -1, -1));

        jPanel4.add(jcategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 416, 180, -1));

        fecha1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        fecha1.setForeground(new java.awt.Color(102, 102, 102));
        fecha1.setText("Fecha Publicación");
        jPanel4.add(fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 322, -1, -1));

        jtextpaginas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextpaginas1KeyTyped(evt);
            }
        });
        jPanel4.add(jtextpaginas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 272, 180, -1));

        edicion2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        edicion2.setForeground(new java.awt.Color(102, 102, 102));
        edicion2.setText("Número Edición ");
        jPanel4.add(edicion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 372, -1, -1));
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 90, -1, -1));

        id2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        id2.setForeground(new java.awt.Color(102, 102, 102));
        id2.setText("DIGITE ID QUE DESEA MODIFICAR");
        jPanel4.add(id2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 153, 153));
        jButton1.setText("Obtener");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        Ingreso3.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar (2).png"))); // NOI18N
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        Ingreso3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 186, 201));

        usu3.setForeground(new java.awt.Color(153, 153, 153));
        usu3.setBorder(null);
        Ingreso3.add(usu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 156, 36));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 40, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        Ingreso3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 120, 90));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 270, 20));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel43.setText("Presione para modificar libro");
        Ingreso3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));
        Ingreso3.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 270, 20));

        javax.swing.GroupLayout LibrosUpLayout = new javax.swing.GroupLayout(LibrosUp.getContentPane());
        LibrosUp.getContentPane().setLayout(LibrosUpLayout);
        LibrosUpLayout.setHorizontalGroup(
            LibrosUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LibrosUpLayout.createSequentialGroup()
                .addComponent(Ingreso3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        LibrosUpLayout.setVerticalGroup(
            LibrosUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LibrosUpLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(LibrosUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ingreso3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        ClienteIn.setUndecorated(true);
        ClienteIn.setResizable(false);
        ClienteIn.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        idioma2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        idioma2.setForeground(new java.awt.Color(102, 102, 102));
        idioma2.setText("Nombre");

        correo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correoFocusLost(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setText("Correo Electronico");

        titulo3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo3.setForeground(new java.awt.Color(102, 102, 102));
        titulo3.setText("Celular");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/grupo.png"))); // NOI18N

        idioma3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        idioma3.setForeground(new java.awt.Color(102, 102, 102));
        idioma3.setText("Id Usuario");

        titulo4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo4.setForeground(new java.awt.Color(102, 102, 102));
        titulo4.setText("Contraseña");

        titulo5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo5.setForeground(new java.awt.Color(102, 102, 102));
        titulo5.setText("Direccion");

        dire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direActionPerformed(evt);
            }
        });

        celuco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celucoActionPerformed(evt);
            }
        });
        celuco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celucoKeyTyped(evt);
            }
        });

        jPasswordField2.setText("jPasswordField2");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idioma3)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(idioma2)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(titulo3)
                            .addComponent(jLabel45)))
                    .addComponent(titulo4)
                    .addComponent(titulo5))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idusu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dire, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celuco, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel46)
                .addGap(472, 472, 472))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idioma2)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idioma3)
                            .addComponent(idusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo3)
                            .addComponent(celuco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo4)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo5)
                            .addComponent(dire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel46)))
                .addContainerGap())
        );

        ClienteIn.getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 890, 580));

        Ingreso4.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/programador (2).png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        Ingreso4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 186, 201));

        usu4.setForeground(new java.awt.Color(153, 153, 153));
        usu4.setText("Ingrese Usuario ");
        usu4.setBorder(null);
        Ingreso4.add(usu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 156, 36));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 40, -1));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        Ingreso4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 120, 90));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso4.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 270, 20));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel52.setText("Presione para guardar");
        Ingreso4.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));
        Ingreso4.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 270, 20));

        ClienteIn.getContentPane().add(Ingreso4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 580));

        ClienteOut.setUndecorated(true);
        ClienteOut.setResizable(false);

        Ingreso6.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/visualizar - copia.png"))); // NOI18N
        Ingreso6.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, 201));

        usu5.setForeground(new java.awt.Color(153, 153, 153));
        usu5.setBorder(null);
        Ingreso6.add(usu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 120, 36));
        Ingreso6.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 170, 20));

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso6.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 40, -1));

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
        });
        Ingreso6.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 120, 90));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso6.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso6.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 160, 20));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Tabla1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla1FocusGained(evt);
            }
        });
        jScrollPane12.setViewportView(Tabla1);

        jButton2.setText("Cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Administrador");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout ClienteOutLayout = new javax.swing.GroupLayout(ClienteOut.getContentPane());
        ClienteOut.getContentPane().setLayout(ClienteOutLayout);
        ClienteOutLayout.setHorizontalGroup(
            ClienteOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClienteOutLayout.createSequentialGroup()
                .addComponent(Ingreso6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ClienteOutLayout.setVerticalGroup(
            ClienteOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClienteOutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(ClienteOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ingreso6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Admin.setUndecorated(true);
        Admin.setResizable(false);
        Admin.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ingreso5.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/perfil2.png"))); // NOI18N
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });
        Ingreso5.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 210, 201));

        usu6.setForeground(new java.awt.Color(153, 153, 153));
        usu6.setText("Ingrese Usuario\n");
        usu6.setBorder(null);
        Ingreso5.add(usu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 156, 36));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso5.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 40, -1));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
        });
        Ingreso5.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 120, 90));

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso5.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 270, 20));

        jLabel57.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel57.setText("Presione para guardar");
        Ingreso5.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));
        Ingreso5.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 270, 20));

        Admin.getContentPane().add(Ingreso5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 580));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        idioma4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        idioma4.setForeground(new java.awt.Color(102, 102, 102));
        idioma4.setText("Nombre");

        correo1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correo1FocusLost(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setText("Correo Electronico");

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/administrador-de-sistema.png"))); // NOI18N

        idioma5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        idioma5.setForeground(new java.awt.Color(102, 102, 102));
        idioma5.setText("Id Usuario");

        titulo7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo7.setForeground(new java.awt.Color(102, 102, 102));
        titulo7.setText("Contraseña");

        jPasswordField3.setText("jPasswordField2");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idioma5)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(idioma4)
                        .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(titulo7))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correo1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idusu1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel58)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idioma4)
                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idioma5)
                    .addComponent(idusu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo7)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(194, 194, 194))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addGap(174, 174, 174))
        );

        Admin.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 890, 580));

        Reseñas.setUndecorated(true);
        Reseñas.setResizable(false);
        Reseñas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(descri, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 180, -1));

        titulo2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo2.setForeground(new java.awt.Color(102, 102, 102));
        titulo2.setText("ID libro");
        jPanel8.add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));
        jPanel8.add(idreseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 180, -1));
        jPanel8.add(idusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 180, -1));

        jLabel65.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(102, 102, 102));
        jLabel65.setText("Descripción");
        jPanel8.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        id3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        id3.setForeground(new java.awt.Color(102, 102, 102));
        id3.setText("ID reseña");
        jPanel8.add(id3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 102, 102));
        jLabel66.setText("Calificación");
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        puntaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jPanel8.add(puntaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 180, -1));

        titulo6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        titulo6.setForeground(new java.awt.Color(102, 102, 102));
        titulo6.setText("ID usuario");
        jPanel8.add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla2FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla2);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 740, 200));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/insatisfaccion.png"))); // NOI18N
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 290, 280));

        idlibro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idlibro1KeyTyped(evt);
            }
        });
        jPanel8.add(idlibro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 180, -1));

        jButton7.setText("Mostrar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, -1, -1));

        jButton9.setText("Top 10");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 530, -1, -1));

        Tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla3FocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla3);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 740, 100));

        Reseñas.getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 870, 640));

        Ingreso7.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libros (1) - copia.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        Ingreso7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 210, 201));

        usu7.setForeground(new java.awt.Color(153, 153, 153));
        usu7.setBorder(null);
        Ingreso7.add(usu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 156, 36));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso7.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 40, -1));

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/batras.png"))); // NOI18N
        jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel70MouseClicked(evt);
            }
        });
        Ingreso7.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 120, 90));

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel71.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso7.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));
        Ingreso7.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 270, 20));

        jLabel72.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        jLabel72.setText("Presione para guardar");
        Ingreso7.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));
        Ingreso7.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 270, 20));

        Reseñas.getContentPane().add(Ingreso7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 640));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ingreso.setBackground(new java.awt.Color(255, 255, 255));
        Ingreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingreso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Usuario");
        Ingreso.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña");
        Ingreso.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login.png"))); // NOI18N
        Ingreso.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 74, 186, 201));

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Ingrese Usuario\n");
        jTextField1.setBorder(null);
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField1MousePressed(evt);
            }
        });
        Ingreso.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 156, 36));
        Ingreso.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 270, 20));

        jPasswordField1.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField1.setText("ew ImageIat1");
        jPasswordField1.setBorder(null);
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPasswordField1MousePressed(evt);
            }
        });
        Ingreso.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, 30));
        Ingreso.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 230, 20));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/lock - copia.png"))); // NOI18N
        Ingreso.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 30, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (2).png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jLabel6.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jLabel6InputMethodTextChanged(evt);
            }
        });
        Ingreso.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 110, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ingreso.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logout (1).png"))); // NOI18N
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });
        Ingreso.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        Ingreso.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 40, -1));
        Ingreso.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 270, 20));

        getContentPane().add(Ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salida.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 301, 125, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setText("Ingresar Administrador");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 231, -1, -1));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel23.setText("Ingresar Cliente");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 231, -1, -1));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel24.setText("Visualizar Usuarios");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 229, -1, -1));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel25.setText("Ingresar y eliminar Libro");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 229, -1, -1));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel26.setText("Ingresar Reseña");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel27.setText("Visualizar Libro");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, -1, -1));

        bienvenido.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        bienvenido.setText(" ");
        jPanel1.add(bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 70, 20));

        ja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/perfil.png"))); // NOI18N
        ja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jaMouseClicked(evt);
            }
        });
        jPanel1.add(ja, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 134, -1));

        ji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nota.png"))); // NOI18N
        ji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jiMouseClicked(evt);
            }
        });
        jPanel1.add(ji, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 127, 122));

        je.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/programador.png"))); // NOI18N
        je.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jeMouseClicked(evt);
            }
        });
        jPanel1.add(je, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 139, 100));

        ja2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/perfil.png"))); // NOI18N
        jPanel1.add(ja2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 134, -1));

        jaa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar (1).png"))); // NOI18N
        jaa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jaaMouseClicked(evt);
            }
        });
        jPanel1.add(jaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 110, 112));

        je2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/programador.png"))); // NOI18N
        jPanel1.add(je2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 139, -1));

        ji2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nota.png"))); // NOI18N
        jPanel1.add(ji2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 127, 122));

        jo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro (5).png"))); // NOI18N
        jo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                joMouseClicked(evt);
            }
        });
        jPanel1.add(jo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 134, -1));

        ju.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro.png"))); // NOI18N
        ju.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                juMouseClicked(evt);
            }
        });
        jPanel1.add(ju, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 127, 112));

        jo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro (5).png"))); // NOI18N
        jPanel1.add(jo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 134, 120));

        jaa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualizar (1).png"))); // NOI18N
        jPanel1.add(jaa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 110, 112));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login (3).png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 40, -1));

        jLabel63.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel63.setText("Actualizar Libro");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        jLabel64.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel64.setText("Salir");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 453, -1, -1));

        ju3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro.png"))); // NOI18N
        jPanel1.add(ju3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 127, 112));

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libros (1).png"))); // NOI18N
        jPanel1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 127, 112));

        ju2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libros (1).png"))); // NOI18N
        ju2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ju2MouseClicked(evt);
            }
        });
        jPanel1.add(ju2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 127, 112));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 760, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void joMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joMouseClicked
        // TODO add your handling code here:
        LibrosIn.setVisible(true);
        LibrosIn.setSize(1126, 580);
        LibrosIn.setLocationRelativeTo(null);
        jtextid.setText(String.valueOf(AleatorioIDlibro()));
        jtextid.setEditable(false);
    }//GEN-LAST:event_joMouseClicked
 
    private void juMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_juMouseClicked
        // TODO add your handling code here:
        LibrosOut.setVisible(true);
        LibrosOut.setSize(1126, 580);
        LibrosOut.setLocationRelativeTo(null);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
                GestionDatos.libreria(),
                new String[]{
                    "ID", "Titulo", "Editorial", "Formato", "Autor", "Categoria", "Precio", "Idioma", "Reseña", "Número de paginas", "Fecha Publicacion", "Numero Edicion", "ISBN", "Encuadernacion"

                }
        ));
        
        tmodel.fireTableDataChanged();
        
    }//GEN-LAST:event_juMouseClicked

    private long validaLong(String number) {
        float result = 0; //Valor default.
        try {
            if (number != null) {
                result = Long.parseLong(number);
            }
        } catch (NumberFormatException nfe) {
            //*No es numerico!
        }
        return (long) result;
    }

    // Metodo para evitar excepcion java-lang-numberformatexception 
    private int ConvertIntoNumeric(String xVal) {
        try {
            return Integer.parseInt(xVal);
        } catch (Exception ex) {
            return 0;
        }
    }
    
     private  static int AleatorioIDlibro() {
            Random r = new Random();
            int n = r. nextInt();
            int r2;
            if (n > 0) {
              r2 = n;
         }else{
               r2 = n*(-1);
            }
            return r2;
    }
     private  static int AleatorioIDUsuario() {
            Random r = new Random();
            int n = r. nextInt();
            int r2;
            if (n > 0) {
              r2 = n-1000;
         }else{
               r2 = ((n*(-1))-1000);
            }
            return r2;
    }
      private  static int AleatorioIDreseña() {
            Random r = new Random();
            int n = r. nextInt();
            int r2;
            if (n > 0) {
              r2 = n-1000;
         }else{
               r2 = ((n*(-1))-1000);
            }
            return r2;
    }
 // Metodo para validar correo electronico 
    
    public boolean isEmail (String correo){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if(mat.find()){
            return true;
        }else{
            return false;
        }
    }

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:

        
        int id = ConvertIntoNumeric(jtextid.getText());
        String titulo = jtexttitulo.getText();
        String editorial = jtexteditorial.getText();
        String formato = jformato.getSelectedItem().toString();
        String autor = jtextautor.getText();
        String categoria = jcategoria.getSelectedItem().toString();
        long precio = validaLong(jtextprecio.getText().trim());
        String idioma = jtextidioma.getText();
        String reseña = jtextreseña.getText();
        int paginas = ConvertIntoNumeric(jtextpaginas.getText());
        //agregar fecha
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = dFormat.format(jdate.getDate());
        // fin agregar fecha
        String edicion = jedicion.getSelectedItem().toString();
        String isbn = jtextisbn.getText();
        String encuadernacion = jencuadernacion.getSelectedItem().toString();
        ImageIcon imagen = new ImageIcon(img.getText());

        GestionDatos.insertarLibro(id, titulo, editorial, formato, autor, categoria, precio, idioma, reseña, paginas, fecha, edicion, isbn, encuadernacion, imagen);

        JOptionPane.showMessageDialog(rootPane, "Libro registrado");
        //Limpiamos las celdas de los datos ingresados
        jtextid.setText(String.valueOf(AleatorioIDlibro()));
        jtexttitulo.setText("");
        jtexteditorial.setText("");
        jformato.setModel(cmodel1);
        jtextautor.setText("");
        jcategoria.setModel(cmodel);
        jtextprecio.setText("");
        jtextidioma.setText("");
        jtextreseña.setText("");
        jtextpaginas.setText("");
        img.setText("");
        //            jdate.setDateFormatString("");
        jedicion.setModel(cmodel2);
        jtextisbn.setText("");
        jencuadernacion.setModel(cmodel3);
        
        boolean sw = GestionDatos.guardar4();
        
        if(sw){
            JOptionPane.showMessageDialog(this, "Datos Almacenados Correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Datos NO almacenados", "Guardado", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
        LibrosIn.dispose();
        String[][] datos = GestionDatos.libreria();
        tmodel.setDataVector(datos, nomcols);
        tmodel.fireTableDataChanged();
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jtextidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextidKeyTyped
        // TODO add your handling code here:

        char validar = evt.getKeyChar();
        if (jtextid.getText().length() >= 5) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }
    }//GEN-LAST:event_jtextidKeyTyped

    private void jtextprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextprecioKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if (jtextprecio.getText().length() >= 10) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }


    }//GEN-LAST:event_jtextprecioKeyTyped

    private void jtextpaginasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextpaginasKeyTyped
        // TODO add your handling code here:

        char validar = evt.getKeyChar();
        if (jtextpaginas.getText().length() >= 8) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }
    }//GEN-LAST:event_jtextpaginasKeyTyped

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:

        int idd = ConvertIntoNumeric(jTextField4.getText());
        GestionDatos.EliminarLibro(idd);

    }//GEN-LAST:event_jLabel36MouseClicked

    private void jtextid1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextid1KeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if (jtextid.getText().length() >= 10) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }
    }//GEN-LAST:event_jtextid1KeyTyped

    private void jtextprecio1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextprecio1KeyTyped
        // TODO add your handling code here:
        
        char validar = evt.getKeyChar();
        if (jtextid.getText().length() >= 9) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }
    }//GEN-LAST:event_jtextprecio1KeyTyped

    private void jtextpaginas1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextpaginas1KeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if (jtextid.getText().length() >= 5) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }
    }//GEN-LAST:event_jtextpaginas1KeyTyped

    
    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:

        Libro libro;
        int id = ConvertIntoNumeric(jtextid1.getText());
        int index = GestionDatos.BuscarIndice(id);
        libro = GestionDatos.BuscarLibro(id);
        int sw = JOptionPane.showConfirmDialog(this, "Desea modificar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (sw == JOptionPane.YES_OPTION) {
            String titulo = jtexttitulo1.getText();
            String editorial = jtexteditorial1.getText();
            String formato = jformato1.getSelectedItem().toString();
            String autor = jtextautor1.getText();
            String categoria = jcategoria1.getSelectedItem().toString();
            long precio = validaLong(jtextprecio1.getText());
            String idioma = jtextidioma1.getText();
            String reseña = jtextreseña1.getText();
            int paginas = ConvertIntoNumeric(jtextpaginas1.getText());
            //agregar fecha
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fecha = dFormat.format(jdate.getDate());
//            // fin agregar fecha
            String edicion = jedicion1.getSelectedItem().toString();
            String isbn = jtextisbn1.getText();
            String encuadernacion = jencuadernacion1.getSelectedItem().toString();
            GestionDatos.modificarLibro(index,id, titulo, editorial, formato, autor, categoria, precio, idioma, reseña, paginas, fecha, edicion, isbn, encuadernacion);
            JOptionPane.showMessageDialog(null, "Libro modificado");
               jtextid1.setEditable(true);
               jtextid1.setText("");
             //Limpiamos las celdas de los datos ingresados
                jtexttitulo1.setText("");
                jtexteditorial1.setText("");
                jformato1.setModel(cmodel1);
                jtextautor1.setText("");
                jcategoria1.setModel(cmodel);
                jtextprecio1.setText("");
                jtextidioma1.setText("");
                jtextreseña1.setText("");
                jtextpaginas1.setText("");
                //            jdate.setDateFormatString("");
                jedicion.setModel(cmodel2);
                jtextisbn.setText("");
                jencuadernacion1.setModel(cmodel3);
        }
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
        LibrosUp.dispose();
    }//GEN-LAST:event_jLabel41MouseClicked
    
    private void jLabel6InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabel6InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6InputMethodTextChanged

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:

        ArrayList<Usuario> lista = s.getList();
        String correo = jTextField1.getText();
        String pass = jPasswordField1.getText();
        int sesion = AleatorioIDUsuario();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = dtf.format(LocalDateTime.now());
        int aux  = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            if (correo.equals("") && (pass.equals(""))) {
                JOptionPane.showMessageDialog(null, "Ingrese un usuario y clave validos");
            } else if(!correo.contains(lista.get(i).getCorreo()) && !pass.contains(lista.get(i).getPass())) {
                JOptionPane.showMessageDialog(null, "Usuario y Contraseña incorectos");
            } else if(correo.contains(lista.get(i).getCorreo()) && pass.contains(lista.get(i).getPass())) {
                
                if (lista.get(i)instanceof Administrador) {
                int id = lista.get(i).getId();
                String nombre = lista.get(i).getNombre();
                JOptionPane.showMessageDialog(null, "Usuario Existente, Bienvenido " + nombre);
                GestionDatos.inisiarSesion(sesion, id, fecha, "dd-MM-yyyy");
                jLabel29.setText(String.valueOf(id));
                ja.setVisible(true);
                je.setVisible(true);
                ji.setVisible(true);
                jo.setVisible(true);
                ju.setVisible(true);
                jaa.setVisible(true);
                jLabel67.setVisible(true);
                bienvenido.setText(nombre);
                usu1.setText(nombre);
                usu2.setText(nombre);
                usu3.setText(nombre);
                usu4.setText(nombre);
                usu5.setText(nombre);
                usu6.setText(nombre);
                usu7.setText(nombre);
                idusuario.setText(String.valueOf(id));
                break;
                }else if (lista.get(i)instanceof Cliente && aux ==1) {
                int id = lista.get(i).getId();
                String nombre = lista.get(i).getNombre();
                JOptionPane.showMessageDialog(null, "Usuario Existente, Bienvenido " + nombre);
                GestionDatos.inisiarSesion(sesion, id, fecha, "dd-MM-yyyy");
                ja.setVisible(false);
                je.setVisible(false);
                ji.setVisible(false);
                jo.setVisible(false);
                ju.setVisible(true);
                jaa.setVisible(false);
                jLabel67.setVisible(true);
                bienvenido.setText(nombre);
                jLabel29.setText(String.valueOf(id));
                usu1.setText(nombre);
                usu2.setText(nombre);
                usu3.setText(nombre);
                usu4.setText(nombre);
                usu5.setText(nombre);
                usu6.setText(nombre);
                usu7.setText(nombre);
                idusuario.setText(String.valueOf(id));
                break;
                }
                
            }
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:

        String nombre = name.getText();
        int id = ConvertIntoNumeric(idusu.getText().trim());
        String mail = correo.getText();
        long celular = Long.valueOf(celuco.getText());
        String pass = jPasswordField2.getText();
        String direcciono = dire.getText();
        String[][] datos = GestionDatos.UsuarioCliente();
        String[][] datos2 = GestionDatos.UsuarioaAdmin();
  
             for (String[] dato : datos) {
            if (mail.equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese un correo valido");
            } else if (mail.equals(dato[2])) {
                JOptionPane.showMessageDialog(null, "Correo existente, por favor ingrese otro");
            } else if (!mail.equals(dato[2])) {
               for (String[] datos21 : datos2) {
             if (mail.contains(datos21[2])) {
                JOptionPane.showMessageDialog(null, "Correo existente, por favor ingrese otro");
            } else if (!mail.contains(datos21[2])) {
                GestionDatos.insertarCLiente(nombre, id, mail, celular, pass, direcciono);
                JOptionPane.showMessageDialog(rootPane, "Usuario registrado");
                name.setText("");
                idusu.setText("");
                correo.setText("");
                celuco.setText("");
                dire.setText("");
                idusu.setText(String.valueOf(AleatorioIDUsuario()));
            }
           }
         }
      }
        boolean sw = GestionDatos.guardar2();
        
        if(sw){
            JOptionPane.showMessageDialog(this, "Datos Almacenados Correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Datos NO almacenados", "Guardado", JOptionPane.ERROR_MESSAGE);
        }

       
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        // TODO add your handling code here:
        ClienteIn.dispose();
    }//GEN-LAST:event_jLabel50MouseClicked

    private void direActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direActionPerformed

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        // TODO add your handling code here:

        String nombre = name1.getText();
        int id = ConvertIntoNumeric(idusu1.getText().trim());
        String mail = correo1.getText();
        String password = jPasswordField3.getText();
        String[][] datos = GestionDatos.UsuarioaAdmin();
        String[][] datos2 = GestionDatos.UsuarioCliente();
         
             for (String[] dato : datos) {
            if (mail.equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese un correo valido");
            } else if (mail.equals(dato[2])) {
                JOptionPane.showMessageDialog(null, "Correo existente, por favor ingrese otro");
            } else if (!mail.equals(dato[2])) {
               for (String[] datos21 : datos2) {
             if (mail.contains(datos21[2])) {
                JOptionPane.showMessageDialog(null, "Correo existente, por favor ingrese otro");
            } else if (!mail.contains(datos21[2])) {
                GestionDatos.generico(nombre, id, mail, password);
                JOptionPane.showMessageDialog(rootPane, "Usuario registrado");
                name1.setText("");
                correo1.setText("");
                idusu1.setText(String.valueOf(AleatorioIDUsuario()));
            }
           }
                
         }
      }
              boolean sw = GestionDatos.guardar();
        
        if(sw){
            JOptionPane.showMessageDialog(this, "Datos Almacenados Correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Datos NO almacenados", "Guardado", JOptionPane.ERROR_MESSAGE);
        }
 
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        // TODO add your handling code here:

        Admin.dispose();
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseClicked
        // TODO add your handling code here:
        ClienteOut.dispose();

    }//GEN-LAST:event_jLabel61MouseClicked

    private void Tabla1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla1FocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Le mandamos el modelo y lo usuarios a la tabla
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                GestionDatos.UsuarioCliente(),
                new String[]{
                    "Nombre", "ID", "Correo", "Celular", "contraseña", "Direccion"
                }
        ));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //Le mandamos el modelo y lo usuarios a la tabla
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                GestionDatos.UsuarioaAdmin(),
                new String[]{
                    "Nombre", "ID", "Correo", "contraseña"
                }
        ));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MousePressed
        // TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MousePressed

    private void jPasswordField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MousePressed
        // TODO add your handling code here:

        jPasswordField1.setText("");
    }//GEN-LAST:event_jPasswordField1MousePressed

    private void jaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jaMouseClicked
        // TODO add your handling code here:
        
        Admin.setVisible(true);
        Admin.setSize(1126, 580);
        Admin.setModal(true);
        Admin.setLocationRelativeTo(null);
        idusu1.setText(String.valueOf(AleatorioIDUsuario()));
        idusu1.setEditable(false);
    }//GEN-LAST:event_jaMouseClicked

    private void jeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jeMouseClicked
        // TODO add your handling code here:
        ClienteIn.setVisible(true);
        ClienteIn.setSize(1126, 580);
        ClienteIn.setLocationRelativeTo(null);
        idusu.setText(String.valueOf(AleatorioIDUsuario()));
        idusu.setEditable(false);
    }//GEN-LAST:event_jeMouseClicked

    private void jaaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jaaMouseClicked
        // TODO add your handling code here:
        LibrosUp.setVisible(true);
        LibrosUp.setSize(1126, 580);
        LibrosUp.setLocationRelativeTo(null);
        
        
    }//GEN-LAST:event_jaaMouseClicked

    private void jiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jiMouseClicked
        // TODO add your handling code here:
        ClienteOut.setVisible(true);
        ClienteOut.setSize(1126, 580);
        ClienteOut.setLocationRelativeTo(null);

    }//GEN-LAST:event_jiMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Libro libro;
        int id = ConvertIntoNumeric(jtextid1.getText());
        libro = GestionDatos.BuscarLibro(id);
       //Mandamos datos a modificar
        jtexttitulo1.setText(libro.getTitulo());
        jtexteditorial1.setText(libro.getEditorial());
        cmodel1.setSelectedItem(libro.getFormato());
        jformato1.setModel(cmodel1);
        jtextautor1.setText(libro.getAutor());
        cmodel.setSelectedItem(libro.getCategoria());
        jcategoria1.setModel(cmodel);
        jtextprecio1.setText(String.valueOf(libro.getPaginas()));
        jtextidioma1.setText(libro.getIdioma());
        jtextreseña1.setText(libro.getSinapsis());
        jtextpaginas1.setText(String.valueOf(libro.getPaginas()));
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fecha2 = libro.getFecha();
        try {
            Date fecha = dFormat.parse(fecha2);
            jdate1.setDate(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmodel2.setSelectedItem(libro.getEdicion());
        jedicion1.setModel(cmodel2);
        jtextisbn1.setText(libro.getIsbn());
        cmodel3.setSelectedItem(libro.getEncuadernacion());
        jencuadernacion1.setModel(cmodel3);
        jtextid1.setEditable(false);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtextautor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextautor1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtextautor1ActionPerformed

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
        int id = ConvertIntoNumeric(jLabel29.getText());
        GestionDatos.Buscarsesion(id);
        int index = GestionDatos.BuscarIndice2(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechafin = dtf.format(LocalDateTime.now());
        
        try {
            
        GestionDatos.modificarSesion(index, fechafin);
        JOptionPane.showMessageDialog(null, "Adios, cerrando sesión");
        ja.setVisible(false);
        je.setVisible(false);
        ji.setVisible(false);
        jo.setVisible(false);
        ju.setVisible(false);
        jaa.setVisible(false);
        jLabel67.setVisible(false);
        bienvenido.setText("");
        usu1.setText("");
        usu2.setText("");
        usu3.setText("");
        usu4.setText("");
        usu5.setText("");
        usu6.setText("");
        jTextField1.setText("Ingrese Usuario");
        jPasswordField1.setText("||||||||||||||||");
        jLabel29.setText("");
        
        
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("No existe un carácter en esa posición");
        }
        
    }//GEN-LAST:event_jLabel38MouseClicked

    private void correo1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correo1FocusLost
        // TODO add your handling code here:
         if(!isEmail(correo1.getText())){
            JOptionPane.showMessageDialog(null, "¡Debes validar el email!", "ATENCIÓN ADMINISTRADOR", JOptionPane.WARNING_MESSAGE);
            correo1.requestFocus();
        }
        
    }//GEN-LAST:event_correo1FocusLost

    private void correoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correoFocusLost
        // TODO add your handling code here:
         if(!isEmail(correo.getText())){
            JOptionPane.showMessageDialog(null, "¡Debes validar el email!", "ATENCIÓN ADMINISTRADOR", JOptionPane.WARNING_MESSAGE);
            correo.requestFocus();
        }
    }//GEN-LAST:event_correoFocusLost

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        
        
        int id1 = ConvertIntoNumeric(idreseña.getText());
        int id2 = ConvertIntoNumeric(idusuario.getText());
        long id3 = Long.parseLong(idlibro1.getText());
        String id = idlibro1.getText();
        String descripcion = descri.getText();
        int calificacion = ConvertIntoNumeric(puntaje.getSelectedItem().toString());
        String[][] datos = GestionDatos.libreria();
         
             for (String[] dato : datos) {
            if (idlibro1.equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese un id válido valido");
            } else if (id.equals(dato[0])) {
                JOptionPane.showMessageDialog(null, "Libro existente");
                
                        GestionDatos.reseña(id1, id2, id3, descripcion, calificacion);
                        JOptionPane.showMessageDialog(null, "Reseña Ingresada");
                        idreseña.setText(String.valueOf(AleatorioIDreseña()));
                      
                }
           }
            boolean sw = GestionDatos.guardar3();
        
        if(sw){
            JOptionPane.showMessageDialog(this, "Datos Almacenados Correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Datos NO almacenados", "Guardado", JOptionPane.ERROR_MESSAGE);
        }
 
         Tabla2.setModel(new javax.swing.table.DefaultTableModel(
                GestionDatos.reseñas(),
                new String[]{
                    "ID Reseña", "ID Usuario", "ID Libro", "Descripción","Calificación"

                }
        ));
   
         
         idlibro1.setText("");
         descri.setText("");
         
        
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseClicked
        // TODO add your handling code here:
        Reseñas.setVisible(false);
    }//GEN-LAST:event_jLabel70MouseClicked

    private void celucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celucoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celucoActionPerformed

    private void celucoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celucoKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        if (celuco.getText().length() >= 11) {
            evt.consume();
        }
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números");
        }
    }//GEN-LAST:event_celucoKeyTyped

    private void Tabla2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla2FocusGained
        // TODO add your handling code here:
        tmodel2.fireTableDataChanged();
    }//GEN-LAST:event_Tabla2FocusGained

    private void idlibro1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idlibro1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_idlibro1KeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        JFileChooser file = new JFileChooser();
        file.showOpenDialog(this);
        File archivo = file.getSelectedFile();

        //verificamos la seleccion de un archivo
        if (archivo != null) {
            try {
                //Definimos el destino del archivo
                String dest = System.getProperty("user.dir") + "/ImagenLibro/" + archivo.getName();
                Path Destino = Paths.get(dest);
                // Origen
                String orig = archivo.getPath();
                Path Origen = Paths.get(orig);
                //copiamos el nuevo archivo con la clase files 
                Files.copy(Origen, Destino, REPLACE_EXISTING);
                JOptionPane.showMessageDialog(rootPane, "El archivo fue copiado a la carpeta");
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        img.setText(archivo.getName());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Reseñas.setVisible(true);
        Reseñas.setSize(1126, 580);
        Reseñas.setLocationRelativeTo(null);
        String[][] reseñas = GestionDatos.SumarReseña();
       
        
//        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
//        for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
//            model.removeRow(i);
//        }
        
        try {
            Tabla2.setModel(new javax.swing.table.DefaultTableModel(
                    reseñas,
                    new String[]{
                        "ID Libro", "Titulo", "Calificación Total"

                    }
            ));

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Error al mostrar");
            
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void busqueda1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busqueda1KeyTyped
        // TODO add your handling code here:

        busqueda1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + busqueda1.getText(), 1));
            }

        });
        trs = new TableRowSorter(Tabla.getModel());
        Tabla.setRowSorter(trs);
    }//GEN-LAST:event_busqueda1KeyTyped

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        LibrosOut.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
                GestionDatos.reseñas(),
                new String[]{
                    "ID Reseña", "ID Usuario", "ID Libro", "Descripción", "Calificación"
                }
        ));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ju2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ju2MouseClicked
        // TODO add your handling code here:
        Reseñas.setVisible(true);
        Reseñas.setSize(1126, 580);
        Reseñas.setLocationRelativeTo(null);
        idreseña.setText(String.valueOf(AleatorioIDreseña()));
        idreseña.setEnabled(false);
        idusuario.setEnabled(false);
    }//GEN-LAST:event_ju2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int x = Tabla.getSelectedRow();
        //necesito obtener los datos
        if (x != -1) {
            Libro b = GestionDatos.getlibro(x);
            long id = b.getIdlibro();
            idlibro1.setText(String.valueOf(id));
            Reseñas.setVisible(true);
            Reseñas.setSize(1126, 580);
            Reseñas.setLocationRelativeTo(null);
            idreseña.setText(String.valueOf(AleatorioIDreseña()));
            idreseña.setEnabled(false);
            idusuario.setEnabled(false);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    
    
    private void jComboBox20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox20ItemStateChanged
        // TODO add your handling code here:
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) Tabla.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(jComboBox20.getSelectedItem().toString()));

        Tabla.setRowSorter(sorter);

    }//GEN-LAST:event_jComboBox20ItemStateChanged

    private void TablaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TablaFocusGained
        // TODO add your handling code here:
        String[][] datos = GestionDatos.libreria();
        tmodel.setDataVector(datos, nomcols);
        tmodel.fireTableDataChanged();
    }//GEN-LAST:event_TablaFocusGained

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) Tabla.getModel()));
        Tabla.setRowSorter(sorter);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         String[][] reseñas = GestionDatos.SumarReseña();
        
        try {
            
            Tabla2.setModel(new javax.swing.table.DefaultTableModel(
                    reseñas,
                    new String[]{
                        "ID Libro", "Titulo", "Calificación Total"

                    }
            ));
            

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Error al mostrar");
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void Tabla3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla3FocusGained

   
 
  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Admin;
    private javax.swing.JDialog ClienteIn;
    private javax.swing.JDialog ClienteOut;
    private javax.swing.JPanel Ingreso;
    private javax.swing.JPanel Ingreso1;
    private javax.swing.JPanel Ingreso2;
    private javax.swing.JPanel Ingreso3;
    private javax.swing.JPanel Ingreso4;
    private javax.swing.JPanel Ingreso5;
    private javax.swing.JPanel Ingreso6;
    private javax.swing.JPanel Ingreso7;
    private javax.swing.JDialog LibrosIn;
    private javax.swing.JDialog LibrosOut;
    private javax.swing.JDialog LibrosUp;
    private javax.swing.JDialog Reseñas;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla1;
    private javax.swing.JTable Tabla2;
    private javax.swing.JTable Tabla3;
    private javax.swing.JLabel bienvenido;
    private javax.swing.JTextField busqueda1;
    private javax.swing.JLabel categoria;
    private javax.swing.JLabel categoria1;
    private javax.swing.JTextField celuco;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField correo1;
    private javax.swing.JTextField descri;
    private javax.swing.JTextField dire;
    private javax.swing.JLabel edicion;
    private javax.swing.JLabel edicion1;
    private javax.swing.JLabel edicion2;
    private javax.swing.JLabel encuadernacion;
    private javax.swing.JLabel encuadernacion1;
    private javax.swing.JLabel encuadernacion2;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel fecha1;
    private javax.swing.JLabel formato;
    private javax.swing.JLabel formato1;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id1;
    private javax.swing.JLabel id2;
    private javax.swing.JLabel id3;
    private javax.swing.JLabel idioma;
    private javax.swing.JLabel idioma1;
    private javax.swing.JLabel idioma2;
    private javax.swing.JLabel idioma3;
    private javax.swing.JLabel idioma4;
    private javax.swing.JLabel idioma5;
    private javax.swing.JTextField idlibro1;
    private javax.swing.JTextField idreseña;
    private javax.swing.JTextField idusu;
    private javax.swing.JTextField idusu1;
    private javax.swing.JTextField idusuario;
    private javax.swing.JTextField img;
    private javax.swing.JLabel isbn;
    private javax.swing.JLabel isbn1;
    private javax.swing.JLabel isbn2;
    private javax.swing.JLabel isbn3;
    private javax.swing.JLabel isbn4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel ja;
    private javax.swing.JLabel ja2;
    private javax.swing.JLabel jaa;
    private javax.swing.JLabel jaa2;
    private javax.swing.JComboBox<String> jcategoria;
    private javax.swing.JComboBox<String> jcategoria1;
    private com.toedter.calendar.JDateChooser jdate;
    private com.toedter.calendar.JDateChooser jdate1;
    private javax.swing.JLabel je;
    private javax.swing.JLabel je2;
    private javax.swing.JComboBox<String> jedicion;
    private javax.swing.JComboBox<String> jedicion1;
    private javax.swing.JComboBox<String> jencuadernacion;
    private javax.swing.JComboBox<String> jencuadernacion1;
    private javax.swing.JComboBox<String> jformato;
    private javax.swing.JComboBox<String> jformato1;
    private javax.swing.JLabel ji;
    private javax.swing.JLabel ji2;
    private javax.swing.JLabel jo;
    private javax.swing.JLabel jo2;
    private javax.swing.JTextField jtextautor;
    private javax.swing.JTextField jtextautor1;
    private javax.swing.JTextField jtexteditorial;
    private javax.swing.JTextField jtexteditorial1;
    private javax.swing.JTextField jtextid;
    private javax.swing.JTextField jtextid1;
    private javax.swing.JTextField jtextidioma;
    private javax.swing.JTextField jtextidioma1;
    private javax.swing.JTextField jtextisbn;
    private javax.swing.JTextField jtextisbn1;
    private javax.swing.JTextField jtextpaginas;
    private javax.swing.JTextField jtextpaginas1;
    private javax.swing.JTextField jtextprecio;
    private javax.swing.JTextField jtextprecio1;
    private javax.swing.JTextField jtextreseña;
    private javax.swing.JTextField jtextreseña1;
    private javax.swing.JTextField jtexttitulo;
    private javax.swing.JTextField jtexttitulo1;
    private javax.swing.JLabel ju;
    private javax.swing.JLabel ju2;
    private javax.swing.JLabel ju3;
    private javax.swing.JTextField name;
    private javax.swing.JTextField name1;
    private javax.swing.JLabel paginas;
    private javax.swing.JLabel paginas1;
    private javax.swing.JLabel paginas2;
    private javax.swing.JLabel precio;
    private javax.swing.JLabel precio1;
    private javax.swing.JComboBox<String> puntaje;
    private javax.swing.JLabel reseña;
    private javax.swing.JLabel reseña1;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo3;
    private javax.swing.JLabel titulo4;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField usu1;
    private javax.swing.JTextField usu2;
    private javax.swing.JTextField usu3;
    private javax.swing.JTextField usu4;
    private javax.swing.JTextField usu5;
    private javax.swing.JTextField usu6;
    private javax.swing.JTextField usu7;
    // End of variables declaration//GEN-END:variables
}
