package cl.coopeuch.desafio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Tarea {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String descripcion;

    private LocalDate fechaCreacion;

    private boolean vigente;

    protected Tarea() {}

    public Tarea(String descripcion, boolean vigente) {
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDate.now();
        this.vigente = vigente;
    }
}
