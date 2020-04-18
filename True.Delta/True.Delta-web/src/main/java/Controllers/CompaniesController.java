package Controllers;

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

import Entities.Company;
import Entities.Complain;
import Entities.Security;
import Interfaces.CompaniesServicesInterfaceLocal;
import Interfaces.ComplainIServices;
import Services.CompaniesServices;

@RequestScoped
@Path("companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompaniesController {
	@Inject
	CompaniesServicesInterfaceLocal service;
	@POST
	@Path("create")
    public Response CreateCompany(Company C) {
		
        String cc = service.AddCompany(C);
        return Response.ok(cc).build();
    }
	@DELETE
	@Path("delete/{sym}")
    public Response DeleteCompany(@PathParam("sym") String sym) {
        
        service.DeleteCompany(sym);

        return Response.ok().build();
    }
	@PUT
    @Path("update/{Sym}/{c})")
    public Response UpdateCompany(@PathParam("Sym,c") String Sym, Company c) {
		Company cc=service.EditCompany(Sym, c);
        return Response.ok(cc).build();
    }
	@GET
	@Path("DisplayCompanies")
    public Response DisplayCompanies() {
        return Response.ok(service.DisplayCompanies()).build();
    }
	@GET
	@Path("DisplayCompany/{sym}")
    public Response DisplayCompany(@PathParam("sym") String sym) {
        return Response.ok(service.DisplayCompany(sym)).build();
    }
	@GET
	@Path("Find/{SearchField}/{operator}/{o}")
    public Response FindCompany(@PathParam("SearchField, operator, o") String SearchField,String operator,Object o) {
        return Response.ok(service.SearchByInput(SearchField, operator, o)).build();
    }
	@GET
	@Path("Top/{Input}/{TopN}")
    public Response getTopNCompanies(@PathParam("Input, TopN") String Input,int TopN) {
        return Response.ok(service.GetTopByInput(Input, TopN)).build();
    }
	@GET
	@Path("Last/{Input}/{TopN}")
    public Response getLastNCompanies(@PathParam("Input, TopN") String Input,int TopN) {
        return Response.ok(service.GetLastByInput(Input, TopN)).build();
    }
	
	


}
