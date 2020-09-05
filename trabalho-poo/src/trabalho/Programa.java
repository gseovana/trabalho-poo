package trabalho;

import java.util.Scanner;

public class Programa {
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {

		Disciplina[] disciplinas = new Disciplina[10];
		Aluno[] alunos = new Aluno[30];

		int opcao;
		int proxCodDisciplina = 0;
		int proxCodAluno = 0;

		do {
			System.out.println("");
			System.out.println("------ MINI SISTEMA ACADEMICO ------");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar disciplina");
			System.out.println("2 - Remover disciplina");
			System.out.println("3 - Listar disciplinas");
			System.out.println("4 - Cadastrar aluno");
			System.out.println("5 - Alterar dados do aluno");
			System.out.println("6 - Detalhar aluno");
			System.out.println("7 - Listar alunos");
			System.out.println("8 - Matricular aluno em disciplina");
			System.out.println("9 - Listar matrículas");

			opcao = scn.nextInt();

			// switch substitui os if's aninhados
			switch (opcao) {
			case 0:
				System.out.println("Saindo...");
				break;
			case 1:
				System.out.println("");
				System.out.println("------ CADASTRAR DISCIPLINA ------");

				Disciplina d = lerDisciplina();
				boolean inserirDisciplina = inserirDisciplinas(disciplinas, d, proxCodDisciplina);

				if (inserirDisciplina)
					System.out.println("\nDisciplina cadastrada com sucesso!");
				else
					System.out.println("\nErro ao cadastrar disciplina.");

				break;
			case 2:
				System.out.println("");
				System.out.println("------ REMOVER DISCIPLINA ------");

				int codigoDisciplina = lerCodigoDisciplina();
				boolean removerDisciplina = removerDisciplinas(disciplinas, codigoDisciplina);

				if (removerDisciplina)
					System.out.println("\nDisciplina removida com sucesso!");
				else
					System.out.println("\nErro ao remover disciplina.");

				break;
			case 3:
				System.out.println("");
				System.out.println("------ LISTAR DISCIPLINA ------");

				listarDisciplinas(disciplinas);
				break;
			case 4:
				System.out.println("");
				System.out.println("------ CADASTRAR ALUNO ------");

				Aluno a = lerAluno();
				boolean inserirAluno = inserirAluno(alunos, a, proxCodAluno);

				if (inserirAluno)
					System.out.println("Aluno cadastrado com sucesso!");
				else
					System.out.println("Erro ao cadastrar aluno.");

				break;
			case 5:
				menuAlteracao();
				break;

			default:
				System.out.println("Comando não encontrado. Insira um comando válido.");
				break;
			}

		} while (opcao != 0);
	}

	private static void menuAlteracao() {

		int opcao;

		do {
			System.out.println("------ ALTERAR DADOS DO ALUNO ------");
			System.out.println("0 - Voltar ao menu principal <<");
			System.out.println("1 - Nome do aluno");
			System.out.println("2 - Nome da mae");
			System.out.println("3 - Nome do pai");
			System.out.println("4 - Endereco");

			opcao = scn.nextInt();

			switch (opcao) {
			case 1:

				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}

		} while (opcao != 0);

	}

	private static boolean inserirAluno(Aluno[] alunos, Aluno a, int proxCodAluno) {
		if (alunos != null && a != null) {
			for (int i = 0; i < alunos.length; i++) {
				if (alunos[i] != null && alunos[i].nome != a.nome && alunos[i].nomeMae != a.nomeMae) // ficar esperta
																										// nessa logica
																										// aqui
					if (alunos[i] == null) {
						proxCodAluno++;
						a.codigoMatricula = proxCodAluno;
						alunos[i] = a;
					}
			}
		}
		return false;
	}

	private static Aluno lerAluno() {
		Aluno a = new Aluno();

		System.out.print("Nome: ");
		a.nome = scn.next();

		System.out.print("Nome da mae: ");
		a.nomeMae = scn.next();

		System.out.print("Nome do pai: ");
		a.nomePai = scn.next();

		System.out.print("Endereco ");
		a.endereco = scn.next();

		System.out.print("Endereco ");
		a.endereco = scn.next();

		return a;
	}

	private static int lerCodigoDisciplina() {
		System.out.print("Código da disciplina: ");
		int codigoDisciplina = scn.nextInt();
		return codigoDisciplina;
	}

	static Disciplina lerDisciplina() {
		Disciplina d = new Disciplina();
		System.out.print("Nome: ");
		d.nome = scn.next();

		System.out.print("Ano: ");
		d.ano = scn.nextInt();

		System.out.print("Numero de vagas: ");
		d.numVagas = scn.nextInt();

		System.out.print("Nome do professor: ");
		d.nomeProf = scn.next();

		return d;
	}

	static boolean inserirDisciplinas(Disciplina[] disciplinas, Disciplina d, int proxCodDisciplina) {
		if (disciplinas != null && d != null) {
			for (int i = 0; i < disciplinas.length; i++) {
				if (disciplinas[i] != null && disciplinas[i].nome == d.nome) {
					return false;
				} else {
					if (disciplinas[i] == null) {
						proxCodDisciplina++;
						d.codigo = proxCodDisciplina;
						disciplinas[i] = d;
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean removerDisciplinas(Disciplina[] disciplinas, int proxCodDisciplina) {
		if (proxCodDisciplina > 0) {
			for (int i = 0; i < disciplinas.length; i++) {
				if (disciplinas[i].numAlunosMatriculados == 0) {
					if (disciplinas[i].codigo == proxCodDisciplina) {
						disciplinas[i] = null;
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void listarDisciplinas(Disciplina[] disciplinas) {
		if (disciplinas != null) {
			System.out.println("COD\t NOME\t ALUNOS\t PROFESSOR ");
			for (int i = 0; i < disciplinas.length; i++) {
				if (disciplinas[i] != null) {
					System.out.println(disciplinas[i].codigo + "\t" + disciplinas[i].nome + "\t"
							+ disciplinas[i].numAlunosMatriculados + "\t" + disciplinas[i].nomeProf);
				}
			}
		}
	}
}
