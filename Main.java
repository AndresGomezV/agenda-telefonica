package Hackathon2;


public class Main {
    public static void main(String[] args) {
        // Crear una instancia de Menu
        Menu menu = new Menu();

        try {
            // Llamar al menú de la agenda
            menu.menuAgenda();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
