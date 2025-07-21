package com.maestria.platform.maestriaplatform.infrastructure.persistence.jpa.repositories;

import com.maestria.platform.maestriaplatform.domain.model.aggregates.DidacticUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DidacticUnitRepository extends JpaRepository<DidacticUnit, Long> {
}
