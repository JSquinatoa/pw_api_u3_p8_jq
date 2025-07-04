package uce.edu.web.api.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService iProfesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar un Profesor por id", description = "Esta capacidad permite consultar un profesor por el id")
    public Response consultarPorId(@PathParam("id") Integer id) {
        return Response.status(200).entity(this.iProfesorService.buscarPorId(id)).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar todos los profesores por su genero", description = "Esta capacidad permite consultar todos los profesors por el genero")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.ok(200).entity(this.iProfesorService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar un Profesor", description = "Esta capacidad permite guardar un profesor en la base de datos")
    public Response guardar(@RequestBody Profesor profesor) {
        this.iProfesorService.guardar(profesor);
        return Response.status(200).build(); 
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actulizar un Profesor segun su id", description = "Esta capacidad permite actualizar un profesor por su id completamente")
    public Response actualizar(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.iProfesorService.actualizarPorId(profesor);
        return Response.status(200).build(); 
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actulizar un Profesor Parcialmente segun su id", description = "Esta capacidad permite actualizar un profesor parcialmente por su id")
    public Response actualizarParcialPorId(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        Profesor p = this.iProfesorService.buscarPorId(id);
        if (profesor.getApellido() != null) {
            p.setApellido(profesor.getApellido());
        }
        if (profesor.getFechaNacmimiento() != null) {
            p.setFechaNacmimiento(profesor.getFechaNacmimiento());
        }
        if (profesor.getNombre() != null) {
            p.setNombre(profesor.getNombre());
        }
        if (profesor.getSueldo() != null) {
            p.setSueldo(profesor.getSueldo());
        }
        if (profesor.getGenero() != null) {
            p.setGenero(profesor.getGenero());            
        }
        this.iProfesorService.actualizarParcialPorId(p);
        return Response.status(200).build(); 
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Borrar un Profesor por Id", description = "Esta capacidad permite borrar un profesor por el id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.iProfesorService.borrarPorId(id);
        return Response.status(200).build(); 
    }

}