package estacionamento.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import estacionamento.dao.VeiculoDAO;
import estacionamento.dao.VagaDAO;
import estacionamento.dao.MovimentacaoDAO;

public class Estacionamento {

    private List<Vaga> vagas;
    private List<Movimentacao> movimentacoes;

    public Estacionamento() {

        vagas = new ArrayList<>();
        movimentacoes = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            vagas.add(new Vaga(i));
        }
    }

    public void registrarEntrada(Veiculo veiculo) {

        // Verifica se veículo já está estacionado
        for (Movimentacao mov : movimentacoes) {

            if (
                    mov.getVeiculo().getPlaca().equalsIgnoreCase(veiculo.getPlaca())
                            && mov.getDataSaida() == null
            ) {

                System.out.println("Veículo já está estacionado.");
                return;
            }
        }

        // Procura vaga livre
        for (Vaga vaga : vagas) {

            if (!vaga.isOcupada()) {

                vaga.setOcupada(true);

                VagaDAO vagaDAO = new VagaDAO();
                vagaDAO.salvar(vaga);

                Movimentacao movimentacao = new Movimentacao(
                        veiculo,
                        vaga,
                        LocalDateTime.now()
                );

                movimentacoes.add(movimentacao);

                MovimentacaoDAO movDAO = new MovimentacaoDAO();
                movDAO.salvar(movimentacao);

                System.out.println("Entrada registrada.");
                System.out.println("Vaga: " + vaga.getNumero());

                return;
            }
        }

        System.out.println("Estacionamento lotado.");
    }

    public void listarVeiculosEstacionados() {

        for (Movimentacao mov : movimentacoes) {

            if (mov.getDataSaida() == null) {

                System.out.println(
                        mov.getVeiculo().getPlaca()
                                + " - Vaga "
                                + mov.getVaga().getNumero()
                );
            }
        }
    }

    public void registrarSaida(String placa, int horas) {

        for (Movimentacao mov : movimentacoes) {

            if (
                    mov.getVeiculo().getPlaca().equalsIgnoreCase(placa)
                            && mov.getDataSaida() == null
            ) {

                // Registra data/hora real da saída
                mov.setDataSaida(LocalDateTime.now());

                // Valor base
                double valorBase = 5.0;

                // Horas adicionais
                if (horas > 1) {
                    valorBase += (horas - 1) * 3;
                }

                // Polimorfismo
                double valorFinal = mov.getVeiculo().calcularValor(valorBase);

                mov.setValorPago(valorFinal);

                // Libera vaga
                mov.getVaga().setOcupada(false);

                System.out.println("\nSaída registrada com sucesso!");
                System.out.println("Placa: " + mov.getVeiculo().getPlaca());
                System.out.println("Horas permanência: " + horas);
                System.out.println("Data/Hora saída: " + mov.getDataSaida());
                System.out.println("Valor pago: R$ " + valorFinal);

                VeiculoDAO dao = new VeiculoDAO();
                dao.excluirPorPlaca(
                        mov.getVeiculo().getPlaca()
                );

                VagaDAO vagaDAO = new VagaDAO();
                vagaDAO.excluirPorNumero(
                        mov.getVaga().getNumero()
                );
                return;
            }
        }

        System.out.println("Veículo não encontrado.");
    }

    public void exibirHistorico() {

        for (Movimentacao mov : movimentacoes) {

            System.out.println("----------------------------");

            System.out.println(
                    "Placa: "
                            + mov.getVeiculo().getPlaca()
            );

            System.out.println(
                    "Modelo: "
                            + mov.getVeiculo().getModelo()
            );

            System.out.println(
                    "Vaga: "
                            + mov.getVaga().getNumero()
            );

            System.out.println(
                    "Entrada: "
                            + mov.getDataEntrada()
            );

            System.out.println(
                    "Saída: "
                            + mov.getDataSaida()
            );

            System.out.println(
                    "Valor Pago: R$ "
                            + mov.getValorPago()
            );
        }
    }
}