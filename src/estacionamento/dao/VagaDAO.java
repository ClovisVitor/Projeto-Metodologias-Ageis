package estacionamento.dao;

import estacionamento.config.conexao;
import estacionamento.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VagaDAO {

    public void salvar(Vaga vaga) {

        String sql = """
                INSERT INTO vagas
                (numero, ocupada)
                VALUES (?, ?)
                """;

        try (
                Connection conn = conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, vaga.getNumero());
            stmt.setBoolean(2, vaga.isOcupada());

            stmt.executeUpdate();

        } catch (SQLException e) {

            if (!e.getMessage().contains("duplicate")) {
                System.out.println("Erro ao salvar vaga.");
            }
        }
    }

    public void excluirPorNumero(int numero) {

        String sql = """
                DELETE FROM vagas
                WHERE numero = ?
                """;

        try (
                Connection conn = conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, numero);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Erro ao excluir vaga.");
        }
    }
}