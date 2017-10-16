package com.br.projeto.model.entities;

import com.br.projeto.model.entities.Place;
import com.br.projeto.model.entities.Userproj;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-30T08:20:39")
@StaticMetamodel(Directory.class)
public class Directory_ { 

    public static volatile SingularAttribute<Directory, String> nameDirectory;
    public static volatile ListAttribute<Directory, Place> placeList;
    public static volatile SingularAttribute<Directory, Long> idDirectory;
    public static volatile SingularAttribute<Directory, Userproj> fkUserproj;

}