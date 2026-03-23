// Estruturas de dados
data class Pessoa(val nome: String, val endereco: String, val telefone: String)
data class Aluno(val nome: String, val notas: List<Double>)
data class PessoaAltura(val nome: String, val altura: Double)
data class Funcionario(val matricula: Int, val nome: String, val salario: Double)

// Exercício Agenda
fun agenda() {
    val agenda = mutableListOf<Pessoa>()
    var opcao: Int
    do {
        println("\nMenu Agenda")
        println("1 - Cadastrar")
        println("2 - Pesquisar por nome")
        println("3 - Classificar por nome")
        println("4 - Mostrar todos")
        println("5 - Sair")
        opcao = readln().toInt()
        when (opcao) {
            1 -> for (i in 1..10) {
                print("Nome: "); val nome = readln()
                print("Endereço: "); val endereco = readln()
                print("Telefone: "); val telefone = readln()
                agenda.add(Pessoa(nome, endereco, telefone))
            }
            2 -> { print("Nome: "); val busca = readln()
                println(agenda.find { it.nome == busca } ?: "Não encontrado") }
            3 -> agenda.sortBy { it.nome }
            4 -> agenda.forEach { println(it) }
        }
    } while (opcao != 5)
}

// Exercício Notas
fun notas() {
    val alunos = mutableListOf<Aluno>()
    var opcao: Int
    do {
        println("\nMenu Notas")
        println("1 - Cadastrar")
        println("2 - Pesquisar por nome")
        println("3 - Mostrar todos")
        println("4 - Sair")
        opcao = readln().toInt()
        when (opcao) {
            1 -> {
                for (i in 1..20) {
                    print("Nome: "); val nome = readln()
                    val notas = (1..4).map {
                        print("Nota $it: "); readln().toDouble()
                    }
                    alunos.add(Aluno(nome, notas))
                }
                alunos.sortBy { it.nome }
            }
            2 -> { print("Nome: "); val busca = readln()
                val aluno = alunos.find { it.nome == busca }
                if (aluno != null) {
                    val media = aluno.notas.average()
                    println("Média: $media - ${if (media >= 5) "Aprovado" else "Reprovado"}")
                } else println("Não encontrado") }
            3 -> alunos.forEach {
                val media = it.notas.average()
                println("${it.nome} - Média: $media - ${if (media >= 5) "Aprovado" else "Reprovado"}")
            }
        }
    } while (opcao != 4)
}

// Exercício Alturas
fun alturas() {
    val pessoas = mutableListOf<PessoaAltura>()
    var opcao: Int
    do {
        println("\nMenu Alturas")
        println("1 - Cadastrar")
        println("2 - <= 1.5m")
        println("3 - > 1.5m")
        println("4 - Entre 1.5 e 2.0m")
        println("5 - Média")
        println("6 - Sair")
        opcao = readln().toInt()
        when (opcao) {
            1 -> for (i in 1..15) {
                print("Nome: "); val nome = readln()
                print("Altura: "); val altura = readln().toDouble()
                pessoas.add(PessoaAltura(nome, altura))
            }
            2 -> pessoas.filter { it.altura <= 1.5 }.forEach { println(it) }
            3 -> pessoas.filter { it.altura > 1.5 }.forEach { println(it) }
            4 -> pessoas.filter { it.altura > 1.5 && it.altura < 2.0 }.forEach { println(it) }
            5 -> println("Média: ${pessoas.map { it.altura }.average()}")
        }
    } while (opcao != 6)
}

// Exercício Funcionários
fun funcionarios() {
    val funcionarios = mutableListOf<Funcionario>()
    var opcao: Int
    do {
        println("\nMenu Funcionários")
        println("1 - Cadastrar")
        println("2 - Pesquisar por matrícula")
        println("3 - Salário > 1000")
        println("4 - Salário < 1000")
        println("5 - Salário = 1000")
        println("6 - Sair")
        opcao = readln().toInt()
        when (opcao) {
            1 -> {
                for (i in 1..20) {
                    print("Matrícula: "); val mat = readln().toInt()
                    print("Nome: "); val nome = readln()
                    print("Salário: "); val sal = readln().toDouble()
                    funcionarios.add(Funcionario(mat, nome, sal))
                }
                funcionarios.sortBy { it.matricula }
            }
            2 -> { print("Matrícula: "); val busca = readln().toInt()
                println(funcionarios.find { it.matricula == busca } ?: "Não encontrado") }
            3 -> funcionarios.filter { it.salario > 1000 }.forEach { println(it) }
            4 -> funcionarios.filter { it.salario < 1000 }.forEach { println(it) }
            5 -> funcionarios.filter { it.salario == 1000.0 }.forEach { println(it) }
        }
    } while (opcao != 6)
}

// Menu principal
fun menuPrincipal() {
    var escolha: Int
    do {
        println("\nMenu Principal")
        println("1 - Agenda")
        println("2 - Notas")
        println("3 - Alturas")
        println("4 - Funcionários")
        println("5 - Sair")
        escolha = readln().toInt()
        when (escolha) {
            1 -> agenda()
            2 -> notas()
            3 -> alturas()
            4 -> funcionarios()
        }
    } while (escolha != 5)
}

// Ponto de entrada
fun main() {
    menuPrincipal()
}