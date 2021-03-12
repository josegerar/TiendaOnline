/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.online.webservice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tienda.online.controller.UsuarioController;
import com.tienda.online.model.ResultData;
import com.tienda.online.model.Usuario;
import com.tienda.online.util.Constants;
import com.tienda.online.util.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Usuario
 */
@Path("api")
public class TiendaResource {

    @Context
    private UriInfo context;
    
    UsuarioController usuarioController;

    /**
     * Creates a new instance of TiendaResource
     */
    public TiendaResource() {
        usuarioController = new UsuarioController();
    }

    /**
     * Retrieves representation of an instance of
     * com.tienda.online.webservice.TiendaResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TiendaResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response LogIn(String data) {
        JsonArray Jso = Json.stringToJSONArray(data);
        Usuario usuario = Json.reflectJSONArrayToObject(Jso, Usuario.class);
        ResultData resultData;
        if (usuario != null) {
            resultData = usuarioController.Login(usuario.getUsername(), usuario.getPassword());
        } else {
            resultData = new ResultData();
            resultData.setEstado(Constants.RESSTATUSERROR);
            resultData.setInformacion("Datos faltantes");
            resultData.setDatos("{}");
        }
        return Response.ok(new Gson().toJson(resultData))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/registroUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registroUsuario(String data) {
        JsonArray Jso = Json.stringToJSONArray(data);
        Usuario usuario = Json.reflectJSONArrayToObject(Jso, Usuario.class);
        ResultData resultData;
        if (usuario != null) {
            resultData = usuarioController.registrarUsuario(usuario, Constants.USERCLIENTE);
        } else {
            resultData = new ResultData();
            resultData.setEstado(Constants.RESSTATUSERROR);
            resultData.setInformacion("Datos faltantes");
            resultData.setDatos("{}");
        }
        return Response.ok(new Gson().toJson(resultData))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/registroEmpresa")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registroEmpresa(String data) {
        JsonArray Jso = Json.stringToJSONArray(data);
        Usuario usuario = Json.reflectJSONArrayToObject(Jso, Usuario.class);
        ResultData resultData;
        if (usuario != null) {
            resultData = usuarioController.registrarUsuario(usuario, Constants.USEREMPRESA);
        } else {
            resultData = new ResultData();
            resultData.setEstado(Constants.RESSTATUSERROR);
            resultData.setInformacion("Datos faltantes");
            resultData.setDatos("{}");
        }
        return Response.ok(new Gson().toJson(resultData))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
}
