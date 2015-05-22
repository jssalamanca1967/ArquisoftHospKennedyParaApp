/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hospitalKennedy.servicios;

import co.edu.uniandes.csw.hospitalKennedy.dto.Doctor;
import co.edu.uniandes.csw.hospitalKennedy.dto.Paciente;
import co.edu.uniandes.csw.hospitalKennedy.logica.ejb.ServicioPacienteMock;
import co.edu.uniandes.hospitalkennedy.security.jwt.api.JsonWebToken;
import co.edu.uniandes.hospitalkennedy.security.jwt.api.JwtHashAlgorithm;
import co.edu.uniandes.hospitalkennedy.security.logic.dto.PathInfo;
import co.edu.uniandes.hospitalkennedy.security.logic.dto.UserDTO;
import com.google.gson.Gson;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.tenant.Tenant;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author estudiante
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserLoginService {
    
    @Path("/login")
    @POST
    public Response login(UserDTO user) {
        int status = 500; //Codigo de error en el servidor
        String token = "User and/or password wrong";
        UserDTO userStorm = new UserDTO();
        String path = "src\\main\\webapp\\WEB-INF\\apiKey-4Q4FXMVW3LPNYYFXEX4A7J3S7.properties";//Colocar la Ubicacion de su archivo apiKey.properties
        ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
        Client client = Clients.builder().setApiKey(apiKey).build();
        Paciente paciente;
        Doctor doctor;

        try {
            AuthenticationRequest request = new UsernamePasswordRequest(user.getUsername(), user.getPassword());
            Tenant tenant = client.getCurrentTenant();
            ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("HospitalKennedy")));
            Application application = applications.iterator().next();

            AuthenticationResult result = application.authenticateAccount(request);
            Account account = result.getAccount();
            
            GroupList g = account.getGroups();
            
            for(Group p: g)
            {
                userStorm.setGrupo(p.getName());
                System.out.println(p.getName() + " - " + userStorm.getGrupo());
            }
            
            userStorm.setEmail(account.getEmail());
            userStorm.setName(account.getFullName());
            userStorm.setUsername(account.getUsername());
            userStorm.setPassword(user.getPassword());
            token = new Gson().toJson(JsonWebToken.encode(userStorm, "Un14nd3s2014@", JwtHashAlgorithm.HS256));
            status = 200;
            
            if(userStorm.getGrupo().equals(PathInfo.PACIENTE)){
            
                paciente = new Paciente();
                paciente.setGrupo( userStorm.getGrupo());
                paciente.setNombre( userStorm.getUsername() );
                paciente.setPassword( userStorm.getPassword() );

                return Response.status(status).entity(paciente).build();
            }
            if(userStorm.getGrupo().equals(PathInfo.DOCTOR)){
                
                doctor = new Doctor();
                
                doctor.setNombre(userStorm.getUsername());
                doctor.setPassword(userStorm.getPassword());
                doctor.setGrupo(userStorm.getGrupo());
                doctor.setToken(token);
                
                return Response.status(status).entity(doctor).build();
            }
            else
                return Response.status(400).entity("User and/or password wrong").build();
            

        } catch (ResourceException ex) {
            status = 400;
            
            return Response.status(status).entity(token).build();
        }

        
    }
    
}
