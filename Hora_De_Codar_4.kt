val nomes = mutableListOf<String>()
val idades = mutableListOf<Int>()


fun main() {

    Estudantes()
    Planetas()
    Compras()
}

fun Estudantes() {
    println("Olá, vamos fazer seu cadastro")

    var resposta: String
    do {
        resposta = LoopCadastro()
    } while (resposta.uppercase() == "SIM")

    if (resposta.uppercase() == "PARE") {
        println("Final do Programa")
        println("Resumo dos cadastros:")
        for (i in nomes.indices) {
            println("Nome: ${nomes[i]}, Idade: ${idades[i]}")
        }
    } else {
        println("Resposta inválida. Programa encerrado.")
    }
}

fun LoopCadastro(): String {
    println("Informe o seu nome:")
    val nome = readln()
    println("Informe a sua idade:")
    val idade = readln().toInt()

    nomes.add(nome)
    idades.add(idade)

    println("Cadastro realizado: Nome = $nome, Idade = $idade")
    println("Deseja cadastrar mais? (SIM/PARE):")
    return readln()
}

fun Planetas() {

    val planetas = listOf("Terra", "Marte", "Plutão", "Vênus", "Júpiter", "Saturno")

    println("Digite o nome de um planeta:")
    val planetaInformado = readln()

    if (planetaInformado in planetas) {
        println("Planeta $planetaInformado está na lista!")
    } else {
        println("Planeta $planetaInformado não está na lista!")
    }

}

fun Compras() {
    val frutas = mutableListOf("pera", "maça", "banana", "manga", "mamão", "morango")

    println("Hortifruti do Tio Zé:")
    println(frutas)

    while (frutas.isNotEmpty()) {
        println("Digite o nome de uma fruta para remover:")
        val escolha = readln()

        if (escolha in frutas) {
            frutas.remove(escolha)
            println("Fruta $escolha foi retirada da lista!")
        } else {
            println("Fruta indisponível no nosso mercado.")
        }

        println("Lista atual: $frutas")
    }

    println("Lista de compras finalizada")
}


