package com.maestria.platform.maestriaplatform.domain.model.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SelectedCompetency {

    private Long competencyId;

    @ElementCollection
    private List<String> selectedAbilities;
}
