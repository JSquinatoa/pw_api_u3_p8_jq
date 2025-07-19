package uce.edu.web.api.controller;

import java.time.Duration;

import org.eclipse.microprofile.openapi.annotations.Operation;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/generate")
public class TokenController {

    @GET
    @Path("")
    @Operation(summary = "Generar un token por 30 segundos" ,description = "esta capacidad me permite obtener un token por 30 segundos")
    public String generar(){
        return Jwt.issuer("http://uce.edu.ec").upn("jsquinatoa@uce.edu.ec").groups("admin").expiresIn(Duration.ofSeconds((30))).sign();
    }

}
