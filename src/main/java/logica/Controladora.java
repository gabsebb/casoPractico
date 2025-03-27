package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;

/**
 *
 * @author Gabse
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearVotanteConUsuario(String nombre, String apellido, String cedula, String provincia, String ciudad, String genero, String username, String password, String rol) {
        controlPersis.crearVotanteConUsuario(nombre,apellido,cedula,provincia,ciudad,genero,username,password,rol);
    }

    public Usuario validarLogin(String usuario, String contra) {
        return controlPersis.accesoLogin(usuario, contra);
    }

    public List<Object[]> obtenerVotantesConUsuarios() {
        return controlPersis.getVotantesConUsuarios();
    }

    public Votante obtenerVotantePorUsuario(int id_usuario) {
        return controlPersis.getVotantePorUsuario(id_usuario);
    }

    public List<PartidoPol> obtenerPartidos() {
        return controlPersis.getPartidos();
    }

    public boolean yaVoto(int id_votante) {
        return controlPersis.existeVoto(id_votante);
    }

    public void registrarVoto(int id_votante, int id_partido) throws Exception {
        if (yaVoto(id_votante)) {
            throw new Exception("El votante ya ejerció su derecho al voto");
        }
        controlPersis.registrarVoto(id_votante, id_partido);
    }

    public Votante getVotyUser(int id_votante) {
        return controlPersis.getVotyUser(id_votante);
    }

    public void actualizarVotanteYUsuario(int idVotante, String cedula, String nombre, String apellido, String provincia, String ciudad, String genero) throws Exception {
        Votante votante = controlPersis.getVotyUser(idVotante);
        if (votante == null) {
            throw new Exception("Votante no encontrado");
        }
        votante.setNombre(nombre);
        votante.setApellido(apellido);
        votante.setCedula(cedula);
        votante.setProvinciaVotante(provincia);
        votante.setCiudadVotante(ciudad);
        votante.setGeneroVotante(genero);

        Usuario usuario = votante.getUsuario();
        usuario.setUsuario(cedula);
        usuario.setContrasenia(cedula);
        usuario.setRol("VOTANTE");

        controlPersis.actualizarVotanteYUsuario(votante, usuario);
    }

    public void eliminarVotanteYUsuario(int id_votante, int id_usuario) throws Exception {
        Votante votante = controlPersis.getVotyUser(id_votante);
        if (votante == null || votante.getUsuario() == null) {
            throw new Exception("No se puede eliminar: relación inválida");
        }
        controlPersis.eliminarVotanteYUsuario(id_votante, id_usuario);
    }
    public List<Object[]> obtenerConteoVotosPorPartido(){
        return controlPersis.obtenerConteoVotosPorPartido();
    }
    
    public List<Object[]> obtenerConteoPorGenero(String generoSeleccionado) {
        return controlPersis.obtenerConteoPorGenero(generoSeleccionado);
    }

    public List<Object[]> obtenerConteoPorProvincia(String provinciaSeleccionada) {
       return controlPersis.obtenerConteoPorProvincia(provinciaSeleccionada);
    }

    public List<Object[]> obtenerConteoPorCiudad(String ciudadSeleccionada) {
        return controlPersis.obtenerConteoPorCiudad(ciudadSeleccionada);
    }

}
