package cl.coopeuch.desafio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long id;

    @NotNull
    private String descripcion;

    @NotNull
    private LocalDate fechaCreacion;

    @NotNull
    private boolean vigente;

    protected Tarea() {}

    public Tarea(String descripcion, boolean vigente) {
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDate.now();
        this.vigente = vigente;
    }
}
