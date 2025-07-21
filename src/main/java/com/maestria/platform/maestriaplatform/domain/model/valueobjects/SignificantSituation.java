package com.maestria.platform.maestriaplatform.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SignificantSituation(String value) {

    public SignificantSituation {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Significant situation cannot be null or blank");
        }
    }
}
