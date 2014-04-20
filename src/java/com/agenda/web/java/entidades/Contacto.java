package com.agenda.web.java.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "contacto")
public class Contacto implements java.io.Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer telefono;

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, Integer telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer contactoId) {
        this.id = contactoId;
    }

    @Column(name = "nombre", length = 25)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String contactoNombre) {
        this.nombre = contactoNombre;
    }

    @Column(name = "telefono")
    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer contactoTelefono) {
        this.telefono = contactoTelefono;
    }

    @Column(name = "apellido", length = 25)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido + " " + String.valueOf(this.telefono);
    }
}
