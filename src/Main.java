import estacionamento.model.*;

import java.util.Scanner;
import estacionamento.config.conexao;
import java.sql.Connection;
import estacionamento.dao.VeiculoDAO;

public class Main {



    public static void main(String[] args) {

        Connection conn = conexao.conectar();

        if (conn != null) {
            System.out.println("Conectado ao PostgreSQL!");
        }
        Scanner scanner = new Scanner(System.in);

        Estacionamento estacionamento = new Estacionamento();

        int opcao;

        do {

            System.out.println("\n===== ESTACIONAMENTO =====");
            System.out.println("1 - Registrar entrada");
            System.out.println("2 - Registrar saída");
            System.out.println("3 - Listar estacionados");
            System.out.println("4 - Histórico");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();

                    System.out.print("Cor: ");
                    String cor = scanner.nextLine();

                    System.out.println("Tipo:");
                    System.out.println("1 - Carro");
                    System.out.println("2 - Moto");
                    System.out.println("3 - Caminhonete");

                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    Veiculo veiculo;

                    veiculo = switch (tipo) {
                        case 1 -> new Carro(placa, modelo, cor);
                        case 2 -> new Moto(placa, modelo, cor);
                        case 3 -> new Caminhonete(placa, modelo, cor);
                        default -> null;
                    };

                    if (veiculo != null) {
                        VeiculoDAO dao = new VeiculoDAO();
                        dao.salvar(veiculo);

                        estacionamento.registrarEntrada(veiculo);
                    }

                    break;

                case 2:

                    System.out.print("Digite a placa: ");
                    String placaSaida = scanner.nextLine();

                    System.out.print("Quantas horas o veículo ficou? ");
                    int horas = scanner.nextInt();
                    scanner.nextLine();

                    estacionamento.registrarSaida(placaSaida, horas);

                    break;

                case 3:

                    estacionamento.listarVeiculosEstacionados();

                    break;

                case 4:

                    estacionamento.exibirHistorico();

                    break;

                case 5:

                    System.out.println("Sistema encerrado.");

                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}