package uce.edu.web.api.service.mapper;

import java.util.ArrayList;
import java.util.List;

import uce.edu.web.api.repository.modelo.HijoProfesor;
import uce.edu.web.api.service.to.HijoProfesorTo;

public class HijoProfesorMapper {

    public static HijoProfesorTo toTo(HijoProfesor hijoProfesor) {
        HijoProfesorTo hpTo = new HijoProfesorTo();
        hpTo.setId(hijoProfesor.getId());
        hpTo.setNombre(hijoProfesor.getNombre());
        hpTo.setApellido(hijoProfesor.getApellido());
        return hpTo;
    }

    public static HijoProfesor toEntity(HijoProfesorTo hijoProfesorTo) {
        HijoProfesor hp = new HijoProfesor();
        hp.setId(hijoProfesorTo.getId());
        hp.setNombre(hijoProfesorTo.getNombre());
        hp.setApellido(hijoProfesorTo.getApellido());
        return hp;
    }

    public static List<HijoProfesorTo> toToList(List<HijoProfesor> hijosprofesor) {
        List<HijoProfesorTo> resultList = new ArrayList<>();
        for (HijoProfesor hijoProfesor : hijosprofesor) {
            HijoProfesorTo hpTo = new HijoProfesorTo();
            hpTo.setId(hijoProfesor.getId());
            hpTo.setNombre(hijoProfesor.getNombre());
            hpTo.setApellido(hijoProfesor.getApellido());
            resultList.add(hpTo);
        }

        return resultList;
    }
}
