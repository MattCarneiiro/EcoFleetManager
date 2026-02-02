package org.example.ecofleet.view;

import org.example.ecofleet.service.GestaoFrotaService;
import org.example.ecofleet.model.Veiculo;
import java.util.Scanner;
import java.io.IOException;
import org.example.ecofleet.util.ArquivoHandler;

public class MenuPrincipal implements Menu {

    private final GestaoFrotaService service = new GestaoFrotaService();
    private final Scanner scanner;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
    }

    String base =  """
                    -------- ECO FLEET MANAGER --------
                    1 - Cadastrar Novo Veículo
                    2 - Listar Todos Veículos
                    3 - Pesquisar Veículo
                    4 - Atualizar Status
                    5 - Carregar Veículo (Assíncrono)
                    6 - Relatórios e Filtros
                    0 - Sair
                    -----------------------------------
                    Digite uma Opção:
                    """;

    @Override
    public void executar() throws InterruptedException {
        boolean rodando = true;
        while (rodando) {
            System.out.println(base);
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o Enter

            switch (opcao) {
                case 1 -> cadastrarUI();
                case 2 -> listarUI();
                case 3 -> System.out.println("Em breve: Pesquisa...");
                case 4 -> System.out.println("Em breve: Atualização...");
                case 5 -> carregarUI();
                case 6 -> relatoriosUI();
                case 0 -> {
                    System.out.println("Salvando dados...");
                    ArquivoHandler handler = new ArquivoHandler();
                    try {
                        handler.salvarDados(service.listarTodos());
                        System.out.println("Dados salvos com sucesso!");
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
                    }
                    System.out.println("Encerrando sistema...");
                    rodando = false;
                }
                default -> System.out.println("Opção Inválida! Tente novamente.");
            }

            if (rodando) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private void cadastrarUI() {
        System.out.println("\n--- NOVO CADASTRO ---");

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.println("Tipo: [1] Carro Autônomo | [2] Caminhão Elétrico");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        String dadoExtra = "";

        if (tipo == 1) {
            System.out.print("Versão do Software (ex: v3.5): ");
            dadoExtra = scanner.nextLine();
        } else if (tipo == 2) {
            System.out.print("Capacidade de Carga (Ton): ");
            dadoExtra = scanner.nextLine();
        }

        service.cadastrarVeiculo(marca, modelo, ano, placa, tipo, dadoExtra);
    }

    private void listarUI() {
        System.out.println("\n--- FROTA ATUAL ---");
        var frota = service.listarTodos();

        if (frota.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado na garagem.");
        } else {
            frota.forEach(Veiculo::exibirDetalhes);
        }
    }

    private void relatoriosUI() {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Veículos com Bateria Baixa (<20%)");
        System.out.println("2. Veículos Ordenados por Ano");
        System.out.print("Escolha uma opção: ");

        int subOpcao = scanner.nextInt();
        scanner.nextLine();

        if (subOpcao == 1) {
            service.listarVeiculoscomBateriaBaixa().forEach(Veiculo::exibirDetalhes);
        } else if (subOpcao == 2) {
            service.listarVeiculoporAno().forEach(Veiculo::exibirDetalhes);
        } else {
            System.out.println("Opção inválida.");
        }
    }


    private void carregarUI() throws InterruptedException {
        System.out.println("\n--- CARREGAR VEÍCULO ---");
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.print("Tempo de carga (segundos): ");
        int tempo = scanner.nextInt();
        scanner.nextLine();


        service.carregarVeiculo(placa, tempo);
    }
}