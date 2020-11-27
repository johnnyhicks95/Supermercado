/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.Persona;
/**
 *
 * @author ASUS
 */
public final class Administrador extends Persona {

    private final String adminUsuario;
    private final String adminContrasegna;
    
    public Administrador(String cedAdm, String nomAdm, String apeAdm, int edadAdm, char sexAdm) {
        super(cedAdm, nomAdm, apeAdm, edadAdm, sexAdm);
        this.adminUsuario = "admin";
        this.adminContrasegna = "1234";
    }
     
    
    public String getAdmUsuario(){
        return this.adminUsuario;
    }
    
    public String getAdmContrasegna(){
        return this.adminContrasegna;
    }
}
