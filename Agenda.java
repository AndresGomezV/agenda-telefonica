package Hackathon2;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Agenda {

    int capacidadAgenda = 10;

    //Inicializa el scanner
    Scanner scanner = new Scanner(System.in);

    //
    Map<String, Contacto> contactos = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public Agenda() {
    }

    public Agenda(int capacidadAgenda) {
        this.capacidadAgenda = capacidadAgenda;
    }

    public int getCapacidadAgenda() {
        return capacidadAgenda;
    }

    public void setCapacidadAgenda(int capacidadAgenda) {
        this.capacidadAgenda = capacidadAgenda;
    }


    public String pedirNombreApellido() {
        String nombre, apellido;

        do {
            System.out.println("Ingrese el nombre del contacto: ");
            nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío, inténtalo nuevamente");
            }
        } while (nombre.isEmpty());

        do {

            System.out.println("Ingrese el apellido del contacto: ");
            apellido = scanner.nextLine().trim();

            if (apellido.isEmpty()) {
                System.out.println("El apellido no puede estar vacío, inténtalo nuevamente");
            }

        } while (apellido.isEmpty());

        return nombre + " " + apellido;
    }

    public String pedirTelefono() {
        String telefono;
        do {
            System.out.println("Ingrese el teléfono del contacto: ");
            telefono = scanner.nextLine();
        } while (telefono.isEmpty());

        return telefono;
    }


    public void agregarContacto() {

        //Primero verifica si es posible añadir otro contacto
        if (verificarAgendaLlena()) {
            System.out.println("La agenda está llena. No se pueden añadir más contactos.");
        } else {
            // Solicitar datos al usuario
            String nombreApellido = pedirNombreApellido();


            if (verificadorContacto(nombreApellido)) {
                System.out.println("El contacto " + nombreApellido + " ya existe.");

            } else {
                String telefono = pedirTelefono();
                // Crear el nuevo contacto con los datos introducidos
                Contacto contacto = new Contacto(nombreApellido, telefono);
                contactos.put(nombreApellido, contacto);
                System.out.println("El contacto " + nombreApellido + " fue agregado exitosamente.");
            }
        }
    }

    public boolean verificadorContacto(String nombreVerificar) {
        //Se verifica si el TreeMap contiene o no una clave con los valores ingresados
        return contactos.containsKey(nombreVerificar);
    }

    public void verificarContacto() {
        if (!agendaVacia()) {
            //Se solicita al usuario que ingrese nombre y apellido del contacto a verificar
            String nombreApellidoVerificar = pedirNombreApellido();
            String nombreFormat = nombreApellidoVerificar.substring(0, 1).toUpperCase() + nombreApellidoVerificar.substring(1).toLowerCase();

            if (!verificadorContacto(nombreApellidoVerificar)) {
                System.out.println("El contacto " + nombreFormat + " no existe en su lista de contactos.");
            } else {
                System.out.println("El contacto " + nombreFormat + " si fué encontrado en su lista de contactos.");
            }
        }
    }

    public boolean agendaVacia() {
        if (contactos.isEmpty()) {
            System.out.println("Actualmente no tienes contactos en tu agenda");
        }
        return contactos.isEmpty();
    }


    public void listarContactos() {

        //Si no hay contactos, notifica al usuario
        if (!agendaVacia()) {
            System.out.println("Contactos: ");
            System.out.printf("%-20s | %14s%n", "Nombre y Apellido", "Teléfono");
            System.out.println("-------------------------------------");

            // Itera sobre cada contacto en el TreeMap y muestra la información formateada
            for (Contacto contacto : contactos.values()) {
                System.out.printf("%-20s | %14s%n", contacto.getNombreApellido(), contacto.getTelefono());
            }
        }
    }

    public void buscarContactoNombreApellido() {
        if (!agendaVacia()) {
            // Solicita el nombre y apellido o telefono
            String nombreApellidoVerificar = pedirNombreApellido();


            // Recorre todos los contactos en el TreeMap
            for (Contacto contacto : contactos.values()) {
                // Compara el nombre y el apellido del contacto
                if (contacto.getNombreApellido().equalsIgnoreCase(nombreApellidoVerificar)) {
                    // Si el contacto fue encontrado, imprime detalles del contacto
                    System.out.println("Contacto encontrado: " + contacto.getNombreApellido() + ", Teléfono: " + contacto.getTelefono());

                    break; // Sale del bucle si el contacto fue encontrado
                } else {
                    // Si se indica que no se encontró el contacto, imprime un mensaje de búsqueda fallida
                    System.out.println("Contacto no encontrado.");
                }
            }
        }
    }

    public void buscarContactoTelefono() {

        if (!agendaVacia()) {
            String telefonoVerificar = pedirTelefono();
            for (Contacto contacto : contactos.values()) {

                // Compara el nombre y el apellido del contacto
                if (contacto.getTelefono().equals(telefonoVerificar)) {

                    // Si el contacto fue encontrado, imprime detalles del contacto
                    System.out.println("Contacto encontrado: " + contacto.getNombreApellido() + ", Teléfono: " + contacto.getTelefono());

                    break; // Sale del bucle si el contacto fue encontrado
                } else {
                    // Si se indica que no se encontró el contacto, imprime un mensaje de búsqueda fallida
                    System.out.println("Contacto no encontrado.");
                }
            }
        }
    }

    public void eliminarContacto() {
        if (!agendaVacia()) {
            // Solicitar datos al usuario
            String contactoEliminar = pedirNombreApellido();

            if (!contactos.containsKey(contactoEliminar)) {
                System.out.println("No se encontró un contacto llamado " + contactoEliminar);
            } else {
                contactos.remove(contactoEliminar);
                System.out.println("El contacto " + contactoEliminar + " ha sido eliminado exitosamente.");
            }
        }
    }

    public void modificarTelefono() {
        if (!agendaVacia()) {

            String nombreApellidoModificar = pedirNombreApellido();
            if (!verificadorContacto(nombreApellidoModificar)) {
                System.out.println("Lo sentimos, el contacto " + nombreApellidoModificar + " no existe.");
                System.out.println("No se pudo modificar un teléfono.");
            } else {
                String numeroTelefono = pedirTelefono();
                contactos.get(nombreApellidoModificar).setTelefono(numeroTelefono);
                System.out.println("Se modificó el numero de teléfono de " + nombreApellidoModificar + ", nuevo número de teléfono: " + numeroTelefono);
            }
        }
    }

    /*Metodo para comprobar si la agenda está llena (capacidad máxima de 10 default)*/
    public boolean verificarAgendaLlena() {
        return contactos.size() >= capacidadAgenda; /* La capacidad máxima es 10 por default*/
    }

    /* Metodo para ver cuántos contactos más se pueden añadir*/
    public String espacioLibres() {
        int espacio = capacidadAgenda - contactos.size(); /* Espacio restante en una agenda de capacidad 10*/
        return " espacio libre para " + espacio + " contactos.";
    }

    public void agendaLlena() {

        if (verificarAgendaLlena()) {
            System.out.println("La agenda está llena");
        } else {
            String espacios = espacioLibres();
            System.out.println("La agenda tiene" + espacios);
        }

    }


}
