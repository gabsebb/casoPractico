package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Gabse
 */
@Entity
public class PartidoPol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_partidoPol;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(name = "nombre_presidente", nullable = false)
    private String nombre_presidente;
    @Column(name = "nombre_vice", nullable = false)
    private String nombre_vice;
    @OneToMany(mappedBy="partidoPol",cascade = CascadeType.ALL)
    private List<Voto> votos = new ArrayList<>();
    
    public PartidoPol() {
    }
    
    public PartidoPol(String nombre, String nombre_presidente, String nombre_vice) {
        this.nombre = nombre;
        this.nombre_presidente = nombre_presidente;
        this.nombre_vice = nombre_vice;
    }

    public int getId_partidoPol() {
        return id_partidoPol;
    }

    public void setId_partidoPol(int id_partidoPol) {
        this.id_partidoPol = id_partidoPol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_presidente() {
        return nombre_presidente;
    }

    public void setNombre_presidente(String nombre_presidente) {
        this.nombre_presidente = nombre_presidente;
    }

    public String getNombre_vice() {
        return nombre_vice;
    }

    public void setNombre_vice(String nombre_vice) {
        this.nombre_vice = nombre_vice;
    }

    public List<Voto> getLista_voto() {
        return votos;
    }

    public void setLista_voto(List<Voto> lista_voto) {
        this.votos = lista_voto;
    }
    
    
    
    
}
