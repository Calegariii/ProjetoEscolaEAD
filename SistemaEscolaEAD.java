import java.util.Scanner;

/**
 * Classe principal do Projeto Escola EAD.
 * Integra as 5 partes do trabalho em um unico sistema de console
 * com menu interativo (Scanner + switch + do-while).
 */
public class SistemaEscolaEAD {

    private static Scanner entrada = new Scanner(System.in);
    private static ListaDeAlunos lista = new ListaDeAlunos(50);

    // ===== Parte 02 - ARRAY BIDIMENSIONAL obrigatorio =====
    // Matriz de cursos organizada por turma: [turma][posicao]
    private static Curso[][] matrizCursos = new Curso[2][3];
    private static String[] nomesTurmas = {"Turma A", "Turma B"};
    private static int totalCursos = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    lista.exibirLista();
                    break;
                case 2:
                    adicionarAluno();
                    break;
                case 3:
                    menuCursos();
                    break;
                case 4:
                    verificarNotas();
                    break;
                case 5:
                    verificarFinanceiro();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        entrada.close();
    }

    private static void exibirMenu() {
        System.out.println("========== ESCOLA EAD ==========");
        System.out.println("1 - Visualizar Lista de Alunos");
        System.out.println("2 - Adicionar Aluno");
        System.out.println("3 - Cursos e Matriculas");
        System.out.println("4 - Verificar Notas do Aluno");
        System.out.println("5 - Verificar Financeiro do Aluno");
        System.out.println("0 - Sair");
        System.out.println("================================");
    }

    // ==================== Opcao 2 - Adicionar Aluno ====================

    private static void adicionarAluno() {
        System.out.println("--- CADASTRAR ALUNO ---");
        int codigo = lerInteiro("Codigo: ");
        String nome = lerTexto("Nome: ");
        String dataNasc = lerTexto("Data de nascimento (dd/mm/aaaa): ");
        String email = lerTexto("E-mail: ");
        String senha = lerTexto("Senha: ");
        int tipo = lerInteiro("Tipo (1 - Comum | 2 - Bolsista): ");

        Aluno novo;
        if (tipo == 2) {
            String tipoBolsa = lerTexto("Tipo de bolsa: ");
            novo = new AlunoBolsista(codigo, nome, dataNasc, email, senha, tipoBolsa);
        } else {
            novo = new Aluno(codigo, nome, dataNasc, email, senha);
        }

        // Matricula em um curso, se houver cursos cadastrados (Parte 02)
        if (totalCursos > 0) {
            System.out.println("Cursos disponiveis:");
            exibirCursosNumerados();
            int escolha = lerInteiro("Matricular no curso numero (0 para nenhum): ");
            Curso escolhido = buscarCursoPorNumero(escolha);
            if (escolhido != null) {
                novo.setCursoMatriculado(escolhido);
                System.out.println("Aluno matriculado em: " + escolhido.getNome());
            }
        } else {
            System.out.println("(Nenhum curso cadastrado ainda - use a opcao 3 do menu.)");
        }

        if (lista.adicionarAluno(novo)) {
            System.out.println("Aluno cadastrado com sucesso!");
        }
    }

    // ==================== Opcao 3 - Cursos e Matriculas ====================

    private static void menuCursos() {
        int opcao;
        do {
            System.out.println("--- CURSOS E MATRICULAS ---");
            System.out.println("1 - Cadastrar cursos");
            System.out.println("2 - Exibir cursos e alunos matriculados");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1:
                    cadastrarCursos();
                    break;
                case 2:
                    exibirCursosEAlunos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
            System.out.println();
        } while (opcao != 0);
    }

    // Cadastro multiplo de ate N cursos usando laco (Parte 02)
    private static void cadastrarCursos() {
        int capacidade = matrizCursos.length * matrizCursos[0].length;
        int vagas = capacidade - totalCursos;

        if (vagas == 0) {
            System.out.println("Capacidade maxima de cursos atingida!");
            return;
        }

        int n = lerInteiro("Quantos cursos deseja cadastrar (max " + vagas + ")? ");
        if (n < 1 || n > vagas) {
            System.out.println("Quantidade invalida!");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("--- Curso " + (i + 1) + " de " + n + " ---");
            int codigo = lerInteiro("Codigo: ");
            String nome = lerTexto("Nome: ");
            int duracao = lerInteiro("Duracao (horas): ");

            // Procura a proxima posicao livre na matriz bidimensional
            boolean inserido = false;
            for (int t = 0; t < matrizCursos.length && !inserido; t++) {
                for (int c = 0; c < matrizCursos[t].length && !inserido; c++) {
                    if (matrizCursos[t][c] == null) {
                        matrizCursos[t][c] = new Curso(codigo, nome, duracao);
                        totalCursos++;
                        inserido = true;
                        System.out.println("Curso cadastrado na " + nomesTurmas[t] + ".");
                    }
                }
            }
        }
    }

    // Exibe os cursos da matriz e, para cada um, os alunos matriculados
    private static void exibirCursosEAlunos() {
        if (totalCursos == 0) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }

        for (int t = 0; t < matrizCursos.length; t++) {
            System.out.println("===== " + nomesTurmas[t] + " =====");
            for (int c = 0; c < matrizCursos[t].length; c++) {
                Curso curso = matrizCursos[t][c];
                if (curso != null) {
                    curso.exibeDados();
                    System.out.println("Alunos matriculados:");
                    lista.exibirAlunosDoCurso(curso);
                    System.out.println();
                }
            }
        }
    }

    // Lista os cursos com numeracao sequencial (para escolha na matricula)
    private static void exibirCursosNumerados() {
        int numero = 1;
        for (int t = 0; t < matrizCursos.length; t++) {
            for (int c = 0; c < matrizCursos[t].length; c++) {
                if (matrizCursos[t][c] != null) {
                    System.out.println(numero + " - " + matrizCursos[t][c].getNome()
                            + " (" + nomesTurmas[t] + ")");
                    numero++;
                }
            }
        }
    }

    private static Curso buscarCursoPorNumero(int numeroDesejado) {
        int numero = 1;
        for (int t = 0; t < matrizCursos.length; t++) {
            for (int c = 0; c < matrizCursos[t].length; c++) {
                if (matrizCursos[t][c] != null) {
                    if (numero == numeroDesejado) {
                        return matrizCursos[t][c];
                    }
                    numero++;
                }
            }
        }
        return null;
    }

    // ==================== Opcao 4 - Notas ====================

    private static void verificarNotas() {
        int codigo = lerInteiro("Digite o codigo do aluno: ");
        Aluno aluno = lista.buscarPorCodigo(codigo);

        // Validacao: verifica se o aluno existe antes de exibir
        if (aluno == null) {
            System.out.println("Aluno nao encontrado!");
            return;
        }

        if (!aluno.todasNotasLancadas()) {
            String resp = lerTexto("Notas ainda nao lancadas. Deseja lancar agora? (s/n): ");
            if (resp.equalsIgnoreCase("s")) {
                double n1 = lerDouble("Nota 1: ");
                double n2 = lerDouble("Nota 2: ");
                double n3 = lerDouble("Nota 3: ");
                aluno.lancarNotas(n1, n2, n3);
            }
        }

        aluno.exibirNotas();
    }

    // ==================== Opcao 5 - Financeiro ====================

    private static void verificarFinanceiro() {
        int codigo = lerInteiro("Digite o codigo do aluno: ");
        Aluno aluno = lista.buscarPorCodigo(codigo);

        if (aluno == null) {
            System.out.println("Aluno nao encontrado!");
            return;
        }

        // Se ainda nao ha mensalidades, gera as parcelas agora
        if (!aluno.possuiMensalidades()) {
            int parcelas = lerInteiro("Quantidade de parcelas (ex: 12): ");
            double valor = lerDouble("Valor de cada parcela: R$ ");
            double[] valores = new double[parcelas];
            for (int i = 0; i < parcelas; i++) {
                valores[i] = valor;
            }
            aluno.adicionarMensalidades(valores);
        }

        aluno.exibirMensalidades();

        String resp = lerTexto("Deseja pagar uma parcela? (s/n): ");
        if (resp.equalsIgnoreCase("s")) {
            int numero = lerInteiro("Numero da parcela (1 a " + aluno.getNumParcelas() + "): ");
            aluno.pagarMensalidade(numero - 1); // converte para o indice do vetor
        }
    }

    // ==================== Metodos auxiliares de leitura ====================
    // Toda a leitura usa nextLine() para evitar o classico erro
    // de misturar nextInt() com nextLine() no Scanner.

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = entrada.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido! Digite um numero inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = entrada.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido! Digite um numero (ex: 7.5).");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine();
    }
}
