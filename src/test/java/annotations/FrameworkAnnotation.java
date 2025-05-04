package annotations;

import enums.BrowserType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameworkAnnotation {

    BrowserType browser() default BrowserType.CHROME;

}
