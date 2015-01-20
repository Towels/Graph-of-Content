package com.towels.graphofcontent.util;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;



@Retention (RUNTIME)
@Target({METHOD})
public @interface RestrictedTo {
	String[] value();
}
