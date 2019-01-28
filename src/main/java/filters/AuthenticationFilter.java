package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/profil/*")
public class AuthenticationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String identifiant = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");
        if (identifiant == null || "".equals(identifiant)) {
            System.out.println("Il faut être connecté pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("../connexion");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}