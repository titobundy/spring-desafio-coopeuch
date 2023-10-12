package cl.coopeuch.desafio.services;

import cl.coopeuch.desafio.controllers.TareaRequest;
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
    public Tarea createTarea(@NonNull TareaRequest tarea) {
        Tarea entity = new Tarea(tarea.descripcion(), tarea.vigente());
        return tareaRepository.save(entity);
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
    public Tarea updateTarea(@NonNull Long id, @NonNull TareaRequest request) {
        Tarea updatedTarea = this.getTareaById(id);
        updatedTarea.setDescripcion(request.descripcion());
        updatedTarea.setVigente(request.vigente());
        tareaRepository.save(updatedTarea);
        return updatedTarea;
    }

    @Override
    public void deleteTarea(@NonNull Long tareaId) {
        Tarea deletedTarea = this.getTareaById(tareaId);
        tareaRepository.delete(deletedTarea);
    }
}
