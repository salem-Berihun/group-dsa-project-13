import java.util.Scanner;

public class MusicApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicCache cache = null;

        System.out.println("Commands:\nINIT <n>\nPLAY <song title>\nSHOW_CACHE\nEXIT\n");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String command = parts[0].toUpperCase();

            if (command.equals("INIT")) {
                int capacity = Integer.parseInt(parts[1]);
                cache = new MusicCache(capacity);
                System.out.println("Music cache initialized with capacity " + capacity + ".");
            } else if (command.equals("PLAY") && cache != null) {
                String title = parts[1];
                cache.playSong(title);
                cache.showCache();
            } else if (command.equals("SHOW_CACHE") && cache != null) {
                cache.showCache();
            } else if (command.equals("EXIT")) {
                break;
            } else {
                System.out.println("Unknown command or cache not initialized. Use INIT <n> first.");
            }
        }

        scanner.close();
    }
}
