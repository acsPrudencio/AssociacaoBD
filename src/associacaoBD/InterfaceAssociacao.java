package associacaoBD;

public interface InterfaceAssociacao {

	// Calcula a frequência de um associado nas reuniões ocorridas durante um
	// determinado período, retornando um número entre 0 e 1 (ex: 0,6, indicando que
	// o associado participou de 60% das reuniões.
	public double calcularFrequencia(int codigoAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, AssociacaoNaoExistente;

	// Registra a frequência de um associado em uma reunião. Não deveria registrar participação em reuniões
	// acontecidas antes da sua filiação na associação.
	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel;

	// Registra o pagamento de uma taxa, em uma associação, dentro uma determinada
	// competência, para um associado. O valor a ser pago não pode ser menor que uma
	// parcela, embora não precise ser exatamente duas parcelas. Uma parcela de
	// R$20,00 por mês aceita um pagamento de R$30,00, sendo uma parcela completa e
	// um pedaço da próxima. Associados remidos não deveriam mais realizar
	// pagamentos de taxas administrativas vigentes em datas antes da sua remissão. Apenas
	// o ultimo pagamento pode ser menor que uma parcela, se for para quitar o ano.
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido;

	// Calcula o total de pagamentos realizado por um associado, em uma associação,
	// para uma taxa, que possui uma vigência, dentro de um certo período de tempo.
	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente;

	// Calcula o total de taxas previstas para um dado ano, em uma associação.
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente;

	// Adiciona uma associação a ser gerenciada.
	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido;

	// Adiciona um associado a uma associação.
	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido;

	// Adiciona uma reunião a uma associação.
	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido;

	// Adiciona uma taxa a uma associação.
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido;

	// Esvazia as tabelas do banco para permitir a execução dos testes.
	public void limparBanco();
}
