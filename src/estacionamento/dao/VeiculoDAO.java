package estacionamento.dao;

import estacionamento.config.conexao;
import estacionamento.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VeiculoDAO {

    public void salvar(Veiculo veiculo) {

        String sql = """
                INSERT INTO veiculos
                (placa, modelo, cor, tipo)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getCor());

            String tipo;

            if (veiculo instanceof Carro) {
                tipo = "CARRO";
            } else if (veiculo instanceof Moto) {
                tipo = "MOTO";
            } else {
                tipo = "CAMINHONETE";
            }

            stmt.setString(4, tipo);

            stmt.executeUpdate();

            System.out.println("Veículo salvo no banco!");

        } catch (SQLException e) {

            if (e.getMessage().contains("veiculos_placa_key")) {

                System.out.println("Veículo com essa placa já está cadastrado.");

            } else {

                System.out.println("Erro ao salvar veículo.");
            }
        }
    }
    public void excluirPorPlaca(String placa) {

        String sql = """
            DELETE FROM veiculos
            WHERE placa = ?
            """;

        try (
                Connection conn = conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, placa);

            stmt.executeUpdate();

            System.out.println("Veículo removido do banco.");

        } catch (SQLException e) {

            System.out.println("Erro ao excluir veículo.");
        }
    }
}