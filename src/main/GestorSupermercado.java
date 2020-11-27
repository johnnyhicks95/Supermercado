/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.*;
import model.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class GestorSupermercado {

    Supermercado supermercado;
    private Dependiente auxDep;
    private Cliente auxCli;
    private Factura auxFac;
    private Productos auxProd;
    Scanner entrada = new Scanner(System.in);

    GestorSupermercado() {
        supermercado = new Supermercado();
    }

    //menú del sistema 
    public void ingresoGestor() {
        int opcion;
        do {
            System.out.println("============= SISTEMA DE VENTAS DEL SUPERMERCADO =============\n");
            System.out.println("1.-INGRESAR AL SISTEMA");
            System.out.println("2.-SALIR\n");
            System.out.print("Opción: ");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    ingresoSistema();
                    break;
                default:
                    System.out.println("\n============= GRACIAS POR USAR EL SISTEMA =============\n");
            }
        } while (opcion != 2);
    }

    //M ingresar al sistema
    public void ingresoSistema() {
        System.out.println("\n============= INICIAR SESIÓN =============\n");
        System.out.print("Usuario: ");
        String usuario = entrada.next();
        System.out.print("Contraseña: ");
        String contra = entrada.next();
        if (supermercado.administrador.getAdmUsuario().equals(usuario)
                && supermercado.administrador.getAdmContrasegna().equals(contra)) {
            opcionesAdmin();
        } else {
            auxDep = (Dependiente) supermercado.buscarDependiente(usuario);
            if (auxDep != null && auxDep.contraDep.equals(contra)) {
                System.out.println("Bienvenido " + auxDep.nomPer + " " + auxDep.apePer);
                opcionesDependiente();
            } else {
                System.out.println("\nAVISO: Usuario inexistente o contraseña incorrecta\n");
            }
        }
    }



    //M registrar vendedor
    public void ingresarDependientes() {
        String cedula;
        String nombre;
        String apellido;
        int edad;
        char sexo;
        String codDependiente;
        boolean aux = true;

        do {
            System.out.print("\nCódigo de dependiente: ");
            codDependiente = entrada.next();
            if (supermercado.dependientes.buscar(codDependiente) != null) {
                System.out.print("\nAVISO: El código de este dependiente ya está en uso, ingrese un código válido\n");
            } else {
                aux = false;
            }
        } while (aux);

        System.out.print("Cédula: ");
        cedula = entrada.next();

        do {
            System.out.print("Nombre: ");
            nombre = entrada.next();
        } while (validacion.validarTexto(nombre));

        do {
            System.out.print("Apellido: ");
            apellido = entrada.next();
        } while (validacion.validarTexto(apellido));

        do {
            System.out.print("Edad: ");
            edad = entrada.nextInt();
        } while (validacion.validarEdadDependiente(edad));

        do {
            System.out.print("Sexo: ");
            sexo = entrada.next().toUpperCase().charAt(0);
        } while (validacion.validarGenero(sexo));

        System.out.print("Contraseña: ");
        String contDependiente = entrada.next();
        supermercado.ingresarDependiente(new Dependiente(cedula, nombre, apellido, edad, sexo,
                contDependiente, codDependiente));
        System.out.println("\nAVISO: Se agregó un nuevo dependiente correctamente");
    }

    //Menu opciones dependientes
    public void mopcion1Admin() {
        System.out.println("\n============= DEPENDIENTE =============\n");
        System.out.println("1.-INGRESAR NUEVO");
        System.out.println("2.-MODIFICAR");
        System.out.println("3.-ELIMINAR");
        System.out.println("4.-LISTAR");
        System.out.println("5.-SALIR\n");
        System.out.print("Opción: ");
    }

    //M opciones funcionales del admin
    public void opcion1Admin() {
        int opcion;
        String codDep;
        do {
            mopcion1Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("\n============= NUEVO DEPENDIENTE =============");
                    ingresarDependientes();
                    break;
                case 2:
                    System.out.println("\n============= MODIFICAR DEPENDIENTE =============");
                    System.out.print("\nCódigo de dependiente: ");
                    codDep = entrada.next();
                    auxDep = supermercado.buscarDependiente(codDep);
                    if (auxDep != null) {
                        String nombre;
                        String apellido;
                        int edad;
                        char sexo;
                        do {
                            System.out.print("Nombre: ");
                            nombre = entrada.next();
                        } while (validacion.validarTexto(nombre));
                        do {
                            System.out.print("Apellido: ");
                            apellido = entrada.next();
                        } while (validacion.validarTexto(apellido));
                        do {
                            System.out.print("Edad: ");
                            edad = entrada.nextInt();
                        } while (validacion.validarEdad(edad));
                        do {
                            System.out.print("Sexo: ");
                            sexo = entrada.next().toUpperCase().charAt(0);
                        } while (validacion.validarGenero(sexo));
                        System.out.print("Contraseña: ");
                        String contDependiente = entrada.next();
                        auxDep.nomPer = nombre;
                        auxDep.apePer = apellido;
                        auxDep.edadPer = edad;
                        auxDep.sexPer = sexo;
                        auxDep.contraDep = contDependiente;
                        System.out.println("\nAVISO: Los datos fueron actualizados correctamente");
                    } else {
                        System.out.println("\nAVISO: Identificador erroneo o dependiente inexistente");
                    }
                    break;
                case 3:
                    System.out.println("\n============= ELIMINAR DEPENDIENTE =============\n");
                    System.out.print("Código de dependiente: ");
                    codDep = entrada.next();
                    auxDep = supermercado.buscarDependiente(codDep);
                    if (auxDep != null) {
                        supermercado.dependientes.borrar(codDep);
                        System.out.println("\nAVISO: Dependiente eliminado correctamente");
                    } else {
                        System.out.println("\nAVISO: Identificador erroneo o dependiente inexistente");
                    }
                    break;
                case 4:
                    System.out.println("\n============= LISTADO DE DEPENDIENTES =============\n");
                    supermercado.imprimirDependientes();
                    break;

            }
        } while (opcion != 5);
    }

    // ----------------------------------
    // CLIENTES
    //menu opciones clientes
    public void mopcion2Admin() {
        System.out.println("\n============= CLIENTE =============\n");
        System.out.println("1.-MODIFICAR");
        System.out.println("2.-ELIMINAR");
        System.out.println("3.-LISTAR");
        System.out.println("4.-SALIR\n");
        System.out.print("Opción: ");
    }

    //M registrar cliente
    public void ingresarCliente() {
        String cedula;
        String nombre;
        String apellido;
        char sexo;
        int edad;
        boolean aux = true;
        do {
            System.out.print("\nCédula: ");
            cedula = entrada.next();
            if (supermercado.clientes.buscar(cedula) != null) {
                System.out.println("\nAVISO: Existe un cliente registrado con este dato, ingrese un dato válido}n");
            } else {
                aux = false;
            }
        } while (aux);

        do {
            System.out.print("Nombre: ");
            nombre = entrada.next();
        } while (validacion.validarTexto(nombre));

        do {
            System.out.print("Apellido: ");
            apellido = entrada.next();
        } while (validacion.validarTexto(apellido));

        do {
            System.out.print("Edad: ");
            edad = entrada.nextInt();
        } while (validacion.validarEdad(edad));

        do {
            System.out.print("Sexo: ");
            sexo = entrada.next().toUpperCase().charAt(0);
        } while (validacion.validarGenero(sexo));

        System.out.print("Teléfono: ");
        String telefono = entrada.next();

        System.out.print("Dirección: ");
        String direccion = entrada.next();

        supermercado.ingresarCliente(new Cliente(cedula, nombre, apellido, edad, sexo, telefono, direccion));
        System.out.println("\nAVISO: Se agregó un nuevo cliente correctamente\n");
    }

    //m modificar cliente
    public void opcion2Admin() {
        int opcion;
        String cedCli;
        do {
            mopcion2Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("\n============= MODIFICAR CLIENTE =============\n");
                    System.out.print("Cédula cliente: ");
                    cedCli = entrada.next();
                    auxCli = supermercado.buscarCliente(cedCli);
                    if (auxCli != null) {
                        String nombre;
                        String apellido;
                        int edad;
                        char sexo;
                        do {
                            System.out.print("Nombre: ");
                            nombre = entrada.next();
                        } while (validacion.validarTexto(nombre));

                        do {
                            System.out.print("Apellido: ");
                            apellido = entrada.next();
                        } while (validacion.validarTexto(apellido));

                        do {
                            System.out.print("Edad: ");
                            edad = entrada.nextInt();
                        } while (validacion.validarEdad(edad));

                        do {
                            System.out.print("Sexo: ");
                            sexo = entrada.next().toUpperCase().charAt(0);
                        } while (validacion.validarGenero(sexo));

                        System.out.print("Teléfono: ");
                        String telefono = entrada.next();
                        System.out.print("Dirección");
                        String direccion = entrada.next();
                        auxCli.nomPer = nombre;
                        auxCli.apePer = apellido;
                        auxCli.edadPer = edad;
                        auxCli.sexPer = sexo;
                        auxCli.telfCli = telefono;
                        auxCli.dirCli = direccion;
                        System.out.println("\nAVISO: Los datos fueron actualizados correctamente\n");
                    } else {
                        System.out.println("\nAVISO: Cédula erronea o cliente inexistente\n");
                    }
                    break;
                case 2:
                    System.out.println("\n============= ELIMINAR CLIENTE =============\n");
                    System.out.print("Cédula del cliente: ");
                    cedCli = entrada.next();
                    auxCli = supermercado.buscarCliente(cedCli);
                    if (auxCli != null) {
                        supermercado.clientes.borrar(cedCli);
                        System.out.println("\nAVISO: Cliente eliminado correctamente\n");
                    } else {
                        System.out.println("\nAVISO: Cédula erronea o Cliente inexistente\n");
                    }
                    break;
                case 3:
                    System.out.println("\n============= LISTADO DE CLIENTES =============\n");
                    supermercado.imprimirClientes();
                    break;
            }
        } while (opcion != 4);
    }

    // ----------------------------------
    // CLIENTES
    public void mestadFacClientes() {
        System.out.println("\n============= FACTURAS POR CLIENTE =============\n");
        System.out.println("1.-PROMEDIO CONSUMO");
        System.out.println("2.-FACTURA DE MAYOR CONSUMO");
        System.out.println("3.-TOTAL DE CONSUMO");
        System.out.println("4.-SALIR\n");
        System.out.print("Opción: ");
    }

    public void estadFactClientes() {
        int opcion;
        do {
            mestadFacClientes();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    promCliente();
                    break;
                case 2:
                    mayorFactCli();
                    break;
                case 3:
                    totalConsumoCli();
                    break;
            }
        } while (opcion != 4);
    }

    //Promedio facturado del cliente
    public void promCliente() {
        System.out.println("\n============= PROMEDIO DE CONSUMO =============\n");
        System.out.print("Cédula: ");
        String cedula = entrada.next();
        double promFact = 0;
        auxCli = supermercado.buscarCliente(cedula);
        if (auxCli != null) {
            int cantFact = auxCli.facturas.contar();
            if (cantFact != 0) {
                auxFac = (Factura) auxCli.facturas.primero.dato;
                Nodo aux = auxCli.facturas.primero;
                while (aux != null) {
                    promFact = promFact + auxFac.totFac;
                    aux = aux.siguiente;
                    if (aux != null) {
                        auxFac = (Factura) aux.dato;
                    }
                }
                auxCli.toString();
                System.out.println("\nPromedio de consumo: " + promFact / cantFact + "\n");
            } else {
                System.out.println("\n********** No existen consumos aún **************\n");
            }
        } else {
            System.out.println("\nAVISO: Cédula erronea o cliente inexistente\n");
        }
    }

    //mayor factura del cliente
    public void mayorFactCli() {
        System.out.println("\n============= FACTURA DE MAYOR CONSUMO =============\n");
        System.out.print("Cédula: ");
        String cedula = entrada.next();
        auxCli = supermercado.buscarCliente(cedula);
        if (auxCli != null) {
            Nodo aux = auxCli.facturas.primero;
            int numFacturas = auxCli.facturas.contar();
            int iterador = 0;
            Factura[] factOrdenadas = new Factura[numFacturas];
            while (aux != null) {
                factOrdenadas[iterador] = (Factura) (aux.dato);
                iterador++;
                aux = aux.siguiente;
            }
            if (factOrdenadas.length != 0) {
                Arrays.sort(factOrdenadas, Comparator.reverseOrder());
                auxCli.toString();
                System.out.println("============= Factura de mayor consumo =============");
                System.out.println(factOrdenadas[0]);

            } else {
                System.out.println("\n*************** No existen consumo aún *****************\n");
            }
        } else {
            System.out.println("\nAVISO: Cédula erronea o cliente inexistente\n");
        }
    }

    //total de consumo del cliente
    public void totalConsumoCli() {
        System.out.println("\n============= TOTAL CONSUMO =============\n");
        System.out.print("Cédula: ");
        String cedula = entrada.next();
        auxCli = supermercado.buscarCliente(cedula);
        if (auxCli != null) {
            double total = 0;
            auxFac = (Factura) auxCli.facturas.primero.dato;
            Nodo aux = auxCli.facturas.primero;
            while (aux != null) {
                total = total + auxFac.totFac;
                aux = aux.siguiente;
                if (aux != null) {
                    auxFac = (Factura) aux.dato;
                }
            }
            if (total != 0) {
                auxCli.toString();
                System.out.println("\nTotal de consumo: " + total + "\n");
            } else {
                System.out.println("\n*************** No existen consumos aún ***************\n");
                auxCli.toString();
            }

        } else {
            System.out.println("\nAVISO: Cédula erronea o cliente inexistente\n");
        }

    }

     // ----------------------------------
    // INVENTARIO
    //menu opciones Inventario
    public void mopcion3Admin() {
        System.out.println("\n============= INVENTARIO =============\n");
        System.out.println("1.-INGRESAR NUEVO");
        System.out.println("2.-MODIFICAR");
        System.out.println("3.-ELIMINAR");
        System.out.println("4.-LISTAR");
        System.out.println("5.-SALIR\n");
        System.out.print("Opción: ");
    }

    // m ingresar inventario
    public void opcion3Admin() {
        int opcion;
        String codProd;
        do {
            mopcion3Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("\n============= NUEVO PRODUCTO =============\n");
                    ingresarInventario();
                    break;
                case 2:
                    System.out.println("\n============= MODIFICAR PRODUCTO =============\n");
                    System.out.print("Código de producto: ");
                    codProd = entrada.next();
                    auxProd = (Productos) Inventario.productos.buscar(codProd);
                    if (auxProd != null) {
                        String nombre;
                        double precio;
                        int cantidad;
                        do {
                            System.out.print("Nombre: ");
                            nombre = entrada.next();
                        } while (validacion.validarTexto(nombre));

                        do {
                            System.out.print("Precio: ");
                            precio = entrada.nextDouble();
                        } while (validacion.validarDouble(precio));

                        do {
                            System.out.print("Cantidad: ");
                            cantidad = entrada.nextInt();
                        } while (validacion.validarEntero(cantidad));

                        auxProd.articulo.nomArt = nombre;
                        auxProd.articulo.precArt = precio;
                        auxProd.cantidad = cantidad;
                        auxProd.articulo.calculoModificado();
                        System.out.println("\nAVISO: Los datos fueron actualizados correctamente\n");
                    } else {
                        System.out.println("\nAVISO: Identificador erroneo o producto inexistente\n");
                    }
                    break;
                case 3:
                    System.out.println("\n============= ELIMINAR PRODUCTO =============\n");
                    System.out.print("Código de producto: ");
                    codProd = entrada.next();
                    auxProd = (Productos) Inventario.productos.buscar(codProd);
                    if (auxProd != null) {
                        Inventario.productos.borrar(codProd);
                        System.out.println("\nAVISO: Producto eliminado correctamente\n");
                    } else {
                        System.out.println("\nAVISO: Identificador erroneo o producto inexistente\n");
                    }
                    break;
                case 4:
                    System.out.println("\n============= LISTADO DE PRODUCTOS =============\n");
                    Inventario.productos.imprimir();
                    break;

            }
        } while (opcion != 5);
    }

    public void imprimirInventario() {
        supermercado.inventario.listaProductos();
    }

    //registrar inventario
    public void ingresarInventario() {
        String nomArticulo;
        String codArticulo;
        double precArticulo;
        int cantArticulo;
        boolean aux = true;
        do {
            System.out.print("Nombre: ");
            nomArticulo = entrada.next();
        } while (validacion.validarTexto(nomArticulo));

        do {
            System.out.print("Código artículo: ");
            codArticulo = entrada.next();
            if (Inventario.productos.buscar(codArticulo) != null) {
                System.out.println("\nAVISO: Ya existe un producto con este código, por favor ingresa un código válido\n");
            } else {
                aux = false;
            }
        } while (aux);

        do {
            System.out.print("Precio: ");
            precArticulo = entrada.nextDouble();
        } while (validacion.validarDouble(precArticulo));

        do {
            System.out.print("Cantidad: ");
            cantArticulo = entrada.nextInt();
        } while (validacion.validarEntero(cantArticulo));

        supermercado.ingresarInventario(new Productos(new Articulo(codArticulo, nomArticulo, precArticulo), cantArticulo));
    }

    // ----------------------------------
    // FACTURAS
    //menu opciones facturado
    public void mopcion4Admin() {
        System.out.println("\n============= FACTURAS =============\n");
        System.out.println("1.-LISTAR TODAS");
        System.out.println("2.-POR CLIENTES");
        System.out.println("3.-POR DEPENDIENTES");
        System.out.println("4.-FACTURA MAYOR CONSUMO");
        System.out.println("5.-TOTAL FACTURADO");
        System.out.println("6.-SALIR\n");
        System.out.print("Opción: ");
    }

    //menu opciones funcionales 
    public void opcion4Admin() {
        int opcion;
        do {
            mopcion4Admin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("\n============= TODAS LAS FACTURAS =============\n");
                    facturasOrdenadas();
                    break;
                case 2:
                    estadFactClientes();
                    break;
                case 3:
                    estadFactDependientes();
                    break;
                case 4:
                    montoMayorFac();
                    break;
                case 5:
                    totalVentas();
                    break;
            }
        } while (opcion != 6);
    }

    //m factura mayor monto supermercado
    public void montoMayorFac() {
        if (supermercado.facturas.primero != null) {
            Nodo aux = supermercado.facturas.primero;
            int numFacturas = supermercado.facturas.contar();
            int iterador = 0;
            Factura[] factOrdenadas = new Factura[numFacturas];
            while (aux != null) {
                factOrdenadas[iterador] = (Factura) (aux.dato);
                iterador++;
                aux = aux.siguiente;
            }
            Arrays.sort(factOrdenadas, Comparator.reverseOrder());
            System.out.println("\n============= Mayor monto facturado =============\n");
            System.out.println(factOrdenadas[0]);
        } else {
            System.out.println("\nAVISO: No se ha vendido nada\n");
        }
    }

    //m listado facturas ordenadas
    public void facturasOrdenadas() {
        Nodo aux = supermercado.facturas.primero;
        int numFacturas = supermercado.facturas.contar();
        int iterador = 0;
        Factura[] factOrdenadas = new Factura[numFacturas];

        while (aux != null) {
            factOrdenadas[iterador] = (Factura) (aux.dato);
            iterador++;
            aux = aux.siguiente;
        }
        if (factOrdenadas.length != 0) {
            Arrays.sort(factOrdenadas, Comparator.reverseOrder());
            for (int i = 0; i < numFacturas; i++) {
                System.out.println(factOrdenadas[i]);
            }
        } else {
            System.out.println("\n************ No existen ventas aún **************\n");
        }

    }

    //m total de ventas
    public void totalVentas() {
        System.out.println("\n============= TOTAL VENTA =============\n");
        if (supermercado.facturas.primero != null) {
            double total = 0;
            auxFac = (Factura) supermercado.facturas.primero.dato;
            Nodo aux = supermercado.facturas.primero;
            while (aux != null) {
                total = total + auxFac.totFac;
                aux = aux.siguiente;
                if (aux != null) {
                    auxFac = (Factura) aux.dato;
                }
            }
            if (total != 0) {
                System.out.println("\nTotal de vendido: " + total + "\n");
            } else {
                System.out.println("\n*************** No existen ventas aún ************\n");
            }
        } else {
            System.out.println("\nAVISO: No se ha vendido nada\n");
        }

    }

    //****************DEPENDIENTES******************//
    //menu dependientes
    public void menuDependiente() {
        System.out.println("\n============= SISTEMA DE VENTAS DEL SUPERMERCADO =============\n");
        System.out.println("1.-REALIZAR VENTA");
        System.out.println("2.-REVISAR INVENTARIO");
        System.out.println("3.-ANULAR FACTURA");
        System.out.println("4.-SALIR\n");
        System.out.print("Opción: ");
    }

    //opciones funcionales dependiente
    public void opcionesDependiente() {
        int opcion;
        do {
            menuDependiente();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    registrarVenta();
                    break;
                case 2:
                    imprimirInventario();
                    break;
                case 3:
                    System.out.println("\n============= ANULAR FACTURA =============\n");
                    quitarFactura();
                    break;
            }
        } while (opcion != 4);
    }

    public void mfactDependientes() {
        System.out.println("\n============= FACTURAS POR DEPENDIENTE =============\n");
        System.out.println("1.-PROMEDIO VENTAS");
        System.out.println("2.-MAYOR MONTO FACTURADO");
        System.out.println("3.-TOTAL VENTA");
        System.out.println("4.-SALIR\n");
        System.out.print("Opción: ");
    }

    public void estadFactDependientes() {
        int opcion;
        do {
            mfactDependientes();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    promDependiente();
                    break;
                case 2:
                    mayorFactDep();
                    break;
                case 3:
                    totalVentaDep();
                    break;
            }
        } while (opcion != 4);
    }

    //promedio de ventas del dependiente
    public void promDependiente() {
        System.out.println("\n============= PROMEDIO DE VENTA =============\n");
        System.out.print("Código dependiente: ");
        String codigo = entrada.next();
        double promFact = 0;
        auxDep = supermercado.buscarDependiente(codigo);
        if (auxDep != null) {
            int cantFact = auxDep.ventas.contar();
            if (cantFact > 0) {
                auxFac = (Factura) auxDep.ventas.primero.dato;
                Nodo aux = auxDep.ventas.primero;
                while (aux != null) {
                    promFact = promFact + auxFac.totFac;
                    aux = aux.siguiente;
                    if (aux != null) {
                        auxFac = (Factura) aux.dato;
                    }
                }
                auxDep.toString();
                System.out.println("\nPromedio de consumo: " + promFact / cantFact + "\n");
            } else {
                System.out.println("\n********* No existen ventas aún ************\n");
            }
        } else {
            System.out.println("\nAVISO: Código o dependiente inexistente\n");
        }
    }

    public void mayorFactDep() {
        System.out.println("\n============= MAYOR MONTO FACTURADO =============\n");
        System.out.print("Código dependiente: ");
        String codigo = entrada.next();
        auxDep = supermercado.buscarDependiente(codigo);
        if (auxDep != null) {
            Nodo aux = auxDep.ventas.primero;
            int numFacturas = auxDep.ventas.contar();
            if (numFacturas > 0) {
                int iterador = 0;
                Factura[] factOrdenadas = new Factura[numFacturas];
                while (aux != null) {
                    factOrdenadas[iterador] = (Factura) (aux.dato);
                    iterador++;
                    aux = aux.siguiente;
                }
                Arrays.sort(factOrdenadas, Comparator.reverseOrder());
                auxDep.toString();
                System.out.println("\n============= Mayor monto facturado =============\n");
                System.out.println(factOrdenadas[0]);
            } else {
                System.out.println("\n******************* No existen ventas aún *****************\n");
            }
        } else {
            System.out.println("\nAVISO: Código erróneo o dependiente inexistente\n");
        }
    }

    public void totalVentaDep() {
        System.out.println("\n============= TOTAL VENTA =============\n");
        System.out.print("Código dependiente: ");
        String codigo = entrada.next();
        auxDep = supermercado.buscarDependiente(codigo);
        if (auxDep != null) {
            double total = 0;
            if (auxDep.ventas.primero != null) {
                auxFac = (Factura) auxDep.ventas.primero.dato;
                Nodo aux = auxDep.ventas.primero;
                while (aux != null) {
                    total = total + auxFac.totFac;
                    aux = aux.siguiente;
                    if (aux != null) {
                        auxFac = (Factura) aux.dato;
                    }
                }
            }
            if (total != 0) {
                auxDep.toString();
                System.out.println("\nTotal de vendido: " + total + "\n");
            } else {
                System.out.println("\n************ No existen ventas aún ************\n");
            }

        } else {
            System.out.println("\nAVISO: Código erroneo o dependiente inexistente\n");
        }

    }

    //registrar venta
    public void registrarVenta() {
        System.out.print("Cédula cliente: ");
        String cliente = entrada.next();
        auxCli = (Cliente) supermercado.clientes.buscar(cliente);
        System.out.print("Fecha: ");
        String fecha = entrada.next();
        if (auxCli != null) {
            Factura factura = new Factura(auxDep, auxCli, fecha);
            auxDep.registrarFactura(factura);
            auxCli.guardarFactura(factura);
            supermercado.ingresarFactura(factura);
        } else {
            System.out.println("\n============= EL CLIENTE NO SE ENCUENTRA REGISTRADO =============");
            System.out.println("========================= NUEVO CLIENTE =========================\n");
            ingresarCliente();
            auxCli = (Cliente) supermercado.clientes.buscar(cliente);
            Factura factura = new Factura(auxDep, auxCli, fecha);
            auxDep.registrarFactura(factura);
            auxCli.guardarFactura(factura);
            supermercado.ingresarFactura(factura);
        }
    }

    //quitar una factura
    public void quitarFactura() {

        if (auxDep.ventas.primero == null) {
            System.out.println("\nAVISO: No tienes ventas aún\n");
        } else {
            System.out.println("\n============= TUS FACTURAS =============\n");
            auxDep.imprimirVentas();
            System.out.print("Número de factura: ");
            String numFac = entrada.next();
            auxFac = (Factura) auxDep.ventas.buscar(numFac);
            if (auxFac != null) {
                auxDep.quitarFactura(numFac);
                supermercado.facturas.borrar(numFac);
                auxCli = (Cliente) supermercado.clientes.buscar(auxFac.cliente.cedPer);
                auxCli.facturas.borrar(numFac);
                System.out.println("\nAVISO: La factura fue eliminada correctamente\n");
            } else {
                System.out.println("\nAVISO: Número de factura inexistente\n");
            }
        }
    }

    //ADMIN
    public void imprimirMenuAdmin() {
        System.out.println("\n============= ADMIN =============\n");
        System.out.println("1.-DEPENDIENTE");
        System.out.println("2.-CLIENTE");
        System.out.println("3.-INVENTARIO");
        System.out.println("4.-FACTURAS");
        System.out.println("5.-SALIR\n");
        System.out.print("Opcion: ");
    }

    //opciones funcionales del administrador
    public void opcionesAdmin() {
        int opcion;
        do {
            imprimirMenuAdmin();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    opcion1Admin();
                    break;
                case 2:
                    opcion2Admin();
                    break;
                case 3:
                    opcion3Admin();
                    break;
                case 4:
                    opcion4Admin();
                    break;
            }
        } while (opcion != 5);
    }

    public void imprimirFacturas() {
        supermercado.facturas.imprimir();
    }

    public void imprimirClientes() {
        supermercado.clientes.imprimir();
    }

    public void imprimirDependientes() {
        supermercado.dependientes.imprimir();
    }

    public static void main(String[] args) {
        GestorSupermercado x = new GestorSupermercado();
        x.ingresoGestor();
    }
}
