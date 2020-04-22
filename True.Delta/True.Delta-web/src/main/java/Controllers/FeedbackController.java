//package Controllers;
//import java.text.ParseException;
////import java.util.Map;
//import java.util.List;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
//import Entities.Feedback;
//import Interfaces.FeedbackIServices;
//
//@RequestScoped
//@Path("feedback")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class FeedbackController {
//	@Inject
//	FeedbackIServices service;
//	@POST
//	@Path("create")
//    public Response create(Feedback f) {
//		
//        int cc = service.AddFeedback(f);
//        return Response.ok(cc).build();
//    }
//	@DELETE
//	@Path("delete/{id}")
//    public Response delete(@PathParam("id") int id) {
//        
//        service.DeleteFeddbackById(id);
//
//        return Response.ok().build();
//    }
//	@DELETE
//	@Path("deleteByIdUser/{id}")
//    public Response deleteByIdUser(@PathParam("id") int id) {
//        
//        int cc=service.DeleteFeedbackByIdUser(id);
//
//        return Response.ok(cc).build();
//    }
//	@DELETE
//	@Path("deleteByD/{date}")
//    public Response deleteByD(@PathParam("date") String date) {
//        
//        int cc=service.DeleteFeedbackByDate(date) ;
//
//        return Response.ok(cc).build();
//    }
//	@PUT
//    @Path("update/{id}")
//    public Response update(@PathParam("id") int id, Feedback f) {
//		Feedback updateFeedback = service.findById(id);
//
//		
//		updateFeedback.setRating(f.getRating());
//		updateFeedback.setDate(f.getDate());
//		updateFeedback.setUser(f.getUser());
//        service.UpdateFeedback(updateFeedback);
//
//        return Response.ok().build();
//    }
//	@GET
//	@Path("getFeedbackById/{id}")
//    public Response getFeedbackById(@PathParam("id") int id) {
//        return Response.ok(service.GetFeedbackById(id)).build();
//    }
//	@GET
//	@Path("getAll")
//    public Response getAll() {
//        return Response.ok(service.getAllFeedback()).build();
//    }
//	@GET
//	@Path("getAllByIdUser/{idUser}")
//	public Response getAllByIdUser(@PathParam("idUser") int idUser) {
//		
//		return Response.ok(service.getAllFeedbackByIdUser(idUser)).build();
//		
//	}
//	@GET
//	@Path("getAllByDate/{date}")
//	public Response getAllByDate(@PathParam("date") String date) throws ParseException {
//		
//		return Response.ok(service.getFeedbackByDate(date)).build();
//		
//	}
//	@GET
//	@Path("getAllFeedbackByRating/{rating}")
//	public Response getAllFeedbackByRating(@PathParam("rating") Enumerations.LevelRating rating) {
//		
//		return Response.ok(service.getAllFeedbackByRating(rating)).build();
//		
//	}
//	@GET
//	@Path("getNbFeedback")
//	public Response getNbFeedback() {
//		double cc = service.getNbFeedback();
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("getNbUser")
//	public Response getNbUser() {
//		double cc = service.getNbUser();
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("getNbFeedbackByIdUser/{idUser}")
//	public Response getNbFeedbackByIdUser(@PathParam("idUser") int idUser) {
//		double cc = service.getNbFeedbackByIdUser(idUser);
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("getNbFeedbackByRating/{rating}")
//	public Response getNbFeedbackByRating(@PathParam("rating") Enumerations.LevelRating rating) {
//		double cc = service.getNbFeedbackByRating(rating);
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("getNbFeedbackByDate/{date}")
//	public Response getNbFeedbackByDate(@PathParam("date") String date) throws ParseException {
//		double cc = service.getNbFeedbackByDate(date);
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("getAvgOfRating/{rating}")
//	public Response getAvgOfRating(@PathParam("rating") Enumerations.LevelRating rating) {
//		double cc = service.getAvgOfRating(rating);
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("nbOfStarsById/{id}")
//	public Response nbOfStarsById(@PathParam("id") int id) {
//		double cc = service.nbOfStarsById(id);
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("noteGlobal")
//    public Response nbOfStarsGlobal() {
//        return Response.ok(service.noteGlobal()).build();
//    }
//	@GET
//	@Path("getNbActivityByUser/{idUser}")
//	public Response getNbActivityByUser(@PathParam("idUser") int idUser) {
//		double cc = service.getNbActivityByUser(idUser);
//		
//		return Response.ok(cc).build();
//		
//	}
//	@GET
//	@Path("best3ActiveUser")
//    public Response best3ActiveUser() {
//        return Response.ok(service.best3ActiveUser()).build();
//    }
//	/*@POST
//	@Path("affichageOfBest3")
//	public Response affichageOfBest3( Map<Integer, Long> h) {
//		//String cc = service.affichageOfBest3(h);
//		
//		return Response.ok(service.affichageOfBest3(h)).build();
//		
//	}*/
//	
//	@GET
//	@Path("affichageOfBest3")
//	public Response affichageOfBest3( ) {
//		List <String> s=service.affichageOfBest3();
//		
//		return Response.ok(s).build();
//		
//	}
//	@GET
//	@Path("validerFeedback/{f}")
//    public Response validerFeedback(@PathParam("f") String Str) {
//        return Response.ok(service.validerFeedback(Str)).build();
//    }
//	
//
//}
