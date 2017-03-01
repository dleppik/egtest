package com.vocalabs.egtest.processor.data;

import com.vocalabs.egtest.annotation.EgMatch;
import com.vocalabs.egtest.annotation.EgNoMatch;

import java.lang.annotation.Annotation;

/** Matches and NoMatch. */
public abstract class MatchExample implements Example<Annotation> {
    private final Annotation annotation;

    MatchExample(Annotation annotation) {
        this.annotation = annotation;
    }

    public String toMatch() {
        if (annotation instanceof EgMatch)
            return ((EgMatch) annotation).value();
        if (annotation instanceof EgNoMatch)
            return ((EgNoMatch) annotation).value();
        throw new IllegalArgumentException("Wrong class for "+annotation);
    }

    @Override
    public Annotation getAnnotation() { return annotation; }
}
