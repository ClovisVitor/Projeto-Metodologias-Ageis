package estacionamento.model;

public class Caminhonete extends Veiculo{

    public Caminhonete(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
    }

    @Override
    public double calcularValor(double valorBase) {
        return valorBase * 1.5;
    }
}
