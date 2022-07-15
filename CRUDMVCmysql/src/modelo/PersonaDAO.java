/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author carlos
 */
public class PersonaDAO {
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList listar(){
        ArrayList<Persona>datos = new ArrayList<>();
        String sql= "SELECT * FROM persona";
        try {
            conn =conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setTelefono(rs.getString(4));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int agregar(Persona p){
        String sql = "insert into persona(Nombres, Correo, Telefono) values(?,?,?)";
        try{
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefono());
            ps.executeUpdate();
        }catch(Exception e){}
        return 1;
    }
    
    public int actualizar(Persona p){
        int r = 0;
        String sql = "update persona set Nombres=?, Correo=?, Telefono=? where Id=?";
        try{
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre() );
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefono());
            ps.setInt(4, p.getId());
            r = ps.executeUpdate();
            if(r ==1){
                return 1;
            }else{
                return 0;
            }
        }catch(Exception e){}
        return r;
    }
    
    public void eliminar(int id){
        System.out.print(id);
        String sql = "delete from persona where id="+id;
        try {
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
