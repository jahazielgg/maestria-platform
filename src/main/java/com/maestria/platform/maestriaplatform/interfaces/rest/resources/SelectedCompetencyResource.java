package com.maestria.platform.maestriaplatform.interfaces.rest.resources;

import java.util.List;

public record SelectedCompetencyResource(
        String name,
        String area,
        List<String> selectedAbilities
) {
}
