package servlets;

import entities.AideOperatoire;
import entities.Docteur;
import managers.MainManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import utilisateurs.MotDePasseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifierpassword")
public class ModifierPasswordServlet extends GenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String utilisateurConnecte = (String) request.getSession().getAttribute("utilisateurConnecte");
        Integer profession=(Integer) request.getSession().getAttribute("profession");


        //GET PARAMETER
        String oldPassword=request.getParameter("oldpassword");
        String newPassword=request.getParameter("newpassword");

        if (profession==2){
            Docteur docteur = MainManager.getInstance().getDocteur(utilisateurConnecte);
            //MODIFICATION DE L'OJBET
            String correctOldPassword=docteur.getMdp();
            if (MotDePasseUtils.validerMotDePasse(oldPassword,correctOldPassword)){
                String hashNewPassword=MotDePasseUtils.genererMotDePasse(newPassword);
                docteur.setMdp(hashNewPassword);
                //UPDATE BDD
                MainManager.getInstance().updateDocteurPassword(docteur);

                response.sendRedirect("deconnexion");
            }else {
                response.sendRedirect("modifierpassword");
            }
        }else if(profession==1){
            AideOperatoire aideOperatoire = MainManager.getInstance().getAideOperatoire(utilisateurConnecte);
            //MODIFICATION DE L'OJBET
            String correctOldPassword=aideOperatoire.getMdp();
            if (MotDePasseUtils.validerMotDePasse(oldPassword,correctOldPassword)){
                String hashNewPassword=MotDePasseUtils.genererMotDePasse(newPassword);
                aideOperatoire.setMdp(hashNewPassword);
                //UPDATE BDD
                MainManager.getInstance().updateAideopPassword(aideOperatoire);

                response.sendRedirect("deconnexion");
            }else {
                response.sendRedirect("modifierpassword");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context=new WebContext(request,response,request.getServletContext());
        String utilisateurConnecte = (String) request.getSession().getAttribute("utilisateurConnecte");
        Integer profession=(Integer) request.getSession().getAttribute("profession");
        context.setVariable("profession",profession);


        if (utilisateurConnecte == null || "".equals(utilisateurConnecte)) {
            response.sendRedirect("connexion");
        } else {
            if (profession==2){
                Docteur docteur = MainManager.getInstance().getDocteur(utilisateurConnecte);
                context.setVariable("user",docteur);
            }else if(profession==1){
                AideOperatoire aideOperatoire=MainManager.getInstance().getAideOperatoire(utilisateurConnecte);
                context.setVariable("user",aideOperatoire);
            }

            TemplateEngine engine = createTemplateEngine(request.getServletContext());
            engine.process("modifierpassword", context, response.getWriter());

        }

    }
}
