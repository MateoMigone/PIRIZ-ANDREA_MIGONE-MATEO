package service;

import dao.IDao;
import dao.implementation.OdontologoDAOH2;
import model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService() {
        this.odontologoIDao = new OdontologoDAOH2();
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar (Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarTodos() {
        return odontologoIDao.listarTodos();
    }
}
