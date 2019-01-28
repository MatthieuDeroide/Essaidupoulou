package servlets;


import entities.AideOperatoire;
import entities.Docteur;
import exceptions.UserAlreadyExistsException;
import managers.MainManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import utilisateurs.MotDePasseUtils;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/inscription")
public class InscriptionServlet extends GenericServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //GET PARAMETERS
        String civility = request.getParameter("user_civility");
        String name = request.getParameter("user_name");
        String firstName = request.getParameter("user_firstname");
        String birthDateAsString = request.getParameter("user_birthdate");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateAsString, dateFormat);
        String gsmNumber = request.getParameter("user_GSM");
        String country = request.getParameter("user_country");
        String cp = request.getParameter("user_CP");
        String town = request.getParameter("user_town");
        String address = request.getParameter("user_address");
        String courriel = request.getParameter("courriel");
        String usrPassword = MotDePasseUtils.genererMotDePasse(request.getParameter("user_password"));
        Integer job = Integer.parseInt(request.getParameter("user_job"));

        //CREATING OBJECT
        if (job == 1) {
            AideOperatoire aideOperatoire = new AideOperatoire(civility,
                    name, firstName, birthDate, country, town, Integer.parseInt(cp), address,
                    "Aide Opératoire", courriel, gsmNumber, usrPassword);

            //INTO DATABASE
            try {
                MainManager.getInstance().addAideOperatoire(aideOperatoire);
            } catch (UserAlreadyExistsException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("utilisateurConnecte", aideOperatoire.getMail());
            request.getSession().setAttribute("profession", 1);
            response.sendRedirect("profil");
        } else {
            Docteur docteur = new Docteur(false, civility,
                    name, firstName, birthDate, country, town, Integer.parseInt(cp), address,
                    "Docteur", courriel, gsmNumber, usrPassword);

            //INTO DATABASE
            try {
                MainManager.getInstance().addDocteur(docteur);
            } catch (UserAlreadyExistsException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("utilisateurConnecte", docteur.getMail());
            request.getSession().setAttribute("profession", 2);
            response.sendRedirect("profil");
        }

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine engine = createTemplateEngine(request.getServletContext());

        engine.process("inscription", context, response.getWriter());
    }
}
