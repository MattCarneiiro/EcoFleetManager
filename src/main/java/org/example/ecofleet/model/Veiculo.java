package org.example.ecofleet.model;

import org.example.ecofleet.model.records.FichaTecnica;

import static org.example.ecofleet.model.StatusVeiculo.DISPONIVEL;

public abstract class Veiculo implements Recarregavel {
    private FichaTecnica ficha;
    private StatusVeiculo status;
    private int bateria;
    private double quilometragem;

    public Veiculo(FichaTecnica ficha){
        this.ficha = ficha;
        this.status = DISPONIVEL;
        this.bateria = 50;
        this.quilometragem = 0;
    }
    public void setBateria(int NovaCarga){
        if(NovaCarga > 100 || NovaCarga<0){
            System.out.println("Valor Inválido!");
            return;
        }
        bateria = NovaCarga;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public FichaTecnica getFicha() {
        return ficha;
    }

    public void setFicha(FichaTecnica ficha) {
        this.ficha = ficha;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public int getBateria() {
        return bateria;
    }
    public abstract void exibirDetalhes();
    @Override
    public void carregar(int tempo){
        bateria += tempo;
        if(bateria > 100){
            bateria = 100;
            System.out.println("Bateria do Carro "+ficha.placa()+ "está em 100%");
        }
    }
    public int getAno(){
        return this.ficha.ano();
    }
}

