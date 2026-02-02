package org.example.ecofleet.service;

import org.example.ecofleet.model.CaminhaoEletrico;
import org.example.ecofleet.model.CarroAutonomo;
import org.example.ecofleet.model.Veiculo;
import org.example.ecofleet.model.records.FichaTecnica;
import org.example.ecofleet.repository.GaragemRepository;
import org.example.ecofleet.util.ArquivoHandler;

import javax.lang.model.util.ElementScanner6;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class GestaoFrotaService {
    private final GaragemRepository repositorio;

    public GestaoFrotaService(){
        this.repositorio = new GaragemRepository();
        ArquivoHandler arquivo = new ArquivoHandler();
        List<Veiculo> frotaSalva = arquivo.lerDados();

        for(Veiculo v : frotaSalva){
            repositorio.salvar(v);
        }
    }
    public void cadastrarVeiculo(String marca, String modelo, int ano, String placa, int tipo, String extra){
        FichaTecnica ficha = new FichaTecnica(marca, modelo, ano, placa);

        Veiculo novoVeiculo = null;

        if(tipo == 1){
            novoVeiculo = new CarroAutonomo(ficha, extra);
        }
        else if(tipo == 2){
            double carga = Double.parseDouble(extra);
            novoVeiculo = new CaminhaoEletrico(ficha, carga);

        }

        if( novoVeiculo != null){
            repositorio.salvar(novoVeiculo);
            System.out.println("Veiculo salvo com sucesso!");
        }
        else {
            System.out.println("Erro ao salvar veiculo");
        }
    }
    public List<Veiculo> listarVeiculoscomBateriaBaixa(){
        return repositorio.listarTodos().stream().filter(n -> n.getBateria()< 20).toList();
    }

    public List<Veiculo> listarVeiculoporAno(){
        return repositorio.listarTodos().stream().sorted(Comparator.comparingInt(Veiculo::getAno)).toList();
    }
    public List<Veiculo> listarTodos() {
        return repositorio.listarTodos();
    }
    public void carregarVeiculo(String placa, int tempo) throws InterruptedException {
        Veiculo v = repositorio.buscar(placa);

        if(v ==null){
            return;
        }
        System.out.println("Iniciando Carregamento, continue  usando o sistema! ");
        CompletableFuture.runAsync(()-> {
            try {
                v.carregar(tempo);
                TimeUnit.SECONDS.sleep(tempo);
                System.out.println("A carga acabou! \n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

