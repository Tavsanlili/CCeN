# Cinema Ticket Reservation System

A Spring Boot modular monolith boilerplate for a cinema ticket reservation system.

## Package Tree

```text
src/main/java/com/cinema/ticketsystem
├── TicketSystemApplication.java
├── booking
│   ├── controller
│   ├── entity
│   │   ├── Reservation.java
│   │   └── Seat.java
│   ├── repository
│   │   ├── ReservationRepository.java
│   │   └── SeatRepository.java
│   └── service
├── catalog
│   ├── controller
│   ├── entity
│   │   ├── Movie.java
│   │   └── Session.java
│   ├── repository
│   │   ├── MovieRepository.java
│   │   └── SessionRepository.java
│   └── service
├── common
│   ├── dto
│   └── exception
└── config
    └── DataSeeder.java
```

## Run

```bash
mvn spring-boot:run
```

Swagger UI is available at `/swagger-ui.html`, and the H2 console is available at `/h2-console`.
