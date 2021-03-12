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
public class Producto {
    private Integer id;
    private String codigo;
    private String nombre;
    private String stock;
    private float precio;
    private Integer tienda_id;

    public Producto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getTienda_id() {
        return tienda_id;
    }

    public void setTienda_id(Integer tienda_id) {
        this.tienda_id = tienda_id;
    }
    
    
    
}
