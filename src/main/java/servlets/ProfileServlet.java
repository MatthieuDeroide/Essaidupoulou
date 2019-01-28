package servlets;

import entities.AideOperatoire;
import entities.Docteur;
import managers.MainManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/profil")
public class ProfileServlet extends GenericServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        Integer profession= (Integer)req.getSession().getAttribute("profession");
        if (utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            resp.sendRedirect("connexion");
        }else {

            //RECUPERATION et CREATION de l'Objet DOCTEUR ou AIDEop CONCERNE 1- aideop 2- docteur

            if (profession==1){//AIDEOP

                AideOperatoire aideOperatoire=MainManager.getInstance().getAideOperatoire(utilisateurConnecte);
                System.out.println("J'ai récupéré " + utilisateurConnecte + " dans la session");
                //ENVOI DE L'OBJET DOCTEUR AU TEMPLATE
                context.setVariable("user", aideOperatoire);
                context.setVariable("profession",profession);

            }else if (profession==2){ //DOCTEUR

                Docteur docteur = MainManager.getInstance().getDocteur(utilisateurConnecte);
                System.out.println("J'ai récupéré " + utilisateurConnecte + " dans la session");
                //ENVOI DE L'OBJET DOCTEUR AU TEMPLATE
                context.setVariable("user", docteur);
                context.setVariable("profession",profession);
            }




            TemplateEngine engine = createTemplateEngine(req.getServletContext());
            engine.process("profile", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
