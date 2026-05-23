package estacionamento.dao;

import estacionamento.config.conexao;
import estacionamento.model.Movimentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoDAO {

    public void salvar(Movimentacao mov) {

        String sql = """
                INSERT INTO movimentacoes
                (
                    data_entrada,
                    data_saida,
                    valor_pago
                )
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setTimestamp(
                    1,
                    java.sql.Timestamp.valueOf(
                            mov.getDataEntrada()
                    )
            );

            if (mov.getDataSaida() != null) {

                stmt.setTimestamp(
                        2,
                        java.sql.Timestamp.valueOf(
                                mov.getDataSaida()
                        )
                );

            } else {

                stmt.setTimestamp(2, null);
            }

            stmt.setDouble(
                    3,
                    mov.getValorPago()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Erro ao salvar movimentação.");
        }
    }
}