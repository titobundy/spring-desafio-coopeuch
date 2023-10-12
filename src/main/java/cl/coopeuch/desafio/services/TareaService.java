package cl.coopeuch.desafio.services;

import cl.coopeuch.desafio.controllers.TareaRequest;
import cl.coopeuch.desafio.models.Tarea;

import java.util.Collection;

public interface TareaService {

    Tarea createTarea(TareaRequest request);
    Tarea getTareaById(Long tareaId);
    Collection<Tarea> getTareas();
    Tarea updateTarea(Long id, TareaRequest request);
    void deleteTarea(Long tareaId);
}
