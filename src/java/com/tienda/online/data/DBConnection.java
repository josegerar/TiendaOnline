/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.online.data;

import com.tienda.online.util.Constants;
import com.tienda.online.util.ReflectToClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public abstract class DBConnection {

    Connection conex;

    public DBConnection() {
        conex = getConecction();
    }

    protected boolean openConecction() {
        try {
            if (conex == null || conex.isClosed()) {
                conex = getConecction();
                return conex != null;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return false;
    }

    protected synchronized Connection getConecction() {
        Connection conecction = null;
        try {
            Class.forName("org.postgresql.Driver");
            conecction = DriverManager.getConnection("jdbc:postgresql://" + Constants.DBHOST + ":" + Constants.DBPORT + "/" + Constants.DBNAME, Constants.DBUSER, Constants.DBPASSWORD);
        } catch (ClassNotFoundException | SQLException exc) {
            System.out.println("No connection");
            System.out.println(exc.getMessage());
        }
        return conecction;
    }

    protected boolean closeConnection() {
        try {
            if (conex != null && !conex.isClosed()) {
                conex.close();
            }
        } catch (SQLException exc) {
            System.out.println("Error close connection:" + exc.getMessage());
            return false;
        }
        return true;
    }

    public boolean executeQuery(String sentecy) {
        if (openConecction()) {
            try (Statement st = conex.createStatement()) {
                st.execute(sentecy);
            } catch (Exception exc) {
                System.out.println("Error ModifyBD:" + exc.getMessage());
                return false;
            } finally {
                closeConnection();
            }
            return true;
        } else {
            return false;
        }
    }

    public int executeUpdateQuery(String sentecy) {
        int counts = 0;
        if (openConecction()) {
            try (Statement st = conex.createStatement()) {
                counts = st.executeUpdate(sentecy);
            } catch (Exception exc) {
                System.out.println("Error UpdateBD:" + exc.getMessage());
                counts = 0;
            } finally {
                closeConnection();
            }
        } else {
            counts = 0;
        }
        return counts;
    }

    public boolean testConection() {
        boolean test = openConecction();
        if (test) {
            closeConnection();
        }
        System.out.println("test:" + test);
        return test;
    }

    public <T> ArrayList<T> getObjectDB(String sql, Class<T> obj, int structure) {
        ArrayList<T> datos = new ArrayList();
        if (openConecction()) {
            try (Statement stm = conex.createStatement()) {
                try (ResultSet rs = stm.executeQuery(sql)) {
                    datos = ReflectToClass.putResult(rs, obj, structure);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection();
            }
        }
        return datos;
    }

}
