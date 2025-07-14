package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacmimiento;
    private Integer sueldo;
    private String genero;
    public Map<String, String> _links = new HashMap<>();

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

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(UriInfo uriInfo) {
        URI todosHijos = uriInfo.getBaseUriBuilder().path(ProfesorController.class)
                .path(ProfesorController.class, "obtenerHijosPorId").build(id);
        _links.put("hijosProfesor", todosHijos.toString());
    }

}
