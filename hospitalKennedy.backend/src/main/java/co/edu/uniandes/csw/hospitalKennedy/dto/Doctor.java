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
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
 


/**
 *
 * @author jssalamanca1967
 */
@Entity(name="Doctor")
@Table(name="Doctor")
public class Doctor implements Serializable{
     private static final long serialVersionUID = 1L;
    //--------------------------
    // Atributos
    //--------------------------
     
    /**
     * El id del doctor será la cédula
     */
    @Id
    private Long id;
    
    @NotNull
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;
 
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;

    private String nombre;
    private String password;
    private String login;
    
    public Doctor(Long id, String pNombre, String pPsw, String pLogin){
        this.id = id;
        nombre = pNombre;
        password = pPsw;
        login = pLogin;
    }
    public Doctor ()
    {
        
    }
    
    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }
 
    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
    
    
}
