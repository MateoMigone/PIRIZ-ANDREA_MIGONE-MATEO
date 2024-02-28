package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    // NO SUPIMOS COMO IMPORTAR LOS SCRIPTS SQL ASI QUE LO HICIMOS CON STRING PORQUE NOS QUEDAMOS SIN TIEMPO
    private static final String SQL_CREATE_TABLE_ODONTOLOGOS = "DROP TABLE IF EXISTS " +
            "ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " MATRICULA INT NOT NULL," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100) NOT NULL" +
            ")";

    private static final Logger LOGGER = Logger.getLogger(BD.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./clinicaOdontologica",
                "sa", "sa");
    }

    public static void crearTablaOdontologos() {

        LOGGER.info("Estamos creando la tabla de odontologos.");

        Connection connection = null;

        try {

            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE_ODONTOLOGOS);

        } catch (Exception e) {
            LOGGER.error("Error al crear la tabla de odontologos",e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos",e);

            }
        }

    }
}
