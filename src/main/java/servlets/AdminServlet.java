package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminHome")
public class AdminServlet extends GenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");
        Integer profession= (Integer)req.getSession().getAttribute("profession");
        Integer admin = (Integer)req.getSession().getAttribute("admin");

        if (profession != 3 || admin != 1){
            resp.sendRedirect("profil");
        }else {
            TemplateEngine engine = createTemplateEngine(req.getServletContext());
            engine.process("adminHome", context, resp.getWriter());
        }

    }
}
