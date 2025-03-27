package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;

/**
 *
 * @author Gabse
 */
@WebServlet(name = "SvReportes_Ciudad", urlPatterns = {"/SvReportes_Ciudad"})
public class SvReportes_Ciudad extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ciudadSeleccionada = request.getParameter("ciudad");

        List<Object[]> datosGrafico = ciudadSeleccionada != null
                ? control.obtenerConteoPorCiudad(ciudadSeleccionada)
                : control.obtenerConteoVotosPorPartido();

        request.setAttribute("ciudadSeleccionada", ciudadSeleccionada);
        request.setAttribute("datosGrafico", datosGrafico);
        request.getRequestDispatcher("i_ciudad.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
