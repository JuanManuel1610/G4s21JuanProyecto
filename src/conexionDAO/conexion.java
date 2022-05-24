/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionDAO;

import DTO.Datos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author user
 */
public class conexion {
    
    Connection conexion;
    List<Datos> listaDatos = new ArrayList<Datos>();
    
    public void abrir(){
        String user="root";
        String password="root";
        String url="jbc:mysql://localhost:3306/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try{
           Class.forName("com.mysql.cj.jdbc.Driver"); 
           conexion= DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public void cerrar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean insertar(Datos datos){
        boolean estado = true;
        
        try{
            abrir();
            PreparedStatement ps = conexion.prepareStatement("insert into Datos(nombre,telefono,celular,direccion) values (?,?,?)");
            ps.setString(1, datos.getNombre());
            ps.setInt(2, datos.getTelefono());
            ps.setInt(3, datos.getCelular());
            ps.setString(4, datos.getDireccion());
            ps.execute();            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }        
        return estado;        
    }
    
    public boolean actualizar(Datos datos){
        boolean estado = true;
        try{
            abrir();
            PreparedStatement ps = conexion.prepareCall("update Datos set nombre = ?, telefono = ?, celular = ?, direciion =?, where id = ?");
            ps.setString(1, datos.getNombre());
            ps.setInt(2, datos.getTelefono());
            ps.setInt(3, datos.getCelular());
            ps.setString(4, datos.getDireccion());
            ps.setInt(5, datos.getId());
            ps.executeUpdate();            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        return estado;
    }
    
    public boolean consultartodos(){
        boolean estado = true;
        try{
            
            abrir();
            PreparedStatement ps = conexion.prepareStatement("select * from Datos");
            ResultSet rs = ps.executeQuery();
            Datos datos;
            
            while(rs.next()){
                datos = new Datos(rs.getString("nombre"),rs.getInt("telefono"),rs.getInt("celular"),rs.getString("direccion"),rs.getInt("id"));
                //datos.setNombre(rs.getString("nombre"));
                listaDatos.add(datos);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        return estado;
    }
    
    public List<Datos> getListaDatos(){
        return listaDatos;
    }
    
    public boolean borrar(int id){
        boolean estado = true;
        try{
            abrir();
            PreparedStatement ps = conexion.prepareStatement("delete from Datos where id = ?");
            ps.setInt(1, id);
            ps.execute();            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        return estado;
    }
}
