package controllers;

import entities.Admin;
import entities.AideOperatoire;
import entities.Docteur;
import exceptions.OfferNotFoundException;
import exceptions.UserNotFoundException;
import managers.AdminManager;
import managers.MainManager;
import sun.applet.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("/admin")
public class AdminController {

    @GET
    @Path("/listAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Admin> listAdmin(){
        return AdminManager.getInstance().listAdmin();
    }

    @GET
    @Path("/listDocteur")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Docteur> listDocteur(){
        return MainManager.getInstance().listDocteur();
    }

    @GET
    @Path("/listAideOp")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AideOperatoire> listAideOp(){
        return MainManager.getInstance().listAideOperatoire();
    }

    @DELETE
    @Path("/{type}/{idUser}")
    public Response deleteUser(@PathParam("type") Integer type,
                               @PathParam("idUser") Integer idUser){
        if (type == 1){
            try {
                MainManager.getInstance().deleteAideop(idUser);
                return Response.status(200).build();
            }catch (IllegalArgumentException | UserNotFoundException e){
                return Response.status(404).build();
            }
        }else if (type == 2){
            try {
                MainManager.getInstance().deleteDocteur(idUser);
                return Response.status(200).build();
            }catch (IllegalArgumentException | UserNotFoundException e){
                return Response.status(404).build();
            }
        }else if (type == 4){
           try {
               MainManager.getInstance().deleteOffre(idUser);
               return Response.status(200).build();
           }catch (IllegalArgumentException | OfferNotFoundException e){
               return Response.status(404).build();
           }
        }
        return Response.status(404).build();
    }
}
