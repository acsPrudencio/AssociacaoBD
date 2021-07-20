package associacaoBD;

public interface InterfaceAssociacao {

	// Calcula a frequ�ncia de um associado nas reuni�es ocorridas durante um
	// determinado per�odo, retornando um n�mero entre 0 e 1 (ex: 0,6, indicando que
	// o associado participou de 60% das reuni�es.
	public double calcularFrequencia(int codigoAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, AssociacaoNaoExistente;

	// Registra a frequ�ncia de um associado em uma reuni�o. N�o deveria registrar participa��o em reuni�es
	// acontecidas antes da sua filia��o na associa��o.
	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel;

	// Registra o pagamento de uma taxa, em uma associa��o, dentro uma determinada
	// compet�ncia, para um associado. O valor a ser pago n�o pode ser menor que uma
	// parcela, embora n�o precise ser exatamente duas parcelas. Uma parcela de
	// R$20,00 por m�s aceita um pagamento de R$30,00, sendo uma parcela completa e
	// um peda�o da pr�xima. Associados remidos n�o deveriam mais realizar
	// pagamentos de taxas administrativas vigentes em datas antes da sua remiss�o. Apenas
	// o ultimo pagamento pode ser menor que uma parcela, se for para quitar o ano.
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido;

	// Calcula o total de pagamentos realizado por um associado, em uma associa��o,
	// para uma taxa, que possui uma vig�ncia, dentro de um certo per�odo de tempo.
	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente;

	// Calcula o total de taxas previstas para um dado ano, em uma associa��o.
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente;

	// Adiciona uma associa��o a ser gerenciada.
	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido;

	// Adiciona um associado a uma associa��o.
	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido;

	// Adiciona uma reuni�o a uma associa��o.
	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido;

	// Adiciona uma taxa a uma associa��o.
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido;

	// Esvazia as tabelas do banco para permitir a execu��o dos testes.
	public void limparBanco();
}
