package Hackathon2;


public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu(); // Crear una instancia de Menu
        try {
            menu.menuAgenda(); // Llamar al menú de la agenda
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
