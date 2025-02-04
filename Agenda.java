package Hackathon2;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Agenda {

    private int capacidadAgenda = 10;

    //Inicializa el scanner
    Scanner scanner = new Scanner(System.in);

    //Map ordena los contactos alfabéticamente por nombre y apellido al listarlos, las claves se ordenarán de forma alfabética sin tener en cuenta si las letras están en mayúsculas o minúsculas.
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

        //Solicita al usuario nombre y apellido hasta que el campo no esté vacío
        do {
            System.out.println("Ingresa el nombre del contacto: ");
            nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío, inténtalo nuevamente");
            }
        } while (nombre.isEmpty());

        do {

            System.out.println("Ingresa el apellido del contacto: ");
            apellido = scanner.nextLine().trim();

            if (apellido.isEmpty()) {
                System.out.println("El apellido no puede estar vacío, inténtalo nuevamente");
            }

        } while (apellido.isEmpty());

        //Da formato al nombre y apellido para que lo ingresado por el usuario se muestre con mayúscula inicial y el resto en minúscula
        String nombreFormateado = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        String apellidoFormateado = apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase();

        return nombreFormateado + " " + apellidoFormateado;

    }

    public String pedirTelefono() {
        String telefono;

        //Pide el número de teléfono hasta que se ingrese un valor
        do {
            System.out.println("Ingresa el teléfono del contacto: ");
            telefono = scanner.nextLine();
            if (telefono.isEmpty()) {
                System.out.println("El teléfono no puede estar vacío, inténtalo nuevamente");
            }
        } while (telefono.isEmpty());

        return telefono;
    }


    public void agregarContacto() {

        //Primero verifica si es posible añadir otro contacto
        if (verificarAgendaLlena()) {
            System.out.println("Tu agenda está llena. No puedes añadir más contactos.");
        } else {
            // Solicitar datos al usuario
            String nombreApellido = pedirNombreApellido();

            //Verifica si el contacto ya está en la lista de contactos
            if (verificadorContacto(nombreApellido)) {
                System.out.println("El contacto " + nombreApellido + " ya se encuentra en tu agenda.");

            } else {
                //De lo contrario, continua a pedir el teléfono y crear el contacto
                String telefono = pedirTelefono();
                // Crear el nuevo contacto con los datos introducidos
                Contacto contacto = new Contacto(nombreApellido, telefono);
                contactos.put(nombreApellido, contacto);
                System.out.println("El contacto " + nombreApellido + " fue agregado exitosamente.");
            }
        }
    }

    //Funciones para verificar si existe un contacto en la agenda
    public boolean verificadorContacto(String nombreVerificar) {
        //Se verifica si el TreeMap contiene o no una clave con los valores ingresados
        return contactos.containsKey(nombreVerificar);
    }

    public void verificarContacto() {
        if (agendaVacia()) {
            //Se solicita al usuario que ingrese nombre y apellido del contacto a verificar
            String nombreApellidoVerificar = pedirNombreApellido();

            if (!verificadorContacto(nombreApellidoVerificar)) {
                System.out.println("El contacto " + nombreApellidoVerificar + " no se encuentra en tu lista de contactos.");
            } else {
                System.out.println("El contacto " + nombreApellidoVerificar + " si se encuentra en tu lista de contactos.");
            }
        }
    }

    public boolean agendaVacia() {
        if (contactos.isEmpty()) {
            System.out.println("Actualmente no tienes contactos en tu agenda");
        }
        return !contactos.isEmpty();
    }


    public void listarContactos() {

        //Si no hay contactos, notifica al usuario
        if (agendaVacia()) {
            System.out.println("Contactos: ");
            //Formato de impresión para centrar títulos y alinear datos
            System.out.printf("%-20s | %14s%n", "Nombre y Apellido", "Teléfono");
            System.out.println("-------------------------------------");

            // Itera sobre cada contacto en el TreeMap y muestra la información formateada
            for (Contacto contacto : contactos.values()) {
                System.out.printf("%-20s | %14s%n", contacto.getNombreApellido(), contacto.getTelefono());
            }
        }
    }

    public void buscarContactoNombreApellido() {

        // Solicita el nombre y apellido
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
                System.out.println("El contacto " + nombreApellidoVerificar + " no se encuentra en tu lista de contactos.");
            }
        }

    }

    public void buscarContactoTelefono() {

        String telefonoVerificar = pedirTelefono();
        for (Contacto contacto : contactos.values()) {

            // Compara el nombre y el apellido del contacto
            if (contacto.getTelefono().equals(telefonoVerificar)) {

                // Si el contacto fue encontrado, imprime detalles del contacto
                System.out.println("Contacto encontrado: " + contacto.getNombreApellido() + ", Teléfono: " + contacto.getTelefono());

                break; // Sale del bucle si el contacto fue encontrado
            } else {
                // Si se indica que no se encontró el contacto, imprime un mensaje de búsqueda fallida
                System.out.println("El contacto con número de teléfono " + telefonoVerificar + " no se encuentra en tu lista de contactos.");
            }
        }

    }

    public void eliminarContacto() {
        if (agendaVacia()) {
            // Solicitar datos al usuario
            String contactoEliminar = pedirNombreApellido();

            //Busca en el Map Contactos el contacto a eliminar, si lo encuentra lo remueve, si no, informa al usuario.
            if (!contactos.containsKey(contactoEliminar)) {
                System.out.println("El contacto " + contactoEliminar + " no se encuentra en tu lista de contactos.");
            } else {
                contactos.remove(contactoEliminar);
                System.out.println("El contacto " + contactoEliminar + " ha sido eliminado exitosamente.");
            }
        }
    }

    public void modificarTelefono() {
        if (agendaVacia()) {

            //Primero verifica si el contacto se encuentra en la lista de contactos
            String nombreApellidoModificar = pedirNombreApellido();
            if (!verificadorContacto(nombreApellidoModificar)) {
                System.out.println("El contacto " + nombreApellidoModificar + " no se encuentra en tu lista de contactos.");
            } else {
                //Si se encuentra, pide el nuevo número de teléfono e informa al usuario.
                String numeroTelefono = pedirTelefono();
                contactos.get(nombreApellidoModificar).setTelefono(numeroTelefono);
                System.out.println("Se modificó el numero de teléfono de " + nombreApellidoModificar + ", nuevo número de teléfono: " + numeroTelefono);
            }
        }
    }

    //Metodo para comprobar si la agenda está llena (capacidad máxima 10 por default)
    public boolean verificarAgendaLlena() {
        //Verifica si la cantidad de contactos en el Map contactos es mayor o igual a la capacidad de la agenda y devuelve un booleano
        return contactos.size() >= capacidadAgenda;
    }

    //Metodo para ver cuántos contactos más se pueden añadir
    public String espacioLibres() {
        //Determina el espacio restando la cantidad actual de contactos de la capacidad total de la agenda
        int espacio = capacidadAgenda - contactos.size();
        return " espacio libre para " + espacio + " contactos.";
    }

    public void agendaLlena() {
        //Utiliza los metodos de verificarAgendaLlena y espacioLibres para informar al usuario
        if (verificarAgendaLlena()) {
            System.out.println("Tu agenda está llena");
        } else {
            String espacios = espacioLibres();
            System.out.println("Tu agenda tiene" + espacios);
        }

    }


}
