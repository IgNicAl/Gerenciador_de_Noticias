# Projeto: Gerenciador de Notícias - POO 2025.2

## 1\. Introdução

Este é um projeto acadêmico desenvolvido para a disciplina de **Orientação a Objetos (2° Período - 2025.2)**. O objetivo foi criar uma aplicação de desktop para o gerenciamento de notícias, utilizando a biblioteca **SWT (Standard Widget Toolkit)** para a construção da interface gráfica.

A aplicação aplica conceitos fundamentais de Programação Orientada a Objetos (POO), como classes, encapsulamento e métodos, para permitir ao usuário visualizar, adicionar, editar, excluir e filtrar notícias de forma eficiente.

## 2\. Funcionalidades Principais

  - **Visualização em Tabela:** Exibe as notícias de forma organizada.
  - **Operações CRUD:** Permite Adicionar, Editar e Excluir notícias.
  - **Busca e Filtragem:** Funcionalidade para buscar notícias por título e filtrar por categoria.
  - **Interface Nativa:** Utiliza componentes nativos do sistema operacional para uma melhor performance e aparência.
  - **Status Dinâmico:** Exibe o total de notícias e mensagens de status temporárias após cada ação.

## 3\. Tecnologias Utilizadas

  - **Linguagem:** Java
  - **Interface Gráfica:** SWT (Standard Widget Toolkit) - v4.29

## 4\. Estrutura do Projeto

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

## 5\. Como Executar o Projeto

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

Após a execução dos comandos, a janela do "Gerenciador de Notícias" deverá aparecer na tela.

## 6\. Integrantes do Grupo

  - Daniela Regina Sales de Santana
  - Eduardo Mamedes Martiniano Monteiro
  - Erivan Alves da Silva Junior
  - Igor Nicholai Alencar de Menezes
  - Maria Vitória Lopes Sá de Oliveira
  - Maximus Luiz Lacerda Bragança
  - Saulo Rodrigues Gomes de Lima
