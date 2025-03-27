package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-03-26T18:30:15")
@StaticMetamodel(Votante.class)
public class Votante_ { 

    public static volatile SingularAttribute<Votante, Integer> id_votante;
    public static volatile SingularAttribute<Votante, String> ciudadVotante;
    public static volatile SingularAttribute<Votante, String> cedula;
    public static volatile SingularAttribute<Votante, String> apellido;
    public static volatile SingularAttribute<Votante, Usuario> usuario;
    public static volatile SingularAttribute<Votante, String> nombre;
    public static volatile SingularAttribute<Votante, String> generoVotante;
    public static volatile SingularAttribute<Votante, String> provinciaVotante;

}