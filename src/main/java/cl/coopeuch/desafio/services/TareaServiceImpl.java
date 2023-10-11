package cl.coopeuch.desafio.services;

import cl.coopeuch.desafio.exceptions.NotFoundException;
import cl.coopeuch.desafio.models.Tarea;
import cl.coopeuch.desafio.repositories.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    @Override
    public Tarea createTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea getTareaById(@NonNull Long tareaId) {
        return tareaRepository.findById(tareaId).orElseThrow(NotFoundException::new);

    }

    @Override
    public Collection<Tarea> getTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea updateTarea(Tarea tarea) {
        Tarea updatedTarea = this.getTareaById(tarea.getId());
        updatedTarea.setDescripcion(tarea.getDescripcion());
        updatedTarea.setFechaCreacion(tarea.getFechaCreacion());
        updatedTarea.setVigente(tarea.isVigente());
        tareaRepository.save(updatedTarea);
        return updatedTarea;
    }

    @Override
    public void deleteTarea(@NonNull Long tareaId) {
        Tarea deletedTarea = this.getTareaById(tareaId);
        tareaRepository.delete(deletedTarea);
    }
}
