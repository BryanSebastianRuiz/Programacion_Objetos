import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightManager manager = new FlightManager();

        boolean running = true;
        while (running) {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar panel de vuelos");
            System.out.println("2. Comprar boleto para un vuelo");
            System.out.println("3. Recuperar detalles de un boleto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    manager.displayFlightPanel();
                    break;
                case 2:
                    buyTicket(scanner, manager);
                    break;
                case 3:
                    retrieveTicketDetails(scanner, manager);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }

        scanner.close();
    }

    private static void buyTicket(Scanner scanner, FlightManager manager) {
        System.out.println("\nIngrese su nombre completo:");
        String nombreCompleto = scanner.nextLine();

        System.out.println("Ingrese su número de teléfono:");
        String numeroTelefono = scanner.nextLine();

        System.out.println("Ingrese su correo electrónico:");
        String correoElectronico = scanner.nextLine();

        System.out.println("Ingrese su país:");
        String pais = scanner.nextLine();

        System.out.println("Ingrese el ID de vuelo para comprar el boleto:");
        String flightId = scanner.nextLine();

        Vuelo vuelo = manager.getFlightById(flightId);
        if (vuelo != null) {
            System.out.println("Mapa del avión:");
            Map<String, String> planeMap = vuelo.getPlaneMap();
            for (Map.Entry<String, String> entry : planeMap.entrySet()) {
                String row = entry.getKey() + " |" + entry.getValue() + "|";
                row = row.replace("O", "OOO").replace("X", "XXX");
                System.out.println(row);
            }

            System.out.println("\nAsientos disponibles (O) y ocupados (X):");
            for (Map.Entry<String, String> entry : planeMap.entrySet()) {
                String row = entry.getValue();
                StringBuilder formattedRow = new StringBuilder();
                for (int i = 0; i < row.length(); i++) {
                    char seatStatus = row.charAt(i);
                    if (seatStatus == 'O') {
                        formattedRow.append("O");
                    } else {
                        formattedRow.append("X");
                    }
                }
                System.out.println(entry.getKey() + " |" + formattedRow + "|");
            }

            System.out.println("\nSeleccione un asiento (fila-sentado, por ejemplo A-01):");
            String seatSelection = scanner.nextLine();
            manager.buyTicket(flightId, nombreCompleto, numeroTelefono, correoElectronico, pais, seatSelection);
        } else {
            System.out.println("No se encontró el vuelo especificado.");
        }
    }

    private static void retrieveTicketDetails(Scanner scanner, FlightManager manager) {
        System.out.println("\nIngrese el ID del boleto para recuperar detalles:");
        String ticketId = scanner.nextLine();

        Ticket ticket = manager.retrieveTicketDetails(ticketId);
        if (ticket != null) {
            System.out.println("\n¿Desea ver más detalles del vuelo asociado? (Sí/No):");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("Sí")) {
                Vuelo vuelo = manager.getFlightById(ticket.getFlightId());
                if (vuelo != null) {
                    System.out.println("\nDetalles del vuelo asociado:");
                    System.out.println("ID de vuelo: " + vuelo.getId());
                    System.out.println("Origen: " + vuelo.getId().substring(0, 2));
                    System.out.println("Destino: " + vuelo.getId().substring(2, 4));
                    System.out.println("Fecha y hora de salida: " + vuelo.getFechaHora());
                    System.out.println("Puerta de salida: " + vuelo.getGate());
                    System.out.println("Precio del boleto: $" + vuelo.getTicketPrice());
                    System.out.println("Asientos vendidos:");
                    for (Map.Entry<String, Ticket> entry : vuelo.getSoldTickets().entrySet()) {
                        System.out.println("Asiento: " + entry.getKey() +
                                " | ID de boleto: " + entry.getValue().getTicketId() +
                                " | Nombre completo: " + entry.getValue().getUserInfo().getNombreCompleto());
                    }
                } else {
                    System.out.println("No se encontró el vuelo asociado.");
                }
            }
        }
    }

}
