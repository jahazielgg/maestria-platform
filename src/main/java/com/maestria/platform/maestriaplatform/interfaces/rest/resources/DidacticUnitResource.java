package com.maestria.platform.maestriaplatform.interfaces.rest.resources;

import java.util.List;

public record DidacticUnitResource(
        String educationLevel,
        String cycle,
        String grade,
        List<String> selectedCurricularAreas,
        List<SelectedCompetencyResource> selectedCompetencies,
        List<SelectedTransversalCompetencyResource> selectedTransversalCompetencies,
        List<String> selectedTransversalApproaches,
        String significantSituation,
        DidacticUnitDetailsResource didacticUnitDetails
) {}
