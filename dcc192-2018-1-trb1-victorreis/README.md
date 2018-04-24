# Trabalho de Laboratório de Programação Sistemas Web

## Identificação do aluno: nome, matrícula e curso
Nome: `Victor Crisóstomo Cruz Reis`

Matrícula: `201176037`

Curso: `Sistemas de Informação`

## Texto explicando o cenário para o qual o sistema foi desenvolvido

## Modelo de dados utilizado e Levantamento dos campos necessários para a construção das telas
* **MODELOS (getters e setters omitidos):****
    * ITEM: 
        * `Integer id`
        * `String nome`
        * `Double valor`
    * MESA: 
        * `Integer id`
        * `String nome`
    * COMANDA: 
        * `Integer id`
        * `Mesa mesa`
            * Uma comanda está relacionada a apenas uma mesa, mas uma mesa pode estar relacionada à várias comandas.
        * `String nomeClienteResponsavel`
        * `String horaAbertura`
        * `String horaFechamento` (inicialmente nulo)
        * `Map<Item, Integer> itens` (mapemento de Itens e suas quantidades)
            * Cada `COMANDA` pode apontar para zero ou mais `ITEM` desde que os objetos não se repitam. Para saber a quantidade basta usar a função `get(KEY) -> VALUE`, em que `KEY` é um `ITEM` e `VALUE` é um inteiro que representa a quantidade de itens.

* **PERSISTENCIA:**
    * VARIAVEIS:
        * `ArrayList<Comanda> comandas` (lista de `COMANDAs`)
        * `ArrayList<Mesa> mesas` (lista de `MESAs`)
        * `ArrayList<Item> itens` (lista de `ITEMs`)
        * `Integer autoIncrementoComandas` (para que cada comanda tenha um ID único)
            * Como `mesas` e `itens` são criados apenas na inicialização, não há necessidade de colocar auto incremento para eles.
    * FUNÇÕES de Inicialização:
        * `ArrayList<Item> getInstanceItens()` (inicializa a lista de `ITENs` e retorna-a)
        * `ArrayList<Mesa> getInstanceMesas()` (inicializa a lista de `MESAs` e retorna-a)
        * `ArrayList<Comandas> getInstanceComandas()` (inicializa a lista de `COMANDAs` e retorna-a)
    * FUNÇÕES Auxiliares:
        * `Mesa getMesaById(Object id)` (dado um Inteiro ou uma String retorna uma `MESA` ou `null`)
        * `Item getItemById(Object id)` (dado um Inteiro ou uma String retorna um `ITEM` ou `null`)
        * `Comanda getComandaById(Object id)` (dado um Inteiro ou uma String retorna uma `COMANDA` ou `null`)
    * FUNÇÕES CRUD:
        * `boolean criarNovaComanda(String idMesa, String nomeCliente, String horaAbertura)`
        * `boolean abrirOuFecharComanda(String id)`
            * Se estiver aberta a função fecha a conta e vice e versa.
        * `Integer adicionarItensNaComanda(String idComanda, String idItem, String quantidade)`
            * Pode ser usada para adicionar `ITEMs` e para remover (caso quantidade seja negativa). Se chegar a zero o item é removido automaticamente.
        * `boolean removerItensNaComanda(String idComanda, String idItem)`
            * Remove todos os `ITEM` de uma `COMANDA`, independente de quantidade.

* **COMANDA SERVLET:**
    * `void doGet(HttpServletRequest request, HttpServletResponse response)`
    * `void doPost(HttpServletRequest request, HttpServletResponse response)`
    * `void processRequest(HttpServletRequest request, HttpServletResponse response)`
        * recebe as requisições `GET` e `POST` e para cada `ServletPath` chama uma função
    * `void index(HttpServletRequest request, HttpServletResponse response)`
        * Encaminha o RequestDispatcher para `index.jsp`
    * `void criarComanda(HttpServletRequest request, HttpServletResponse response)`
        * Encaminha o RequestDispatcher para `criar-comanda.jsp` caso seja uma requisição `GET`
        * redireciona para `listar-comandas.html` caso seja uma requisição referente à criação de uma nova `COMANDA`
    * `void listarComandas(HttpServletRequest request, HttpServletResponse response)`
        * Encaminha o RequestDispatcher para `listar-comandas.jsp`
    * `void abrirOuFecharComanda(HttpServletRequest request, HttpServletResponse response)`
        * redireciona para `listar-comandas.html` caso seja uma requisição referente à ABERTURA/FECHAMENTO de uma `COMANDA`
    * `void listarItensCadastrados(HttpServletRequest request, HttpServletResponse response)`
        * Encaminha o RequestDispatcher para `listar-itens-cadastrados.jsp`
    * `void listarItensComanda(HttpServletRequest request, HttpServletResponse response)`
        * Recebe a requisição `POST` referente ao formulário de adicionar `ITEMs` na `COMANDA` e chama `Persistencia.adicionarItensNaComanda()`
        * Caso o parametro ID não seja passado redireciona-se para `listar-comandas.html`
        * Encaminha o RequestDispatcher para `listar-itens-comanda.jsp`
    * `void removerItensComanda(HttpServletRequest request, HttpServletResponse response)`
        * Recebe a requisição POST referente ao botão de remover `ITEMs` na `COMANDA` e chama `Persistencia.removerItensNaComanda()`
        * Caso o parametro ID não seja passado redireciona-se para `listar-comandas.html`
        * Encaminha o RequestDispatcher para `listar-itens-comanda.jsp`

## Descrição sucinta dos pontos importantes do funcionamento da interface
* Os arquivos `jpsf/cabecalho.jpsf` e `jpsf/rodape.jpsf` são adicionados em todos os JSPs.
    * `jpsf/cabecalho.jpsf` contem os metadados HTML, um menu e um título dinâmico (obtido por parametro)
* O arquivo `pagina-de-erro.jsp` foi usado para mostrar erros na tela
* Os formulários serem redirecionam para a própria página de origem, assim cada uma das funções em ComandaServlet tratam as requisições de sua respectiva página.
* Todas as páginas acessíveis sem a necessidade de passagem de parâmetros se encontram no menu.

## Discussão dos pontos que apresentaram maior dificuldade de implementação
* Sintaxe do `<c:forEach></c:forEach>`, tive de olhar na internet tanto para o `ArrayList` quanto para o `HashMap`.
* No JSP não consegui identificar o tipo de uma variável do lado do JSP, então o `CTRL+ESPAÇO` não funciona.
* Não consegui pegar um parâmetro passado por GET e fazer um redirecionamento pra mesma página de forma que a URL ficasse limpa.
    * Por exemplo listar-itens-comanda.html?id=1 eu queria redirecionar para listar-itens-comanda.html, mas ainda mantendo o id como parâmetro. 
    * Ao invés de usar uma Âncora no botão LISTAR eu poderia fazer um formulário e enviar por POST, mas ficaria muito gambiarrado, então deixei o parametro por GET mesmo.

## Pontos onde podem ser realizadas melhorias futuras
* Substituição da classe Persistencia por um banco real.
* Utilizar algum framework CSS.
* CRUD de Itens e de Mesas, já que estão estáticos.
* Registrar o horário de inserção e de remoção de itens numa comanda através de um log.
* Formatar as datas/horas.
