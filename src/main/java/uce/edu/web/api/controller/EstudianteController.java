package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.annotation.security.RolesAllowed;
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
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.mapper.HijoMapper;
import uce.edu.web.api.service.to.EstudianteTo;
import uce.edu.web.api.service.to.HijoTo;

@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteController {

    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim("sub")
    ClaimValue<String> subject;

    @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService iHijoService;

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Consultar un Estudiante por ID", description = "Este capacidad consulta un estudiante por id")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EstudianteTo estuTo = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estuTo.buildURI(uriInfo);
        return Response.status(227).entity(estuTo).build();
    }

    @GET
    @Path("/genero")
    @Operation(summary = "Consultar Todos los estudiantes por genero", description = "Este capacidad permite consulta todos los estudiantes por genero")
    public Response consultarTodosPorGenero(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia,
            @Context UriInfo uriInfo) {
        List<EstudianteTo> estuToList = EstudianteMapper.toToList(this.estudianteService.buscarTodosPorGenero(genero));
        /*
         * List<EstudianteTo> estudianteTos =
         * this.estudianteService.buscarTodos(genero).stream().map(EstudianteMapper::
         * toTo).collect(Collectors.toList());
         */
        for (EstudianteTo estuTo : estuToList) {
            estuTo.buildURI(uriInfo);
        }
        return Response.status(Response.Status.OK).entity(estuToList).build();
    }

    @GET
    @Path("")
    public Response consultarTodos(@Context UriInfo uriInfo) {
        List<EstudianteTo> estuToList = this.estudianteService.buscarTodos().stream().map(EstudianteMapper::toTo)
                .peek(estuTo -> estuTo.buildURI(uriInfo)).collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(estuToList).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Guardar Estudiante", description = "Esta capacidad permite guardar un estudiante en la base")
    public Response guardar(@RequestBody EstudianteTo estudianteTo) {
        this.estudianteService.guardar(EstudianteMapper.toEntity(estudianteTo));
        return Response.status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar un Estudiante por Id", description = "Esta capacidad permite actulizar un estudiante por Id")
    public Response actualizar(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        estudianteTo.setId(id);
        this.estudianteService.actualizarPorId(EstudianteMapper.toEntity(estudianteTo));
        return Response.status(200).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar un Estudiante Parcialmente por Id", description = "Esta capacidad permite actulizar un estudiante Parcialmente por Id")
    public Response actualizarParcialPorId(@RequestBody EstudianteTo estudiantetTo, @PathParam("id") Integer id) {
        estudiantetTo.setId(id);
        EstudianteTo eTo = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        if (estudiantetTo.getApellido() != null) {
            eTo.setApellido(estudiantetTo.getApellido());
        }
        if (estudiantetTo.getNombre() != null) {
            eTo.setNombre(estudiantetTo.getNombre());
        }
        if (estudiantetTo.getFechaNacimiento() != null) {
            eTo.setFechaNacimiento(estudiantetTo.getFechaNacimiento());
        }
        if (estudiantetTo.getGenero() != null) {
            eTo.setGenero(estudiantetTo.getGenero());
        }
        this.estudianteService.actualizarParcialPorId(EstudianteMapper.toEntity(eTo));
        return Response.status(200).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Borrar Estudiante Por ID", description = "Esta Capacidad permite borrar un Estudiante Por Id")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}/hijos")
    public Response obtenerHijosPorId(@PathParam("id") Integer id) {
        List<HijoTo> hijoToList = HijoMapper.toToList(this.iHijoService.BuscarPorEstudianteId(id));
        return Response.status(Response.Status.OK).entity(hijoToList).build();
    }

}
