package estacionamento.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/estacionamento_db";

    private static final String USUARIO = "postgres";

    private static final String SENHA = "123456";

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    SENHA
            );

        } catch (SQLException e) {

            System.out.println("Erro na conexão com banco.");
            e.printStackTrace();

            return null;
        }
    }
}