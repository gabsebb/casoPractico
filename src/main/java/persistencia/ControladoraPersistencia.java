package persistencia;

import java.util.List;
import logica.PartidoPol;
import logica.Usuario;
import logica.Votante;

/**
 *
 * @author Gabse
 */
public class ControladoraPersistencia {
    AdministradorJpaController adminJPA = new AdministradorJpaController();
    PartidoPolJpaController partidoJPA = new PartidoPolJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();
    VotanteJpaController votanteJPA = new VotanteJpaController();
    VotoJpaController votoJPA = new VotoJpaController();

    public Usuario accesoLogin(String usuario, String contra) {
        return usuarioJPA.validarCredenciales(usuario, contra);
    }

 
    public List<Object[]> getVotantesConUsuarios() {
       return votanteJPA.getVotanteConUsuarios();
    }

    public Votante getVotantePorUsuario(int id_usuario) {
        return votanteJPA.getVotantePorIdUsuario(id_usuario);
    }

    public List<PartidoPol> getPartidos() {
       return partidoJPA.findPartidoPolEntities();
    }

    public boolean existeVoto(int id_votante) {
        return votoJPA.existeVoto(id_votante);
    }

    public void registrarVoto(int id_votante, int id_partido) {
        votoJPA.guardarVoto(id_votante,id_partido);
    }

    public Votante getVotyUser(int id_votante) {
        return votanteJPA.getVotyUser(id_votante);
    }

    public void actualizarVotanteYUsuario(Votante votante, Usuario usuario) throws Exception {
        votanteJPA.actualizarVotante(votante, usuario);
    }

    public void eliminarVotanteYUsuario(int id_votante, int id_usuario) throws Exception {
       votanteJPA.eliminarVotanteYUsuario(id_votante,id_usuario);
    }

    public void crearVotanteConUsuario(String nombre, String apellido, String cedula, String provincia, String ciudad, String genero, String username, String password, String rol) {
       votanteJPA.crearVotanteConUsuario(nombre,apellido,cedula,provincia,ciudad,genero,username,password,rol);
    }
    public List<Object[]> obtenerConteoVotosPorPartido(){
        return votoJPA.obtenerConteoVotosPorPartido();
    }

    public List<Object[]> obtenerConteoPorGenero(String generoSeleccionado) {
        return votoJPA.obtenerConteoPorGenero(generoSeleccionado);
    }

    public List<Object[]> obtenerConteoPorProvincia(String provinciaSeleccionada) {
        return votoJPA.obtenerConteoPorProvincia(provinciaSeleccionada);
    }

    public List<Object[]> obtenerConteoPorCiudad(String ciudadSeleccionada) {
        return votoJPA.obtenerConteoPorCiudad(ciudadSeleccionada);
    }

}
