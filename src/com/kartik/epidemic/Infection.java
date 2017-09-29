package com.kartik.epidemic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface Infection {

    /**
     * @return the disease with which the annotation object is infected
     */
    Disease cause();

}
