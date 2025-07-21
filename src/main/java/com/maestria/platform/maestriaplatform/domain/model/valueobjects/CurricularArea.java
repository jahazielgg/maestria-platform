package com.maestria.platform.maestriaplatform.domain.model.valueobjects;

/*
    Considerar que en el frontend existe cycleToAreas:
    export const cyclesToAreas: Record<Cycle, CurricularArea[]> = {
  'Ciclo I': ['Comunicación', 'Personal Social', 'Matemática', 'Descubrimiento del Mundo'],
  'Ciclo II': ['Comunicación', 'Castellano como Segunda Lengua', 'Personal Social', 'Matemática', 'Ciencia y Tecnología'],
  'Ciclo III': ['Comunicación', 'Castellano como Segunda Lengua', 'Inglés', 'Arte y Cultura', 'Personal Social', 'Educación Religiosa', 'Educación Física', 'Matemática', 'Ciencia y Tecnología'],
  'Ciclo IV': ['Comunicación', 'Castellano como Segunda Lengua', 'Inglés', 'Arte y Cultura', 'Personal Social', 'Educación Religiosa', 'Educación Física', 'Matemática', 'Ciencia y Tecnología'],
  'Ciclo V': ['Comunicación', 'Castellano como Segunda Lengua', 'Inglés', 'Arte y Cultura', 'Personal Social', 'Educación Religiosa', 'Educación Física', 'Matemática', 'Ciencia y Tecnología'],
  'Ciclo VI': ['Comunicación', 'Castellano como Segunda Lengua', 'Inglés', 'Arte y Cultura', 'Desarrollo Personal, Ciudadanía y Cívica', 'Ciencias Sociales', 'Educación Religiosa', 'Educación Física', 'Matemática', 'Ciencia y Tecnología', 'Educación para el Trabajo'],
  'Ciclo VII': ['Comunicación', 'Castellano como Segunda Lengua', 'Inglés', 'Arte y Cultura', 'Desarrollo Personal, Ciudadanía y Cívica', 'Ciencias Sociales', 'Educación Religiosa', 'Educación Física', 'Matemática', 'Ciencia y Tecnología', 'Educación para el Trabajo'],
}

 */

public record CurricularArea(String value) {
}
