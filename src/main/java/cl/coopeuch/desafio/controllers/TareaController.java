package cl.coopeuch.desafio.controllers;

import cl.coopeuch.desafio.models.Tarea;
import cl.coopeuch.desafio.services.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@Tag(name = "Tarea API", description = "Documentacion del contrato de la APIs")
@RestController
@RequestMapping("api/v1/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @Operation(summary = "Obtener Tareas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de tareas",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Tarea.class
                            )))
            })
    })
    @GetMapping
    public Collection<Tarea> findTareas() {
        return tareaService.getTareas();
    }
    @Operation(summary = "Obtener Tarea por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Tarea.class))
            }),
            @ApiResponse(responseCode = "404",
                    description = "Tarea no encontrada",
                    content = @Content) })
    @GetMapping("{id}")
    public Tarea findTareaById(@PathVariable("id") Long id) {
        return tareaService.getTareaById(id);
    }

    @Operation(summary = "Crear Tarea ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Tarea creada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tarea.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Datos Invalidos",
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "descripcion", value = "Campo descripcion requerido"),
                                    @ExampleObject(name = "vigente", value = "Campo vigente requerido")
                            }
                    ))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea createTarea(@Valid @RequestBody TareaRequest request) {
        return tareaService.createTarea(request);
    }
    @Operation(summary = "Actualizar Tarea ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Tarea actualizada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tarea.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Datos Invalidos",
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "descripcion", value = "Campo descripcion requerido"),
                                    @ExampleObject(name = "vigente", value = "Campo vigente requerido")
                            }
                    ))
    })
    @PutMapping("{id}")
    public Tarea updateTarea(@PathVariable("id") Long id, @Valid @RequestBody TareaRequest request) {
        return tareaService.updateTarea(id, request);
    }

    @Operation(summary = "Eliminar Tarea por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Tarea.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Tarea no encontrada",
                    content = @Content) })
    @DeleteMapping("{id}")
    public void deleteTarea(@PathVariable("id") Long id) {
        tareaService.deleteTarea(id);
    }
}
