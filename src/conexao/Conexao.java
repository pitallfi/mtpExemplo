package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_comercial";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public Connection conectaBanco() {
        try {

            Class.forName(DRIVER);
            JOptionPane.showMessageDialog(null, "Conectado");
            return DriverManager.getConnection(URL,USUARIO,SENHA);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException("Driver do banco de dados não encontrado.",e);

        } catch (SQLException e) {

            throw new RuntimeException("Não foi possível conectar ao banco de dados.",e);
        }
    }
}
