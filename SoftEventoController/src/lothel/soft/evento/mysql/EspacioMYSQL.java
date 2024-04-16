/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lothel.soft.evento.mysql;

import java.util.ArrayList;
import lothel.soft.evento.model.Espacio;
import lothel.soft.manager.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Statement;
import lothel.soft.evento.dao.EspacioDAO;
/**
 *
 * @author efeproceres
 */
public class EspacioMYSQL implements EspacioDAO{
    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    @Override
    public int insertar(Espacio espacio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //cs.registerOutParameter("_id_empleado",java.sql.Types.INTEGER);
            cs = con.prepareCall("{call INSERTAR_ESPACIO(?,?,?,?)}");
            cs.setInt("_numeroPiso", espacio.getNumeroPiso());
            cs.setString("_seccion", espacio.getSeccion());
            cs.setInt("_aforo",espacio.getAforo());
            cs.setBoolean("_disponibilidad", espacio.getDisponibilidad());
            //cs.setDate("_fecha_nacimiento", new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            
            resultado=cs.executeUpdate();
            
            //empleado.setIdPersona(cs.getInt("_id_empleado"));
            
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
    public int modificar(Espacio espacio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_ESPACIO(?,?)}");
            cs.setInt("_idEspacio", espacio.getIdEspacio());
            cs.setBoolean("_disponibilidad", espacio.getDisponibilidad());
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idEspacio) {
       int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_ESPACIO(?)}");
            cs.setInt("_idEspacio", idEspacio);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Espacio> listarEspacios() {
        ArrayList<Espacio> espacios =  new ArrayList<Espacio>();
        
        try{
            con = DBManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM espacio";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                Espacio espacio = new Espacio();
                espacio.setAforo(rs.getInt("aforo"));
                espacio.setDisponibilidad(rs.getBoolean("disponibilidad"));
                espacio.setNumeroPiso(rs.getInt("numeroPiso"));
                espacio.setSeccion(rs.getString("seccion"));
                espacios.add(espacio);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return espacios;
    }
    
}
