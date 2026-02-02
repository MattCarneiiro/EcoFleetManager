package org.example.ecofleet.model;

import org.example.ecofleet.model.records.FichaTecnica;

public class CaminhaoEletrico extends Veiculo{
    double espace;
    public CaminhaoEletrico(FichaTecnica fichaTecnica, double espace){
        super(fichaTecnica);
        this.espace = espace;
    }
    @Override
    public void exibirDetalhes(){
        System.out.println(getStatus());
        System.out.println(getQuilometragem());
        System.out.println(getBateria());
        System.out.println(getFicha());
        System.out.println(espace);
    }

    public double getEspace() {
        return espace;
    }

    public void setEspace(double espace) {
        this.espace = espace;
    }

    @Override
    public void carregar(int tempo){
        int bateria = getBateria();
        bateria += tempo*0.5;

        if(bateria > 100){
            setBateria(100);
            System.out.println("Bateria do Carro está em 100%");
            return;
        }
        setBateria(bateria);
    }
}
