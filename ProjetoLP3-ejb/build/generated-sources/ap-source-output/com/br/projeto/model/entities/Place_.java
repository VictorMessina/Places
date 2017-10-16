package com.br.projeto.model.entities;

import com.br.projeto.model.entities.Directory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-30T08:20:39")
@StaticMetamodel(Place.class)
public class Place_ { 

    public static volatile SingularAttribute<Place, Long> idPlace;
    public static volatile SingularAttribute<Place, Integer> popularity;
    public static volatile SingularAttribute<Place, String> idApi;
    public static volatile SingularAttribute<Place, String> nameApi;
    public static volatile ListAttribute<Place, Directory> directoryList;

}