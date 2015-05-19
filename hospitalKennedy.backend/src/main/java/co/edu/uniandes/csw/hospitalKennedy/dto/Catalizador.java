/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Calendar;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author estudiante
 */
@Entity(name="Catalizador")
public class Catalizador implements Serializable{
    private static final long serialVersionUID = 1L;
    //--------------------------
    // Atributos
    //--------------------------
    
    /**
     * Va a tener el mismo id del reporte correspondiente
     */
    @Id
    private Long id;    
    
    private String actividadesFisicas;
    private String alimentacion;
    private String patronSuenio;
    private String medicamentosRecientes;
    private String todoCatalizador;
    
    public Catalizador()
    {
        
    }
    
    public Catalizador(Long id, String actividadesFisicas, String alimentacion, String patronSuenio, String medicamentosRecientes)
    {
        this.id=id;    
        this.actividadesFisicas = actividadesFisicas;
        this.alimentacion = alimentacion;
        this.medicamentosRecientes = medicamentosRecientes;
        this.patronSuenio = patronSuenio;
    }
    
    public void setId(Long id)
    {
        this.id=id;
    }
    
    public Long getId()
    {
        return id;
    }

    public String getActividadesFisicas() {
        return actividadesFisicas;
    }

    public void setActividadesFisicas(String actividadesFisicas) {
        this.actividadesFisicas = actividadesFisicas;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public String getPatronSuenio() {
        return patronSuenio;
    }

    public void setPatronSuenio(String patronSuenio) {
        this.patronSuenio = patronSuenio;
    }

    public String getMedicamentosRecientes() {
        return medicamentosRecientes;
    }

    public void setMedicamentosRecientes(String medicamentosRecientes) {
        this.medicamentosRecientes = medicamentosRecientes;
    }

    public String getTodoCatalizador() {
        return todoCatalizador;
    }

    public void setTodoCatalizador(String todoCatalizador) {
        this.todoCatalizador = todoCatalizador;
    }
            
}
