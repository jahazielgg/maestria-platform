package com.maestria.platform.maestriaplatform.interfaces.rest.controllers;

import com.maestria.platform.maestriaplatform.infrastructure.ollama.OllamaClient;
import com.maestria.platform.maestriaplatform.interfaces.rest.resources.*;
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
        try {
            if (resource == null) {
                return ResponseEntity.badRequest().body("Request body cannot be null");
            }

            if (resource.didacticUnitDetails() == null) {
                return ResponseEntity.badRequest().body("Didactic unit details cannot be null");
            }

            // Debug logging
            System.out.println("Received resource: " + resource);
            System.out.println("Class of didacticUnitDetails: " + resource.didacticUnitDetails().getClass());
            System.out.println("Type: " + resource.didacticUnitDetails().type());

            String prompt = buildPrompt(resource);
            String generatedMaterial = ollamaClient.generateMaterial(prompt);
            return ResponseEntity.ok(generatedMaterial);

        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    private String buildPrompt(DidacticUnitResource request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Eres un experto en diseño curricular del Perú. Genera una UNIDAD DE APRENDIZAJE completa a partir de la siguiente información. Por favor, no empieces dando una introducción como 'A continuación' u otros, de frente brinda el informe. Haz TODO en formato MARKDOWN, SOLO MARKDOWN. Por favor, respeta la ENUMERACIÓN que se te brinda (e.g. Coloca '1. SITUACIÓN SIGNIFICATIVA', 2. PROPÓSITOS DE APRENDIZAJE, etc.), esos subtítulos van en NEGRITA, coloca saltos de línea despues de cada subtítulo.\n\n");

        // Información general
        var details = request.didacticUnitDetails();
        sb.append(details.title()).append("\n\n");
        sb.append("Nivel educativo: ").append(request.educationLevel()).append("\n");
        sb.append("Ciclo: ").append(request.cycle()).append("\n");
        sb.append("Grado: ").append(request.grade()).append("\n");
        sb.append("Fecha de inicio: ").append(details.startDate()).append("\n");
        sb.append("Fecha de fin: ").append(details.endDate()).append("\n\n");

        // Sección 1
        sb.append("1. SITUACIÓN SIGNIFICATIVA\n\n");
        sb.append("Situación significativa proporcionada: ")
                .append(request.significantSituation() != null ? request.significantSituation() : "No se proporcionó una situación. Por favor, propón una.")
                .append("\n");

        sb.append("Por favor, amplía el planteamiento si es necesario, agregando contexto, problemática y preguntas abiertas retadoras que motiven el aprendizaje (por ejemplo: ¿Cómo podríamos...? ¿Qué podemos hacer si...?).\n\n");

        // Sección 2
        sb.append("2. PROPÓSITOS DE APRENDIZAJE\n\n");
        sb.append("Producto de la unidad: ").append(details.finalProduct()).append("\n\n");
        sb.append("Si el producto de la unidad no ha sido especificado, por favor, propón uno relevante para la situación significativa.\n\n");


        switch (details.type()) {
            case "proyectoAprendizaje" -> {
                sb.append("Tipo: Proyecto de Aprendizaje\n");
                if (details instanceof LearningProjectDetailsResource project) {
                    sb.append("Problema o reto central: ").append(project.centralProblem()).append("\n");
                }
            }
            case "moduloAprendizaje" -> {
                sb.append("Tipo: Módulo de Aprendizaje\n");
                if (details instanceof LearningModuleDetailsResource module) {
                    sb.append("Unidad base: ").append(module.baseUnit()).append("\n");
                    sb.append("Justificación pedagógica: ").append(module.pedagogicalJustification()).append("\n");
                }
            }
            case "unidadAprendizaje" -> {
                sb.append("Tipo: Unidad de Aprendizaje\n");
            }
            default -> sb.append("Tipo: No especificado\n");
        }

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

        if (request.selectedCompetencies() != null) {
                for (SelectedCompetencyResource sc : request.selectedCompetencies()) {
                    sb.append("Área: ").append(sc.area()).append("\n");
                    sb.append("Competencia: ").append(sc.name()).append("\n");
                    sb.append("Capacidades: ").append(String.join(", ", sc.selectedAbilities())).append("\n\n");
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
