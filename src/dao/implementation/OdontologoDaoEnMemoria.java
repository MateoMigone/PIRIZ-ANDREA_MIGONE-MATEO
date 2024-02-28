package dao.implementation;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {
    private List<Odontologo> registroOdontologos;

    public OdontologoDaoEnMemoria(List<Odontologo> registroOdontologos) {
        this.registroOdontologos = registroOdontologos;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        registroOdontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return registroOdontologos;
    }

}
