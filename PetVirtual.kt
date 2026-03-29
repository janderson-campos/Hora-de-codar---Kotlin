class Pet(
    val nome: String,
    var fome: Int = 0,
    var felicidade: Int = 100,
    var cansaco: Int = 0,
    var idade: Int = 1
) {
    fun alimentar() {
        fome -= 20
        if (fome < 0) fome = 0

        felicidade += 5
        if (felicidade > 100) felicidade = 100

        println("$nome foi alimentado.")
    }

    fun brincar() {
        felicidade += 10
        if (felicidade > 100) felicidade = 100

        cansaco += 15
        if (cansaco > 100) cansaco = 100

        println("$nome brincou.")
    }

    fun descansar() {
        cansaco -= 30
        if (cansaco < 0) cansaco = 0

        println("$nome descansou.")
    }

    fun verificarStatus() {
        println("Status do $nome")
        println("Fome: $fome")
        println("Felicidade: $felicidade")
        println("Cansaco: $cansaco")
        println("Idade: $idade")
    }

    fun passarTempo() {
        fome += 3
        if (fome > 100) fome = 100

        felicidade -= 3
        if (felicidade < 0) felicidade = 0

        cansaco += 10
        if (cansaco > 100) cansaco = 100

        idade += 1
    }

    fun perdeu(): Boolean {
        return fome >= 100 || cansaco >= 100 || felicidade <= 0
    }

    fun venceu(): Boolean {
        return idade >= 50
    }

    fun motivoDerrota(): String {
        return when {
            fome >= 100 -> "A fome chegou a 100."
            cansaco >= 100 -> "O cansaco chegou a 100."
            felicidade <= 0 -> "A felicidade chegou a 0."
            else -> "Derrota."
        }
    }
}

fun main() {
    println("Seja Bem Vindo!!")

    var nome: String
    var verificar: String

    nome = escolherNome()
    println("Tem certeza? \nS|N")
    verificar = readln().trim().uppercase()

    while (verificar != "S") {
        nome = escolherNome()
        println("Tem certeza? \nS|N")
        verificar = readln().trim().uppercase()
    }

    println("Deseja participar do tutorial \nS|N")
    verificar = readln().trim().uppercase()

    if (verificar == "S") {
        tutorial()
    }

    val pet = Pet(nome)
    println("De as boas vindas para o ${pet.nome}")

    var opcao: Int

    do {
        println()
        println("1. Alimentar")
        println("2. Brincar")
        println("3. Descansar")
        println("4. Verificar status")
        println("0. Sair")

        opcao = readln().trim().toIntOrNull() ?: -1

        when (opcao) {
            1 -> {
                pet.alimentar()
                pet.passarTempo()
            }
            2 -> {
                pet.brincar()
                pet.passarTempo()
            }
            3 -> {
                pet.descansar()
                pet.passarTempo()
            }
            4 -> pet.verificarStatus()
            0 -> println("Encerrando...")
            else -> println("Opcao invalida")
        }

        if (pet.venceu()) {
            println("Parabens! Seu pet chegou a idade 50. Voce venceu!")
            break
        }

        if (pet.perdeu()) {
            println("${pet.motivoDerrota()} Game Over.")
            break
        }

    } while (opcao != 0)
}

fun tutorial() {
    println(
        "Seja bem vindo ao Tutorial\n" +
                "Objetivo: Fazer o pet chegar a idade 50\n" +
                "Derrota: Fome chegar a 100, Cansaco chegar a 100 ou Felicidade chegar a 0\n" +
                "Passagem do tempo (por ciclo): Fome +3, Felicidade -3, Cansaco +10, Idade +1\n"
    )
    println("Continuar: pressione ENTER")
    readln()
}

fun escolherNome(): String {
    println("Escolha o nome do seu mascote:")
    return readln().trim()
}