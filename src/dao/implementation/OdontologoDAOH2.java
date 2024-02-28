package dao.implementation;

import dao.BD;
import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);

    // NO SUPIMOS COMO IMPORTAR LOS SCRIPTS SQL ASI QUE LO HICIMOS CON STRING PORQUE NOS QUEDAMOS SIN TIEMPO
    private static final String INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos persistiendo un odontologo");
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();

        }catch (Exception e) {
            LOGGER.error("Error al persistir un odontologo",e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos",e);
            }
        }

        return odontologo;

    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Estamos consultando todos los odontologos");
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4));

                listaOdontologos.add(odontologo);
            }

        } catch (Exception e) {
            LOGGER.error("Error al consultar todos los odontologos",e);

            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos",e);

            }
        }

        return listaOdontologos;
    }
}
