package cl.coopeuch.desafio.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TareaRequest(
    @NotBlank(message = "Campo descripcion es requerido")
    String descripcion,

    @NotNull(message = "Campo vigente es requerido")
    Boolean vigente
) {}
