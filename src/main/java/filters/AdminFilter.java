package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/adminHome/*")
public class AdminFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String utilisateurConnecte = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");
        Integer profession= (Integer)httpRequest.getSession().getAttribute("profession");
        Integer admin = (Integer)httpRequest.getSession().getAttribute("admin");

        if(utilisateurConnecte==null){
            System.out.println("Il faut être administrateur pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            httpResponse.sendRedirect("../connexion");
        }
        else if(admin==null && utilisateurConnecte!=null){
            System.out.println("Il faut être administrateur pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            httpResponse.sendRedirect("../profil");
        }
        else {
            chain.doFilter(req, resp);
        }

    }

    public void destroy() {
    }
}
