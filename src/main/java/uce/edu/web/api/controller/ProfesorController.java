package uce.edu.web.api.controller;

import java.util.List;

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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.service.IHijoProfesorService;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.HijoProfesorMapper;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.HijoProfesorTo;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {

    @Inject
    private IProfesorService iProfesorService;

    @Inject
    private IHijoProfesorService iHijoProfesorService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar un Profesor por id", description = "Esta capacidad permite consultar un profesor por el id")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profTo = ProfesorMapper.toTo(this.iProfesorService.buscarPorId(id));
        profTo.buildURI(uriInfo);
        return Response.status(200).entity(profTo).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todos los profesores por su genero", description = "Esta capacidad permite consultar todos los profesors por el genero")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia, @Context UriInfo uriInfo) {
        List<ProfesorTo> profToList = ProfesorMapper.toToList(this.iProfesorService.buscarTodos(genero));
        for (ProfesorTo profTo : profToList) {
            profTo.buildURI(uriInfo);            
        }
        return Response.ok(200).entity(profToList).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Guardar un Profesor", description = "Esta capacidad permite guardar un profesor en la base de datos")
    public Response guardar(@RequestBody ProfesorTo profesorTo) {
        this.iProfesorService.guardar(ProfesorMapper.toEntity(profesorTo));
        return Response.status(200).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Actulizar un Profesor segun su id", description = "Esta capacidad permite actualizar un profesor por su id completamente")
    public Response actualizar(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        profesorTo.setId(id);
        this.iProfesorService.actualizarPorId(ProfesorMapper.toEntity(profesorTo));
        return Response.status(200).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actulizar un Profesor Parcialmente segun su id", description = "Esta capacidad permite actualizar un profesor parcialmente por su id")
    public Response actualizarParcialPorId(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        profesorTo.setId(id);
        ProfesorTo pTo = ProfesorMapper.toTo(this.iProfesorService.buscarPorId(id));
        if (profesorTo.getNombre() != null) {
            pTo.setNombre(profesorTo.getNombre());
        }
        if (profesorTo.getApellido() != null) {
            pTo.setApellido(profesorTo.getApellido());
        }
        if (profesorTo.getFechaNacmimiento() != null) {
            pTo.setFechaNacmimiento(profesorTo.getFechaNacmimiento());
        }
        if (profesorTo.getSueldo() != null) {
            pTo.setSueldo(profesorTo.getSueldo());
        }
        if (profesorTo.getGenero() != null) {
            pTo.setGenero(profesorTo.getGenero());
        }
        this.iProfesorService.actualizarParcialPorId(ProfesorMapper.toEntity(pTo));
        return Response.status(200).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Borrar un Profesor por Id", description = "Esta capacidad permite borrar un profesor por el id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.iProfesorService.borrarPorId(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}/hijos")
    public Response obtenerHijosPorId(@PathParam("id") Integer id) {
        List<HijoProfesorTo> hijoProfesorToList = HijoProfesorMapper
                .toToList(this.iHijoProfesorService.BuscarPorEstudianteId(id));
        return Response.status(Response.Status.OK).entity(hijoProfesorToList).build();
    }

}