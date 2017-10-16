package com.br.projeto.model.entities;

import com.br.projeto.model.entities.Userproj;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-30T08:20:39")
@StaticMetamodel(Usertype.class)
public class Usertype_ { 

    public static volatile SingularAttribute<Usertype, Long> idUsertype;
    public static volatile ListAttribute<Usertype, Userproj> userprojList;
    public static volatile SingularAttribute<Usertype, String> title;

}