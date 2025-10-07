# Projeto: Gerenciador de Notícias - POO 2025.2

## Introdução

Este documento detalha a implementação de um "Gerenciador de Notícias", um projeto desenvolvido para a disciplina de Orientação a Objetos (2° Período - 2025.2).

O objetivo principal da atividade era criar uma tela simples para visualização e gerenciamento de notícias, aplicando conceitos fundamentais de Programação Orientada a Objetos (POO). O foco do trabalho foi a montagem de uma interface gráfica funcional e a correta aplicação de conceitos como classes, atributos, métodos e encapsulamento, sem a necessidade de implementar todas as funcionalidades complexas de um sistema real.

Como funcionalidade adicional, o projeto implementa um seletor de categorias para filtrar as notícias exibidas, enriquecendo a interação do usuário com a interface. Embora a sugestão inicial fosse o uso da biblioteca SWT, optou-se pela biblioteca **Swing**, que é parte padrão do Java, para a construção da interface gráfica.

## Requisitos da Classe `Noticia`

A base do sistema é a classe `Noticia`, que serve como modelo para representar os dados de uma notícia. Seus requisitos, conforme a especificação do projeto, são:

  * **Atributos:**

      * `id` (int): Identificador numérico único para cada notícia.
      * `titulo` (String): O título da notícia.
      * `autor` (String): O nome do autor da notícia.
      * `conteudo` (String): O texto completo da notícia.
      * `categoria` (String): A categoria da notícia (ex: Tecnologia, Esportes, Política).
      * `dataPublicacao` (LocalDate): A data de publicação. No código, foi utilizado o tipo `LocalDate` para uma melhor manipulação de datas, embora a especificação sugerisse uma `String` no formato "dd/MM/yyyy".

  * **Métodos:**

      * Um **construtor** que recebe todos os parâmetros para inicializar um objeto `Noticia`.
      * Métodos **Getters e Setters** para todos os atributos, garantindo o encapsulamento dos dados.
      * Um método `toString()` sobrescrito para exibir apenas o título da notícia na lista da interface gráfica.

## Explicação Detalhada do Código

O projeto foi estruturado em duas classes principais: `Noticia.java`, que representa o modelo de dados, e `GerenciadorDeNoticias.java`, que controla a lógica da aplicação e a interface gráfica.

### Conceitos de POO Aplicados

  * **Classes e Objetos**:

      * `Noticia`: Classe que modela uma notícia, com seus atributos e comportamentos.
      * `GerenciadorDeNoticias`: Classe principal que cria a janela (objeto `JFrame`) e gerencia os objetos `Noticia`.

  * **Encapsulamento**:

      * Os atributos da classe `Noticia` são declarados como `private`, e o acesso a eles é feito por meio de métodos públicos `get` e `set`, protegendo a integridade dos dados.

    <!-- end list -->

    ```java
    // Gerenciador_de_Noticias/Noticia.java
    public class Noticia {
        private int id;
        private String titulo;
        // ... outros atributos privados

        public Noticia(int id, String titulo, String autor, String conteudo, String categoria, LocalDate dataPublicacao) {
            this.id = id;
            this.titulo = titulo;
            // ... inicialização
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        // ... outros getters e setters
    }
    ```

  * **Abstração**:

      * A classe `Noticia` abstrai a complexidade de uma notícia real, representando-a apenas por seus atributos e métodos essenciais para o sistema. A classe `GerenciadorDeNoticias` utiliza essa abstração para manipular as notícias sem precisar conhecer os detalhes internos de como os dados são armazenados.

### A Interface Gráfica (GUI)

A interface foi construída com a biblioteca Swing e organizada da seguinte forma:

  * **Janela Principal (`JFrame`)**: Contêiner principal da aplicação.
  * **Painéis (`JPanel`)**: Utilizados para organizar os componentes em seções: busca, lista de notícias e painel de ações/status.
  * **Busca e Filtros**:
      * Um campo de texto (`JTextField`) para buscar notícias por título.
      * Um seletor (`JComboBox`) para filtrar as notícias por categoria.
      * Botões (`JButton`) para "Buscar" e "Limpar" os filtros.
  * **Lista de Notícias**:
      * Uma lista (`JList`) exibe os títulos das notícias cadastradas. A seleção de um item na lista habilita os botões de edição e exclusão.
  * **Ações e Status**:
      * Botões para "Adicionar", "Editar" e "Excluir" notícias.
      * Rótulos (`JLabel`) que mostram o total de notícias cadastradas e o status atual do sistema.

### Funcionalidades Principais

  * **Adicionar, Editar e Excluir Notícias**:

      * Ao clicar em "Adicionar" ou "Editar", uma caixa de diálogo (`JOptionPane`) é exibida com campos para preencher os dados da notícia. A exclusão é confirmada por meio de uma caixa de diálogo para evitar remoções acidentais.

    <!-- end list -->

    ```java
    // Gerenciador_de_Noticias/GerenciadorDeNoticias.java
    private void mostrarDialogoNoticia(Noticia noticia) {
        // ... (criação dos campos de texto e combobox)
        String title = noticia == null ? "Adicionar Notícia" : "Editar Notícia";
        int result = JOptionPane.showConfirmDialog(this, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (noticia == null) {
                // Lógica para adicionar nova notícia
            } else {
                // Lógica para atualizar notícia existente
            }
            atualizarLista(searchField.getText());
        }
    }
    ```

  * **Busca e Filtragem**:

      * Qualquer alteração no campo de busca ou no filtro de categoria aciona o método `atualizarLista`, que refaz a consulta na lista de notícias em memória e atualiza a exibição na tela. A busca é *case-insensitive* (não diferencia maiúsculas de minúsculas).

    <!-- end list -->

    ```java
    // Gerenciador_de_Noticias/GerenciadorDeNoticias.java
    private void atualizarLista(String filtroTitulo) {
        listModel.clear();
        String categoriaSelecionada = (String) categoryFilter.getSelectedItem();

        List<Noticia> noticiasFiltradas = todasAsNoticias.stream()
            .filter(n -> n.getTitulo().toLowerCase().contains(filtroTitulo.toLowerCase()))
            .filter(n -> "Todas".equals(categoriaSelecionada) || n.getCategoria().equals(categoriaSelecionada))
            .collect(Collectors.toList());

        noticiasFiltradas.forEach(listModel::addElement);
        totalNewsLabel.setText("Total de notícias: " + todasAsNoticias.size());
    }
    ```

## Como Executar o Programa

Como o projeto utiliza a biblioteca Swing, que é parte do Java padrão, não é necessário instalar bibliotecas externas como o SWT.

### Pré-requisitos

  * **Java Development Kit (JDK)**: É necessário ter o JDK instalado. Recomenda-se a versão 17 ou superior.

### Compilação e Execução

Assumindo que os arquivos `Noticia.java` e `GerenciadorDeNoticias.java` estão dentro de um diretório chamado `Gerenciador_de_Noticias`:

1.  **Abra um terminal** ou prompt de comando.
2.  **Navegue até o diretório pai** que contém a pasta `Gerenciador_de_Noticias`.
3.  **Compile os arquivos `.java`** com o seguinte comando:
    ```bash
    javac Noticia.java && javac GerenciadorDeNoticias.java
    ```
4.  **Execute o programa** com o comando abaixo, que especifica o nome completo da classe principal:
    ```bash
    java GerenciadorDeNoticias
    ```

Após a execução, a janela do "Gerenciador de Notícias" deverá aparecer na tela. O programa é compatível com qualquer sistema operacional que possua uma JVM (Java Virtual Machine) instalada.

## Integrantes do Grupo

O trabalho foi realizado em equipe pelos seguintes integrantes, listados em ordem alfabética:

  * Daniela Regina Sales de Santana
  * Eduardo Mamedes Martiniano Monteiro
  * Erivan Alves da Silva Junior
  * Igor Nicholai Alencar de Menezes
  * Maria Vitória Lopes Sá de Oliveira
  * Maximus Luiz Lacerda Bragança
  * Saulo Rodrigues Gomes de Lima
