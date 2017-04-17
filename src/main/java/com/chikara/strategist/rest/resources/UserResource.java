package com.chikara.strategist.rest.resources;

import com.chikara.strategist.entity.AccessToken;
import com.chikara.strategist.entity.Role;
import com.chikara.strategist.entity.User;
import com.chikara.strategist.service.UserService;
import com.chikara.strategist.transfer.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Component
@Path("/user")
public class UserResource
{
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    /**
     * Retrieves the currently logged in user.
     *
     * @return A transfer containing the username and the roles.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserTransfer getUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new WebApplicationException(401);
        }
        UserDetails userDetails = (UserDetails) principal;

        return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
    }

    /**
     * Authenticates a user and creates an access token.
     *
     * @param username The name of the user.
     * @param password The password of the user.
     * @return The generated access token.
     */
    @Path("authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AccessToken authenticate(@FormParam("username") String username, @FormParam("password") String password)
    {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, (password));
        
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new WebApplicationException(401);
        }
        
		
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		authentication.setAuthenticated(true); 

		SecurityContextHolder.getContext().setAuthentication(authentication);
        Set<Role> roles = new HashSet<Role>();
        User adminUser = new User(username, password);

		if(username.equals("ankush")){
            roles.add(Role.FACULTY);
            roles.add(Role.USER);
            
    	}else{
			roles.add(Role.USER);
	        roles.add(Role.ADMIN);
    	}
        adminUser.setRoles(roles);
        
        return this.userService.createAccessToken(adminUser);
    }

    /*
    @Path("authenticate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AccessToken logout(String username)
    {
    	
    
    }*/
    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();

    	if(userDetails.getUsername().equals("admin")){
	        for (GrantedAuthority authority : userDetails.getAuthorities()) {
	            roles.put(authority.getAuthority(), Boolean.TRUE);
	        }
    	}

    	if(userDetails.getUsername().equals("ankush")){
            roles.put("USER", Boolean.TRUE);
            roles.put("FACULTY", Boolean.TRUE);
            
    	}
        return roles;
    }
}