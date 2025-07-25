package com.example.restaurante.app.presentacion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.restaurante.app.modelo.Reserva;
import com.example.restaurante.app.negocio.ReservaService;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.restaurante.app")
public class ReservaApp implements CommandLineRunner{

	@Autowired
	private ReservaService service;
	private final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		SpringApplication.run(ReservaApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while(true){
			System.out.println("\n--- SISTEMA DE RESERVAS ---");
            System.out.println("1. Registrar nueva reserva");
            System.out.println("2. Listar reservas activas");
            System.out.println("3. Buscar reservas por cliente");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Salir");
            System.out.print("Seleccione: \n");
            String opcion = scanner.nextLine();

			try{
				switch(opcion) {
                    case "1" -> registrar();
                    case "2" -> listar();
                    case "3" -> buscar();
                    case "4" -> cancelar();
                    case "5" -> System.exit(0);
                    default -> System.out.println("Opción inválida");
				}
			}catch (Exception e){
				System.out.println("Error: " +e.getMessage());
			}
		}
	}

	private void registrar(){
		try{
		System.out.print("\nNombre del cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Número de personas: ");
        int personas = Integer.parseInt(scanner.nextLine());
        System.out.print("Fecha y hora (dia/mes/año hora:minutos): ");
        String fecha = scanner.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fecha, formato);

        Reserva reserva = service.registrarReserva(cliente, personas, fechaHora);
        System.out.println("Reserva registrada con el ID: " + reserva.getId());
		}catch(IllegalArgumentException e){
			System.out.println("\nError: " +e.getMessage());
		}
	}

	private void listar() {
        List<Reserva> reservas = service.listarReservas();
        if(reservas.isEmpty()){
            System.out.println("No hay reservas activas");
        }else{
            for( Reserva r : reservas){
                System.out.println(r.getId() + " - " + r.getCliente() + " - " + r.getFechaHora() + " - " + r.getCantPersonas() + " personas");
			}
		}
	}

	private void buscar() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        List<Reserva> reservas = service.buscarPorCliente(nombre);
        if(reservas.isEmpty()){
            System.out.println("No hay reservas activas para el cliente");
        }else{
			for( Reserva r : reservas){
                System.out.println(r.getId() + " - " + r.getFechaHora() + " - " + r.getCantPersonas() + " personas");
			}
		}
    }

	private void cancelar() {
        System.out.print("ID de la reserva: ");
        String id = scanner.nextLine();
        if(service.cancelarReserva(id)){
            System.out.println("Reserva cancelada correctamente");
        }else{
            System.out.println("No se encontró la reserva");
        }
    }
}