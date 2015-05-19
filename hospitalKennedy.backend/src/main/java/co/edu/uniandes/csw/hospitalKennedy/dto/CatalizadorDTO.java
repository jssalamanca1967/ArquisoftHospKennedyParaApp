/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class CatalizadorDTO {
    
    //--------------------------
    // Atributos
    //--------------------------
    
    /**
     * Va a tener el mismo id del reporte correspondiente
     */
    private Long id;    
    private String actividadesFisicas;
    private String alimentacion;
    private String patronSuenio;
    private String medicamentosRecientes;
    private String todoCatalizador;
    
    public CatalizadorDTO()
    {
        
    }
    
    public CatalizadorDTO(Long id)
    {
        this.id=id;    
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
