# 🍽️ Sistema de reservas para restaurante

Este proyecto es una aplicación de consola desarrollada en **Java con Spring Boot**, que permite gestionar reservas de manera sencilla para un restaurante.

A través de un menú interactivo, los usuarios pueden registrar nuevas reservas, listar las reservas activas, buscar por cliente o cancelar una reserva existente. Todo el manejo de datos se realiza en memoria utilizando una simulación de base de datos con `HashMap`.

---

## 🧩 Estructura del proyecto

El sistema está organizado en capas:

- **Modelo**: Define la clase `Reserva`, que representa cada solicitud de reserva.
- **Persistencia**: Implementa un repositorio que simula una base de datos con operaciones CRUD básicas.
- **Negocio**: Contiene la lógica de validación y reglas del sistema.
- **Presentación**: Ofrece una interfaz por consola para interactuar con el sistema.

---

## ✅ Validaciones implementadas

El sistema incluye varias reglas de negocio para garantizar reservas válidas:

- El nombre del cliente no puede estar vacío.
- La cantidad de personas debe ser entre 1 y 8.
- Las reservas solo pueden hacerse para fechas y horas futuras.
- El horario permitido es entre **12:00** y **23:00**.
- No se permite más de una reserva activa por cliente en el mismo horario.

---
