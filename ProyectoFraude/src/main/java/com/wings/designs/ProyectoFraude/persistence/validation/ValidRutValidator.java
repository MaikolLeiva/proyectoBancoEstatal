/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.persistence.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRutValidator implements ConstraintValidator<ValidRut, String> {
    public void initialize(ValidRut constraint) {
    }

    public boolean isValid(String rut, ConstraintValidatorContext context) {
        boolean validation = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validation = true;
            }

        } catch (java.lang.NumberFormatException e) {
            return  false;
        } catch (Exception e) {
            return false;
        }
        return validation;
    }



}
