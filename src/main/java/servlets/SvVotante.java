package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;
import logica.Usuario;
import logica.Votante;

/**
 *
 * @author Gabse
 */
@WebServlet(name = "SvVotante", urlPatterns = {"/SvVotante"})
public class SvVotante extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Object[]> resultados = control.obtenerVotantesConUsuarios();
        List<Map<String, String>> datosCombinados = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Votante v = (Votante) resultado[0];
            Usuario u = (Usuario) resultado[1];

            Map<String, String> datos = new HashMap<>();
            datos.put("id", Integer.toString(v.getId_votante()));
            datos.put("cedula", v.getCedula());
            datos.put("nombre", v.getNombre());
            datos.put("apellido", v.getApellido());
            datos.put("provincia", v.getProvinciaVotante());
            datos.put("ciudad", v.getCiudadVotante());
            datos.put("genero", v.getGeneroVotante());
            datos.put("usuario", u != null ? u.getUsuario() : "Sin usuario");
            datos.put("rol", u != null ? u.getRol() : "Sin asignar");

            datosCombinados.add(datos);
        }

        request.setAttribute("datosVotantes", datosCombinados);
        request.getRequestDispatcher("Votantes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cedula = request.getParameter("inputCed");

        String nombre = request.getParameter("inputNom");
        String apellido = request.getParameter("inputApe");
        String provincia = request.getParameter("inputProv");
        String ciudad = request.getParameter("inputCiu");
        String genero = request.getParameter("inputGen");

        try {
            String username = cedula;
            String password = cedula;
            String rol = "VOTANTE";

            control.crearVotanteConUsuario(nombre, apellido, cedula, provincia, ciudad, genero,
                    username, password, rol);
            response.sendRedirect(request.getContextPath() + "/SvVotante");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear votante: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
