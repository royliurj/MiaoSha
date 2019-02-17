package com.roy.miaosha.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface  IsMobile {

    boolean required() default true;

    String message() default "手机号码格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
