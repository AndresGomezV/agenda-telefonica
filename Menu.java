package Hackathon2;

import java.util.Scanner;

public class Menu {
    //Inicializa el scanner
    Scanner scanner = new Scanner(System.in);

    int opcion;

    String opciones = """
            
                 ============================
                        Menú Contactos
                 ============================
            
            ¿Qué deseas hacer? Selecciona una de las siguientes opciones:
            
            1. Añadir un contacto.
            2. Verificar si un contacto existe.
            3. Listar todos mis contactos.
            4. Buscar un contacto.
            5. Eliminar un contacto.
            6. Modificar el número de teléfono de un contacto.
            7. ¿Mi agenda está llena o tengo espacios libres?
            8. Salir.
            """;

    String opcionesAgenda = """
            
                 =============================
                          Menú Agenda
                 =============================
            
            ¿Deseas definir el tamaño de la agenda?
            1. Sí.
            
            Ingresa cualquier número para inicializar tu agenda con un máximo de 10 contactos.
            
            """;

    String opcionesBuscar = """
            ¿Cómo deseas buscar el contacto?
            1. Nombre y apellido.
            2. Teléfono.
            """;

    public void menuAgenda() {
        Agenda agenda;
        System.out.println(opcionesAgenda);
        String entradaAgenda = scanner.nextLine();

        //Se repite el menú si el usuario no ingresa un valor
        while (entradaAgenda.isEmpty()) {
            System.out.println("No ingresaste ninguna opción. Inténtalo de nuevo.");
            System.out.println(opcionesAgenda);
            entradaAgenda = scanner.nextLine();
        }

        //Convierte la entrada String a un entero
        int respuesta = Integer.parseInt(entradaAgenda);

        //El usuario tiene la opción de crear una agenda con 10 espacios por defecto o determinar su capacidad
        if (respuesta == 1) {
            System.out.println("Ingresa la capacidad máxima de tu agenda: ");
            int capacidad = Integer.parseInt(scanner.nextLine());
            agenda = new Agenda(capacidad);
            System.out.println("Tu agenda ha sido creada exitosamente, capacidad: " + capacidad + " contactos");
        } else {
            agenda = new Agenda();
            System.out.println("Tu agenda ha sido creada exitosamente, capacidad: " + 10 + " contactos");
        }


        do {
            System.out.println(opciones);
            String entrada = scanner.nextLine().trim();

            // Verificar si el usuario presionó Enter sin ingresar un número
            if (entrada.isEmpty()) {
                System.out.println("No ingresaste ninguna opción. Inténtalo de nuevo.");
                // Regresa al inicio del bucle sin ejecutar el switch
                continue;
            }

            // Convertir entrada a entero
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1 -> agenda.agregarContacto();
                case 2 -> agenda.verificarContacto();
                case 3 -> agenda.listarContactos();
                case 4 -> {
                    //Primero verifica si la agenda está vacía para evitar preguntar al usuario el metodo de búsqueda. Ya que permite buscar al contacto por nombre y apellido o por teléfono
                    if (agenda.agendaVacia()) {
                        System.out.println(opcionesBuscar);
                        opcion = Integer.parseInt(scanner.nextLine());
                        if (opcion == 1) {
                            agenda.buscarContactoNombreApellido();
                        } else if (opcion == 2) {
                            agenda.buscarContactoTelefono();
                        } else {
                            System.out.println("Opción inválida. Intenta nuevamente");
                        }
                    }
                }
                case 5 -> agenda.eliminarContacto();
                case 6 -> agenda.modificarTelefono();
                case 7 -> agenda.agendaLlena();
                case 8 -> System.out.println("¡Hasta pronto! Gracias por usar la agenda. 😊");
                default -> System.out.println("Opción inválida, intenta nuevamente.");
            }
        } while (opcion != 8);
    }
}
