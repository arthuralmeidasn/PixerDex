package br.com.pixeldex.application;

import java.util.Scanner;
import br.com.pixeldex.model.Pixel;
import br.com.pixeldex.structures.BST;
import br.com.pixeldex.structures.ListaEncadeada;

public class Main {

    // Instâncias estáticas
    private static ListaEncadeada<Pixel> lista = new ListaEncadeada<>();
    private static BST<Pixel> indice = new BST<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- PixelDex CLI Iniciada ---");
        System.out.println("Digite HELP para ver comandos ou EXIT para sair.");

        boolean running = true;

        while (running) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String command = parts[0].toUpperCase();

            try {
                switch (command) {
                    case "EXIT":
                        running = false;
                        System.out.println("Encerrando PixelDex...");
                        break;

                    case "HELP":
                        printHelp();
                        break;

                    case "ADD":
                        if (parts.length < 5) {
                            System.out.println("Erro: Uso incorreto. Tente: ADD <id> <nome> <raridade> <poder>");
                        } else {
                            int id = Integer.parseInt(parts[1]);
                            String nome = parts[2];
                            String raridade = parts[3];
                            int poder = Integer.parseInt(parts[4]);

                            Pixel novoPixel = new Pixel(id, nome, raridade, poder);
                            
                            lista.addLast(novoPixel);
                            indice.insert(novoPixel);
                            
                            System.out.println("OK: " + novoPixel + " adicionado");
                        }
                        break;

                    case "LIST":
                        if (lista.isEmpty()) {
                            System.out.println("Colecao vazia.");
                        } else {
                            int index = 0;
                            for (Pixel p : lista) {
                                System.out.println("[" + index++ + "] " + p);
                            }
                        }
                        break;

                    case "FIND":
                        if (parts.length < 2) {
                            System.out.println("Uso: FIND <nome>");
                        } else {
                            String nomeBusca = parts[1];
                            Pixel chaveBusca = new Pixel(0, nomeBusca, "", 0);
                            Pixel resultado = indice.search(chaveBusca);
                            
                            if (resultado != null) {
                                System.out.println("Encontrado: " + resultado);
                            } else {
                                System.out.println("Pixel nao encontrado no indice.");
                            }
                        }
                        break;

                    case "RANGE":
                        if (parts.length < 3) {
                            System.out.println("Uso: RANGE <letra_inicio> <letra_fim>");
                        } else {
                            String inicio = parts[1];
                            String fim = parts[2];
                            Pixel min = new Pixel(0, inicio, "", 0);
                            Pixel max = new Pixel(0, fim + "zzzz", "", 0);
                            
                            System.out.println("--- Pixels entre " + inicio + " e " + fim + " ---");
                            indice.range(min, max);
                        }
                        break;

                    case "LIST-INDEX":
                        System.out.println("--- Indice Global (BST) ---");
                        if (parts.length > 1 && parts[1].equalsIgnoreCase("PREORDER")) {
                            indice.preOrder();
                        } else if (parts.length > 1 && parts[1].equalsIgnoreCase("POSTORDER")) {
                            indice.postOrder();
                        } else {
                            indice.inOrder();
                        }
                        break;

                    case "TREE-INFO":
                        System.out.println("Altura: " + indice.height());
                        System.out.println("Nos: " + indice.countNodes());
                        System.out.println("Folhas: " + indice.countLeaves());
                        break;

                    case "REVERSE":
                        lista.reverse();
                        System.out.println("OK: Lista invertida.");
                        break;
                    
                    case "UNIQUE":
                        lista.unique();
                        System.out.println("OK: Duplicatas removidas da lista.");
                        break;

                    case "REMOVE-COLLECTION":
                        if (parts.length < 2) {
                            System.out.println("Uso: REMOVE-COLLECTION <index>");
                        } else {
                            int indexRemover = Integer.parseInt(parts[1]);
                            try {
                                Pixel removido = lista.removeAt(indexRemover);
                                System.out.println("OK: " + removido + " removido da colecao.");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Erro: Indice fora dos limites.");
                            }
                        }
                        break;

                    case "MOVE":
                        if (parts.length < 3) {
                            System.out.println("Uso: MOVE <indice_origem> <indice_destino>");
                        } else {
                            int origem = Integer.parseInt(parts[1]);
                            int destino = Integer.parseInt(parts[2]);
                            try {
                                lista.move(origem, destino);
                                System.out.println("OK: Elemento movido.");
                            } catch (Exception e) {
                                System.out.println("Erro ao mover: " + e.getMessage());
                            }
                        }
                        break;

                    case "SLICE":
                        if (parts.length < 3) {
                            System.out.println("Uso: SLICE <inicio> <fim>");
                        } else {
                            int inicio = Integer.parseInt(parts[1]);
                            int fim = Integer.parseInt(parts[2]);
                            try {
                                ListaEncadeada<Pixel> fatia = lista.slice(inicio, fim);
                                System.out.println("--- Slice [" + inicio + " a " + fim + "] ---");
                                int idx = 0;
                                for (Pixel p : fatia) {
                                    System.out.println("[" + idx++ + "] " + p);
                                }
                            } catch (Exception e) {
                                System.out.println("Erro: Intervalo invalido.");
                            }
                        }
                        break;

                    case "REMOVE-INDEX":
                        if (parts.length < 2) {
                            System.out.println("Uso: REMOVE-INDEX <nome>");
                        } else {
                            String nomeRemover = parts[1];
                            Pixel chaveRemover = new Pixel(0, nomeRemover, "", 0);
                            indice.remove(chaveRemover);
                            System.out.println("OK: " + nomeRemover + " removido do índice (se existia).");
                        }
                        break;

                    default:
                        System.out.println("Comando desconhecido. Digite HELP.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID ou Poder devem ser numeros inteiros.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace(); 
            }
        }
        scanner.close();
    }

    private static void printHelp() {
        System.out.println("=== Comandos Disponiveis ===");
        System.out.println("ADD <id> <nome> <raridade> <poder>");
        System.out.println("LIST");
        System.out.println("FIND <nome>");
        System.out.println("RANGE <inicio> <fim>");
        System.out.println("LIST-INDEX [INORDER|PREORDER|POSTORDER]");
        System.out.println("REMOVE-INDEX <nome>");
        System.out.println("REVERSE | UNIQUE");
        System.out.println("EXIT");
    }
}