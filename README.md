# Maestria Platform

Este es el backend de **Maestria**, una plataforma web que permite a docentes peruanos generar **unidades didácticas** y **sesiones de aprendizaje** utilizando inteligencia artificial, en base al Currículo Nacional del MINEDU.

Este servicio expone una API REST desarrollada en **Spring Boot**, encargada de:

- Procesar las entradas del usuario (tipo de unidad, competencias, etc.)
- Generar prompts personalizados
- Consumir un modelo LLM (como **Llama3**) a través de la API de **Groq**
- Retornar la unidad didáctica generada en formato estructurado (markdown)

---

## Tecnologías utilizadas

- ☕ **Spring Boot 3** – Framework principal para la API REST
- 🔗 **Spring AI** – Integración con modelos de lenguaje como GPT y Llama
- 🌐 **Groq API** – LLM backend para procesamiento de lenguaje natural
- 🧱 **Java 24** – Versión del JDK utilizada
- 📦 **Maven** – Herramienta de construcción
- 🧪 **JUnit** – Tests unitarios (opcional)

---

## Ejecución local
- Requisitos previos
- JDK 17+
- Maven 3+

## Comandos
```bash
# Compilar el proyecto
mvn clean install

# Ejecutar localmente
mvn spring-boot:run
```

El servicio estará disponible por defecto en:
http://localhost:8080