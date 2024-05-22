import java.util.Map;

public class FlightManager {
    private final Map<String, Vuelo> flights;

    public FlightManager() {
        flights = Map.of(
                "ORDS-0001", new Vuelo(Destinos.US.name(), Destinos.UK.name(), "2024-04-30 14:00"),
                "LAXS-0002", new Vuelo(Destinos.US.name(), Destinos.MX.name(), "2024-05-01 12:00"),
                "CDGS-0003", new Vuelo(Destinos.CN.name(), Destinos.US.name(), "2024-05-02 10:00"),
                "TYOS-0004", new Vuelo(Destinos.JP.name(), Destinos.US.name(), "2024-05-03 15:00"),
                "CUNM-0005", new Vuelo(Destinos.MX.name(), Destinos.US.name(), "2024-05-04 13:00"),
                "LDNS-0006", new Vuelo(Destinos.UK.name(), Destinos.US.name(), "2024-05-05 11:00"),
                "HNDJ-0007", new Vuelo(Destinos.JP.name(), Destinos.CN.name(), "2024-05-06 16:00"),
                "CDGM-0008", new Vuelo(Destinos.CN.name(), Destinos.MX.name(), "2024-05-07 09:00"),
                "JFKU-0009", new Vuelo(Destinos.US.name(), Destinos.JP.name(), "2024-05-08 17:00"),
                "CUNU-0010", new Vuelo(Destinos.MX.name(), Destinos.UK.name(), "2024-05-09 08:00")
        );
    }

    public void displayFlightPanel() {
        System.out.println("Panel de vuelos:");
        for (Map.Entry<String, Vuelo> entry : flights.entrySet()) {
            Vuelo vuelo = entry.getValue();
            System.out.println("| ID de vuelo: " + entry.getKey() +
                    " | Origen: " + vuelo.getId().substring(0, 2) +
                    " | Destino: " + vuelo.getId().substring(2, 4) +
                    " | Precio: $" + vuelo.getTicketPrice() +
                    " |");
        }
    }

    public void buyTicket(String flightId, String nombreCompleto, String numeroTelefono, String correoElectronico, String pais, String seatSelection) {
        Vuelo vuelo = flights.get(flightId);
        if (vuelo != null) {
            vuelo.buyTicket(nombreCompleto, numeroTelefono, correoElectronico, pais, seatSelection);
            System.out.println("Boleto comprado exitosamente. ID de boleto: " + vuelo.getTicket(seatSelection).getTicketId());
        } else {
            System.out.println("No se encontró el vuelo especificado.");
        }
    }

    public Ticket retrieveTicketDetails(String ticketId) {
        for (Vuelo vuelo : flights.values()) {
            Ticket ticket = vuelo.getTicket(ticketId);
            if (ticket != null) {
                System.out.println("\nDetalles del boleto para el ticket ID " + ticketId + ":");
                System.out.println("ID de boleto: " + ticket.getTicketId());
                System.out.println("ID de vuelo: " + ticket.getFlightId());
                System.out.println("Nombre completo: " + ticket.getUserInfo().getNombreCompleto());
                System.out.println("Número de teléfono: " + ticket.getUserInfo().getNumeroTelefono());
                System.out.println("Correo electrónico: " + ticket.getUserInfo().getCorreoElectronico());
                System.out.println("País: " + ticket.getUserInfo().getPais());
                System.out.println("Fecha de vuelo: " + ticket.getFecha());
                System.out.println("Precio del boleto: $" + ticket.getTicketPrice());
                System.out.println("\nInformación del vuelo:");
                System.out.println("ID de vuelo: " + vuelo.getId());
                System.out.println("Origen: " + vuelo.getId().substring(0, 2));
                System.out.println("Destino: " + vuelo.getId().substring(2, 4));
                System.out.println("Fecha y hora de salida: " + vuelo.getFechaHora());
                System.out.println("Puerta de salida: " + vuelo.getGate());
                System.out.println("Precio del boleto: $" + vuelo.getTicketPrice());
                return ticket;
            }
        }
        System.out.println("No se encontró ningún ticket con el ID especificado.");
        return null;
    }



    public Vuelo getFlightById(String flightId) {
        return flights.get(flightId);
    }
}
