package Controllers;
import java.text.ParseException;
//import java.util.Map;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Entities.Feedback;
import Entities.User;
import Interfaces.UserIServices;
import Security.JWTTokenNeeded;
import Security.LoginToken;



@RequestScoped
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	@Inject
	UserIServices service;
	@POST
	@Path("log")
	public Response log(User user) {
		User us = service.authentication(user.getLogin(), user.getPassword());
		if(us == null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		if(!us.isValid())
			return Response.ok("please verify your Email First!").build();
		
		
		String token = LoginToken.createJWT("TrueDelta", user.getLogin(), 0);
		us.setCode(token);
		service.EditUser(us, us.getId());
		return Response.ok(us).header("AUTHORIZATION", "Bearer " + token).build();	
	}
	
	@POST
	@Path("create")
    public Response AddUser(User U) {
		
        int cc = service.AddUser(U);
        return Response.ok(cc).build();
    }
}
