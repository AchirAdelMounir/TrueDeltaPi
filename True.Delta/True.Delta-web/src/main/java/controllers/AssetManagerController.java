package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Entities.AssetManager;
import Entities.Ratings;
import Interfaces.RatingServiceInterface;
import Services.AssetManagerService;
import Services.RatingService;

@RequestScoped
@Path("AssetManager")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssetManagerController {

	@Inject
	AssetManagerService cs;
	
	@Inject
	RatingService rs;

	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@POST
	@Path("create")
	public Response ajouterCompte1(AssetManager c) {
		int Id = cs.addAssetManager(c);
		return Response.ok(Id).build();
	}
	
	@DELETE
	@Path("delete")
	public Response deleteAssetManager1(@QueryParam(value="Id") int Id) {
		cs.deleteAssetManager(Id);
		return Response.ok().build();
	}
	
	
	
	@GET
	@Path("getById")
	public Response getAssetManagerById1(@QueryParam(value="Id") int Id) {
		AssetManager c =  cs.getAssetManagerById(Id);
		return Response.ok(c).build();
	}
	
	@PUT
	@Path("update")
	public Response updateAssetManager1(AssetManager c) {
		return Response.ok(cs.updateAssetManager(c)).build();
	}
	
	@GET
	@Path("listeconf")
	public Response getListeConf() {
		
		return Response.ok(cs.getall()).build();
	}
	
	
	@GET
	@Path("Attente")
	public Response getListeAttente() {
		
		return Response.ok(cs.getallConfirmation()).build();
	}
	
	@PUT
	@Path("confirmer")
	public Response confirmer(@QueryParam(value="Id") int Id) {
		//AssetManager c = cs.getAssetManagerById(Id);
		//c.setConfirmation(true);
		AssetManager am = cs.getAssetManagerById(Id);
		cs.acceptAssetManager(am);
		return Response.ok(am).build();
	
	}
	
	
	@GET
	@Path("conversion")
	public Response conversion(@QueryParam(value="a") String a,@QueryParam(value="b") String b,@QueryParam(value="c") Float c) throws IOException {
		
	float d = cs.Conversion(a, b, c);
		return Response.ok(d).build();
	}

	@GET
	@Path("taux")
	public Response taux() throws IOException {
		
		Map<String, Float> map = cs.Change();
		return Response.ok(map).build();
	}
	
	
	@PUT
	@Path("classification")
	public Response classification() {
		//AssetManager c = cs.getAssetManagerById(Id);
		//c.setConfirmation(true);
		
		
		//cs.Classification();
		List<AssetManager> am =  cs.Classification();
		return Response.ok(am).build();
	
	}
	
	
	@POST
	@Path("createR")
	public Response ajouterRating(Ratings r) {
		int Id = rs.addRating(r);
		cs.CalculRating();
		return Response.ok(Id).build();
	}
	
	
	
	@GET
	@Path("listeR")
	public Response getListeRatings() {
		List<Ratings> am =  rs.getall();
		return Response.ok(am).build();
	}
	
	
	///////////////////////////la liste des AM non confirmés
	
	@GET
	@Path("liste0")
	public Response getListe0() {
		List<String> am =  cs.getall0();
		return Response.ok(am).build();
	}
	
	///////////////////////////la liste des AM confirmés

	@GET
	@Path("liste1")
	public Response getListe1() {
		List<String> am =  cs.getall1();
		return Response.ok(am).build();
	}
	
	
	
}