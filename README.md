# PixelDex - Projeto de Estrutura de Dados

Projeto desenvolvido para a disciplina de Estrutura de Dados. O sistema gerencia uma coleção de Pixels (criaturas 8-bit) utilizando estruturas dinâmicas personalizadas.

## Tecnologias

- **Linguagem:** Java 17+
- **Build System:** Gradle (Kotlin DSL)
- **IDE:** VS Code

## Estruturas de Dados Implementadas

1.  **Lista Encadeada Genérica (`ListaEncadeada<T>`)**:
    - Usada para armazenar a coleção do usuário.
    - Suporta inserção (início, fim), remoção, e utilitários como `reverse` e `unique`.

2.  **Árvore Binária de Busca (`BST<T>`)**:
    - Usada como Índice Global para buscas rápidas.
    - Suporta `insert`, `search`, `remove` e consultas por intervalo (`range`).

## Decisões de Projeto

### Chave da BST (Documentação)
Conforme solicitado nos requisitos, a chave escolhida para a indexação na Árvore Binária de Busca (BST) foi o **NOME** do Pixel.
- **Critério de Ordenação:** Ordem Lexicográfica (Alfabética).
- **Critério de Desempate:** Em caso de nomes idênticos, o desempate é feito pelo **ID** (crescente).

## Como Rodar

1.  Certifique-se de ter o JDK 17+ instalado.
2.  No terminal, execute:

```bash
./gradlew run --console=plain -q