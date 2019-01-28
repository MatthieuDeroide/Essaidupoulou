package controllers;

import entities.Offre;
import entities.OffreDto;
import exceptions.OfferAlreadyExistsException;
import managers.MainManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/offers")
public class OfferController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOffer(OffreDto offreDto){
        System.out.println(offreDto.auteurId);
        System.out.println(offreDto.isAideOp);
        Offre offre=new Offre(offreDto);
        System.out.println(offre.isAideOp());
        System.out.println(offre.getAuteurId());
        try {
            MainManager.getInstance().addOffre(offre,offre.getAuteurId(),offre.isAideOp());
            return Response.status(201).build();
        }catch (OfferAlreadyExistsException e){
            return Response.status(409).build();
        }



    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offre> listOffres(){
        List<Offre> offersList= MainManager.getInstance().listoffre();
        return offersList;
    }


    @GET
    @Path("/list/{profession}/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Offre> listOffres( @PathParam("profession") String profession,
                                   @PathParam("idUser") String idUser){
        List<Offre> offersList= MainManager.getInstance().listoffre(Integer.parseInt(profession),Integer.parseInt(idUser));
        return offersList;
    }
}
