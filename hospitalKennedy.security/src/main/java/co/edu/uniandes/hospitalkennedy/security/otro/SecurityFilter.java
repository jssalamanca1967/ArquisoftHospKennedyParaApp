/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.hospitalkennedy.security.otro;

import co.edu.uniandes.hospitalkennedy.security.jwt.api.JwtToken;
import co.edu.uniandes.hospitalkennedy.security.logic.dto.PathInfo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 *
 * @author estudiante
 */
public class SecurityFilter extends AuthenticatingFilter{
 
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse sr1) throws Exception {
               
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        
        PathInfo.pathInfo = httpRequest.getPathInfo();
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + httpRequest.getPathInfo());
        return new JwtToken(httpRequest.getHeader("x_rest_user"));
    }
 
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaasdasdasdadasdasdasdasdasdasdasdasd " + request.getLocalAddr());
        HttpServletResponse toHttp = WebUtils.toHttp(response);
        try {
            toHttp.sendError(401);
        } catch (IOException ex) {
            Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    @Override
    protected boolean onAccessDenied(ServletRequest sr, ServletResponse sr1) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(sr);
        HttpServletResponse httpResponse = WebUtils.toHttp(sr1);
		String httpMethod = httpRequest.getMethod();
		if ("OPTIONS".equalsIgnoreCase(httpMethod)) {
                        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
                        httpResponse.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
                        httpResponse.addHeader("Access-Control-Allow-Headers","X-Requested-With,Origin,Content-Type, Accept, x_rest_user");
			return true;
		} else {
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
                        httpResponse.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
                        httpResponse.addHeader("Access-Control-Allow-Headers","X-Requested-With,Origin,Content-Type, Accept, x_rest_user"); 
                        return executeLogin(sr, sr1);
		}
 
    } 
 
}