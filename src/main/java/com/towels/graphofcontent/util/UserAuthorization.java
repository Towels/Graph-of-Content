package com.towels.graphofcontent.util;
import java.lang.annotation.*;

import javax.ws.rs.NameBinding;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;



@NameBinding
@Target( { ElementType.TYPE, ElementType.METHOD } )
@Retention( value = RetentionPolicy.RUNTIME )
public @interface UserAuthorization {
}
