/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.hospitalkennedy.security.logic.dto;

/**
 *
 * @author estudiante
 */
import com.stormpath.sdk.group.Group;
import java.util.Date;
 
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class UserDTO {
 
    private Long componentId;
 
    public Long getComponentId() {
        return componentId;
    }
 
    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }
 
    private String grupo;
    
    private Long id;
 
    private String username;
 
    private String password;
 
    private String email;
 
    private String firstName;
 
    private String secondName;
 
    private String lastName;
 
    private String birthDate;
 
    private String gender;
 
    private String name;
 
    private String levelAccess;
 
    private String tenantID;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }
 
    public String getSecondName() {
        return secondName;
    }
 
    public void setSecondName(String secondname) {
        this.secondName = secondname;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
 
    public String getBirthDate() {
        return birthDate;
    }
 
    public void setBirthDate(String birthdate) {
        this.birthDate = birthdate;
    }
 
    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getLevelAccess() {
        return levelAccess;
    }
 
    public void setLevelAccess(String levelAccess) {
        this.levelAccess = levelAccess;
    }
 
    public String getTenantID() {
        return tenantID;
    }
 
    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    
}