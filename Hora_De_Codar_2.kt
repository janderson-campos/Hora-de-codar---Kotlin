fun main() {

    Atividade_1()
    Atividade_2()
    Atividade_3()
    Atividade_4()
    Atividade_5()
    Atividade_6()
    Atividade_7()
    Atividade_8()
    Atividade_9()
    Atividade_10()
    Atividade_11()



}

fun Atividade_1() {
    println("Digite dois números: ")
    var maior = 0
    val a = readln().toInt()
    val b = readln().toInt()
    if (a > b){
        maior = a
    } else{
        maior = b
    }
    println("O maior valor é: $maior")
}

fun Atividade_2() {

    println("Digite um número: ")
    val x = readln().toInt()
    if (x > 0){
        println("Seu número é positivo")
    } else if (x < 0) {
        println("Seu número é negativo")
    } else {
        println("Seu número é zero")
    }
}

fun Atividade_3(){
    println("Digite três números (todos diferentes): ")
    val a = readln().toDouble()
    val b = readln().toDouble()
    val c = readln().toDouble()

    val numeros = listOf(a, b, c).sortedDescending()
    val MaiorNumero = numeros[0]

    println("O maior número é: $MaiorNumero")

}


fun Atividade_4() {
    println("Digite três números (todos diferentes): ")
    val a = readln().toDouble()
    val b = readln().toDouble()
    val c = readln().toDouble()

    val numeros = listOf(a, b, c).sortedDescending()
    val SomaDoisMaiores = numeros[0] + numeros[1]
//67
    println("A soma dos dois maiores números é: $SomaDoisMaiores")
}

fun Atividade_5() {
    println("Digite 6 valores: ")

    val v1 = readln().toDouble()
    val v2 = readln().toDouble()
    val v3 = readln().toDouble()
    val v4 = readln().toDouble()
    val v5 = readln().toDouble()
    val v6 = readln().toDouble()

    val numeroMedia = listOf(v1, v2, v3, v4, v5, v6)
    val media = numeroMedia.sum() / numeroMedia.size

    println("Os valores informados foram: $numeroMedia")
    println("A média aritmética desses valores é: $media")
}
fun Atividade_6(){
    println("Digite 4 valores (todos diferentes): ")
    val a = readln().toDouble()
    val b = readln().toDouble()
    val c = readln().toDouble()
    val d = readln().toDouble()

    var maior = a
    if (b > maior) maior = b
    if (c > maior) maior = c
    if (d > maior) maior = d

    println("Primeiro número: $a")
    println("Último número: $d")
    println("Maior número: $maior")

}
fun Atividade_7(){

    println("Digite 6 números reais: ")

    val numeros = List(6) { readln().toDouble() }
    val soma = numeros.filter { it < 72 }.sum()

    println("Os valores informados foram: $numeros")
    println("A soma dos valores inferiores a 72 é: $soma")

}
fun Atividade_8(){
    println("Digite 4 valores (cada um maior que 0 e menor que 10): ")

    val n1 = readln().toDouble()
    val n2 = readln().toDouble()
    val n3 = readln().toDouble()
    val n4 = readln().toDouble()

    if (n1 > 0 && n1 < 10 &&
        n2 > 0 && n2 < 10 &&
        n3 > 0 && n3 < 10 &&
        n4 > 0 && n4 < 10) {

        val media = (n1 + n2 + n3 + n4) / 4.0
        println("A média é: $media")

        if (media > 5) {
            println("Você passou no teste")
        } else {
            println("Tente novamente")
        }
    } else {
        println("Valores inválidos. Todos devem ser maiores que 0 e menores que 10")
    }
}

fun Atividade_9(){

    println("Digite o ano de nascimento: ")
    val anoNascimento = readln().toInt()

    println("Digite o ano atual: ")
    val anoAtual = readln().toInt()

    val idade = anoAtual - anoNascimento

    if (idade >= 16) {
        println("Você pode votar este ano.")
    } else {
        println("Você não pode votar este ano.")
    }

}
fun Atividade_10(){

    println("Digite a altura em metros (ex: 1.75): ")
    val altura = readln().toDouble()

    println("Digite o gênero ao nascer (1 = feminino, 2 = masculino): ")
    val genero = readln().toInt()

    val pesoIdeal: Double

    if (genero == 2) {
        pesoIdeal = (72.7 * altura) - 58
        println("Peso ideal (masculino): $pesoIdeal kg")
    } else if (genero == 1) {
        pesoIdeal = (62.1 * altura) - 44.7
        println("Peso ideal (feminino): $pesoIdeal kg")
    } else {
        println("Gênero inválido. Use 1 para feminino ou 2 para masculino")
    }

}

fun Atividade_11(){

    println("CALCULADORA 2000")
    println("Digite dois valores: ")
    val a = readln().toDouble()
    val b = readln().toDouble()

    println("1. Adição")
    println("2. Subtração")
    println("3. Divisão")
    println("4. Multiplicação")
    println("Digite a opção (1 a 4): ")
    val opcao = readln().toInt()

    val resultado: Double

    when (opcao) {
        1 -> {
            resultado = a + b
            println("Resultado: $resultado")
        }
        2 -> {
            resultado = a - b
            println("Resultado: $resultado")
        }
        3 -> {
            if (b == 0.0) {
                println("Erro: divisão por zero")
            } else {
                resultado = a / b
                println("Resultado: $resultado")
            }
        }
        4 -> {
            resultado = a * b
            println("Resultado: $resultado")
        }
        else -> {
            println("Opção inválida. Use apenas 1, 2, 3 ou 4")
        }
    }
}
