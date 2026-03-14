import kotlin.math.PI

fun main() {

    atividade_1()
    atividade_2()
    atividade_3()
    atividade_4()
}

fun atividade_1() {

    val nome_do_carro = "Fusca"
    println(nome_do_carro)
}

fun atividade_2() {
    println("Escreva o seu nome: ")
    val NomeDoUsuario = readlnOrNull()
    println("Olá, $NomeDoUsuario")
}

fun atividade_3() {
    println("Escreva o seu nome: ")
    val NomeDoUsuario = readlnOrNull()
    println("Olá, $NomeDoUsuario")
    println("Escreva sua Idade: ")
    val IdadeDoUsuario = readlnOrNull()
    println("Olá, $NomeDoUsuario, sua idade é $IdadeDoUsuario")
}

fun atividade_4() {

    //Retângulo
    println("Digite o valor da Base do Retângulo: ")
    val Base = readln().toInt()
    println("Digite o valor da Altura do Retângulo: ")
    val Altura = readln().toInt()
    val Retangulo = Base * Altura
    println("O valor do Retângulo é: $Retangulo")

    //Quadrado
    println("Digite o valor da Base do Quadrado: ")
    val Lado = readln().toInt()
    val Quadrado = Lado * Lado
    println("O valor do Quadrado é: $Quadrado")

    //Losango
    println("Digite o valor da Diagonal Maior e da Diagonal Menor:  ")
    val DiagonalMaior = readln().toInt()
    val DiagonalMenor = readln().toInt()
    val Losango = (DiagonalMaior * DiagonalMenor) / 2
    println("O valor do Losango é: $Losango")

    //trapézio
    println("Digite o valor da Base Maior do Trapézio: ")
    val BaseMaior = readln().toInt()
    println("Digite o valor da Base Menor do Trapézio: ")
    val BaseMenor = readln().toInt()
    println("Digite a Altura do Trapézio: ")
    val H = readln().toInt()
    val Trapezio = (BaseMaior + BaseMenor) * H / 2
    println("O valor do Trapézio é: $Trapezio")
    //Triângulo

    println("Digite o valor de Base e Altura: ")
    val BaseTriangulo = readln().toInt()
    val AlturaTriangulo = readln().toInt()
    val Triangulo = BaseTriangulo * AlturaTriangulo / 2
    println("O valor do Triângulo é: $Triangulo")

    //Círculo

    println("Digite o valor do Raio: ")
    val Raio = readln().toInt()
    val Circulo = PI * Raio * Raio
    println("O valor do Círculo é: $Circulo")

}