package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Voto;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-03-26T18:30:15")
@StaticMetamodel(PartidoPol.class)
public class PartidoPol_ { 

    public static volatile SingularAttribute<PartidoPol, String> nombre_presidente;
    public static volatile SingularAttribute<PartidoPol, Integer> id_partidoPol;
    public static volatile ListAttribute<PartidoPol, Voto> votos;
    public static volatile SingularAttribute<PartidoPol, String> nombre;
    public static volatile SingularAttribute<PartidoPol, String> nombre_vice;

}