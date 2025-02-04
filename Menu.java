package Hackathon2;

import java.util.Scanner;

public class Menu {
    //Inicializa el scanner
    Scanner scanner = new Scanner(System.in);

    String opciones = """
                    1. Añadir un contacto.
                    2. Verificar si un contacto existe.
                    3. Listar todos mis contactos.
                    4. Buscar un contacto.
                    5. Eliminar un contacto.
                    6. Modificar el número de teléfono de un contacto.
                    7. ¿Mi agenda está llena o tengo espacios libres?
                    8. Salir.
                    """;
    int opcion;
    String opcionesAgenda = """
            ¿Deseas definir el tamaño de la agenda?
            1. Sí.
            2. No.
            """;

    public void menuAgenda() throws Exception {
        System.out.println("============================");
        System.out.println("        Menú Agenda         ");
        System.out.println("============================ \n");
        System.out.println(opcionesAgenda);

        int respuesta = Integer.parseInt(scanner.nextLine());
        Agenda agenda;
        if (respuesta == 1) {
            System.out.println("Ingresa la capacidad máxima de tu agenda: ");
            int capacidad =  Integer.parseInt(scanner.nextLine());
            agenda = new Agenda(capacidad);
            System.out.println("Tu agenda ha sido creada exitosamente, capacidad: " + capacidad + " contactos");
        } else {
            agenda = new Agenda();
            System.out.println("Tu agenda ha sido creada exitosamente, capacidad: " + 10 + " contactos");
        }

        System.out.println("============================");
        System.out.println("      Menú Contactos  ");
        System.out.println("============================");

        do {
            System.out.println(opciones);
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> agenda.agregarContacto();
                case 2 -> agenda.verificarContacto();
                case 3 -> agenda.listarContactos();
                case 4 -> agenda.buscarContacto();
                case 5 -> agenda.eliminarContacto();
                case 6 -> agenda.modificarTelefono();
                case 7 -> agenda.agendaLlena();
                case 8 -> System.out.println("Saliendo, hasta luego.");
                default -> System.out.println("Opción inválida, intenta nuevamente.");
            }
        } while (opcion != 8);


    }

}
