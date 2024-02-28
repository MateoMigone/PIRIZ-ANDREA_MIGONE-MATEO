package service;

import dao.IDao;
import dao.implementation.OdontologoDAOH2;
import dao.implementation.OdontologoDaoEnMemoria;
import model.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private IDao<Odontologo> odontologoDaoEnMemoria = new OdontologoDaoEnMemoria(new ArrayList());
    private IDao<Odontologo> odontologoDaoH2 = new OdontologoDAOH2();
    private OdontologoService odontologoService = new OdontologoService();

    @Test
    void guardarYListarOdontologos() {
        Odontologo odontologo1 = new Odontologo(123,"Juan","Perez");
        Odontologo odontologo2 = new Odontologo(321,"Pedro","Lopez");
        Odontologo odontologo3 = new Odontologo(456,"Matias","Gomez");
        Odontologo odontologo4 = new Odontologo(654,"Susana","Gimenez");

        odontologoService.setOdontologoIDao(odontologoDaoEnMemoria);

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        List<Odontologo> listaOdontologosEnMemoria =  odontologoService.listarTodos();

        odontologoService.setOdontologoIDao(odontologoDaoH2);

        odontologoService.guardar(odontologo3);
        odontologoService.guardar(odontologo4);
        List<Odontologo> listaOdontologosDaoH2 =  odontologoService.listarTodos();

        assertTrue(listaOdontologosEnMemoria.size() == 2);

        assertEquals(listaOdontologosEnMemoria.get(0).getMatricula(),123);
        assertEquals(listaOdontologosEnMemoria.get(0).getNombre(),"Juan");
        assertEquals(listaOdontologosEnMemoria.get(0).getApellido(),"Perez");

        assertEquals(listaOdontologosEnMemoria.get(1).getMatricula(),321);
        assertEquals(listaOdontologosEnMemoria.get(1).getNombre(),"Pedro");
        assertEquals(listaOdontologosEnMemoria.get(1).getApellido(),"Lopez");

        assertTrue(listaOdontologosDaoH2.size() == 2);

        assertEquals(listaOdontologosDaoH2.get(0).getMatricula(),456);
        assertEquals(listaOdontologosDaoH2.get(0).getNombre(),"Matias");
        assertEquals(listaOdontologosDaoH2.get(0).getApellido(),"Gomez");

        assertEquals(listaOdontologosDaoH2.get(1).getMatricula(),654);
        assertEquals(listaOdontologosDaoH2.get(1).getNombre(),"Susana");
        assertEquals(listaOdontologosDaoH2.get(1).getApellido(),"Gimenez");
    }
}