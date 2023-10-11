package cl.coopeuch.desafio.services;

import cl.coopeuch.desafio.models.Tarea;

import java.util.Collection;

public interface TareaService {

    Tarea createTarea(Tarea tarea);
    Tarea getTareaById(Long tareaId);
    Collection<Tarea> getTareas();
    Tarea updateTarea(Tarea tarea);
    void deleteTarea(Long tareaId);
}
