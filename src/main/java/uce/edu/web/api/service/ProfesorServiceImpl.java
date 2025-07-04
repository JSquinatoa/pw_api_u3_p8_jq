package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo iProfesorRepo;

    @Override
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo) {
        Profesor p1 = this.iProfesorRepo.seleccionarPorId(id);
        ProfesorTo p = new ProfesorTo(id, p1.getNombre(), p1.getApellido(), p1.getFechaNacmimiento(), p1.getSueldo(), p1.getGenero(), uriInfo);
        return p;
    }

    @Override
    public List<Profesor> buscarTodos(String genero) {
        return this.iProfesorRepo.seleccionarTodos(genero);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.iProfesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.iProfesorRepo.actualizarParcialPorId(profesor);  
    }

    @Override
    public void borrarPorId(Integer id) {
        this.iProfesorRepo.borrarPorId(id);
    }

    @Override
    public void guardar(Profesor profesor) {
        this.iProfesorRepo.insertar(profesor);
    }

}