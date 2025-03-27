package logica;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/**
 *
 * @author Gabse
 */
@Entity
public class Votante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_votante;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false) 
    private String cedula;
    @Column(nullable = false)
    private String provinciaVotante;
    @Column(nullable = false)
    private String ciudadVotante;
    @Column(nullable = false)
    private String generoVotante;
    @OneToOne
    @JoinColumn(name = "id_usuario",unique = true,nullable=false)
    private Usuario usuario;

    public Votante() {
    }

    public Votante(String nombre, String apellido, String cedula, String provinciaVotante, String ciudadVotante, String generoVotante, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.provinciaVotante = provinciaVotante;
        this.ciudadVotante = ciudadVotante;
        this.generoVotante = generoVotante;
        this.usuario = usuario;
    }

    public int getId_votante() {
        return id_votante;
    }

    public void setId_votante(int id_votante) {
        this.id_votante = id_votante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getProvinciaVotante() {
        return provinciaVotante;
    }

    public void setProvinciaVotante(String provinciaVotante) {
        this.provinciaVotante = provinciaVotante;
    }

    public String getCiudadVotante() {
        return ciudadVotante;
    }

    public void setCiudadVotante(String ciudadVotante) {
        this.ciudadVotante = ciudadVotante;
    }

    public String getGeneroVotante() {
        return generoVotante;
    }

    public void setGeneroVotante(String generoVotante) {
        this.generoVotante = generoVotante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
}
