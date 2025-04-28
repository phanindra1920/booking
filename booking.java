import java.util.*;

class MovieBooking {
    private Map<String, String> userDatabase = new HashMap<>();
    private List<String> locations = new ArrayList<>();
    private List<String> languages = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<String> timing = new ArrayList<>();
    private List<Theater> theaters = new ArrayList<>();
    private String loggedInUser = null;
    private Location selectedLocation = null;
    private String selectedLanguage = null;
    private Movie selectedMovie = null;
    private String selectedTiming = null;
    private Theater selectedTheater = null;
    private Scanner scanner = new Scanner(System.in);
    private Map<String, List<Booking>> userBookings = new HashMap<>();  // Booking storage

    public static void main(String[] args) {
        MovieBooking program = new MovieBooking();
        program.run();
    }

    public void run() {
        // Add some sample locations
        locations.add("ONGOLE");
        locations.add("MARTUR");
        locations.add("GUNTUR");
        locations.add("CHILAKALURIPETA");

        // Add some sample languages
        languages.add("TELUGU");
        languages.add("TAMIL");
        languages.add("MALAYALAM");
        languages.add("HINDI");
        languages.add("KANNADA");

        // Add some sample movies with different ticket costs
        movies.add(new Movie("SALAAR", 100, 150));
        movies.add(new Movie("GUNTUR KARAM", 120, 180));
        movies.add(new Movie("SKANDHA", 80, 120));
        movies.add(new Movie("LEO", 90, 135));
        movies.add(new Movie("RULES RANJAN", 110, 165));
        movies.add(new Movie("OG", 140, 210));

        // Add some sample timings
        timing.add("08:30:00 AM");
        timing.add("11:15:00 AM");
        timing.add("02:15:00 PM");
        timing.add("06:15:00 PM");
        timing.add("09:30:00 PM");

        // Add some sample theaters with different seat availability
        theaters.add(new Theater("AMB MALL", 50));
        theaters.add(new Theater("VISWANATH", 80));
        theaters.add(new Theater("ARJUN", 60));
        theaters.add(new Theater("NEXUS MALL", 70));
        theaters.add(new Theater("INORBIT MALL", 90));

        while (true) {
            if (loggedInUser == null) {
                System.out.println("Welcome to User Registration and Login!");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
            } else if (selectedLocation == null) {
                System.out.println("1. Select Location");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
            } else if (selectedLanguage == null) {
                System.out.println("1. Select Language");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
            } else if (selectedMovie == null) {
                System.out.println("1. Select Movie");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
            } else if (selectedTiming == null) {
                System.out.println("1. Select Timing");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
            } else if (selectedTheater == null) {
                System.out.println("1. Select Theater");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
            } else {
                // Movie booking options when all selections are made
                System.out.println("Movie: " + selectedMovie.getTitle());
                System.out.println("Theater: " + selectedTheater.getName());
                System.out.println("Available Seats: " + selectedTheater.getAvailableSeats());
                System.out.println("Ticket Price: $" + selectedMovie.getTicketPrice());
                System.out.println("Show Timing: " + selectedTiming);
                System.out.println("1. Book Tickets");
                System.out.println("2. Cancel Booking");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (loggedInUser == null) {
                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (selectedLocation == null) {
                switch (choice) {
                    case 1:
                        selectLocation();
                        break;
                    case 2:
                        loggedInUser = null;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (selectedLanguage == null) {
                switch (choice) {
                    case 1:
                        selectLanguage();
                        break;
                    case 2:
                        loggedInUser = null;
                        selectedLocation = null;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (selectedMovie == null) {
                switch (choice) {
                    case 1:
                        selectMovie();
                        break;
                    case 2:
                        loggedInUser = null;
                        selectedLocation = null;
                        selectedLanguage = null;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (selectedTiming == null) {
                switch (choice) {
                    case 1:
                        selectTiming();
                        break;
                    case 2:
                        loggedInUser = null;
                        selectedLocation = null;
                        selectedLanguage = null;
                        selectedMovie = null;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (selectedTheater == null) {
                switch (choice) {
                    case 1:
                        selectTheater();
                        break;
                    case 2:
                        loggedInUser = null;
                        selectedLocation = null;
                        selectedLanguage = null;
                        selectedMovie = null;
                        selectedTiming = null;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                switch (choice) {
                    case 1:
                        bookMovieTickets();
                        break;
                    case 2:
                        cancelBooking();
                        break;
                    case 3:
                        loggedInUser = null;
                        selectedLocation = null;
                        selectedLanguage = null;
                        selectedMovie = null;
                        selectedTiming = null;
                        selectedTheater = null;
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Methods for user registration, login, and selection
    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists. Please try a different one.");
        } else {
            userDatabase.put(username, password);
            System.out.println("User registered successfully!");
        }
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            loggedInUser = username;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void selectLocation() {
        System.out.println("Select a location:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i));
        }
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= locations.size()) {
            selectedLocation = new Location(locations.get(choice - 1));
            System.out.println("Location selected: " + selectedLocation.getName());
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void selectLanguage() {
        System.out.println("Select a language:");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println((i + 1) + ". " + languages.get(i));
        }
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= languages.size()) {
            selectedLanguage = languages.get(choice - 1);
            System.out.println("Language selected: " + selectedLanguage);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void selectMovie() {
        System.out.println("Select a movie:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle());
        }
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= movies.size()) {
            selectedMovie = movies.get(choice - 1);
            System.out.println("Movie selected: " + selectedMovie.getTitle());
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void selectTiming() {
        System.out.println("Select a timing:");
        for (int i = 0; i < timing.size(); i++) {
            System.out.println((i + 1) + ". " + timing.get(i));
        }
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= timing.size()) {
            selectedTiming = timing.get(choice - 1);
            System.out.println("Timing selected: " + selectedTiming);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void selectTheater() {
        System.out.println("Select a theater:");
        for (int i = 0; i < theaters.size(); i++) {
            System.out.println((i + 1) + ". " + theaters.get(i).getName());
        }
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= theaters.size()) {
            selectedTheater = theaters.get(choice - 1);
            System.out.println("Theater selected: " + selectedTheater.getName());
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // Helper methods for booking and canceling tickets
    private void bookMovieTickets() {
        // Booking logic (not fully implemented here)
        System.out.println("Booking tickets...");
    }

    private void cancelBooking() {
        List<Booking> bookings = userBookings.getOrDefault(loggedInUser, new ArrayList<>());

        if (bookings.isEmpty()) {
            System.out.println("You have no bookings to cancel.");
            return;
        }

        System.out.println("Your Bookings:");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i).getBookingInfo());
        }

        System.out.print("Enter the number of the booking you want to cancel: ");
        int cancelIndex = scanner.nextInt();
        scanner.nextLine();

        if (cancelIndex < 1 || cancelIndex > bookings.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Booking bookingToCancel = bookings.remove(cancelIndex - 1);
        Theater theater = bookingToCancel.getTheater();

        // Free the seats
        for (int seat : bookingToCancel.getSeats()) {
            if (seat >= 1 && seat <= theater.getTotalSeats()) {
                theater.getSeats()[seat - 1] = true;
                theater.availableSeats++;
            }
        }

        System.out.println("Booking cancelled. Refunded $" + bookingToCancel.getAmountPaid());
    }

    // Helper classes for Booking, Movie, Theater, Location

    class Booking {
        private Movie movie;
        private Theater theater;
        private String timing;
        private List<Integer> seats;
        private double amountPaid;

        public Booking(Movie movie, Theater theater, String timing, List<Integer> seats, double amountPaid) {
            this.movie = movie;
            this.theater = theater;
            this.timing = timing;
            this.seats = new ArrayList<>(seats);
            this.amountPaid = amountPaid;
        }

        public List<Integer> getSeats() {
            return seats;
        }

        public Theater getTheater() {
            return theater;
        }

        public double getAmountPaid() {
            return amountPaid;
        }

        public String getBookingInfo() {
            return "Movie: " + movie.getTitle() +
                   ", Theater: " + theater.getName() +
                   ", Timing: " + timing +
                   ", Seats: " + seats +
                   ", Amount Paid: $" + amountPaid;
        }
    }

    class Movie {
        private String title;
        private double ticketPrice;
        private double premiumTicketPrice;

        public Movie(String title, double ticketPrice, double premiumTicketPrice) {
            this.title = title;
            this.ticketPrice = ticketPrice;
            this.premiumTicketPrice = premiumTicketPrice;
        }

        public String getTitle() {
            return title;
        }

        public double getTicketPrice() {
            return ticketPrice;
        }

        public double getPremiumTicketPrice() {
            return premiumTicketPrice;
        }
    }

    class Theater {
        private String name;
        private int totalSeats;
        private boolean[] seats;
        private int availableSeats;

        public Theater(String name, int totalSeats) {
            this.name = name;
            this.totalSeats = totalSeats;
            this.seats = new boolean[totalSeats];  // All seats are available initially
            this.availableSeats = totalSeats;
        }

        public String getName() {
            return name;
        }

        public int getAvailableSeats() {
            return availableSeats;
        }

        public int getTotalSeats() {
            return totalSeats;
        }

        public boolean[] getSeats() {
            return seats;
        }
    }

    class Location {
        private String name;

        public Location(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
