/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.hospitalkennedy.security.jwt.api;

import co.edu.uniandes.hospitalkennedy.security.logic.dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Jj.alarcon10
 */
public class VerifyToken {

    public UserDTO getDataUser(String token) {

        try {
            String userToken = JsonWebToken.decode(token, "Un14nd3s2014@", true);
            Gson gson = new GsonBuilder().serializeNulls().create();
            UserDTO res = gson.fromJson(userToken, UserDTO.class);
            return res;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    

}
