package uce.edu.web.api.repository.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "hijoprofesor")
@Entity
public class HijoProfesor {

    @Id
    @GeneratedValue
    @Column(name = "hipr_id")    
    private Integer id;
    @Column(name = "hipr_nombre")
    private String nombre;
    @Column(name = "hipr_apellido")
    private String apellido;
    
    // Relaciones
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hijo_profesor")
    private Profesor profesor;
    
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