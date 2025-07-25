package com.example.restaurante.app.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reserva {
    private String id;
    private String cliente;
    private int cantPersonas;
    private LocalDateTime fechaHora;
    private boolean activa;

    public Reserva(String cliente, int cantPersonas, LocalDateTime fechaHora){
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.cantPersonas = cantPersonas;
        this.fechaHora = fechaHora;
        this.activa = true;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getCliente(){
        return cliente;
    }
    public void setCliente(String cliente){
        this.cliente = cliente;
    }

    public int getCantPersonas(){
        return cantPersonas;
    }
    public void setCantPersonas(int cantPersonas){
        this.cantPersonas = cantPersonas;
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }
    
    public boolean getActiva(){
        return activa;
    }
    public void setActiva(boolean activa){
        this.activa = activa;
    }
}