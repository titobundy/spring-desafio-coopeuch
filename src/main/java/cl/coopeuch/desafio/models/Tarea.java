package cl.coopeuch.desafio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String descripcion;

    private Date fechaCreacion;

    private boolean vigente;

    protected Tarea() {}

    public Tarea(String descripcion, boolean vigente) {
        this.descripcion = descripcion;
        this.fechaCreacion = new Date();
        this.vigente = vigente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
