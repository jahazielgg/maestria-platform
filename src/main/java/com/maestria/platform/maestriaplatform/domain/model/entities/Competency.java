package com.maestria.platform.maestriaplatform.domain.model.entities;

import com.maestria.platform.maestriaplatform.domain.model.valueobjects.CurricularArea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Competency {

    @Id
    private Long id;

    private String name;

    private String curricularArea;

    @ElementCollection
    private List<String> abilities;
}

