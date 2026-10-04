/**
 * Parte 03 - Gerencia a lista de alunos usando ARRAY unidimensional (obrigatorio).
 * Nao utiliza ArrayList como estrutura principal.
 */
public class ListaDeAlunos {
    private Aluno[] alunos;      // Array unidimensional obrigatorio
    private int totalAlunos;

    public ListaDeAlunos(int capacidade) {
        alunos = new Aluno[capacidade];
        totalAlunos = 0;
    }

    // Adiciona aluno, verificando duplicidade por codigo
    public boolean adicionarAluno(Aluno a) {
        if (buscarPorCodigo(a.getCodigo()) != null) {
            System.out.println("Erro: ja existe um aluno com o codigo " + a.getCodigo() + ".");
            return false;
        }
        if (totalAlunos >= alunos.length) {
            System.out.println("Erro: lista de alunos cheia!");
            return false;
        }
        alunos[totalAlunos] = a;
        totalAlunos++;
        return true;
    }

    // Percorre o vetor e chama exibeDados() de cada aluno
    public void exibirLista() {
        if (totalAlunos == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        System.out.println("===== LISTA DE ALUNOS (" + totalAlunos + ") =====");
        for (int i = 0; i < totalAlunos; i++) {
            // Polimorfismo: chama o exibeDados() correto (Aluno ou AlunoBolsista)
            alunos[i].exibeDados();
            System.out.println("----------------------------------");
        }
    }

    // Busca um aluno pelo codigo (usada pelas opcoes 4 e 5 do menu)
    public Aluno buscarPorCodigo(int codigo) {
        for (int i = 0; i < totalAlunos; i++) {
            if (alunos[i].getCodigo() == codigo) {
                return alunos[i];
            }
        }
        return null;
    }

    // Exibe os alunos matriculados em um curso especifico (Parte 02)
    public void exibirAlunosDoCurso(Curso curso) {
        boolean encontrou = false;
        for (int i = 0; i < totalAlunos; i++) {
            if (alunos[i].getCursoMatriculado() == curso) {
                System.out.println("- " + alunos[i].getNome() + " (codigo " + alunos[i].getCodigo() + ")");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("- nenhum aluno matriculado");
        }
    }

    public int getTotalAlunos() {
        return totalAlunos;
    }
}
