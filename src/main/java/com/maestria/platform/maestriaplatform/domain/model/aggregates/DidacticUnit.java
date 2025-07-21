package com.maestria.platform.maestriaplatform.domain.model.aggregates;

import com.maestria.platform.maestriaplatform.domain.model.entities.SelectedCompetency;
import com.maestria.platform.maestriaplatform.domain.model.valueobjects.DidacticUnitType;
import com.maestria.platform.maestriaplatform.domain.model.valueobjects.FinalProduct;
import com.maestria.platform.maestriaplatform.domain.model.valueobjects.NumberOfSessions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class DidacticUnit {
    @Id
    private UUID id;

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @Embedded
    public DidacticUnitType type;

    @ElementCollection
    private Map<String, String> purposeByArea; // CurricularArea -> purpose

    @Embedded
    private FinalProduct finalProduct;

    @Embedded
    private NumberOfSessions numberOfSessions;

    @ElementCollection
    private List<SelectedCompetency> selectedCompetencies;

    @ElementCollection
    private List<String> selectedTransversalApproaches;

    private String significantSituation;
}
