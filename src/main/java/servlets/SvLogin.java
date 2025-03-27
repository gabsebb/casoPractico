package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;
import logica.Votante;

/**
 *
 * @author Gabse
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("inputUsuario");
        String contra = request.getParameter("inputContra");
        Controladora contro = new Controladora();

        Usuario usuarioAutenticado = contro.validarLogin(usuario, contra);

        if (usuarioAutenticado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("Usuario", usuarioAutenticado);

            Votante votante = contro.obtenerVotantePorUsuario(usuarioAutenticado.getId_usuario());
            session.setAttribute("Votante", votante);

            if (usuarioAutenticado.getRol().equals("ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/SvReportes");
            } else {
                response.sendRedirect(request.getContextPath() + "/SvVotar");
            }
        } else {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
