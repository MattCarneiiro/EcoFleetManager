package org.example.ecofleet.model;

import org.example.ecofleet.model.records.FichaTecnica;

public class CarroAutonomo  extends Veiculo{

    String versaoSoftware;
    public CarroAutonomo(FichaTecnica fichaTecnica, String versaoSoftware){
        super(fichaTecnica);
        this.versaoSoftware = versaoSoftware;
    }
    @Override
    public void exibirDetalhes() {
        System.out.println(getStatus());
        System.out.println(getQuilometragem());
        System.out.println(getBateria());
        System.out.println(getFicha());
        System.out.println(versaoSoftware);
    }


    public String getVersaoSoftware() {
        return versaoSoftware;
    }

    public void setVersaoSoftware(String versaoSoftware) {
        this.versaoSoftware = versaoSoftware;
    }
}

