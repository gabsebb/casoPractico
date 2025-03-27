package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.PartidoPol;
import logica.Votante;

/**
 *
 * @author Gabse
 */
@WebServlet(name = "SvVotar", urlPatterns = {"/SvVotar"})
public class SvVotar extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PartidoPol> partidos = control.obtenerPartidos();
        request.setAttribute("partidos", partidos);
        request.getRequestDispatcher("votacion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Votante votante = (Votante) session.getAttribute("Votante");
        if (votante == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Calendar ahora = Calendar.getInstance();
        int horaActual = ahora.get(Calendar.HOUR_OF_DAY);

        if (horaActual < 7 || horaActual >= 17) {
            request.setAttribute("error", "El sistema de votación solo está disponible de 7:00 AM a 5:00 PM");
            doGet(request, response);
            return;
        }

        if (control.yaVoto(votante.getId_votante())) {
            request.setAttribute("error", "Usted ya ha emitido su voto");
            doGet(request, response);
            return;
        }

        try {
            int id_partido = Integer.parseInt(request.getParameter("idPartido"));
            control.registrarVoto(votante.getId_votante(), id_partido);
            session.setAttribute("mensajeExito", "¡Voto registrado correctamente!");
            response.sendRedirect("votacion_realizada.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Error al procesar el voto: " + e.getMessage());
            doGet(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
