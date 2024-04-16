/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lothel.soft.ventas.dao;

import java.util.ArrayList;
import lothel.soft.ventas.model.Servicio;

/**
 *
 * @author efeproceres
 */
public interface ServicioDAO {
   public int insertar(Servicio servicio);
   public int modificar(Servicio servicio);
   public int eliminar(int idServicio);
   public ArrayList<Servicio> listarServicios();
}
