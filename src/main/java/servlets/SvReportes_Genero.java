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
@WebServlet(name = "SvReportes_Genero", urlPatterns = {"/SvReportes_Genero"})
public class SvReportes_Genero extends HttpServlet {
    
    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String generoSeleccionado = request.getParameter("genero");

        List<Object[]> resultados = generoSeleccionado != null
                ? control.obtenerConteoPorGenero(generoSeleccionado)
                : control.obtenerConteoVotosPorPartido();


        request.setAttribute("generoSeleccionado", generoSeleccionado);
        request.setAttribute("datosGrafico", resultados);
        request.getRequestDispatcher("i_genero.jsp").forward(request, response);
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
