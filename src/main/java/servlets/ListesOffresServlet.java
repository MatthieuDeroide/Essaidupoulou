package servlets;

import entities.AideOperatoire;
import entities.Docteur;
import entities.Offre;
import managers.MainManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listeoffres")
public class ListesOffresServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        String utilisateurConnecte = (String) request.getSession().getAttribute("utilisateurConnecte");
        Integer profession= (Integer) request.getSession().getAttribute("profession");
        if (utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            response.sendRedirect("connexion");
        } else{
            context.setVariable("profession",profession);
            List<Offre> offreList= MainManager.getInstance().listoffre();
            context.setVariable("offrelist",offreList);
            if (profession==1){
                AideOperatoire aideOperatoire=MainManager.getInstance().getAideOperatoire(utilisateurConnecte);
                context.setVariable("idUser",aideOperatoire.getId());
            }else if (profession == 2){
                Docteur docteur = MainManager.getInstance().getDocteur(utilisateurConnecte);
                context.setVariable("idUser",docteur.getId());
            }
            TemplateEngine engine = createTemplateEngine(request.getServletContext());
            engine.process("listeoffres", context, response.getWriter());

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
