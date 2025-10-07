# Projeto: Gerenciador de Notícias com SWT

Este é um projeto acadêmico que implementa um gerenciador de notícias com interface gráfica, desenvolvido com a biblioteca **SWT (Standard Widget Toolkit)** em Java.

![Demonstração do Gerenciador de Notícias](https://i.imgur.com/PIQ5hAs.png)

## 1\. Sobre o Desafio

Este projeto foi desenvolvido como solução para a atividade prática ("Desafio de Código") da disciplina de **Orientação a Objetos (2° Período - 2025.2)**.

O objetivo principal era criar uma tela simples para visualização e gerenciamento de notícias, aplicando na prática os seguintes conceitos:

  - **Criação de interface gráfica com SWT.**
  - **Utilização de componentes visuais básicos** (tabelas, botões, campos de texto).
  - **Aplicação dos pilares de Programação Orientada a Objetos:** classes, atributos, métodos, encapsulamento e abstração.
  - **Implementação de funcionalidades essenciais**, como busca por texto e limpeza de filtros.

## 2\. Funcionalidades Implementadas

  - **Visualização em Tabela:** Exibe as notícias de forma organizada, com colunas para ID, Título, Autor, Categoria e Data.
  - **Operações CRUD:**
      - **[Adicionar]:** Abre uma nova janela para cadastrar uma notícia.
      - **[Editar]:** Permite modificar as informações de uma notícia selecionada.
      - **[Excluir]:** Remove uma notícia selecionada após confirmação.
  - **Busca e Filtragem:**
      - Campo para buscar notícias por **título**.
      - Seletor para filtrar notícias por **categoria**.
      - Botão **[Limpar Filtros]** para resetar a busca e a filtragem.
  - **Interface Nativa:** Utiliza componentes nativos do sistema operacional para uma melhor performance e aparência.
  - **Status Dinâmico:** Exibe o total de notícias cadastradas e mostra mensagens de status temporárias após cada ação do usuário (ex: "Notícia adicionada com sucesso\!").

## 3\. Conceitos de POO Aplicados

  - **Classes e Objetos:** O projeto é estruturado em duas classes principais:
      - `Noticia.java`: Modela o objeto "Notícia", contendo seus atributos e métodos (POJO).
      - `GerenciadorDeNoticiasSWT.java`: Orquestra a interface gráfica e a lógica de negócio.
  - **Abstração:** A classe `Noticia` abstrai a entidade do mundo real, representando suas características (título, autor, etc.) e comportamentos essenciais.
  - **Encapsulamento:** Os atributos da classe `Noticia` são privados e acessados por meio de métodos públicos (getters e setters), garantindo a integridade dos dados.

## 4\. Tecnologias Utilizadas

  - **Linguagem:** Java (versão 8 ou superior)
  - **Interface Gráfica:** SWT (Standard Widget Toolkit) - v4.29

## 5\. Estrutura do Projeto

```
/Gerenciador_de_Noticias
|
├── lib/
│   └── swt.jar                  # Biblioteca SWT para a interface gráfica
|
├── Noticia.java                 # Classe de modelo (POJO) que representa uma notícia
├── GerenciadorDeNoticiasSWT.java  # Classe principal que constrói e executa a aplicação
└── README.md                    # Este arquivo de documentação
```

## 6\. Como Executar o Projeto

### Pré-requisitos

  - **Java Development Kit (JDK)** instalado (versão 8 ou superior).

### Instruções de Execução

Os comandos devem ser executados a partir da pasta raiz do projeto (`/Gerenciador_de_Noticias`). Eles incluem o arquivo `swt.jar` no classpath para que a aplicação funcione corretamente.

**No Windows (usando `;` como separador):**

```bash
# 1. Compilar todos os arquivos .java
javac -cp "lib/swt.jar;." *.java

# 2. Executar a classe principal
java -cp "lib/swt.jar;." GerenciadorDeNoticiasSWT
```

**No Linux ou macOS (usando `:` como separador):**

```bash
# 1. Compilar todos os arquivos .java
javac -cp "lib/swt.jar:." *.java

# 2. Executar a classe principal
java -cp "lib/swt.jar:." GerenciadorDeNoticiasSWT
```

## 7\. Integrantes do Grupo

  - Daniela Regina Sales de Santana
  - Eduardo Mamedes Martiniano Monteiro
  - Erivan Alves da Silva Junior
  - Igor Nicholai Alencar de Menezes
  - Maria Vitória Lopes Sá de Oliveira
  - Maximus Luiz Lacerda Bragança
  - Saulo Rodrigues Gomes de Lima
  - Yuri Galindo França de Oliveira
