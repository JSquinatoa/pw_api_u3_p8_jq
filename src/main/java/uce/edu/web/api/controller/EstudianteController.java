package uce.edu.web.api.controller;

import java.util.ArrayList;
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
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService iHijoService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar un Estudiante", description = "Este capacidad consulta un estudiante por id")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {

        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar Todos los estudiantes", description = "Este capacidad permite consulta todos los estudiantes")
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        System.out.println(genero);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Guardar Estudiante", description = "Esta capacidad permite guardar un estudiante en la base")
    public Response guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar un Estudiante por Id", description = "Esta capacidad permite actulizar un estudiante por Id")
    public Response actualizar(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
    
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(200).build();
    }

    /*
     * @PATCH
     * 
     * @Path("/{id}")
     * 
     * @Operation(summary = "Actualizar un Estudiante Parcialmente por Id",
     * description =
     * "Esta capacidad permite actulizar un estudiante Parcialmente por Id")
     * public Response actualizarParcialPorId(@RequestBody Estudiante
     * estudiante, @PathParam("id") Integer id) {
     * estudiante.setId(id);
     * Estudiante e = this.estudianteService.buscarPorId(id);
     * if (estudiante.getApellido() != null) {
     * e.setApellido(estudiante.getApellido());
     * }
     * if (estudiante.getNombre() != null) {
     * e.setNombre(estudiante.getNombre());
     * }
     * if (estudiante.getFechaNacimiento() != null) {
     * e.setFechaNacimiento(estudiante.getFechaNacimiento());
     * }
     * this.estudianteService.actualizarParcialPorId(e);
     * return Response.status(200).build();
     * }
     */

    @DELETE
    @Path("{id}")
    @Operation(summary = "Borrar Estudiante Por ID", description = "Esta Capacidad permite borrar un Estudiante Por Id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {
        return this.iHijoService.BuscarPorEstudianteId(id);
    }

}
