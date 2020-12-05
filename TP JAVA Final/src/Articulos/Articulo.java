package Articulos;

import java.io.*;

public class Articulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3693461189495729504L;
	private String codigo;
	private String nombre;
	private double precio;
	private int cantidad;
	
	public Articulo(String codigo, String nombre, double precio, int cantidad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.setCantidad(cantidad);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String toString() {
		return "Codigo " + this.getCodigo()+ ": Nombre: " + this.getNombre() + ", Precio: $" + this.getPrecio() + ", Cantidad: " + this.getCantidad();
	}
}
