/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.online.controller.dao;

import com.tienda.online.data.DBConnection;
import com.tienda.online.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO extends DBConnection {

    public UsuarioDAO() {
        super();
    }

    public Usuario login(String username) {
        String sentency = String.format("select* from public.usuario where username = '%s'", username);
        ArrayList<Usuario> listUser = getObjectDB(sentency, Usuario.class, 1);
        if (listUser.size() > 0) {
            return listUser.get(0);
        } else {
            return new Usuario();
        }
    }

    public boolean saveUser(Usuario usuario, Integer userType) {
        String sentency = String.format("INSERT INTO public.usuario( \n"
                + "rol_id, username, password, nombres, apellidos, ruc_ced, telefono, email, isactive) \n"
                + "VALUES (%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', false);",
                userType, usuario.getUsername(), usuario.getPassword(), usuario.getNombres(),
                usuario.getApellidos(), usuario.getRuc_ced(), usuario.getTelefono(), usuario.getEmail());
        return executeQuery(sentency);
    }
}
