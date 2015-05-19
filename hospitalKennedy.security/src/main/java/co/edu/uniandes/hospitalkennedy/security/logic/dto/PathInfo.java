/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.hospitalkennedy.security.logic.dto;

import com.stormpath.sdk.group.Group;

/**
 *
 * @author estudiante
 */
public class PathInfo {
    
    public final static String ADMIN = "Admin";
    public final static String DOCTOR = "Doctor";
    public final static String PACIENTE = "Paciente";
    
    public final static String PATH_DOCTOR = "Doctor";
    public final static String PATH_PACIENTE = "Pacientes";
    public final static String PATH_ADMIN = "Admin";
    
    public static String pathInfo;
    
    private static PathInfo instance;
    
    public static boolean autenticar(String grupo){
        
        boolean rta = false;
        
        if(pathInfo.contains(PATH_DOCTOR) && grupo.equals(DOCTOR))
            rta = true;
        if(pathInfo.contains(PATH_PACIENTE) && grupo.equals(PACIENTE))
            rta = true;
        if(pathInfo.contains(PATH_ADMIN) && grupo.equals(ADMIN))
            rta = true;
       
        return rta;
    }
    
    public static PathInfo getInstance()
	{
		if(instance == null)
			instance = new PathInfo();
		return instance;
	}
    
}
