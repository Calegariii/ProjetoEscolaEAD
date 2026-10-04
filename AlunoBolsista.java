/**
 * Parte 03 - Heranca: AlunoBolsista e subclasse de Aluno.
 */
public class AlunoBolsista extends Aluno {
    private String tipoBolsa;

    // Construtor sobrescrito usando super
    public AlunoBolsista(int codigo, String nome, String dataNascimento, String email, String senha, String tipoBolsa) {
        super(codigo, nome, dataNascimento, email, senha);
        this.tipoBolsa = tipoBolsa;
    }

    public String getTipoBolsa() {
        return tipoBolsa;
    }

    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
    }

    // Sobrescrita do metodo exibeDados para incluir o tipo de bolsa (polimorfismo)
    @Override
    public void exibeDados() {
        super.exibeDados();
        System.out.println("Tipo de Bolsa: " + tipoBolsa);
        System.out.println("----------------------------------");
    }
}
