package com.hautbook.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author caiyao 
 *
 * @function ×Ö·û´®³¤¶È·¶Î§
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
	int minLength() ;
	int maxLength() ;
}
