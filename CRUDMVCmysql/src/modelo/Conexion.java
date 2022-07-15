/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author carlos
 */
public class Conexion {
    Connection conn;
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/testing";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","carlos");
        }catch(Exception e){
            //
        }
        return conn;
    }
}
