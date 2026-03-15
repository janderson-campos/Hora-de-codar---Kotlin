fun main() {
    //Atividade1()
    Atividade2()
    Atividade3()
    Atividade4()
    Atividade5()
    Atividade6()
    Atividade7()
    Atividade8()
    Atividade9()
    Atividade10()
    Atividade11()
}

fun Atividade1() {
    var contador = 30
    while (contador > 0) {
        contador--
        println(contador)
        if (contador == 0) println("Explosão")
    }
}

fun Atividade2() {
    println("Digite o primeiro valor (a): ")
    val a = readln().toInt()
    var b: Int
    do {
        println("Digite o segundo valor (b) - deve ser > 0: ")
        b = readln().toInt()
        if (b <= 0) println("Valor inválido! O segundo valor deve ser maior que zero")
    } while (b <= 0)
    println("Resultado da divisão a / b = ${a / b}")
}

fun Atividade3() {
    var contador = 10
    while (contador >= 1) {
        println(contador)
        contador--
    }
}

fun Atividade4() {
    val inicio = 15
    val fim = 100
    var soma = 0.0
    var quantidade = 0
    for (i in inicio..fim) {
        soma += i
        quantidade++
    }
    val media = soma / quantidade
    println("A soma é: $soma")
    println("A quantidade de números é: $quantidade")
    println("A média aritmética é: $media")
}

fun Atividade5() {
    var primeiro: Int
    var segundo: Int
    do {
        println("Digite o primeiro valor (menor): ")
        primeiro = readln().toInt()
        println("Digite o segundo valor (maior): ")
        segundo = readln().toInt()
        if (primeiro >= segundo) println("O primeiro deve ser menor que o segundo. Tente novamente")
    } while (primeiro >= segundo)

    val media = (primeiro + segundo) / 2.0
    println("A média aritmética de todos os inteiros de $primeiro até $segundo é: $media")
}

fun Atividade6() {
    println("Digite a primeira nota: ")
    val nota1 = readln().toDouble()
    println("Digite a segunda nota: ")
    val nota2 = readln().toDouble()

    val media = (nota1 + nota2) / 2.0
    println("A média final do aluno é: $media")

    if (media >= 9.5) println("Aluno aprovado!") else println("Aluno reprovado!")
}

fun Atividade7() {
    println("Digite as 6 notas do aluno (entre 0 e 10):")
    val notas = mutableListOf<Double>()
    repeat(6) {
        var nota: Double
        do {
            nota = readln().toDouble()
            if (nota !in 0.0..10.0) println("Nota inválida! Digite novamente:")
        } while (nota !in 0.0..10.0)
        notas.add(nota)
    }
    val media = notas.average()
    println("As notas informadas foram: $notas")
    println("A média do aluno é: $media")
}

fun Atividade8() {
    var N: Int
    do {
        println("Digite o valor N (maior que zero): ")
        N = readln().toInt()
        if (N <= 0) println("Valor inválido! N deve ser maior que zero.")
    } while (N <= 0)

    for (i in 1..N) println(i)
}

fun Atividade9() {
    for (j in 101..110) println(j)
}

fun Atividade10() {
    val numeros = IntArray(10)
    var dentro = 0
    var fora = 0
    println("Digite 10 valores inteiros:")
    for (i in 0 until 10) {
        numeros[i] = readln().toInt()
        if (numeros[i] in 24..42) dentro++ else fora++
    }
    println("Quantidade de valores dentro do intervalo [24,42]: $dentro")
    println("Quantidade de valores fora do intervalo: $fora")
}

fun Atividade11() {
    println("Digite o valor de N: ")
    val N = readln().toInt()
    for (i in 1..N) {
        println("Tabuada do $i:")
        for (j in 1..10) {
            println("$i x $j = ${i * j}")
        }
    }
}