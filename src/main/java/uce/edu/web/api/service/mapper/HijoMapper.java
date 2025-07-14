package uce.edu.web.api.service.mapper;

import java.util.ArrayList;
import java.util.List;

import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.to.HijoTo;

public class HijoMapper {

    public static HijoTo toTo(Hijo hijo) {
        HijoTo hTo = new HijoTo();
        hTo.setId(hijo.getId());
        hTo.setNombre(hijo.getNombre());
        hTo.setApellido(hijo.getApellido());
        return hTo;
    }

    public static Hijo toEntity(HijoTo hijoTo) {
        Hijo h = new Hijo();
        h.setId(hijoTo.getId());
        h.setNombre(hijoTo.getNombre());
        h.setApellido(hijoTo.getApellido());
        return h;
    }

    public static List<HijoTo> toToList(List<Hijo> hijos) {
        List<HijoTo> resultList = new ArrayList<>();
        for (Hijo hijo : hijos) {
            HijoTo hTo = new HijoTo();
            hTo.setId(hijo.getId());
            hTo.setNombre(hijo.getNombre());
            hTo.setApellido(hijo.getApellido());
            resultList.add(hTo);
        }

        return resultList;
    }
}
