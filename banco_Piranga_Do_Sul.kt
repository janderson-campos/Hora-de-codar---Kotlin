import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.floor

data class ReservaQuarto(
    val hospede: String,
    val quarto: Int,
    val tipoQuarto: String,
    val diarias: Int,
    val valorDiaria: Double,
    val subtotal: Double,
    val taxaServico: Double,
    val total: Double,
    val criadoEm: LocalDateTime
)

data class HospedeCadastro(
    var nome: String,
    val criadoEm: LocalDateTime
)

data class EventoReserva(
    val auditorio: String,
    val cadeirasAdicionais: Int,
    val convidados: Int,
    val dia: String,
    val horaInicio: Int,
    val horaFim: Int,
    val duracao: Int,
    val empresa: String,
    val garcons: Int,
    val custoGarcons: Double,
    val cafeLitros: Double,
    val aguaLitros: Double,
    val salgadosUn: Int,
    val custoBuffet: Double,
    val totalEvento: Double,
    val criadoEm: LocalDateTime
)

data class AppState(
    val hotel: String,
    val usuario: String,
    val quartosOcupados: BooleanArray,
    val reservas: MutableList<ReservaQuarto>,
    val hospedes: MutableList<HospedeCadastro>,
    val eventos: MutableList<EventoReserva>,
    var receitaHospedagem: Double,
    var receitaEventos: Double
)

fun main() {
    inicio()
}

fun inicio() {
    val hotel = "Hotel Terabithia"
    println("Bem-vindo ao $hotel")
    val usuario = lerTextoNaoVazio("Informe seu nome: ")

    val autenticado = autenticar(usuario)
    if (!autenticado) return

    val state = AppState(
        hotel = hotel,
        usuario = usuario,
        quartosOcupados = BooleanArray(20) { false },
        reservas = mutableListOf(),
        hospedes = mutableListOf(),
        eventos = mutableListOf(),
        receitaHospedagem = 0.0,
        receitaEventos = 0.0
    )

    println("Bem-vindo ao Hotel ${state.hotel}, ${state.usuario}. É um imenso prazer ter você por aqui!")
    menuPrincipal(state)
}

fun autenticar(usuario: String): Boolean {
    val senhaObrigatoria = "2678"
    var tentativas = 0

    while (tentativas < 3) {
        val senha = lerTextoNaoVazio("Informe a senha: ")
        if (senha == senhaObrigatoria) return true
        tentativas++
        val restante = 3 - tentativas
        if (restante > 0) {
            println("Senha incorreta. Tentativas restantes: $restante")
        }
    }

    println("Sistema bloqueado. Número máximo de tentativas excedido.")
    return false
}

fun menuPrincipal(state: AppState) {
    while (true) {
        println()
        println("Escolha uma opção:")
        println("1-Reservas de Quartos")
        println("2-Cadastro de Hóspedes")
        println("3-Eventos")
        println("4-Ar-Condicionado")
        println("5-Abastecimento")
        println("6-Relatórios Operacionais")
        println("7-Sair")

        val escolha = lerInt("Opção: ") { it in 1..7 }
        when (escolha) {
            1 -> reservasDeQuartos(state)
            2 -> cadastroHospedes(state)
            3 -> eventos(state)
            4 -> arCondicionado(state)
            5 -> abastecimentoDeAutomoveis(state)
            6 -> relatoriosOperacionais(state)
            7 -> {
                sairDoHotel(state)
                return
            }
            else -> erroOpcaoMenu()
        }
    }
}

fun erroOpcaoMenu() {
    println("Opção inválida.")
}

fun sairDoHotel(state: AppState) {
    println("Muito obrigado e até logo, ${state.usuario}.")
}

fun reservasDeQuartos(state: AppState) {
    println()
    println("[Reservas]")

    val valorDiaria = lerDouble("Informe o valor da diária: ") { it > 0.0 }
    val diarias = lerInt("Informe a quantidade de diárias (1-30): ") { it in 1..30 }

    if (valorDiaria <= 0.0 || diarias !in 1..30) {
        println("Valor inválido, ${state.usuario}")
        return
    }

    val nomeHospede = lerTextoNaoVazio("Informe o nome do hóspede: ")
    val tipo = lerOpcaoTexto("Tipo de quarto (S/E/L): ", setOf("S", "E", "L"))

    val (fator, tipoDescricao) = when (tipo) {
        "S" -> 1.00 to "Standard"
        "E" -> 1.35 to "Executivo"
        else -> 1.65 to "Luxo"
    }

    var quarto = lerInt("Escolha um quarto (1-20): ") { it in 1..20 }
    while (state.quartosOcupados[quarto - 1]) {
        println("Quarto já está ocupado.")
        val livres = (1..20).filter { !state.quartosOcupados[it - 1] }
        if (livres.isEmpty()) {
            println("Não há quartos livres no momento.")
            return
        }
        println("Quartos livres: ${livres.joinToString(", ")}")
        quarto = lerInt("Escolha outro quarto (1-20): ") { it in 1..20 }
    }

    val subtotal = valorDiaria * diarias * fator
    val taxa = subtotal * 0.10
    val total = subtotal + taxa

    println()
    println("Resumo:")
    println("Hóspede: $nomeHospede")
    println("Quarto: $quarto ($tipoDescricao)")
    println("Subtotal: ${formatarMoeda(subtotal)}")
    println("Taxa de serviço (10%): ${formatarMoeda(taxa)}")
    println("Total: ${formatarMoeda(total)}")
    println()

    val confirma = lerSimNao("${state.usuario}, confirma a reserva? (S/N): ")
    if (!confirma) {
        println("Reserva não efetuada.")
        return
    }

    state.quartosOcupados[quarto - 1] = true
    state.reservas.add(
        ReservaQuarto(
            hospede = nomeHospede,
            quarto = quarto,
            tipoQuarto = tipoDescricao,
            diarias = diarias,
            valorDiaria = valorDiaria,
            subtotal = subtotal,
            taxaServico = taxa,
            total = total,
            criadoEm = LocalDateTime.now()
        )
    )
    state.receitaHospedagem += total

    println("Reserva efetuada com sucesso.")
    exibirMapaQuartos(state.quartosOcupados)
}

fun exibirMapaQuartos(ocupados: BooleanArray) {
    println()
    println("Mapa de Quartos (4x5):")
    var numero = 1
    for (linha in 0 until 4) {
        val itens = mutableListOf<String>()
        for (coluna in 0 until 5) {
            val status = if (ocupados[numero - 1]) "O" else "L"
            itens.add(String.format("%2d:%s", numero, status))
            numero++
        }
        println(itens.joinToString("  "))
    }
}

fun cadastroHospedes(state: AppState) {
    while (true) {
        println()
        println("[Cadastro de Hóspedes]")
        println("1-Cadastrar")
        println("2-Pesquisar exato")
        println("3-Pesquisar prefixo")
        println("4-Listar ordenado (A-Z)")
        println("5-Atualizar cadastro")
        println("6-Remover cadastro")
        println("7-Voltar")

        val escolha = lerInt("Opção: ") { it in 1..7 }
        when (escolha) {
            1 -> cadastrarHospede(state)
            2 -> pesquisarHospedeExato(state)
            3 -> pesquisarHospedePrefixo(state)
            4 -> listarHospedesOrdenado(state)
            5 -> atualizarHospede(state)
            6 -> removerHospede(state)
            7 -> return
            else -> erroOpcaoMenu()
        }
    }
}

fun cadastrarHospede(state: AppState) {
    if (state.hospedes.size >= 15) {
        println("Máximo de cadastros atingido")
        return
    }

    val nome = lerTextoNaoVazio("Nome do hóspede: ")
    val duplicado = state.hospedes.any { it.nome.equals(nome, ignoreCase = true) }
    if (duplicado) {
        println("Hóspede já cadastrado")
        return
    }

    state.hospedes.add(HospedeCadastro(nome = nome, criadoEm = LocalDateTime.now()))
    println("Operação realizada com sucesso")
}

fun pesquisarHospedeExato(state: AppState) {
    val nome = lerTextoNaoVazio("Nome para pesquisa exata: ")
    val encontrado = state.hospedes.any { it.nome.equals(nome, ignoreCase = true) }
    if (encontrado) {
        println("Hóspede ($nome) foi encontrado")
    } else {
        println("Hóspede não encontrado")
    }
}

fun pesquisarHospedePrefixo(state: AppState) {
    val prefixo = lerTextoNaoVazio("Prefixo: ")
    val resultados = state.hospedes
        .map { it.nome }
        .filter { it.startsWith(prefixo, ignoreCase = true) }
        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it })

    if (resultados.isEmpty()) {
        println("Hóspede não encontrado")
        return
    }

    println("Resultados:")
    resultados.forEachIndexed { index, nome ->
        println("[${index + 1}] $nome")
    }
}

fun listarHospedesOrdenado(state: AppState): List<Pair<Int, HospedeCadastro>> {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val lista = state.hospedes
        .mapIndexed { idx, hospede -> idx to hospede }
        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.second.nome })

    if (lista.isEmpty()) {
        println("Nenhum hóspede cadastrado.")
        return lista
    }

    println("Listagem (A-Z):")
    lista.forEachIndexed { pos, par ->
        val hospede = par.second
        println("[${pos + 1}] ${hospede.nome} | ${hospede.criadoEm.format(formatter)}")
    }
    return lista
}

fun atualizarHospede(state: AppState) {
    val lista = listarHospedesOrdenado(state)
    if (lista.isEmpty()) return

    val indice = lerInt("Informe o índice para atualizar: ") { it in 1..lista.size }
    val indiceReal = lista[indice - 1].first

    val novoNome = lerTextoNaoVazio("Novo nome: ")
    val duplicado = state.hospedes.anyIndexed { i, h -> i != indiceReal && h.nome.equals(novoNome, ignoreCase = true) }
    if (duplicado) {
        println("Hóspede já cadastrado")
        return
    }

    state.hospedes[indiceReal].nome = novoNome
    println("Operação realizada com sucesso")
}

fun removerHospede(state: AppState) {
    val lista = listarHospedesOrdenado(state)
    if (lista.isEmpty()) return

    val indice = lerInt("Informe o índice para remover: ") { it in 1..lista.size }
    val indiceReal = lista[indice - 1].first

    state.hospedes.removeAt(indiceReal)
    println("Operação realizada com sucesso")
}

fun <T> List<T>.anyIndexed(predicate: (Int, T) -> Boolean): Boolean {
    for (i in indices) {
        if (predicate(i, this[i])) return true
    }
    return false
}

fun eventos(state: AppState) {
    println()
    println("[Eventos]")

    val convidados = lerInt("Convidados: ") { it in 0..350 }
    if (convidados < 0 || convidados > 350) {
        println("Número de convidados inválido")
        return
    }

    val (auditorio, cadeirasAdicionais) = selecionarAuditorio(convidados)
    if (auditorio.isEmpty()) {
        println("Número de convidados inválido")
        return
    }

    println("Auditório selecionado: $auditorio${if (cadeirasAdicionais > 0) " ($cadeirasAdicionais cadeiras adicionais)" else ""}")

    val dia = lerTextoNaoVazio("Dia: ").trim().lowercase()
    val horaInicio = lerInt("Hora inicial: ") { it in 0..23 }
    val duracao = lerInt("Duração: ") { it in 1..12 }
    val horaFim = horaInicio + duracao

    val (abertura, fechamento) = janelaDia(dia)
    val disponivel = horaInicio >= abertura && horaFim <= fechamento

    if (!disponivel) {
        println("Auditório indisponível.")
        return
    }

    val conflito = state.eventos.any { e ->
        e.auditorio.equals(auditorio, ignoreCase = true) &&
                e.dia.equals(dia, ignoreCase = true) &&
                sobrepoeHorario(horaInicio, horaFim, e.horaInicio, e.horaFim)
    }
    if (conflito) {
        println("Auditório indisponível.")
        return
    }

    val empresa = lerTextoNaoVazio("Empresa: ")
    println("Status: Auditório reservado.")

    val baseGarcons = ceil(convidados / 12.0).toInt()
    val reforco = floor(duracao / 2.0).toInt()
    val totalGarcons = baseGarcons + reforco
    val custoGarcons = totalGarcons * duracao * 10.50

    println()
    println("Garçons necessários: $totalGarcons")
    println("Custo com garçons: ${formatarMoeda(custoGarcons)}")

    val cafe = convidados * 0.2
    val agua = convidados * 0.5
    val salgados = convidados * 7

    val custoCafe = cafe * 0.80
    val custoAgua = agua * 0.40
    val custoSalgados = (salgados / 100.0) * 34.00
    val custoBuffet = custoCafe + custoAgua + custoSalgados

    println()
    println("Buffet:")
    println("Café: ${formatar1(cafe)} L")
    println("Água: ${formatar1(agua)} L")
    println("Salgados: $salgados un")
    println("Custo buffet: ${formatarMoeda(custoBuffet)}")

    val totalEvento = custoGarcons + custoBuffet

    println()
    println("Total do evento: ${formatarMoeda(totalEvento)}")
    val confirmar = lerSimNao("Confirmar reserva? (S/N): ")
    if (!confirmar) {
        println("Reserva não efetuada.")
        return
    }

    state.eventos.add(
        EventoReserva(
            auditorio = auditorio,
            cadeirasAdicionais = cadeirasAdicionais,
            convidados = convidados,
            dia = dia,
            horaInicio = horaInicio,
            horaFim = horaFim,
            duracao = duracao,
            empresa = empresa,
            garcons = totalGarcons,
            custoGarcons = custoGarcons,
            cafeLitros = cafe,
            aguaLitros = agua,
            salgadosUn = salgados,
            custoBuffet = custoBuffet,
            totalEvento = totalEvento,
            criadoEm = LocalDateTime.now()
        )
    )
    state.receitaEventos += totalEvento

    println("Reserva efetuada com sucesso.")
}

fun selecionarAuditorio(convidados: Int): Pair<String, Int> {
    return when {
        convidados <= 150 -> "Laranja" to 0
        convidados <= 220 -> "Laranja" to (convidados - 150)
        convidados <= 350 -> "Colorado" to 0
        else -> "" to 0
    }
}

fun janelaDia(dia: String): Pair<Int, Int> {
    val fimDeSemana = dia == "sabado" || dia == "domingo"
    return if (fimDeSemana) 7 to 15 else 7 to 23
}

fun sobrepoeHorario(inicio1: Int, fim1: Int, inicio2: Int, fim2: Int): Boolean {
    return inicio1 < fim2 && inicio2 < fim1
}

fun arCondicionado(state: AppState) {
    println()
    println("[Ar-Condicionado]")

    val orcamentos = mutableListOf<Pair<String, Double>>()

    while (true) {
        val empresa = lerTextoNaoVazio("Empresa: ")
        val valorPorAparelho = lerDouble("Valor por aparelho: ") { it >= 0.0 }
        val quantidade = lerInt("Quantidade: ") { it >= 0 }
        val descontoPercentual = lerDouble("Desconto (%): ") { it >= 0.0 }
        val minimoDesconto = lerInt("Mínimo para desconto: ") { it >= 0 }
        val deslocamento = lerDouble("Deslocamento: ") { it >= 0.0 }

        val bruto = valorPorAparelho * quantidade
        val desconto = if (quantidade >= minimoDesconto) bruto * (descontoPercentual / 100.0) else 0.0
        val total = bruto - desconto + deslocamento

        println("O serviço de $empresa custará ${formatarMoeda(total)}")
        println("Total: ${formatarMoeda(total)}")

        orcamentos.add(empresa to total)

        val mais = lerSimNao("Deseja informar novos dados, ${state.usuario}? (S/N): ")
        if (!mais) break
        println()
    }

    if (orcamentos.size < 2) {
        println("É necessário informar ao menos duas empresas.")
        return
    }

    val melhor = orcamentos.minBy { it.second }
    val pior = orcamentos.maxBy { it.second }
    val diffPercent = if (pior.second == 0.0) 0.0 else ((pior.second - melhor.second) / pior.second) * 100.0

    println("Melhor orçamento: ${melhor.first} — ${formatarMoeda(melhor.second)}")
    println("Pior orçamento: ${pior.first} — ${formatarMoeda(pior.second)}")
    println("Diferença percentual entre melhor e pior proposta: ${formatar1(diffPercent)}%")
}

fun abastecimentoDeAutomoveis(state: AppState) {
    println()
    println("[Abastecimento]")

    val wayneAlcool = lerDouble("Wayne Oil -> Álcool: ") { it > 0.0 }
    val wayneGasolina = lerDouble("Wayne Oil -> Gasolina: ") { it > 0.0 }
    val starkAlcool = lerDouble("Stark Petrol -> Álcool: ") { it > 0.0 }
    val starkGasolina = lerDouble("Stark Petrol -> Gasolina: ") { it > 0.0 }

    println()
    println("Wayne Oil -> Álcool: ${formatar2(wayneAlcool)} | Gasolina: ${formatar2(wayneGasolina)}")
    println("Stark Petrol -> Álcool: ${formatar2(starkAlcool)} | Gasolina: ${formatar2(starkGasolina)}")
    println()

    val tanque = 42.0

    val melhorWayne = melhorCombustivel(wayneAlcool, wayneGasolina)
    val totalWayne = tanque * if (melhorWayne == "Álcool") wayneAlcool else wayneGasolina

    val melhorStark = melhorCombustivel(starkAlcool, starkGasolina)
    val totalStark = tanque * if (melhorStark == "Álcool") starkAlcool else starkGasolina

    println("Wayne Oil: melhor opção = $melhorWayne | Total (42L) = ${formatarMoeda(totalWayne)}")
    println("Stark Petrol: melhor opção = $melhorStark | Total (42L) = ${formatarMoeda(totalStark)}")
    println()

    val melhor = if (totalWayne <= totalStark) Triple("Wayne Oil", melhorWayne, totalWayne) else Triple("Stark Petrol", melhorStark, totalStark)
    println("${state.usuario}, é mais barato abastecer com ${melhor.second.lowercase()} no posto ${melhor.first}.")
}

fun melhorCombustivel(alcool: Double, gasolina: Double): String {
    return if (alcool <= 0.70 * gasolina) "Álcool" else "Gasolina"
}

fun relatoriosOperacionais(state: AppState) {
    println()
    println("[Relatórios Operacionais]")

    val totalReservas = state.reservas.size
    val ocupados = state.quartosOcupados.count { it }
    val taxaOcupacao = (ocupados / 20.0) * 100.0
    val totalHospedes = state.hospedes.size
    val totalEventos = state.eventos.size

    val receitaHospedagem = state.receitaHospedagem
    val receitaEventos = state.receitaEventos
    val receitaTotal = receitaHospedagem + receitaEventos

    val linhas = listOf(
        "Total de reservas de quartos confirmadas" to totalReservas.toString(),
        "Taxa de ocupação atual" to "${formatar1(taxaOcupacao)}%",
        "Quantidade de hóspedes cadastrados" to totalHospedes.toString(),
        "Quantidade de eventos confirmados" to totalEventos.toString(),
        "Receita acumulada: hospedagem" to formatarMoeda(receitaHospedagem),
        "Receita acumulada: eventos" to formatarMoeda(receitaEventos),
        "Receita acumulada: total geral" to formatarMoeda(receitaTotal)
    )

    val col1 = maxOf(30, linhas.maxOf { it.first.length })
    val col2 = maxOf(12, linhas.maxOf { it.second.length })

    println("=".repeat(col1 + col2 + 5))
    println(String.format("%-${col1}s | %${col2}s", "Indicador", "Valor"))
    println("-".repeat(col1 + col2 + 5))
    linhas.forEach { (k, v) ->
        println(String.format("%-${col1}s | %${col2}s", k, v))
    }
    println("=".repeat(col1 + col2 + 5))
}

fun formatarMoeda(valor: Double): String {
    val nf = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return nf.format(valor)
}

fun formatar1(valor: Double): String {
    return String.format(Locale("pt", "BR"), "%.1f", valor)
}

fun formatar2(valor: Double): String {
    return String.format(Locale("pt", "BR"), "%.2f", valor)
}

fun lerTextoNaoVazio(mensagem: String): String {
    while (true) {
        print(mensagem)
        val texto = readln().trim()
        if (texto.isNotEmpty()) return texto
        println("Entrada inválida.")
    }
}

fun lerInt(mensagem: String, valida: (Int) -> Boolean): Int {
    while (true) {
        print(mensagem)
        val valor = readln().trim().toIntOrNull()
        if (valor != null && valida(valor)) return valor
        println("Entrada inválida.")
    }
}

fun lerDouble(mensagem: String, valida: (Double) -> Boolean): Double {
    while (true) {
        print(mensagem)
        val raw = readln().trim().replace(",", ".")
        val valor = raw.toDoubleOrNull()
        if (valor != null && valida(valor)) return valor
        println("Entrada inválida.")
    }
}

fun lerOpcaoTexto(mensagem: String, opcoes: Set<String>): String {
    while (true) {
        print(mensagem)
        val valor = readln().trim().uppercase()
        if (opcoes.contains(valor)) return valor
        println("Entrada inválida.")
    }
}

fun lerSimNao(mensagem: String): Boolean {
    while (true) {
        print(mensagem)
        val valor = readln().trim().uppercase()
        if (valor == "S") return true
        if (valor == "N") return false
        println("Entrada inválida.")
    }
}
