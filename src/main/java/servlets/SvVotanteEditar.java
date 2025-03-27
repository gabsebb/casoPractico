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
@WebServlet(name = "SvVotanteEditar", urlPatterns = {"/SvVotanteEditar"})
public class SvVotanteEditar extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id_votante = Integer.parseInt(request.getParameter("id"));
            Votante votante = control.getVotyUser(id_votante);

            if (votante == null || votante.getUsuario() == null) {
                throw new Exception("Votante o Usuario no encontrado");
            }

            request.setAttribute("votante", votante);
            request.getRequestDispatcher("Votantes_Edicion.jsp").forward(request, response);

        } catch (Exception e) {
            String errorMsg = URLEncoder.encode("Error al cargar datos: " + e.getMessage(), "UTF-8");
            response.sendRedirect("SvVotante?error=" + errorMsg);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id_votante = Integer.parseInt(request.getParameter("id_votante"));
            String cedula = request.getParameter("inputCed");
            String nombre = request.getParameter("inputNom");
            String apellido = request.getParameter("inputApe");
            String provincia = request.getParameter("inputProv");
            String ciudad = request.getParameter("inputCiu");
            String genero = request.getParameter("inputGen");

            control.actualizarVotanteYUsuario(
                    id_votante,
                    cedula,
                    nombre,
                    apellido,
                    provincia,
                    ciudad,
                    genero
            );

            response.sendRedirect("SvVotante?success=Votante+y+Usuario+actualizados");

        } catch (Exception e) {
            String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
            response.sendRedirect("SvVotanteEditar?id=" + request.getParameter("id_votante") + "&error=" + encodedError);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
