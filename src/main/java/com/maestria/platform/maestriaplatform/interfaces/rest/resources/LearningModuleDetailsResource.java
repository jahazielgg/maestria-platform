package com.maestria.platform.maestriaplatform.interfaces.rest.resources;

import java.util.Map;

public record LearningModuleDetailsResource(
        String type,
        String title,
        String startDate,
        String endDate,
        Map<String, String> purposeByArea,
        String finalProduct,
        int numberOfSessions,
        String baseUnit,
        String pedagogicalJustification
) implements DidacticUnitDetailsResource {}