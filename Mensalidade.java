/**
 * Parte 05 - Controle Financeiro: mensalidade.
 */
public class Mensalidade {
    private double valor;
    private boolean pago;

    public Mensalidade(double valor) {
        this.valor = valor;
        this.pago = false;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    // Marca a mensalidade como paga
    public void darBaixa() {
        this.pago = true;
    }
}
