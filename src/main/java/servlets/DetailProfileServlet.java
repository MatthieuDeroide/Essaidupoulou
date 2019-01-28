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

@WebServlet("/detailprofil")
public class DetailProfileServlet extends GenericServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        Integer profession=(Integer) req.getSession().getAttribute("profession");
        if (utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            resp.sendRedirect("connexion");
        }else {

            //RECUPERATION et CREATION de l'Objet DOCTEUR CONCERNE ou AIDEOP  1- aideop 2- docteur
            if(profession==1){
                AideOperatoire aideOperatoire=MainManager.getInstance().getAideOperatoire(utilisateurConnecte);

                //ENVOI de l'objet AideOP
                context.setVariable("user",aideOperatoire);

            }else if(profession==2){
                Docteur docteur = MainManager.getInstance().getDocteur(utilisateurConnecte);

                //ENVOI DE L'OBJET DOCTEUR AU TEMPLATE
                context.setVariable("user", docteur);
            }



            TemplateEngine engine = createTemplateEngine(req.getServletContext());
            engine.process("detailprofile", context, resp.getWriter());
        }
    }
}
