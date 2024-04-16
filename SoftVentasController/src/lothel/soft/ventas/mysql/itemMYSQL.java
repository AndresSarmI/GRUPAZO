/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lothel.soft.ventas.mysql;

import java.util.ArrayList;
import lothel.soft.ventas.dao.itemDAO;
import lothel.soft.ventas.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Statement;
import lothel.soft.manager.DBManager;
/**
 *
 * @author efeproceres
 */
public class itemMYSQL implements itemDAO{
    
    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;

    @Override
    public int insertar(Item item) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call InsertarItem(?,?,?,?,?)}");
            cs.setString("_descripcion", item.getDescripcion());
            cs.setString("_nombre", item.getNombre());
            cs.setDouble("_precio",item.getPrecio());
            cs.setDouble("calificacion",item.getCalificacion());
            //cs.setImagen()...
            
            resultado=cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(Item item,double precio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ModificarItem(?,?)}");
            //cambiar precio
            cs.setInt("_idItem",item.getIdIteam());
            cs.setDouble("_idPrecio",precio);

            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int eliminar(int idItem) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call EliminarItem(?)}");
            cs.setInt("_idItem", idItem);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Item> listarItems() {
        ArrayList<Item> items =  new ArrayList<Item>();
        
        try{
            con = DBManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM evento";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                Item item = new Item();
                item.setIdIteam(rs.getInt("idItem"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setNombre(rs.getString("nombre"));
                item.setPrecio(rs.getDouble("precio"));
                item.setCalificacion(rs.getDouble("calificacion"));
                //item.setImagen(imagen);
                items.add(item);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return items;
    }
    
}
