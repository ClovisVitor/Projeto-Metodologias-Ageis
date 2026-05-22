package estacionamento.model;

public class Moto extends Veiculo{

    public Moto(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
    }

    @Override
    public double calcularValor(double valorBase) {
        return valorBase * 0.5;
    }
}
