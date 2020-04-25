package Controllers;
import java.text.ParseException;

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

import Entities.Complain;
import Interfaces.ComplainIServices;

@RequestScoped
@Path("complain")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComplainController {
	@Inject
	ComplainIServices service;
	@POST
	@Path("create")
    public Response create(Complain c) {
		
        int cc = service.AddComplain(c);
        return Response.ok(cc).build();
    }
	@DELETE
	@Path("delete1/{id}")
    public Response delete(@PathParam("id") int id) {
        
        service.DeleteComplainById(id);

        return Response.ok().build();
    }
	@DELETE
	@Path("deleteByIdUser/{id}")
    public Response deleteByIdUser(@PathParam("id") int id) {
        
        int cc=service.DeleteComplainByIdUser(id);

        return Response.ok(cc).build();
    }
	
	@DELETE
	@Path("deleteByS/{status}")
    public Response deleteByS(@PathParam("status") Enumerations.StatusTypeOfComplain status) {
        
        int cc=service.DeleteComplainByStatus(status);

        return Response.ok(cc).build();
    }
	
	
	@DELETE
	@Path("deleteByD/{date}")
    public Response deleteByD(@PathParam("date") String date) {
        
        int cc=service.DeleteComplainByDate(date) ;

        return Response.ok(cc).build();
    }
	@PUT
    @Path("update/{id}")
    public Response update(@PathParam("id") int id, Complain c) {
        Complain updateComplain = service.findById(id);

        updateComplain.setSubject(c.getSubject());
        updateComplain.setDescription(c.getDescription());
        updateComplain.setStatus(c.getStatus());
        updateComplain.setDate(c.getDate());
        updateComplain.setUser(c.getUser());
        service.UpdateComplain(updateComplain);

        return Response.ok().build();
    }
	
	@GET
	@Path("getComplainById/{id}")
    public Response getComplainById(@PathParam("id") int id) {
        return Response.ok(service.getComplainById(id)).build();
    }
	
	@GET
	@Path("getAll")
    public Response getAll() {
        return Response.ok(service.getAllComplain()).build();
    }
	
	@GET
	@Path("getAllByIdC/{idCustomer}")
	public Response getAllByIdC(@PathParam("idCustomer") int idCustomer) {
		
		return Response.ok(service.getAllComplainByIdCustomer(idCustomer)).build();
		
	}
	
	@GET
	@Path("getAllByStatus/{status}")
	public Response getAllByStatus(@PathParam("status") Enumerations.StatusTypeOfComplain status) {
		
		return Response.ok(service.getComplainByStatus(status)).build();
		
	}
	@GET
	@Path("getAllByDate/{date}")
	public Response getAllByDate(@PathParam("date") String date) throws ParseException {
		
		return Response.ok(service.getComplainByDate(date)).build();
		
	}
	
	@GET
	@Path("getNbComplain")
	public Response getNbComplain() {
		double cc = service.getNbComplain();
		
		return Response.ok(cc).build();
		
	}
	@GET
	@Path("getNbComplainByCustomer/{idCustomer}")
	public Response getNbComplainByCustomer(@PathParam("idCustomer") int idCustomer) {
		double cc = service.getNbComplainByCustomer(idCustomer);
		
		return Response.ok(cc).build();
		
	}
	@GET
	@Path("getNbComplainByStatus/{status}")
	public Response getNbComplainByCustomer(@PathParam("status") Enumerations.StatusTypeOfComplain status) {
		double cc = service.getNbComplainByStatus(status);
		
		return Response.ok(cc).build();
		
	}
	@GET
	@Path("getNbComplainByDate/{date}")
	public Response getNbComplainByDate(@PathParam("date") String date) throws ParseException {
		double cc = service.getNbComplainByDate(date);
		
		return Response.ok(cc).build();
		
	}
	@GET
	@Path("findById/{id}")
	public Response findById(@PathParam("id") int id) {
		Complain cc = service.findById(id);
		
		return Response.ok(cc).build();
		
	}
	
	
	
	
	
	

	
	
}
