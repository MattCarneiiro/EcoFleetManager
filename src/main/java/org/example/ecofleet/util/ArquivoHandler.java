package org.example.ecofleet.util;

import org.example.ecofleet.model.CaminhaoEletrico;
import org.example.ecofleet.model.CarroAutonomo;
import org.example.ecofleet.model.StatusVeiculo;
import org.example.ecofleet.model.Veiculo;
import org.example.ecofleet.model.records.FichaTecnica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArquivoHandler {

    // DEFINE O CAMINHO AQUI UMA VEZ SÓ
    private static final Path CAMINHO_ARQUIVO = Path.of("dados_frota.csv");



    public void salvarDados(List<Veiculo> frota) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Veiculo v : frota) {
            sb.append(montarLinha(v)).append("\n");
        }
        // Usa a constante CAMINHO_ARQUIVO
        Files.writeString(CAMINHO_ARQUIVO, sb.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    private String montarLinha(Veiculo v) {
        String placa = v.getFicha().placa();
        String marca = v.getFicha().marca();
        String modelo = v.getFicha().modelo();
        int ano = v.getFicha().ano();
        int bateria = v.getBateria();
        double km = v.getQuilometragem();
        String status = v.getStatus().toString();

        switch (v) {
            case CarroAutonomo c -> {
                return String.format(Locale.US, "1;%s;%s;%s;%d;%d;%.2f;%s;%s",
                        placa, marca, modelo, ano, bateria, km, status, c.getVersaoSoftware());
            }
            case CaminhaoEletrico t -> {
                return String.format(Locale.US, "2;%s;%s;%s;%d;%d;%.2f;%s;%.2f",
                        placa, marca, modelo, ano, bateria, km, status, t.getEspace());
            }
            default -> throw new IllegalStateException("Valor inesperado: " + v);
        }
    }



    public List<Veiculo> lerDados() {
        // Usa a constante CAMINHO_ARQUIVO aqui também
        if (!Files.exists(CAMINHO_ARQUIVO)) {
            return new ArrayList<>();
        }

        List<Veiculo> frotaRecuperada = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);

            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;

                Veiculo v = desmontarLinha(linha);
                if (v != null) {
                    frotaRecuperada.add(v);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler dados: " + e.getMessage());
        }

        return frotaRecuperada;
    }

    private Veiculo desmontarLinha(String linha) {
        String[] dados = linha.split(";");

        // Recupera dados básicos
        int tipo = Integer.parseInt(dados[0]);
        String placa = dados[1];
        String marca = dados[2];
        String modelo = dados[3];
        int ano = Integer.parseInt(dados[4]);
        int bateria = Integer.parseInt(dados[5]);
        double km = Double.parseDouble(dados[6]);
        String statusTexto = dados[7];
        String extra = dados[8];

        FichaTecnica ficha = new FichaTecnica(marca, modelo, ano, placa);
        Veiculo veiculoRecuperado = null;

        if (tipo == 1) {
            veiculoRecuperado = new CarroAutonomo(ficha, extra);
        } else if (tipo == 2) {
            veiculoRecuperado = new CaminhaoEletrico(ficha, Double.parseDouble(extra));
        }

        if (veiculoRecuperado != null) {
            veiculoRecuperado.setBateria(bateria);
            veiculoRecuperado.setQuilometragem(km);
            veiculoRecuperado.setStatus(StatusVeiculo.valueOf(statusTexto));
        }

        return veiculoRecuperado;
    }
}