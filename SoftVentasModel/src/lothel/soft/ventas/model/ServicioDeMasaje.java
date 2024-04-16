/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lothel.soft.ventas.model;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author efeproceres
 */
public class ServicioDeMasaje extends Servicio {
    private Date horaFin;
    private Date horaInicio;

    public ServicioDeMasaje(Date horaFin, Date horaInicio, String estado, String incidencia, String descripcion, String nombre, double precio, double calificacion, Image imagen) {
        super(estado, incidencia, descripcion, nombre, precio, calificacion, imagen);
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    
}
