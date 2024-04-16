/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lothel.soft.ventas.mysql;

import java.util.ArrayList;
import lothel.soft.manager.DBManager;
import lothel.soft.ventas.dao.ServicioDeLavanderiaDAO;
import lothel.soft.ventas.model.ServicioDeLavanderia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
/**
 *
 * @author efeproceres
 */
public class servicioDeLavanderiaMYSQL implements ServicioDeLavanderiaDAO{
    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(ServicioDeLavanderia servicio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_EMPLEADO"
                            + "(?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_empleado",
                            java.sql.Types.INTEGER);
            
            cs.setString("_anotaciones", servicio.getAnotaciones());
            cs.setString("_estado", servicio.getEstado());
            cs.setString("_incidencia",servicio.getIncidencia());
            cs.setString("_descripcion", servicio.getDescripcion());
            cs.setString("nombre",servicio.get);
            cs.setDate("_fecha_nacimiento", new java.sql.Date(
                            empleado.getFechaNacimiento().getTime()));
            cs.setString("_cargo", empleado.getCargo());
            cs.setDouble("_sueldo", empleado.getSueldo());
            cs.executeUpdate();
            
            empleado.setIdPersona(cs.getInt("_id_empleado"));
            resultado = empleado.getIdPersona();
            
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
    public int modificar(ServicioDeLavanderia servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idServicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ServicioDeLavanderia> listarServiciosLavanderia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
