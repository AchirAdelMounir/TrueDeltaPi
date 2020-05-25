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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Entities.Portfolio;
import Entities.Security;
import Enumerations.Portfolio_Type;
import Interfaces.PortfolioServiceLocal;



@RequestScoped
@Path("portfolio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class PortfolioController {
	@Inject
	PortfolioServiceLocal service;
	

	@GET
	@Path("displayById/{id}")
    public Response DisplayPorfolio(@PathParam("id") int id) {
		
        return Response.ok(service.DisplayPortfolio(id)).build();
    }
	
	
	@GET
	@Path("DisplaySecurities")
    public Response DisplayPortfolios() {
        return Response.ok(service.DisplayPortfolios()).build();
    }
	
	
	
	@DELETE
	@Path("deletePortfolio/{id}")
    public Response delete(@PathParam("id") int id) {
        
        service.DeletePortfolio(id);

        return Response.ok().build();
    }
	
	@POST
	@Path("create")
    public Response CreatePortfolio(Portfolio p) {
		
		service.AddPortfolio(p);
		
        return Response.ok("c bn ").build();
    }
	
	
	@PUT
    @Path("update")
    public Response UpdatePortfolio(Portfolio p) {
		service.EditPortfolio(p);
        return Response.ok().build();
    }
	
	
	
	
	
	
	
}
