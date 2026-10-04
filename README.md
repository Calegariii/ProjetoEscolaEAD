======================================================
 PROJETO ESCOLA EAD - Programacao Orientada a Objetos
 Linguagem de Programacao II - Java
======================================================

Sistema de console para uma escola de Educacao a Distancia (EAD),
integrando as 5 partes do trabalho em um unico projeto.

CLASSES
-------
- Aluno ............ dados do aluno, notas (double[3]) e mensalidades
- AlunoBolsista .... herda de Aluno, adiciona tipoBolsa (heranca/polimorfismo)
- Curso ............ codigo, nome e duracao do curso
- ListaDeAlunos .... gerencia vetor Aluno[] com anti-duplicidade
- Mensalidade ...... valor, status e darBaixa()
- SistemaEscolaEAD . classe principal com menu interativo

REQUISITOS ATENDIDOS
--------------------
[x] Heranca e polimorfismo   -> Aluno -> AlunoBolsista (exibeDados sobrescrito)
[x] Array unidimensional     -> notas[3], alunos[], mensalidades[] (sem ArrayList)
[x] Array bidimensional      -> Curso[][] matrizCursos (por turma)
[x] Estruturas de repeticao  -> for, while, do-while
[x] Estruturas de selecao    -> if, switch
[x] Encapsulamento           -> atributos private + getters/setters
[x] Validacoes               -> duplicidade de codigo, aluno existente, parcela valida

MENU DO SISTEMA
---------------
1 - Visualizar Lista de Alunos
2 - Adicionar Aluno (comum ou bolsista, com matricula em curso)
3 - Cursos e Matriculas (cadastrar ate N cursos na matriz / listar)
4 - Verificar Notas do Aluno (lancar 3 notas e exibir media)
5 - Verificar Financeiro do Aluno (gerar parcelas e pagar)
0 - Sair

OBSERVACAO
----------
O sistema inicia VAZIO: todos os cursos, alunos, notas e
mensalidades sao cadastrados pelo usuario no menu, em tempo
de execucao. Fluxo sugerido: cadastre cursos (opcao 3) ANTES
de adicionar alunos (opcao 2), para poder matricula-los.
