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
public class PacienteDTO {
    
    //Atributos
    
    private int altura;
    private int edad;
    
    /**
     * La cédula es el id
     */
    private Long cedulaCiudadania;
    private String nombre;
    private List<Reporte> reportes;
    
    private int id;
    
    public PacienteDTO()
    {
        
    }
    
    public PacienteDTO( Long cedulaCiudadania, String nombre, int edad, int altura, List<Reporte> reportesN) {
//        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.cedulaCiudadania = cedulaCiudadania;
        this.altura = altura;
        reportes = reportesN;
    }
    
    public void setAltura(int altura)
    {
        this.altura=altura;
    }
    
    public int getAltura()
    {
        return this.altura;
    }
    
    public void setEdad(int edad)
    {
        this.edad=edad;
    }
    
    public int getEdad()
    {
        return this.edad;
    }
    
    public void setCedulaCiudadania(Long cedulaCiudadania)
    {
        this.cedulaCiudadania=cedulaCiudadania;
    }
    
    public Long getCedulaCiudadania()
    {
        return this.cedulaCiudadania;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
     public void setId(int id) {
        this.id = id;
    }
     
      public int getId() {
        return id;
    }
    
    public void setReportes(ArrayList<Reporte> reportes)
    {
        if(this.reportes==null)
        {
            reportes= new ArrayList();
        }
        for(int i=0;i<reportes.size();i++)
        {
            this.reportes.add(reportes.get(i));
        }
    }
    
    public List<Reporte> getReportes()
    {
        return reportes;
    }
}
