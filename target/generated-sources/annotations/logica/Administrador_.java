package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-03-26T18:30:15")
@StaticMetamodel(Administrador.class)
public class Administrador_ { 

    public static volatile SingularAttribute<Administrador, Integer> id_admin;
    public static volatile SingularAttribute<Administrador, String> apellido;
    public static volatile SingularAttribute<Administrador, String> departamento;
    public static volatile SingularAttribute<Administrador, Usuario> usuario;
    public static volatile SingularAttribute<Administrador, String> cargo;
    public static volatile SingularAttribute<Administrador, String> nombre;

}