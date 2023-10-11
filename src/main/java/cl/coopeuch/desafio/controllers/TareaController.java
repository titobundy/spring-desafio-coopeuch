package cl.coopeuch.desafio.controllers;

import cl.coopeuch.desafio.models.Tarea;
import cl.coopeuch.desafio.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @GetMapping
    public Collection<Tarea> findTareas() {
        return tareaService.getTareas();
    }

    @GetMapping("{id}")
    public Tarea findTareaById(@PathVariable("id") Long id) {
        return tareaService.getTareaById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea createTarea(@RequestBody Tarea tarea) {
        return tareaService.createTarea(tarea);
    }

    @PutMapping("{id}")
    public Tarea updateTarea(@PathVariable("id") Long id, @RequestBody Tarea tarea) {
        tarea.setId(id);
        return tareaService.updateTarea(tarea);
    }

    @DeleteMapping("{id}")
    public void deleteTarea(@PathVariable("id") Long id) {
        tareaService.deleteTarea(id);
    }
}
