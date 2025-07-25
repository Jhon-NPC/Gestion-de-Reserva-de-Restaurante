package com.example.restaurante.app.negocio;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurante.app.modelo.Reserva;
import com.example.restaurante.app.persistencia.ReservaRepository;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository repo;

    public Reserva registrarReserva(String cliente, int personas, LocalDateTime fechaHora){
        if(cliente == null || cliente.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del cliente no debe estar vacío");
        }

        if (personas <= 0) {
        throw new IllegalArgumentException("La cantidad de personas debe ser mayor a cero");
        }

        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora no pueden estar vacías");
        }
        
        if(personas>8){
            throw new IllegalArgumentException("Máximo 8 personas por reserva");
        }

        if(fechaHora.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("No se puede reservar fechas pasadas");
        }

        if(fechaHora.toLocalTime().isBefore(LocalTime.of(12, 0)) || fechaHora.toLocalTime().isAfter(LocalTime.of(23, 0))){
            throw new IllegalArgumentException("Horario permitido de 12:00 a 23:00");
        }
        
        List<Reserva> clientes = repo.buscarCliente(cliente);
        for (Reserva r : clientes) {
            if(r.getFechaHora().equals(fechaHora) && r.getActiva()){
                throw new IllegalArgumentException("Ya existe una reserva activa para el mismo cliente");
            }
        }

        Reserva reserva = new Reserva(cliente, personas, fechaHora);
        repo.guardar(reserva);
        return reserva;
    }

    public List<Reserva> listarReservas(){
        return repo.listar().stream().filter(Reserva::getActiva).toList();
    }

    public List<Reserva> buscarPorCliente(String cliente){
        return repo.buscarCliente(cliente);
    }

    public boolean cancelarReserva(String id){
        return repo.buscarID(id).map(r -> { r.setActiva(false); return true;}).orElse(false);
    }
}