package com.example.restaurante.app.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.example.restaurante.app.modelo.Reserva;

@Repository
public class ReservaRepository {
    private Map<String,Reserva> baseDatos = new HashMap<>();

    public void guardar(Reserva reserva){
        baseDatos.put(reserva.getId(), reserva);
    }

    public List<Reserva> listar(){
        return new ArrayList<>(baseDatos.values());
    }

    public Optional<Reserva> buscarID(String id){
        return Optional.ofNullable(baseDatos.get(id));
    }
    
    public List<Reserva> buscarCliente(String cliente){
        return baseDatos.values().stream().filter(r -> r.getCliente().equalsIgnoreCase(cliente)).toList(); 
    }
}