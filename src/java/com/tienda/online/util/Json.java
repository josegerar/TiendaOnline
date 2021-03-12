/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.online.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Json {

    public static final Gson GSON = new Gson();

    public static JsonObject stringToJSON(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject Jso = parser.parse(json).getAsJsonObject();
            return Jso;
        } catch (JsonSyntaxException e) {
            System.out.println(e.getMessage());
            return new JsonObject();
        }
    }

    public static JsonArray stringToJSONArray(String json) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray Jso = parser.parse(json).getAsJsonArray();
            return Jso;
        } catch (JsonSyntaxException e) {
            System.out.println(e.getMessage());
            return new JsonArray();
        }
    }

    public static String JsonToString(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            return defaulx;
        }
    }

    public static JsonElement securGetJSON(JsonObject jso, String param) {
        try {
            JsonElement res = jso.get(param);//request.getParameter(param);
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public static String objectToJsonString(Object obj) {
        String result;
        result = GSON.toJson(obj);
        return result;
    }

    public static <T> T stringJsonToObject(String json, Class<T> obj) {
        return GSON.fromJson(json, obj);
    }

    public static <T> T reflectJSONArrayToObject(JsonArray jsonArray, Class<T> obj) {
        try {
            T newInstance = obj.newInstance();

            for (JsonElement jo : jsonArray) {
                JsonObject jsonObject = jo.getAsJsonObject();
                String name = jsonObject.get("name").getAsString();
                String value = jsonObject.get("value").getAsString();
                Class<?> type = obj.getDeclaredField(name).getType();
                String substring = name.substring(0, 1);// title case
                String replace = name.replaceFirst(substring, substring.toUpperCase());
                Method method = obj.getMethod("set" + replace, type);
                if (type.isAssignableFrom(String.class)) {
                        method.invoke(newInstance, value);
                    } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
                        method.invoke(newInstance, value);// byte The data type is an 8-bit signed integer represented by a binary complement
                    } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
                        method.invoke(newInstance, value);// short The data type is a 16 bit signed integer represented by a binary complement
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        method.invoke(newInstance, Integer.parseInt(value));// int The data type is a 32-bit signed integer represented by a binary complement
                    } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
                        method.invoke(newInstance, Long.parseLong(value));// long The data type is a 64 bit signed integer represented by a binary complement
                    } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
                        method.invoke(newInstance, Float.parseFloat(value));// float Data type is single precision, 32-bit, compliant IEEE 754 Standard floating point number
                    } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
                        method.invoke(newInstance, Double.parseDouble(value));// double Data type is double, 64 bit, compliant IEEE 754 Standard floating point number
                    } else if (type.isAssignableFrom(BigDecimal.class)) {
                        method.invoke(newInstance, BigDecimal.valueOf(Long.parseLong(value)));
                    } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
                        method.invoke(newInstance, Boolean.parseBoolean(value));// boolean Data type represents one bit of information
                    } else if (type.isAssignableFrom(Date.class)) {
                        method.invoke(newInstance, new Date());
                    }
            }
            return newInstance;
        } catch (Exception ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
