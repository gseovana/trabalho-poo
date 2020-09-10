package trabalho;

import java.util.Scanner;

public class Programa {
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {

		Disciplina[] disciplinas = new Disciplina[10];
		Aluno[] alunos = new Aluno[30];

		int opcao;
		int proxCodDisciplina = 1;
		int proxCodAluno = 1;

		do {
			System.out.println("");
			System.out.println("------ MENU PRINCIPAL - Sistema Academico ------");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar disciplina"); // FEITO
			System.out.println("2 - Remover disciplina");
			System.out.println("3 - Listar disciplinas"); // FEITO
			System.out.println("4 - Cadastrar aluno"); // FEITO
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

				if (inserirDisciplina) {
					System.out.println("\nDisciplina cadastrada com sucesso!");
					proxCodDisciplina++;
				} else {
					System.out.println("\nErro ao cadastrar disciplina.");
				}

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

				if (inserirAluno) {
					System.out.println("\nAluno cadastrado com sucesso!");
					proxCodAluno++;
				} else {
					System.out.println("Erro ao cadastrar aluno.");
				}

				break;
			case 5:
				menuAlteracao(alunos);
				break;

			default:
				System.out.println("Comando não encontrado. Insira um comando válido.");
				break;
			}

		} while (opcao != 0);
	}

//-----------------------------FUNCOES DISCIPLINAS-------------------------------

	// ler codigo

	private static int lerCodigoDisciplina() {
		System.out.print("Código da disciplina: ");
		int codigoDisciplina = scn.nextInt();
		return codigoDisciplina;
	}

	// ler dados de cadastro de disciplina

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

	// inserir disciplinas

	static boolean inserirDisciplinas(Disciplina[] disciplinas, Disciplina d, int proxCodDisciplina) {
		if (disciplinas != null && d != null) {
			for (int i = 0; i < disciplinas.length; i++) {
				if (disciplinas[i] != null && disciplinas[i].nome.equals(d.nome)) {
					return false;
				} else {
					if (disciplinas[i] == null) {
						d.codigo = proxCodDisciplina;
						disciplinas[i] = d;
						return true;
					}
				}
			}
		}
		return false;
	}

	// remover disciplinas

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

	// listar disciplinas

	private static void listarDisciplinas(Disciplina[] disciplinas) {
		if (disciplinas != null) {
			System.out.println("COD\tNOME\tALUNOS\tPROFESSOR ");
			for (int i = 0; i < disciplinas.length; i++) {
				if (disciplinas[i] != null) {
					System.out.println(disciplinas[i].codigo + "\t" + disciplinas[i].nome + "\t"
							+ disciplinas[i].numAlunosMatriculados + "\t" + disciplinas[i].nomeProf);
				}
			}
		}
	}

	// -----------------------------FUNCOES
	// ALUNOS--------------------------------------------

	// menu de alteração de dados
	private static void menuAlteracao(Aluno[] alunos) {

		int opcao;
		int opcao2;

		do {
			System.out.println("------ ALTERAR DADOS DO ALUNO ------");

			System.out.println("0 - Voltar ao menu principal <<");
			System.out.println("1 - Informar matrícula do aluno");

			opcao = scn.nextInt();

			if (opcao == 1) {
				int matricula = lerMatriculaAluno();
				Aluno aluno = buscarAluno(matricula, alunos);

				if (aluno == null) {
					System.out.println("\nAluno nao encontrado.");
				} else {
					detalharAluno(aluno);
				}

				System.out.println("");
				System.out.println("O que deseja alterar?");
				System.out.println("1 - Nome do aluno");
				System.out.println("2 - Nome da mae");
				System.out.println("3 - Nome do pai");
				System.out.println("4 - Endereco");

				opcao2 = scn.nextInt();

				switch (opcao2) {
				case 1:

					boolean alterarNomeAluno = alterarNomeAluno(aluno, alunos);
					if (alterarNomeAluno) {
						System.out.println("\nNome do aluno alterado com sucesso!");
					} else {
						System.out.println("\nErro ao alterar nome do aluno.");
					}

					break;
				case 2:

					boolean alterarNomeMae = alterarNomeMae(aluno, alunos);
					if (alterarNomeMae) {
						System.out.println("\nNome da mae alterado com sucesso!");
					} else {
						System.out.println("\nErro ao alterar nome da mae.");
					}

					break;
				case 3:

					boolean alterarNomePai = alterarNomePai(aluno);
					if (alterarNomePai) {
						System.out.println("\nNome do pai alterado com sucesso!");
					} else {
						System.out.println("\nErro ao alterar nome do pai.");
					}

					break;
				case 4:

					boolean alterarEndereco = alterarEndereco(aluno);
					if (alterarEndereco) {
						System.out.println("\nEndereco alterado com sucesso!");
					} else {
						System.out.println("\nErro ao alterar endereco.");
					}

					break;

				default:
					System.out.println("Opcao invalida.");
					break;
				}

			}

		} while (opcao != 0);
	}

	// ler dados do aluno

	private static Aluno lerAluno() {
		Aluno a = new Aluno();

		System.out.print("Nome: ");
		a.nome = scn.next();

		System.out.print("Nome da mae: ");
		a.nomeMae = scn.next();

		System.out.print("Nome do pai: ");
		a.nomePai = scn.next();

		System.out.print("Endereco: ");
		a.endereco = scn.next();

		return a;
	}

	// ler matricula
	private static int lerMatriculaAluno() {
		System.out.print("Matricula: ");
		int matricula = scn.nextInt();
		return matricula;
	}

	// funcoes de alteracao
	private static boolean alterarNomeAluno(Aluno aluno, Aluno[] alunos) {
		if (aluno != null && alunos != null) {
			String novoNome;

			System.out.print("Novo nome: ");
			novoNome = scn.next();

			for (int i = 0; i < alunos.length; i++) {
				if (alunos[i].nome.equals(novoNome) && alunos[i].nomeMae.equals(aluno.nomeMae)) {
					return false;
				} else {
					alunos[i].nome = novoNome;
					return true;
				}
			}
		}
		return false;
	}

	private static boolean alterarNomeMae(Aluno aluno, Aluno[] alunos) {
		if (aluno != null) {
			String novoNomeMae;

			System.out.print("Novo nome da mae: ");
			novoNomeMae = scn.next();

			for (int i = 0; i < alunos.length; i++) {
				if (alunos[i] != null & alunos[i].nome.equals(aluno.nome) && alunos[i].nomeMae.equals(novoNomeMae)) {
					return false;
				} else {
					alunos[i].nomeMae = novoNomeMae;
					return true;
				}
			}
		}
		return false;
	}

	private static boolean alterarNomePai(Aluno aluno) {
		if (aluno != null) {
			String novoNomePai;

			System.out.print("Novo nome do pai: ");
			novoNomePai = scn.next();
			aluno.nomePai = novoNomePai;
			return true;
		}
		return false;
	}

	private static boolean alterarEndereco(Aluno aluno) {
		if (aluno != null) {
			String novoEndereco;

			System.out.print("Novo endereco: ");
			novoEndereco = scn.next();
			aluno.endereco = novoEndereco;
			return true;
		}
		return false;
	}

	// buscar aluno
	private static Aluno buscarAluno(int matricula, Aluno[] alunos) {
		if (matricula > 0) {
			for (int i = 0; i < alunos.length; i++) {
				if (alunos[i] != null && alunos[i] != null && alunos[i].codigoMatricula == matricula) {
					return alunos[i];
				}
			}
		}
		return null;
	}

	// inserir aluno

	private static boolean inserirAluno(Aluno[] alunos, Aluno a, int proxCodAluno) {
		if (alunos != null && a != null) {
			for (int i = 0; i < alunos.length; i++) {
				// ficar esperta nessa logica aqui
				if (alunos[i] != null && alunos[i].nome.equals(a.nome) && alunos[i].nomeMae.equals(a.nomeMae)) {
					return false;
				} else {
					if (alunos[i] == null) {
						a.codigoMatricula = proxCodAluno;
						alunos[i] = a;
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void detalharAluno(Aluno aluno) {
		if (aluno != null) {
			System.out.println("Nome: " + aluno.nome);
			System.out.println("Nome da mae: " + aluno.nomeMae);
			System.out.println("Nome do pai: " + aluno.nomePai);
			System.out.println("Endereco: " + aluno.endereco);
		}

	}

}
