/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jario
 */
public class Servidor {
    
     
    private ServerSocket server;
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    public Servidor() throws IOException {
        server = new ServerSocket(1555, 5);
    }
    
    public void init() throws IOException{
        System.out.println("esperando conexiones");
        
        socket = server.accept();
        input = socket.getInputStream();
        output = socket.getOutputStream();
        enviarMsg("Hola mundo servidor>>>>>");
    }
    
    public void enviarMsg(String msg) throws IOException{
        
        byte[] bts = msg.getBytes();
        for (int i = 0; i < bts.length; i++) {
            output.write(bts[i]);
        }
        output.write(0x0A);
    }
    
    
    public void procesarConexion(){
        try {
            if (input.available() == 0){
                int b;
                boolean sw = true;
                StringBuilder sb = new StringBuilder();
                while((b=input.read()) != -1){
                    System.out.println("->");
                    if(b == 10){
                        String msg = sb.toString();
                        System.out.println("-->"+msg);
                        sb.setLength(0);
                        if(sw){
                            sw = false;
                        }else{
                            break;
                        }

                    }else{
                        sb.append((char)b);
                    }
                }
            }
            System.out.println("cerrando conexiones");
            cerrar();
        } catch (IOException ex) {
            System.out.println("Error en la lectura;"+ex.getMessage());
        }
        
        
    }
    
    public void cerrar() throws IOException{
        input.close();
        output.flush();
        output.close();
        socket.close();
    }
    
    
    
}
