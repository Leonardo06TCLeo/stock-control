import java.util.ArrayList;
import java.util.Scanner;

class Produto {
    int codigo;
    String nome;
    double preco;
    int quantidade;

    Produto(int codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    void mostrarProduto() {
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Preço: R$ " + preco);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("--------------------------");
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Produto> estoque = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===== CONTROLE DE ESTOQUE =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Vender produto");
            System.out.println("4 - Adicionar quantidade ao estoque");
            System.out.println("5 - Remover produto");
            System.out.println("6 - Mostrar produtos com estoque baixo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    listarProdutos();
                    break;

                case 3:
                    venderProduto();
                    break;

                case 4:
                    adicionarEstoque();
                    break;

                case 5:
                    removerProduto();
                    break;

                case 6:
                    mostrarEstoqueBaixo();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    static void cadastrarProduto() {
        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Produto produtoExistente = buscarProdutoPorCodigo(codigo);

        if (produtoExistente != null) {
            System.out.println("Já existe um produto com esse código.");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço do produto: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade inicial: ");
        int quantidade = scanner.nextInt();

        Produto novoProduto = new Produto(codigo, nome, preco, quantidade);
        estoque.add(novoProduto);

        System.out.println("Produto cadastrado com sucesso.");
    }

    static void listarProdutos() {
        if (estoque.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n===== PRODUTOS CADASTRADOS =====");

        for (Produto produto : estoque) {
            produto.mostrarProduto();
        }
    }

    static Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : estoque) {
            if (produto.codigo == codigo) {
                return produto;
            }
        }

        return null;
    }

    static void venderProduto() {
        System.out.print("Digite o código do produto vendido: ");
        int codigo = scanner.nextInt();

        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade vendida: ");
        int quantidadeVendida = scanner.nextInt();

        if (quantidadeVendida <= 0) {
            System.out.println("Quantidade inválida.");
        } else if (quantidadeVendida > produto.quantidade) {
            System.out.println("Estoque insuficiente.");
        } else {
            produto.quantidade -= quantidadeVendida;
            double total = quantidadeVendida * produto.preco;

            System.out.println("Venda realizada com sucesso.");
            System.out.println("Total da venda: R$ " + total);
            System.out.println("Quantidade restante: " + produto.quantidade);
        }
    }

    static void adicionarEstoque() {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();

        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade para adicionar: ");
        int quantidade = scanner.nextInt();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
        } else {
            produto.quantidade += quantidade;
            System.out.println("Estoque atualizado com sucesso.");
            System.out.println("Nova quantidade: " + produto.quantidade);
        }
    }

    static void removerProduto() {
        System.out.print("Digite o código do produto que deseja remover: ");
        int codigo = scanner.nextInt();

        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
        } else {
            estoque.remove(produto);
            System.out.println("Produto removido com sucesso.");
        }
    }

    static void mostrarEstoqueBaixo() {
        System.out.print("Mostrar produtos com quantidade menor ou igual a: ");
        int limite = scanner.nextInt();

        boolean encontrou = false;

        System.out.println("\n===== PRODUTOS COM ESTOQUE BAIXO =====");

        for (Produto produto : estoque) {
            if (produto.quantidade <= limite) {
                produto.mostrarProduto();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum produto com estoque baixo.");
        }
    }
}