/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import com.sun.istack.NotNull;
import java.util.ArrayList;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jssalamanca1967
 */
@Entity(name = "Paciente")

public class Paciente implements Serializable {

    private static final long serialVersionUID = 2L;

    //--------------------------------
    // Atributos
    //--------------------------------
    /**
     * La cédula del paciente
     */
    @Id
    private Long id;
    
    
    private int altura;
    private int edad;
    //private Long cedulaCiudadania;
    @NotNull
    private String nombre;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Reporte> reportes;

    @NotNull
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;

    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;

    public Paciente() {

    }

    public Paciente( Long id, String nombre, int edad, int altura, ArrayList<Reporte> reportesN) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        //this.cedulaCiudadania = cedulaCiudadania;
        this.altura = altura;
        reportes = reportesN;
    }

    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }

    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }

    public void setReportes(List<Reporte> reportes) {
        if (this.reportes == null) {
            reportes = new ArrayList();
        }
        this.reportes = reportes;
    }

    public List<Reporte> getReportes() {
        if (this.reportes == null) {
            reportes = new ArrayList<Reporte>();
        }
        return reportes;
    }

    public Reporte getReporte(Long idReporte) {
        Reporte r = null;
        boolean ya = false;
        for (int i = 0; i < reportes.size() && !ya; i++) {
            if (reportes.get(i).getId().equals(idReporte)) {
                r = reportes.get(i);
                ya = true;
            }
        }
        return r;
    }

    public List<Reporte> getReportesEntreFechas(String fecha1, String fecha2) {
        List<Reporte> estos = new ArrayList<Reporte>();
        for (int i = 0; i < reportes.size(); i++) {
            Reporte actual = reportes.get(i);

            if (actual.getFechaCreacion().compareTo(fecha1) >= 0 && actual.getFechaCreacion().compareTo(fecha2) <= 0) {
                estos.add(actual);
            }
        }
        return estos;
    }

    public void removerReporte(Long idReporte) {
        boolean ya = false;
        for (int i = 0; i < reportes.size() && !ya; i++) {
            if (reportes.get(i).getId().equals(idReporte)) {
                reportes.remove(i);
                ya = true;
            }
        }
    }

    //public void setCedulaCiudadania(Long cedulaCiudadania) {
     //   this.cedulaCiudadania = cedulaCiudadania;
    //}

    //public Long getCedulaCiudadania() {
      //  return cedulaCiudadania;
    //}

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setId(Long id) {
       this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }

    public Long getId() {
         return id;
     }
    public String getNombre() {
        return nombre;
    }

    public void agregarReporte(Reporte reporte) {
        reporte.setId(Long.parseLong(id + "" + reportes.size()));
        reporte.setFechaCreacion((new Date(System.currentTimeMillis())).toString());
        reportes.add(reporte);
    }

}
