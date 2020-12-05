package Articulos;

import java.io.*;

import java.util.*;

public class Articulos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2254188526020162849L;
	private String codigo;
	private String nombre;
	private double precio;
	private int cantidad;
	private HashMap<String, Articulo> listaArticulos = new HashMap<String, Articulo>();
	/**
	 * Determina si el codigo existe o no
	 * 
	 * @param codigo : el codigo el producto que se verá si ya esta en el sistema
	 * @return true si el codigo existe, false si el codigo no existe
	 * **/
	public boolean existeArticulo(String codigo) {
		return this.listaArticulos.containsKey(codigo.toLowerCase());
	}
	/**
	 * Crea y agrega un nuevo articulo al diccionario
	 * 
	 * @param codigo : el codigo del articulo
	 * @param nombre : el nombre del articulo
	 * @param precio : el precio del articulo
	 * @param cantidad : la cantidad disponible del articulo
	 * @return true si se creo el articulo, false si ya existia
	 */
	public boolean addArticulo(String codigo, String nombre, double precio, int cantidad) {
		if(this.existeArticulo(codigo)) {
			return false;
		}
		
		Articulo nuevoArticulo = new Articulo(codigo, nombre, precio, cantidad);
		
		listaArticulos.put(codigo, nuevoArticulo);
		return true;
	}
	/**
	 * Trae un articulo a partir de su codigo
	 * 
	 * @param codigo : el codigo del articulo en cuestion
	 * @return el articulo del codigo ingresado por parametro
	 * **/
	public Articulo getArticulo(String codigo) {
		return this.listaArticulos.get(codigo.toLowerCase());
	}
	/**
	 * Muestra los articulos cargados en el sistema
	 * 
	 * @return El listado de articulos cargados en el sistema
	 * 
	 */
	public void mostrarArticulos() {
		for(String codigo : listaArticulos.keySet()) {
			System.out.println(listaArticulos.get(codigo));
		}	
	}
	/**
	 * Elimina un articulo cargado en el sistema
	 * 
	 * @param codigo: el codigo del articulo a eliminar
	 * @return Elimina el articulo
	 */
	public void eliminarArticulo(String codigo) {
		if(listaArticulos.containsKey(codigo)){
			listaArticulos.remove(codigo);
        }
	}
	/**
	 * Verifica si el codigo no existe en el sistema
	 * 
	 * @param codigo : el codigo ingresado
	 * @return El codigo ingresado no existe en el sistema
	 */
	public boolean existeCodigo(String codigo) {
		return !this.listaArticulos.containsKey(codigo.toLowerCase());
	}
	/**
	 * Edita un articulo completo del diccionario
	 * 
	 * @param codigo : codigo del articulo
	 * @param nombre : nombre del articulo
	 * @param precio : precio del articulo
	 * @param cantidad : cantidad del articulo
	 * @return edita un articulo ya creado en el sistema.
	 */
	public boolean editarArticulo(String codigo, String nombre, double precio, int cantidad) {
		if(this.existeCodigo(codigo)) {
			return false;
		}
		Articulo nuevoArticulo = new Articulo(codigo, nombre, precio, cantidad);
		
		listaArticulos.put(codigo, nuevoArticulo);
		return true;
	}
	/**
	 * Agrega un nuevo articulo al diccionario
	 * 
	 * @param codigo : el codigo del articulo
	 * @param nombre : el nombre del articulo
	 * @param precio : el precio del articulo
	 * @param cantidad : la cantidad de unidades seleccionadas
	 * @return crea y agrega un articulo al carrito de compra
	 */
	public void addCarrito(String codigo, String nombre, double precio, int cantidad) {

		Articulo articuloCarrito = new Articulo(codigo, nombre, precio, 1);
		
		listaArticulos.put(codigo, articuloCarrito);
	}
	/**
	 * Muestra los articulos cargados en el sistema
	 * 
	 * @return El listado de articulos en el carrito de compra
	 * 
	 */
	public void mostrarCarrito() {
		for(String codigo : listaArticulos.keySet()) {
			System.out.println(listaArticulos.get(codigo));
		}
	}
	public String toString() {
		return "Codigo " + this.codigo+ ": Nombre: " + this.nombre + ", Precio: $" + this.precio + ", Cantidad: " + this.cantidad; 
	}
	
}
