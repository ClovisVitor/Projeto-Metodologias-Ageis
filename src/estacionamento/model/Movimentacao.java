package estacionamento.model;

import java.time.LocalDateTime;

public class Movimentacao {

    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private double valorPago;

    public Movimentacao(
            Veiculo veiculo,
            Vaga vaga,
            LocalDateTime dataEntrada
    ) {

        this.veiculo = veiculo;
        this.vaga = vaga;
        this.dataEntrada = dataEntrada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}