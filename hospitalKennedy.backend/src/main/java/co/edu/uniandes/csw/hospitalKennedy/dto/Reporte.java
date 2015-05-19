/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import com.sun.istack.NotNull;
import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
 
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author estudiante
 */
@Entity(name="Reporte")
@Table(name="Reporte")
public class Reporte implements Serializable{
    
    private static final long serialVersionUID = 3L;
    
    @Id
    private Long id;
    
    private String actividadFisica;
    private String alimentacion;
    private String gravedad;
    private String fechaCreacion;
    private String localizacionDolor;
    private String patronSuenio;   
    private String medicamentosRecientes;
    
    @NotNull
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;
 
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Catalizador catalizadores;
    
    private String url;
    
    
    public Reporte(){
        
    }
    
    public Reporte(Long id, String actividadFisica, String alimentacion, String gravedad, String fechaCreacion, String localizacionDolor, String patronSuenio, Paciente paciente,String medicamentosRecientes, String url){
        this.id = id;
        this.actividadFisica = actividadFisica;
        this.alimentacion = alimentacion;
        this.gravedad = gravedad;
        this.fechaCreacion  = fechaCreacion;
        this.localizacionDolor = localizacionDolor;
        this.patronSuenio = patronSuenio;
        this.medicamentosRecientes = medicamentosRecientes;
    }
    
    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }
 
    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }
    
    public void setCatalizadores(Catalizador catalizadores)
    {
        this.catalizadores=catalizadores;
    }
    
    public Catalizador getCatalizadores()
    {
        return catalizadores;
    }    
      
    public void setMedicamentosRecientes(String medicamentosRecientes) {
        this.medicamentosRecientes = medicamentosRecientes;
    }

    public String getMedicamentosRecientes() {
        return medicamentosRecientes;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocalizacionDolor(String localizacionDolor) {
        this.localizacionDolor = localizacionDolor;
    }

    public void setPatronSuenio(String patronSuenio) {
        this.patronSuenio = patronSuenio;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public String getGravedad() {
        return gravedad;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public String getLocalizacionDolor() {
        return localizacionDolor;
    }

    public String getPatronSuenio() {
        return patronSuenio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
