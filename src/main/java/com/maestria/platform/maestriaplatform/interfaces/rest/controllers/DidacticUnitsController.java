package com.maestria.platform.maestriaplatform.interfaces.rest.controllers;

import com.maestria.platform.maestriaplatform.infrastructure.ollama.OllamaClient;
import com.maestria.platform.maestriaplatform.interfaces.rest.resources.DidacticUnitResource;
import com.maestria.platform.maestriaplatform.interfaces.rest.resources.SelectedCompetencyResource;
import com.maestria.platform.maestriaplatform.interfaces.rest.resources.SelectedTransversalCompetencyResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/didactic-units")
public class DidacticUnitsController {
    private final OllamaClient ollamaClient;

    public DidacticUnitsController(OllamaClient ollamaClient) {
        this.ollamaClient = ollamaClient;
    }

    @PostMapping
    public ResponseEntity<String> generate(@RequestBody DidacticUnitResource resource) {
        String prompt = buildPrompt(resource);
        String generatedMaterial = ollamaClient.generateMaterial(prompt);
        return ResponseEntity.ok(generatedMaterial);
    }

    private String buildPrompt(DidacticUnitResource request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Eres un experto en diseño curricular del Perú. Genera una UNIDAD DE APRENDIZAJE completa a partir de la siguiente información. Por favor, no empieces dando una introducción como 'A continuación' u otros, de frente brinda el informe. Haz TODO en formato MARKDOWN\n\n");

        // Sección 1
        sb.append("1. PLANTEAMIENTO DE LA SITUACIÓN SIGNIFICATIVA\n");
        sb.append("Nivel educativo: ").append(request.educationLevel()).append("\n");
        sb.append("Ciclo: ").append(request.cycle()).append("\n");
        sb.append("Grado: ").append(request.grade()).append("\n");
        sb.append("Situación significativa proporcionada: ")
                .append(request.significantSituation() != null ? request.significantSituation() : "No se proporcionó una situación. Por favor, propón una.")
                .append("\n");
        sb.append("Por favor, amplía el planteamiento si es necesario, agregando contexto, problemática y preguntas abiertas retadoras que motiven el aprendizaje (por ejemplo: ¿Cómo podríamos...? ¿Qué podemos hacer si...?).\n\n");

        // Sección 2
        sb.append("2. PROPÓSITOS DE APRENDIZAJE\n");
        sb.append("2.1 Producto de la unidad: ");
        sb.append(request.didacticUnitDetails() != null && !request.didacticUnitDetails().isEmpty()
                        ? request.didacticUnitDetails()
                        : "No se indicó un producto final. Por favor, propone uno coherente con la situación significativa.")
                .append("\n");
        sb.append("2.2 Actividades semanales: Diseña una secuencia de actividades distribuidas por semanas, orientadas al logro del producto final.\n\n");

        // Sección 3
        sb.append("3. MATRIZ DE PROPÓSITO DE APRENDIZAJE\n");
        sb.append("Para cada área curricular seleccionada, detalla lo siguiente en una tabla:\n");
        sb.append("- Área Curricular\n");
        sb.append("- Competencia y capacidades asociadas\n");
        sb.append("- Metas de aprendizaje\n");
        sb.append("- Desempeño esperado\n");
        sb.append("- Criterios de evaluación\n");
        sb.append("- Evidencias de aprendizaje\n");
        sb.append("- Instrumentos de evaluación\n");
        sb.append("- Actividad principal asociada\n\n");

        if (request.selectedCurricularAreas() != null && request.selectedCompetencies() != null) {
            for (String area : request.selectedCurricularAreas()) {
                for (SelectedCompetencyResource sc : request.selectedCompetencies()) {
                    sb.append("Área: ").append(area).append("\n");
                    sb.append("Competencia: ").append(sc.name()).append("\n");
                    sb.append("Capacidades: ").append(String.join(", ", sc.selectedAbilities())).append("\n\n");
                }
            }
        }

        // Sección 4
        sb.append("4. ENFOQUES TRANSVERSALES\n");
        sb.append("A partir de los enfoques transversales seleccionados, completa una tabla con:\n");
        sb.append("- Enfoque transversal\n");
        sb.append("- Valores que promueve\n");
        sb.append("- Actitudes esperadas\n\n");

        if (request.selectedTransversalApproaches() != null && !request.selectedTransversalApproaches().isEmpty()) {
            for (String enfoque : request.selectedTransversalApproaches()) {
                sb.append("Enfoque: ").append(enfoque).append("\n");
            }
        } else {
            sb.append("No se indicaron enfoques transversales. Por favor, propone algunos relevantes según la situación significativa.\n");
        }

        // Sección 5
        sb.append("\n5. COMPETENCIAS TRANSVERSALES\n");
        sb.append("Incluye una tabla con las competencias transversales indicando el nombre de la competencia obtenida, y las capacidades enumeradas, no cambies el contenido del nombre ni de las capacidades:\n");
        sb.append("- Competencia transversal\n");
        sb.append("- Capacidades asociadas\n");
        sb.append("- Criterios de evaluación\n\n");

        if (request.selectedTransversalCompetencies() != null && !request.selectedTransversalCompetencies().isEmpty()) {
            for (SelectedTransversalCompetencyResource tc : request.selectedTransversalCompetencies()) {
                sb.append("Competencia transversal: ").append(tc.name()).append("\n");
                sb.append("Capacidades asociadas: ").append(String.join(", ", tc.selectedAbilities())).append("\n\n");
            }
        } else {
            sb.append("No se indicaron competencias transversales. Por favor, incluye al menos una.\n");
        }

        return sb.toString();
    }
}
