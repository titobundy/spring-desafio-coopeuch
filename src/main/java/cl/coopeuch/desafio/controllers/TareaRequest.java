package cl.coopeuch.desafio.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TareaRequest(
    @NotBlank(message = "Campo descripcion es requerido")
    @Schema(name = "descripcion", example = "Nombre de la tarea")
    String descripcion,

    @NotNull(message = "Campo vigente es requerido")
    @Schema(name = "vigente", example = "Indicar booleano de vigencia de la tarea")
    Boolean vigente
) {}
