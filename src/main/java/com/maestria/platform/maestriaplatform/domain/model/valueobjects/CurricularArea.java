package com.maestria.platform.maestriaplatform.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CurricularArea(String value) {

    public CurricularArea {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Curricular area cannot be null or blank");
        }
    }
}
