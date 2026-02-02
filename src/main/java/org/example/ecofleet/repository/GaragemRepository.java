package org.example.ecofleet.repository;
import org.example.ecofleet.model.Veiculo;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class GaragemRepository {
    private final Map<String, Veiculo> bancoDeDados;

    public GaragemRepository() {
        this.bancoDeDados = new HashMap<>();
    }
    public void salvar(Veiculo veiculo) {
        String placa = veiculo.getFicha().placa();

        if (bancoDeDados.containsKey(placa)) {
            System.out.println("Sistema já possui essa placa!");
            return;
        }
        bancoDeDados.put(placa, veiculo);
    }

    public Veiculo buscar(String placa){
        return bancoDeDados.get(placa);
    }

    public List<Veiculo> listarTodos(){
        return new ArrayList<>(bancoDeDados.values());
    }

}
