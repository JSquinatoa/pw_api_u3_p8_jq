package uce.edu.web.api.repository.modelo;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "hijo")
@Entity
public class Hijo {

    @Id
    @GeneratedValue
    @Column(name = "hijo_id")
    private Integer id;
    @Column(name = "hijo_nombre")
    private String nombre;
    @Column(name = "hijo_apellido")
    private String apellido;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "hijo_estudiante")
    private Estudiante estudiante;


    // GET y SET 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
