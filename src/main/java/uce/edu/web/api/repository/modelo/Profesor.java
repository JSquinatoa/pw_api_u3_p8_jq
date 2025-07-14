package uce.edu.web.api.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id")
    private Integer id;
    @Column(name = "prof_nombre")
    private String nombre;
    @Column(name = "prof_apellido")
    private String apellido;
    @Column(name = "prof_fechaNacmimiento")
    private LocalDateTime fechaNacmimiento;
    @Column(name = "prof_sueldo")
    private Integer sueldo;
    @Column(name = "prof_genero")
    private String genero;

    // Relaciones
    @OneToMany(mappedBy = "profesor")
    private List<HijoProfesor> hijosProfesor;

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

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getFechaNacmimiento() {
        return fechaNacmimiento;
    }

    public void setFechaNacmimiento(LocalDateTime fechaNacmimiento) {
        this.fechaNacmimiento = fechaNacmimiento;
    }

    public Integer getSueldo() {
        return sueldo;
    }

    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}