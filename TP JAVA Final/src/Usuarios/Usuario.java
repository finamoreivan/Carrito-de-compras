package Usuarios;

import java.io.*;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 881253194408850016L;
	private String username;
	private String password;
	private String tipoUsuario;
	private double dinero;

	public Usuario(String userName, String password, String tipoUsuario, double dinero) {
		super();
		this.username = userName;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
		this.dinero = dinero;
	}
	public String getUserName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String setPassword(String password) {
		return this.password = password;
	}
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public boolean validar(String password) {
		return this.getPassword().equals(password);
	}
	@Override
	public String toString() {
		return "Usuario: username: " + this.username + ", password: " + this.password;
	}
	
}
