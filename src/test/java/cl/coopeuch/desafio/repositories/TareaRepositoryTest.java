package cl.coopeuch.desafio.repositories;

import cl.coopeuch.desafio.models.Tarea;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TareaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TareaRepository repository;

    @Test
    @Rollback(false)
    @Order(1)
    public void saveTaskTest() {
        Tarea tarea = new Tarea("tarea", true);
        this.repository.save(tarea);
        assertThat(tarea.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getTareaTest() {
        Tarea tarea = this.repository.findById(1L).get();
        assertThat(tarea.getId()).isEqualTo(1L);
        assertThat(tarea).extracting(Tarea::getDescripcion).isEqualTo("tarea");
    }

    @Test
    @Order(3)
    public void getListOfTareasTest() {
        List<Tarea> tareas = this.repository.findAll();
        assertThat(tareas.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateTareaTest() {
        Tarea tarea = this.repository.findById(1L).get();
        tarea.setVigente(false);
        Tarea tareaUpdated = this.repository.save(tarea);
        assertThat(tareaUpdated.isVigente()).isEqualTo(false);
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteTareaTest(){
        Tarea tarea = this.repository.findById(1L).get();

        this.repository.delete(tarea);
        Tarea findedTarea = null;
        Optional<Tarea> optionalTarea = this.repository.findById(1L);
        if(optionalTarea.isPresent()){
            findedTarea = optionalTarea.get();
        }
        assertThat(findedTarea).isNull();
    }
}
