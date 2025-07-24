package com.maestria.platform.maestriaplatform.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LearningUnitDetailsResource.class, name = "unidadAprendizaje"),
        @JsonSubTypes.Type(value = LearningProjectDetailsResource.class, name = "proyectoAprendizaje"),
        @JsonSubTypes.Type(value = LearningModuleDetailsResource.class, name = "moduloAprendizaje")
})
public interface DidacticUnitDetailsResource {
    String type();
    String title();
    String startDate();
    String endDate();
    Map<String, String> purposeByArea();
    String finalProduct();
    int numberOfSessions();
}
