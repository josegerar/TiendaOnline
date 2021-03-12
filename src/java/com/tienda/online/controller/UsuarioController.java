/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.online.controller;

import com.tienda.online.controller.dao.UsuarioDAO;
import com.tienda.online.model.ResultData;
import com.tienda.online.model.Usuario;
import com.tienda.online.util.Constants;
import com.tienda.online.util.Json;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Usuario
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public ResultData Login(String username, String password) {
        ResultData resultData = new ResultData();
        Usuario user = usuarioDAO.login(username);
        String passwordEncript = this.encriptPassword(password);
        if (passwordEncript.equals(user.getPassword())) {
            resultData.setDatos(Json.objectToJsonString(user));
            resultData.setEstado(Constants.RESSTATUSUCCESS);
        } else {
            resultData.setEstado(Constants.RESSTATUSERROR);
            resultData.setInformacion("Usuario o clave invalidos");
        }
        return resultData;
    }

    private String encriptPassword(String pwd) {
        return DigestUtils.sha256Hex(pwd);
    }

    public ResultData registrarUsuario(Usuario usuario, Integer userType) {
        ResultData resultData = new ResultData();
        usuario.setPassword(this.encriptPassword(usuario.getPassword()));
        boolean result = usuarioDAO.saveUser(usuario, userType);
        if(result){
            resultData.setEstado(Constants.RESSTATUSUCCESS);
        } else {
            resultData.setEstado(Constants.RESSTATUSERROR);
            resultData.setInformacion("Ocurrio un error al intentar registrar el usuario");
        }
        return resultData;
    }
}
