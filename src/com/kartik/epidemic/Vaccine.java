package com.kartik.epidemic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Vaccine {

    /**
     * @return the Disease for which this annotated object is vaccinated against
     */
    Disease cures();

    /**
     * @return the seed which created this vaccine for the associated {@linkplain com.kartik.epidemic.Disease}
     */
    long seed() default Long.MIN_VALUE;

    /**
     * @return the antidote for the associated {@linkplain com.kartik.epidemic.Disease}
     */
    String antidote() default "";

}
