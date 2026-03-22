import kotlin.system.exitProcess

var saldo = 100.5f
var usuario: String? = null
const val SENHA = "3589"

fun main() {
    inicio()
}

fun inicio() {
    if (usuario == null) {
        println("Digite o seu nome:")
        usuario = readln()
        println("Olá $usuario é um prazer ter você por aqui!")
    }

    println("\nSeja Bem Vindo ao Banco Piranga Do Sul")
    println("Escolha uma opção:")
    println("1. Saldo")
    println("2. Extrato")
    println("3. Saque")
    println("4. Depósito")
    println("5. Transferência")
    println("6. Sair")

    val escolha = readlnOrNull()?.toIntOrNull()

    when (escolha) {
        1 -> verSaldo()
        2 -> fazerExtrato()
        3 -> fazerSaque()
        4 -> fazerDeposito()
        5 -> fazerTransferencia()
        6 -> sair()
        else -> erro()
    }
}

fun pedirSenha(): Boolean {
    print("Digite sua senha: ")
    val senhaInformada = readln()
    return senhaInformada == SENHA
}

fun verSaldo() {
    if (!pedirSenha()) {
        println("Senha incorreta. Tente novamente.")
        verSaldo()
        return
    }
    println("Seu saldo atual é: R$ $saldo")
    continuar()
    inicio()
}

fun fazerExtrato() {
    if (!pedirSenha()) {
        println("Senha incorreta. Tente novamente.")
        fazerExtrato()
        return
    }
    println("|_____________________________|")
    println("| Extrato do mês              |")
    println("| Banco Tchan----------R$100  |")
    println("| Seu-Zé-comercio------R$200  |")
    println("| Turma da fonica------R$150  |")
    println("| Corrida--------------R$50   |")
    println("| Nice-short-----------R$75   |")
    println("|-------valor total = R$575---|")
    println("|_____________________________|")
    continuar()
    inicio()
}

fun fazerDeposito() {
    print("Qual o valor para depósito? ")
    val deposito = readlnOrNull()?.toFloatOrNull()

    if (deposito == null || deposito <= 0) {
        println("Operação não autorizada.")
    } else {
        saldo += deposito
        println("Depósito realizado com sucesso!")
    }
    continuar()
    inicio()
}

fun fazerSaque() {
    if (!pedirSenha()) {
        println("Senha incorreta. Tente novamente.")
        fazerSaque()
        return
    }
    print("Qual o valor para saque? ")
    val saque = readlnOrNull()?.toFloatOrNull()

    if (saque == null || saque <= 0) {
        println("Operação não autorizada.")
    } else if (saque > saldo) {
        println("Operação não autorizada. Saldo insuficiente.")
    } else {
        saldo -= saque
        println("Saque realizado com sucesso!")
    }
    continuar()
    inicio()
}

fun fazerTransferencia() {
    if (!pedirSenha()) {
        println("Senha incorreta. Tente novamente.")
        fazerTransferencia()
        return
    }
    print("Digite o número da conta destino: ")
    val conta = readlnOrNull()
    if (conta == null || conta.any { !it.isDigit() }) {
        println("Número de conta inválido.")
        continuar()
        inicio()
        return
    }

    print("Digite o valor da transferência: ")
    val valor = readlnOrNull()?.toFloatOrNull()

    if (valor == null || valor <= 0) {
        println("Operação não autorizada.")
    } else if (valor > saldo) {
        println("Operação não autorizada. Saldo insuficiente.")
    } else {
        saldo -= valor
        println("Transferência de R$ $valor para a conta $conta realizada com sucesso!")
    }
    continuar()
    inicio()
}

fun erro() {
    println("Por favor, informe um número entre 1 a 6")
    continuar()
    inicio()
}

fun sair() {
    print("Você deseja sair? (S/N) ")
    val confirma = readlnOrNull().orEmpty().uppercase()

    when (confirma) {
        "S" -> {
            println("$usuario, foi um prazer ter você por aqui!")
            exitProcess(0)
        }
        "N" -> inicio()
        else -> sair()
    }
}

fun continuar() {
    println("Aperte ENTER para continuar")
    readlnOrNull()
}