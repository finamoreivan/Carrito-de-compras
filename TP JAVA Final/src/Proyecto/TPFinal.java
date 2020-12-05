package Proyecto;

import java.util.InputMismatchException;
import java.util.Scanner;

import Articulos.Articulo;
import Articulos.Articulos;
import Usuarios.Usuario;
import Usuarios.Usuarios;
import Utiles.Archivador;
import Utiles.Empresa;

public class TPFinal {

	public static void main(String[] args) {
		Scanner e = new Scanner(System.in);

		Usuarios usuarios = new Usuarios();
		Archivador archiUsuarios = new Archivador();
		String rutaUsuarios = "usuarios.txt";

		if (archiUsuarios.createFile(rutaUsuarios)) {
			archiUsuarios.save(usuarios, rutaUsuarios);
		} else {
			usuarios = (Usuarios) archiUsuarios.load(rutaUsuarios);
		}
		String eleccion;
		String tipoUsuario = null;
		double dinero = 0;
		Usuario usuario = null;
		while(true) {
			System.out.println("¿Posee cuenta en el sistema de " + Empresa.getInstance().getNombreEmpresa() + "?");
			System.out.println("1 - Si");
			System.out.println("2 - No, crear una");
			eleccion = e.next();
			if (eleccion.equals("1")) {
				while(true) {
					System.out.println("Ingrese username: ");
					usuario = usuarios.getUsuario(e.next());
					if (usuario != null) {
						System.out.println("Ingrese clave: ");
						if (usuario.validar(e.next())) {
							System.out.println("Ingreso correcto.");
							tipoUsuario = usuario.getTipoUsuario();
							if (tipoUsuario.equals("C") || tipoUsuario.equals("c")) {
								System.out.println("Usted es un Cliente");
							}
							if (tipoUsuario.equals("E") || tipoUsuario.equals("e")) {
								System.out.println("Usted es un Empleado\n");
							}
							break;
						}
						else {
							System.out.println("Error en la clave ingresada.");
						}
					} 
					else {
						System.out.println("Usuario no registrado en el sistema.");
					}
					archiUsuarios.save(usuarios, rutaUsuarios);
				}
				break;
			}
			if (eleccion.equals("2")) {
				System.out.println("Genere su username: ");
				String username = e.next();
				System.out.println("Genere su clave: ");
				String pass = e.next();
				System.out.println("¿Es usted cliente o empleado?");
				System.out.println("Ingrese C para cliente o E para empleado: ");
				tipoUsuario = e.next();
				if (!tipoUsuario.equals("E") && !tipoUsuario.equals("e") && !tipoUsuario.equals("C")
						&& !tipoUsuario.equals("c")) {
					System.out.println("Error en el dato ingresado.");
					System.exit(0);
				}

				usuario = new Usuario(username, pass, tipoUsuario, dinero);
				if (usuarios.addUsuario(username, pass, tipoUsuario, dinero)) {
					System.out.println("El usuario se creo correctamente");
					System.out.println(usuario);
				} else {
					System.out.println("Error: Ya existe el usuario");
					System.exit(0);
				}
				archiUsuarios.save(usuarios, rutaUsuarios);
				break;
			}
			if (!eleccion.equals("1") && !eleccion.equals("2")) {
				System.out.println("Error en el dato ingresado.");
			}
		}
		Articulos articulos = new Articulos();
		Archivador archiArticulos = new Archivador();
		String rutaArticulos = "articulos.txt";
		
		if (archiArticulos.createFile(rutaArticulos)) {
			archiArticulos.save(articulos, rutaArticulos);
		} else {
			articulos = (Articulos) archiArticulos.load(rutaArticulos);
		}
		
		if (tipoUsuario.equals("C") || tipoUsuario.equals("c")) {
			System.out.println("Seleccione: ");
			System.out.println("1 - Vista y movimiento de fondos en su cuenta.");
			System.out.println("2 - Realizar compras.");
			String eleccionInicio = e.next();
			if(eleccionInicio.equals("1")) {
				String eleccionDinero;
				while(true) {
					System.out.println("Dinero disponible en la cuenta: $" + Math.round(usuario.getDinero()));
					System.out.println("¿Desea continuar con esta suma de dinero o desea cambiar la cantidad?");
					System.out.println("1 - Si\n2 - No, cambiar");
					eleccionDinero = e.next();
					if (eleccionDinero.equals("2")) {
						while(true) {
							System.out.println("¿Cuanto?");
							usuario.setDinero(e.nextDouble());
							double ingresarDinero = usuario.getDinero();
							if(ingresarDinero>0 || ingresarDinero == 0) {	
								usuarios.editarUsuario(usuario.getUserName(), usuario.getPassword(), usuario.getTipoUsuario(),
										usuario.getDinero());
								archiUsuarios.save(usuarios, rutaUsuarios);
								usuarios.editarUsuario(usuario.getUserName(), usuario.getPassword(), usuario.getTipoUsuario(),
										usuario.getDinero());
								archiUsuarios.save(usuarios, rutaUsuarios);
								break;
							}else {
								System.out.println("No se pueden ingresar cantidades negativas.");
							}	
						}
						break;
					}if (eleccionDinero.equals("2") && usuario.getDinero() == 0) {
						System.out.println("No tiene dinero en la cuenta para realizar compras.");
						System.exit(0);
					}if (!eleccionDinero.equals("1") && !eleccionDinero.equals("2")) {
						System.out.println("Error en el dato ingresado.");
					}
					if(eleccionDinero.equals("1")) {
						break;
					}				
				}
				dinero = usuario.getDinero();
				System.out.println("Cantidad de dinero para utilizar: $" + Math.round(dinero));

				System.out.println("Presione 1 para trasferir dinero a otro usuario");
				System.out.println("Presione cualquier otra tecla para salir: ");
				eleccion = e.next();
				Usuario usuarioTransferencia = null;
				if (eleccion.equals("1")) {
					while(true) {
						try {
							System.out.println("¿Cuanto dinero quieres enviar?");
							System.out.println("Presiona -1 para salir.");
							dinero = e.nextDouble();
							if(dinero == -1) {
								break;
							}
						} catch (InputMismatchException i) {
							System.out.println("No ingreso un dato correcto.");
						}
				
						if (dinero > usuario.getDinero()) {
							System.out.println("No tienes suficiente dinero para transferir.\n");
						} else {
							try {
								if (dinero<0) {
									System.out.println("Error en la cantidad ingresada. No puedes transferir cantidades menores a 0.");
								}else {
									System.out.println("Ingrese el nombre del usuario a quien enviar $" + dinero);
									usuarioTransferencia = usuarios.getUsuario(e.next());
									if(usuarioTransferencia.getTipoUsuario().equalsIgnoreCase("E")) {
										System.out.println("No le puedes transferir dinero a un empleado.");
									}else {
										if (usuarios.existeUsuario(usuarioTransferencia.getUserName())) {
											usuario.setDinero(usuario.getDinero() - dinero);
											usuarioTransferencia.setDinero(usuarioTransferencia.getDinero() + dinero);
											usuarios.editarUsuario(usuarioTransferencia.getUserName(),
													usuarioTransferencia.getPassword(), usuarioTransferencia.getTipoUsuario(),
													(usuarioTransferencia.getDinero() + dinero));
											archiUsuarios.save(usuarios, rutaUsuarios);
											System.out.println("Dinero enviado correctamente a " + usuarioTransferencia.getUserName());
											System.out.println("Cantidad de dinero para utilizar: " + usuario.getDinero());
											break;
										}
									}
									
								}						
							} catch (NullPointerException p) {
								System.out.println("El usuario ingresado no existe en el sistema.");
							}
						}
					}
				}else {
					System.out.println("Gracias por utilizar el programa.");
				}
			}
			if(eleccionInicio.equals("2")){
				Articulos productoStock = new Articulos();
				double totalStock = 0;
				int eleccionPago = 0;
				if (tipoUsuario.equals("C") || tipoUsuario.equals("c")) {
					System.err.println("\nBienvenido/a a " + Empresa.getInstance().getNombreEmpresa()
							+ ", el stock disponible es el siguiente: \n");
					System.out.println("Dinero disponible: " + usuario.getDinero() + "\n");
					Articulos stock = (Articulos) archiArticulos.load(rutaArticulos);
					int nuevaCantidad = 0;
					double comparacionDinero = 0;
					int cantidadTotal = 0;
					while (true) {
						System.err.println("Productos para elegir: ");
						stock.mostrarArticulos();
						System.out.println("\nIngrese el codigo del articulo que desea agregar al carrito: ");
						System.out.println("Presiona 'S' para ir a la seccion de pago");
						String opcion = e.next();
						if (!opcion.equals("S") && !opcion.equals("s")) {
							archiArticulos.createFile("stock.txt");
							Articulo miArticulo = stock.getArticulo(opcion);
							if (miArticulo == null) {
								System.out.println("El articulo no existe.");
							} else {
								String nombre = miArticulo.getNombre();
								double precio = miArticulo.getPrecio();
								int cantidad = miArticulo.getCantidad();
								comparacionDinero = usuario.getDinero();
								int cantidadVenta;
								double precioTotal = 0;
								while(true) {
									System.out.println("¿Cuantas unidades?");
									cantidadVenta = e.nextInt();
									cantidadTotal= cantidadTotal +cantidadVenta;									
									precioTotal = +miArticulo.getPrecio();
									if(cantidadVenta<0) {
										System.out.println("Error en el dato ingresado.");
									}
									else {
										break;
									}
								}								
								if (cantidadVenta > cantidad) {
									System.out.println("No hay suficientes unidades disponibles del articulo seleccionado.\n");
								} else {
									System.err.println("Carrito: \n");
									productoStock.editarArticulo(opcion, miArticulo.getNombre(), miArticulo.getPrecio(),
											cantidadTotal);
									productoStock.addArticulo(opcion, nombre, precio, cantidadVenta);
									productoStock.mostrarCarrito();
									double precioStock = precioTotal * cantidadVenta;
									totalStock = totalStock + precioStock;
									System.out.println("\nPrecio total hasta el momento: $" + totalStock + "\n");
									nuevaCantidad = cantidad - cantidadVenta;
									stock.editarArticulo(miArticulo.getCodigo(), miArticulo.getNombre(), miArticulo.getPrecio(),
											nuevaCantidad);
									
									if (comparacionDinero < totalStock) {
										System.out.println("El monto que quieres gastar es mayor al que posees en tu cuenta.");
										totalStock = 0;
									}
									articulos.editarArticulo(miArticulo.getCodigo(), miArticulo.getNombre(),
											miArticulo.getPrecio(), nuevaCantidad);
									archiArticulos.save(articulos, rutaArticulos);
								}
							}
						} else {
							break;
						}
					}
					while (totalStock != 0) {
						dinero = dinero - totalStock;
						String password = usuario.getPassword();
						String usuarioPago = usuario.getUserName();
						double nuevoDinero = 0;
						System.out.println("Monto total de la compra: " + totalStock);

						System.out.println("¿En cuantos pagos desea hacer la transaccion?");
						System.out.println("Ingresar numero de cuotas: ");
						System.out.println("1 pago de: " + totalStock);
						System.out.println("3 pagos de: " + Math.round(totalStock / 3));
						System.out.println("6 pagos de: " + Math.round(totalStock / 6));
						System.out.println("12 pagos de: " + Math.round(totalStock / 12));
						System.out.println("18 pagos de: " + Math.round(totalStock / 18));
						eleccionPago = e.nextInt();
						if (eleccionPago == 1 || eleccionPago == 3 || eleccionPago == 6 || eleccionPago == 12
								|| eleccionPago == 18) {
							nuevoDinero = usuario.getDinero() - (totalStock / eleccionPago);
							usuario.setDinero(nuevoDinero);
							usuarios.editarUsuario(usuarioPago, password, tipoUsuario, nuevoDinero);
							archiUsuarios.save(usuarios, rutaUsuarios);
							System.out.println("Pago realizado. Dinero en la cuenta: " + Math.round(nuevoDinero));
							break;
						} else {
							System.out.println("Error en la eleccion.");
							System.out.println("Ingrese la opción correcta: ");
							System.out.println("");
						}
					}
					System.err.println("\nFactura: ");
					System.out.println(Empresa.getInstance().getNombreEmpresa());
					System.out.println("CUIT: " + Empresa.getInstance().getCuitEmpresa());
					System.out.println("Cliente: " + usuario.getUserName());
					System.out.println("Articulos comprados: ");
					productoStock.mostrarCarrito();
					System.out.println("Total: $" + totalStock);
					System.out.println("Forma de pago: " + eleccionPago + " cuotas de $" + Math.round(totalStock / eleccionPago));
					e.close();
				}
			}
		}		
		if (archiArticulos.createFile(rutaArticulos)) {
			archiArticulos.save(articulos, rutaArticulos);
		} else {
			articulos = (Articulos) archiArticulos.load(rutaArticulos);
		}
		if (tipoUsuario.equals("E") || tipoUsuario.equals("e")) {
			while (true) {
				System.out.println("Elija una opcion: ");
				System.out.println("1 - Cargar articulo.");
				System.out.println("2 - Buscar articulo.");
				System.out.println("3 - Mostrar articulos.");
				System.out.println("4 - Eliminar articulo.");
				System.out.println("5 - Editar articulo.");
				System.out.println("F - Finalizar programa.");

				switch (e.next().toUpperCase()) {
				case "1":
					System.out.println("Ingrese codigo: ");
					String codigo = e.next();
					System.out.println("Ingrese nombre: ");
					e.nextLine();
					String nombre = e.nextLine();
					double precio = 0;
					int cantidad = 0;
					while(true) {
						System.out.println("Ingrese precio: ");
						precio = e.nextDouble();
						if(precio <= 0) {
							System.out.println("Erorr en el dato ingresado.");
						}else {
							break;
						}
					}
					while(true) {
						System.out.println("Ingrese cantidad disponible: ");
						cantidad = e.nextInt();
						if(cantidad <= 0) {
							System.out.println("Erorr en el dato ingresado.");
						}else {
							break;
						}
					}
					if (articulos.addArticulo(codigo, nombre, precio, cantidad)) {
						System.out.println("El articulos se agrego correctamente.");
					} else {
						System.out.println("Error: Ya existe un articulo con este codigo.");
					}
					archiArticulos.save(articulos, rutaArticulos);
					break;
				case "2":
					System.out.println("Ingrese articulo: ");
					Articulo miArticulo = articulos.getArticulo(e.next());
					if (miArticulo == null) {
						System.out.println("El articulo no existe.");
					} else {
						System.out.println(miArticulo);
					}
					break;
				case "3":
					articulos.mostrarArticulos();
					break;
				case "4":
					articulos.mostrarArticulos();
					System.out.println("Ingrese el codigo del articulo a eliminar: ");
					articulos.eliminarArticulo(e.next());
					System.out.println("Stock disponible: ");
					articulos.mostrarArticulos();
					archiArticulos.save(articulos, rutaArticulos);
					break;
				case "5":
					articulos.mostrarArticulos();
					System.out.println("Ingrese el codigo del articulo a editar: ");
					String nuevoCodigo = e.next();
					System.out.println("Ingrese el nombre del nuevo articulo: ");
					String nuevoNombre = e.next();
					System.out.println("Ingrese el precio del nuevo articulo: ");
					double nuevoPrecio = e.nextDouble();
					System.out.println("Ingrese la nueva cantidad disponible del articulo: ");
					int nuevaCantidad = e.nextInt();
					if (articulos.editarArticulo(nuevoCodigo, nuevoNombre, nuevoPrecio, nuevaCantidad)) {
						System.out.println("El articulos se agregaron correctamente.");
					} else {
						System.out.println("Error: No existe el codigo ingresado.");
					}
					articulos.mostrarArticulos();
					archiArticulos.save(articulos, rutaArticulos);
					break;
				case "F":
					System.out.println("Programa finalizado correctamente");
					System.exit(0);
					break;
				default:
					System.out.println("Opcion no valida.");
				}
			}
		}		
	}
}
