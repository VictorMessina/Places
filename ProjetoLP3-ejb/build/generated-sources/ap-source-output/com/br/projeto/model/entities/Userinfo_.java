package com.br.projeto.model.entities;

import com.br.projeto.model.entities.Userproj;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-30T08:20:39")
@StaticMetamodel(Userinfo.class)
public class Userinfo_ { 

    public static volatile SingularAttribute<Userinfo, Date> birthday;
    public static volatile SingularAttribute<Userinfo, Userproj> userproj;
    public static volatile SingularAttribute<Userinfo, Long> idUserinfo;
    public static volatile SingularAttribute<Userinfo, String> fullname;
    public static volatile SingularAttribute<Userinfo, String> email;

}