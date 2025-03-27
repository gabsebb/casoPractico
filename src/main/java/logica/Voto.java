package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Gabse
 */
@Entity
public class Voto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_voto;
    
    @OneToOne
    @JoinColumn(name = "id_votante",unique=true)
    private Votante votante;
    
    @ManyToOne
    @JoinColumn(name="id_partidoPol",nullable = false)
    private PartidoPol partidoPol;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_voto=new Date();

    public Voto() {
        this.fecha_voto = new Date();
    }

    public Voto(Votante votante, PartidoPol partidoPol, Date fecha_voto) {
        this.votante = votante;
        this.partidoPol = partidoPol;
        this.fecha_voto = new Date();
    }

    public int getId_voto() {
        return id_voto;
    }

    public void setId_voto(int id_voto) {
        this.id_voto = id_voto;
    }

    public Votante getVotante() {
        return votante;
    }

    public void setVotante(Votante votante) {
        this.votante = votante;
    }

    public PartidoPol getPartidoPol() {
        return partidoPol;
    }

    public void setPartidoPol(PartidoPol partidoPol) {
        this.partidoPol = partidoPol;
    }

    public Date getFecha_voto() {
        return fecha_voto;
    }

    public void setFecha_voto(Date fecha_voto) {
        this.fecha_voto = fecha_voto;
    }
    
}

