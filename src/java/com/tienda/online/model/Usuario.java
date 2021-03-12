/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.online.model;

/**
 *
 * @author Usuario
 */
public class Usuario {
    
    private Integer id;
    private Integer rol_id;
    private String username;
    private String password;
    private String nombres;
    private String apellidos;
    private String ruc_ced;
    private String telefono;
    private String email;
    private boolean isactive;

    public Usuario() {
    }

    public Usuario(Integer id, Integer rol_id, String username, String password, String nombres, String apellidos, String ruc_ced, String telefono, String email, boolean isactive) {
        this.id = id;
        this.rol_id = rol_id;
        this.username = username;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ruc_ced = ruc_ced;
        this.telefono = telefono;
        this.email = email;
        this.isactive = isactive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRuc_ced() {
        return ruc_ced;
    }

    public void setRuc_ced(String ruc_ced) {
        this.ruc_ced = ruc_ced;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", rol_id=" + rol_id + ", username=" + username + ", password=" + password + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ruc_ced=" + ruc_ced + ", telefono=" + telefono + ", email=" + email + ", isactive=" + isactive + '}';
    }
    
    
}
