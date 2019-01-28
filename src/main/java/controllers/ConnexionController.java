package controllers;

import exceptions.UserNotFoundException;
import managers.AdminManager;
import managers.MainManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/connexion")
public class ConnexionController {

    @GET
    public Response checkMailForConnexion(@QueryParam("mail") String mail, @QueryParam("type") String type){
        Integer typeInt=Integer.parseInt(type);
        System.out.println(typeInt);
        if (typeInt==1){
            try {
                MainManager.getInstance().getAideOperatoire(mail);
                return Response.status(204).build();
            }catch(UserNotFoundException e){
                return Response.status(404).build();
            }
        }else if(typeInt==2){
            try {
                MainManager.getInstance().getDocteur(mail);
                return Response.status(204).build();
            }catch (UserNotFoundException e){
                return Response.status(404).build();
            }
        }else if (typeInt==3){
            try {
                AdminManager.getInstance().getAdmin(mail);
                return Response.status(204).build();
            }catch (UserNotFoundException e){
                return Response.status(404).build();
            }
        }
        return Response.status(404).build();
    }
}
