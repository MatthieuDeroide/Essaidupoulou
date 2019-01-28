package servlets;

import entities.Docteur;
import managers.AdminManager;
import managers.MainManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import utilisateurs.MotDePasseUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //RECUPERATION INFO USER
        Integer profession=Integer.parseInt(request.getParameter("profession"));
        String identifiant = request.getParameter("login");
        String mdp = request.getParameter("password");
        System.out.println(identifiant);

        //RECUPERATION DE L'UTILISATEUR
        if (profession==2){
            String correctMdp=MainManager.getInstance().getDocteur(identifiant).getMdp();
            verifMdp(request, response, profession, identifiant, mdp, correctMdp);
        }else if (profession == 1){
            String correctMdp=MainManager.getInstance().getAideOperatoire(identifiant).getMdp();
            verifMdp(request, response, profession, identifiant, mdp, correctMdp);
        }else if (profession == 3){
            String correctMdp = AdminManager.getInstance().getAdmin(identifiant).getMdp();
            verifMdp(request, response, profession, identifiant, mdp, correctMdp);
        }




    }

    private void verifMdp(HttpServletRequest request, HttpServletResponse response, Integer profession, String identifiant, String mdp, String correctMdp) throws IOException {
        if(MotDePasseUtils.validerMotDePasse(mdp,correctMdp)){
            request.getSession().setAttribute("utilisateurConnecte", identifiant);
            request.getSession().setAttribute("profession", profession);
            if (profession == 3){
                request.getSession().setAttribute("admin", 1);
                response.sendRedirect("adminHome");
            }else {
                response.sendRedirect("profil");
            }
        }else {
            response.sendRedirect("connexion?err=1");
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException, IOException {


        WebContext context = new WebContext(request, response, request.getServletContext());

        if(request.getParameter("err")!=null && !request.getParameter("err").equals("")){
            if (request.getParameter("err").equals("1")){
                context.setVariable("err",1);
                System.out.println(request.getParameter("err"));
            }
        }
        TemplateEngine engine = createTemplateEngine(request.getServletContext());

        engine.process("connexion", context, response.getWriter());
    }
}



