/**
 * Classe Aluno - Parte 01 do Projeto Escola EAD.
 * Atualizada nas Partes 02 (curso), 04 (notas) e 05 (mensalidades).
 */
public class Aluno {
    // ===== Parte 01 - atributos basicos =====
    private int codigo;
    private String nome;
    private String dataNascimento;
    private String email;
    private String senha;

    // ===== Parte 02 - Opcao A: curso em que o aluno esta matriculado =====
    private Curso cursoMatriculado;

    // ===== Parte 04 - notas (array unidimensional obrigatorio) =====
    private double[] notas = new double[3];
    private boolean[] lancada = new boolean[3];

    // ===== Parte 05 - mensalidades (array unidimensional obrigatorio) =====
    private Mensalidade[] mensalidades;
    private int numParcelas;

    // Construtor com todos os parametros
    public Aluno(int codigo, String nome, String dataNascimento, String email, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.notas = new double[3];
        this.lancada = new boolean[3];
        this.mensalidades = null;
        this.numParcelas = 0;
    }

    // ===== Getters e Setters (Parte 01 - encapsulamento) =====
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // ===== Parte 02 =====
    public Curso getCursoMatriculado() {
        return cursoMatriculado;
    }

    public void setCursoMatriculado(Curso cursoMatriculado) {
        this.cursoMatriculado = cursoMatriculado;
    }

    // Imprime todos os dados do aluno
    public void exibeDados() {
        System.out.println("----------------------------------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("E-mail: " + email);
        if (cursoMatriculado != null) {
            System.out.println("Curso Matriculado: " + cursoMatriculado.getNome());
        } else {
            System.out.println("Curso Matriculado: nenhum");
        }
    }

    // ==================== Parte 04 - Notas ====================

    // Lanca as 3 notas do aluno
    public void lancarNotas(double n1, double n2, double n3) {
        notas[0] = n1;
        notas[1] = n2;
        notas[2] = n3;
        lancada[0] = true;
        lancada[1] = true;
        lancada[2] = true;
    }

    // Verifica se todas as notas ja foram lancadas
    public boolean todasNotasLancadas() {
        return lancada[0] && lancada[1] && lancada[2];
    }

    // Retorna a media aritmetica das 3 notas
    public double calcularMedia() {
        return (notas[0] + notas[1] + notas[2]) / 3;
    }

    // Mostra as notas e a media
    public void exibirNotas() {
        System.out.println("--- Notas de " + nome + " ---");
        for (int i = 0; i < notas.length; i++) {
            if (lancada[i]) {
                System.out.println("Nota " + (i + 1) + ": " + notas[i]);
            } else {
                System.out.println("Nota " + (i + 1) + ": nao lancada");
            }
        }
        if (todasNotasLancadas()) {
            System.out.printf("Media: %.2f%n", calcularMedia());
        } else {
            System.out.println("Media indisponivel: existem notas nao lancadas.");
        }
    }

    // ==================== Parte 05 - Financeiro ====================

    // Inicializa o vetor de mensalidades a partir dos valores informados
    public void adicionarMensalidades(double[] valores) {
        numParcelas = valores.length;
        mensalidades = new Mensalidade[numParcelas];
        for (int i = 0; i < numParcelas; i++) {
            mensalidades[i] = new Mensalidade(valores[i]);
        }
    }

    public boolean possuiMensalidades() {
        return mensalidades != null;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    // Mostra valor e status (pago ou nao) de cada mensalidade
    public void exibirMensalidades() {
        if (mensalidades == null) {
            System.out.println("Nenhuma mensalidade cadastrada para este aluno.");
            return;
        }
        System.out.println("--- Mensalidades de " + nome + " ---");
        for (int i = 0; i < mensalidades.length; i++) {
            String status = mensalidades[i].isPago() ? "PAGO" : "EM ABERTO";
            System.out.printf("Parcela %d: R$ %.2f - %s%n", (i + 1), mensalidades[i].getValor(), status);
        }
    }

    // Da baixa (pagamento) na parcela da posicao informada (indice base 0)
    public void pagarMensalidade(int indice) {
        if (mensalidades == null) {
            System.out.println("Nenhuma mensalidade cadastrada.");
            return;
        }
        if (indice < 0 || indice >= mensalidades.length) {
            System.out.println("Parcela invalida!");
            return;
        }
        if (mensalidades[indice].isPago()) {
            System.out.println("Esta parcela ja foi paga.");
            return;
        }
        mensalidades[indice].darBaixa();
        System.out.println("Parcela " + (indice + 1) + " paga com sucesso!");
    }
}
