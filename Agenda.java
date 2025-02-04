package Hackathon2;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Agenda {

    int capacidadAgenda = 10;

    //Inicializa el scanner
    Scanner scanner = new Scanner(System.in);

    //Crea un HashMap que recibe Integer como Id del contacto y Contacto como objeto
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
        System.out.println("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine().trim();

        System.out.println("Ingrese el apellido del contacto: ");
        String apellido = scanner.nextLine().trim();

        return nombre + " " + apellido;
    }

    public String pedirTelefono() {

        System.out.println("Ingrese el teléfono del contacto: ");
        String telefono = scanner.nextLine();

        return telefono;
    }


    public void agregarContacto() throws Exception {

        //Primero verifica si es posible añadir otro contacto
        if (verificarAgendaLlena()) {
            throw new Exception("La agenda está llena. No se pueden añadir más contactos.");
        } else {
            // Solicitar datos al usuario
            String nombreApellido = pedirNombreApellido();
            String telefono = pedirTelefono();

            if (verificadorContacto(nombreApellido)) {
                throw new Exception("El contacto con el nombre '" + nombreApellido + " ya existe.");

            } else {
                // Crear el nuevo contacto con los datos introducidos
                Contacto contacto = new Contacto(nombreApellido, telefono);
                contactos.put(nombreApellido, contacto);
                System.out.println("El contacto " + nombreApellido + " fue agregado exitosamente.");
            }
        }
    }

    public boolean verificadorContacto(String nombreVerificar) {

        //Se verifica si el TreeMap contiene o no una clave con los valores ingresados
        if (!contactos.containsKey(nombreVerificar)) {
            return false;

        } else {
            return true;
        }
    }

    public void verificarContacto() {
        //Se solicita al usuario que ingrese nombre y apellido del contacto a verificar
        String nombreApellidoVerificar = pedirNombreApellido();
        String nombreFormat = nombreApellidoVerificar.substring(0, 1).toUpperCase() + nombreApellidoVerificar.substring(1).toLowerCase();
        if (!verificadorContacto(nombreApellidoVerificar)) {
            System.out.println("El contacto " + nombreFormat + " no existe en su lista de contactos.");
        } else {
            System.out.println("El contacto " + nombreFormat + " si fué encontrado en su lista de contactos.");
        }
    }


    public void listarContactos() throws Exception {
        //Si no hay contactos, notifica mediante una excepción.
        if (contactos.isEmpty()) {
            throw new Exception("No hay contactos para mostrar, ¿Deseas agregar un contacto?");

        } else {
            //De lo contrario, itera sobre cada valor del TreeMap contactos e imprime la información correspondiente.
            System.out.println("Tus contactos: ");
            for (Contacto contacto : contactos.values()) {
                System.out.println(contacto.getNombreApellido() + " - " + contacto.getTelefono());
            }
        }
    }

    public void buscarContacto() {

        // Solicita el nombre y apellido
        String nombreApellidoVerificar = pedirNombreApellido();

        // Recorre todos los contactos en el TreeMap
        for (Contacto contacto : contactos.values()) {
            // Compara el nombre y el apellido del contacto
            if (contacto.getNombreApellido().equalsIgnoreCase(nombreApellidoVerificar)) {
                // Si el contacto fue encontrado, imprime detalles del contacto
                System.out.println("Contacto encontrado: " + contacto.getNombreApellido() + ", Teléfono: " + contacto.getTelefono());

                break; // Sale del bucle ya que el contacto fue encontrado
            } else {
                // Si se indica que no se encontró el contacto, imprime un mensaje de búsqueda fallida
                System.out.println("Contacto no encontrado.");
            }
        }
    }

    public void eliminarContacto() {

        // Solicitar datos al usuario
        String contactoEliminar = pedirNombreApellido();

        if (!contactos.containsKey(contactoEliminar)) {
            System.out.println("No se encontró un contacto llamado " + contactoEliminar);
        } else {
            contactos.remove(contactoEliminar);
            System.out.println("El contacto " + contactoEliminar + " ha sido eliminado exitosamente.");
        }
    }

    public void modificarTelefono() {
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

    /*Metodo para comprobar si la agenda está llena (capacidad máxima de 10)*/
    public boolean verificarAgendaLlena() {
        return contactos.size() >= 10; /* La capacidad máxima es 10*/
    }

    /* Metodo para ver cuántos contactos más se pueden añadir*/
    public String espacioLibres() {
        int espacio = 10 - contactos.size(); /* Espacio restante en una agenda de capacidad 10*/
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
