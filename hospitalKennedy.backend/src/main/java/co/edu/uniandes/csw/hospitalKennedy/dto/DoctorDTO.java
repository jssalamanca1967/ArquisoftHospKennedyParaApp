/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.dto;

/**
 *
 * @author estudiante
 */
public class DoctorDTO {
    
     //--------------------------
    // Atributos
    //--------------------------
    
    /**
     * La cédula es el id.
     */
    private Long id;
    private String nombre;
    private String password;
    private String login;
    
    public DoctorDTO(){
        
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public void setLogin(String login)
    {
        this.login=login;
    }
    
    public String getLogin()
    {
        return this.login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
