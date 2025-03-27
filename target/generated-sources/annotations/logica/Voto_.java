package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.PartidoPol;
import logica.Votante;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-03-26T18:30:15")
@StaticMetamodel(Voto.class)
public class Voto_ { 

    public static volatile SingularAttribute<Voto, Votante> votante;
    public static volatile SingularAttribute<Voto, Date> fecha_voto;
    public static volatile SingularAttribute<Voto, PartidoPol> partidoPol;
    public static volatile SingularAttribute<Voto, Integer> id_voto;

}