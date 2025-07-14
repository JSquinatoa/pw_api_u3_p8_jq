package uce.edu.web.api.service.mapper;

import java.util.ArrayList;
import java.util.List;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {

    public static ProfesorTo toTo(Profesor profesor) {
        ProfesorTo pTo = new ProfesorTo();
        pTo.setId(profesor.getId());
        pTo.setNombre(profesor.getNombre());
        pTo.setApellido(profesor.getApellido());
        pTo.setFechaNacmimiento(profesor.getFechaNacmimiento());
        pTo.setSueldo(profesor.getSueldo());
        pTo.setGenero(profesor.getGenero());
        return pTo;
    }

    public static Profesor toEntity(ProfesorTo profesorTo) {
        Profesor p = new Profesor();
        p.setId(profesorTo.getId());
        p.setNombre(profesorTo.getNombre());
        p.setApellido(profesorTo.getApellido());
        p.setFechaNacmimiento(profesorTo.getFechaNacmimiento());
        p.setSueldo(profesorTo.getSueldo());
        p.setGenero(profesorTo.getGenero());
        return p;
    }

    public static List<ProfesorTo> toToList(List<Profesor> profesores) {
        List<ProfesorTo> resultList = new ArrayList<>();
        for (Profesor profesor : profesores) {
            ProfesorTo pTo = new ProfesorTo();
            pTo.setId(profesor.getId());
            pTo.setNombre(profesor.getNombre());
            pTo.setApellido(profesor.getApellido());
            pTo.setFechaNacmimiento(profesor.getFechaNacmimiento());
            pTo.setSueldo(profesor.getSueldo());
            pTo.setGenero(profesor.getGenero());
            resultList.add(pTo);
        }

        return resultList;
    }

}
