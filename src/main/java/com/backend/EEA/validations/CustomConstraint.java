package com.backend.EEA.validations;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
//@Constraint(validatedBy = ReceiptPaymentValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomConstraint {
    String message() default "CustomConstraint errors: check the CustomConstraint ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}