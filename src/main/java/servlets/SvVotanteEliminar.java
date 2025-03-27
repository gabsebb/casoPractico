package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Votante;
/**
 *
 * @author Gabse
 */
@WebServlet(name = "SvVotanteEliminar", urlPatterns = {"/SvVotanteEliminar"})
public class SvVotanteEliminar extends HttpServlet {
    private Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id_votante = Integer.parseInt(request.getParameter("id"));
            
            Votante votante = control.getVotyUser(id_votante);
            
            if (votante == null) {
                throw new Exception("Votante no encontrado");
            }

            control.eliminarVotanteYUsuario(votante.getId_votante(), votante.getUsuario().getId_usuario());
            
            response.sendRedirect("SvVotante?success=Votante+y+usuario+eliminados");
            
        } catch (Exception e) {
            response.sendRedirect("SvVotante?error=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
