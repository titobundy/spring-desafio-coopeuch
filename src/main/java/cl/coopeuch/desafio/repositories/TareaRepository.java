package cl.coopeuch.desafio.repositories;

import cl.coopeuch.desafio.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
