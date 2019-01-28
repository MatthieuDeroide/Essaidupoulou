package servlets;


import entities.AideOperatoire;
import entities.Docteur;
import managers.MainManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import utilisateurs.MotDePasseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/modifierprofile")
public class ModifierProfileServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        Integer profession=(Integer) req.getSession().getAttribute("profession");



        if (utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            resp.sendRedirect("connexion");
        } else {

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
            engine.process("modifierProfile", context, resp.getWriter());

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");


        //GET PARAMETER
         String gsmNumber = req.getParameter("modification_gsm");
        String country = req.getParameter("modification_pays");
        Integer cp = Integer.parseInt(req.getParameter("modification_cp" ));
        String town = req.getParameter("modification_ville" );
        String address = req.getParameter("modification_adresse");


        Integer profession=(Integer) req.getSession().getAttribute("profession");

        if(profession==1){

            //MODIFICATION DE L'OJBET

            AideOperatoire aideOperatoire=MainManager.getInstance().getAideOperatoire(utilisateurConnecte);
            aideOperatoire.setNumeroTel(gsmNumber);
            aideOperatoire.setPays(country);
            aideOperatoire.setCodePostal(cp);
            aideOperatoire.setVille(town);
            aideOperatoire.setAdresse(address);

            //UPDATE BDD

            MainManager.getInstance().updateAideop(aideOperatoire);

        }else if (profession==2){

            //MODIFICATION DE L'OJBET

            Docteur docteur = MainManager.getInstance().getDocteur(utilisateurConnecte);
            docteur.setNumeroTel(gsmNumber);
            docteur.setPays(country);
            docteur.setCodePostal(cp);
            docteur.setVille(town);
            docteur.setAdresse(address);

            //UPDATE BDD

            MainManager.getInstance().updateDocteur(docteur);
        }




        resp.sendRedirect("detailprofil");

    }
}