package com.br.projeto.model.entities;

import com.br.projeto.model.entities.Directory;
import com.br.projeto.model.entities.Userinfo;
import com.br.projeto.model.entities.Usertype;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-30T08:20:39")
@StaticMetamodel(Userproj.class)
public class Userproj_ { 

    public static volatile SingularAttribute<Userproj, Long> idUser;
    public static volatile SingularAttribute<Userproj, String> password;
    public static volatile SingularAttribute<Userproj, Userinfo> userinfo;
    public static volatile ListAttribute<Userproj, Directory> directoryList;
    public static volatile SingularAttribute<Userproj, Usertype> fkUsertype;
    public static volatile SingularAttribute<Userproj, String> username;

}