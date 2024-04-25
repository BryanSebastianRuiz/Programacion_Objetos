import java.util.HashMap;
import java.util.Map;
import java.util.Random;

enum Destinos {
    MX("México"), UK("Reino Unido"), US("Estados Unidos"), JP("Japón"), CN("China");

    private final String nombre;

    Destinos(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

public class Vuelo {
    private final String id;
    private final int numberSeats = 40;
    private final double ticketPrice;
    private final String fechaHora;
    private final String gate;
    private final Map<String, Ticket> soldTickets = new HashMap<>();

    public Vuelo(String origen, String destino, String fechaHora) {
        this.id = origen + destino + "-" + generateRandomNumber(10000);
        this.ticketPrice = generateRandomTicketPrice();
        this.fechaHora = fechaHora;
        this.gate = generateRandomGate();
    }

    private String generateRandomNumber(int bound) {
        Random random = new Random();
        return String.format("%04d", random.nextInt(bound));
    }

    private double generateRandomTicketPrice() {
        Random random = new Random();
        return 1000 + (random.nextDouble() * (2000 - 1000));
    }

    private String generateRandomGate() {
        Random random = new Random();
        char randomLetter = (char) (random.nextInt(2) + 'A');
        int randomNumber = random.nextInt(9) + 1;
        return randomLetter + "-" + randomNumber;
    }

    public String getId() {
        return id;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getGate() {
        return gate;
    }

    public Map<String, Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void buyTicket(String nombreCompleto, String numeroTelefono, String correoElectronico, String pais, String seatSelection) {
        UserInfo userInfo = new UserInfo(nombreCompleto, numeroTelefono, correoElectronico, pais);
        Ticket ticket = new Ticket(id, userInfo, fechaHora, ticketPrice);
        soldTickets.put(seatSelection, ticket);
    }

    public Map<String, String> getPlaneMap() {
        Map<String, String> planeMap = new HashMap<>();
        for (int i = 0; i < numberSeats / 2; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                String seat = (char) ('A' + i) + "-" + String.format("%02d", j + 1);
                if (soldTickets.containsKey(seat)) {
                    row.append("X");
                } else {
                    row.append("O");
                }
            }
            planeMap.put((char) ('A' + i) + "", row.toString());
        }
        return planeMap;
    }

    public Ticket getTicket(String ticketId) {
        return soldTickets.get(ticketId);
    }
}
