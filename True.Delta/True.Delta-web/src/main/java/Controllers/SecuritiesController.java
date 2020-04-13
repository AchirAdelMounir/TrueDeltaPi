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
import Interfaces.ComplainIServices;
import Interfaces.SecuritiesServicesInterfaceLocal;
import Services.SecuritesServices;

@RequestScoped
@Path("Securities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SecuritiesController {
	@Inject
	SecuritiesServicesInterfaceLocal service;
	@POST
	@Path("create")
    public Response CreateSecurity(Security s) {
		
        int cc = service.AddSecurity(s);
        return Response.ok(cc).build();
    }
	@DELETE
	@Path("delete/{id}")
    public Response DeleteSecurity(@PathParam("id") int id) {
        service.DeleteSecurity(id);
        return Response.ok().build();
    }
	@PUT
    @Path("update/{id}")
    public Response UpdateSecurity(@PathParam("id") int id, Security c) {
		service.EditSecurity(id, c);
        return Response.ok().build();
    }
	
	@GET
	@Path("DisplaySecurities")
    public Response DisplaySecurities() {
        return Response.ok(service.DisplaySecurities()).build();
    }
	@GET
	@Path("DisplaySecurity/{id}")
    public Response DisplaySecurity(@PathParam("id") int id) {
        return Response.ok(service.DisplaySecurity(id)).build();
    }
	@GET
	@Path("Find/{SearchField}/{operator}/{o}")
    public Response SearchCompany(@PathParam("SearchField, operator, o") String SearchField,String operator,Object o) {
        return Response.ok(service.SearchByInput(SearchField, operator, o)).build();
    }
	@GET
	@Path("Top/{Input}/{TopN}")
    public Response getopSecurities(@PathParam("Input, TopN") String Input,int TopN) {
        return Response.ok(service.GetTopByInput(Input, TopN)).build();
    }
	@GET
	@Path("Last/{Input}/{TopN}")
    public Response getLastSecurities(@PathParam("Input, TopN") String Input,int TopN) {
        return Response.ok(service.GetLastByInput(Input, TopN)).build();
    }
	@GET
	@Path("GetStocksHistory/{Sym}/{frequency}/{Period1}/{Period2}")
    public Response getSecurities(@PathParam("Sym, Period1, Period2")String Sym,String frequency, String Period1,String Period2) {
        return Response.ok(service.StocksDownloader(Sym,frequency, Period1, Period2)).build();
    }
	
	@GET
	@Path("DisplayStocks")
    public Response getSecurities() {
        return Response.ok(service.DisplayStocks()).build();
    }
	@GET
	@Path("DisplayBonds")
    public Response getBonds() {
        return Response.ok(service.DisplayBonds()).build();
    }
	@GET
	@Path("CalculateVolatility/{Sym}/{Period1}/{Period2}")
    public Response CalculateVolatility(@PathParam("Sym, Period1, Period2")String Sym, String Period1,String Period2) {
        return Response.ok(service.VolatilityCalculator(Sym, Period1, Period2)).build();
    }
	@GET
	@Path("StockInstant/{Sym}")
    public Response CalculateVolatility(@PathParam("Sym")String Sym) {
        return Response.ok(service.getStockPriceInstantly(Sym)).build();
    }
	@GET
	@Path("SecurityFinder/{Number}/{operator}/{value}")
    public Response DisplaySecuritiesWithPrice(@PathParam("Number,operator,value")int Number,String operator,double value) {
        return Response.ok(service.SecuritiesFinder(Number, operator, value)).build();
    }
	@GET
	@Path("StandardDev/{Sym}/{Period1}/{Period2}")
    public Response StandardDev(@PathParam("Sym, Period1, Period2")String Sym, String Period1,String Period2) {
        return Response.ok(service.StandardDev(Sym, Period1, Period2)).build();
    }
	@GET
	@Path("StandardDev/{Sym}/{Period1}/{Period2}")
    public Response CoefOfDev(@PathParam("Sym, Period1, Period2")String Sym, String Period1,String Period2) {
        return Response.ok(service.CoefOfDeviation(Sym, Period1, Period2)).build();
    }
	
	
	

}
