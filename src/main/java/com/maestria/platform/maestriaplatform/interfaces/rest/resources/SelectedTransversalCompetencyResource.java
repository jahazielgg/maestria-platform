package com.maestria.platform.maestriaplatform.interfaces.rest.resources;

import java.util.List;

public record SelectedTransversalCompetencyResource(
        String name,
        List<String> selectedAbilities
) {
}
