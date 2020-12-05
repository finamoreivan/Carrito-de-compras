package Usuarios;

import java.io.*;

import java.util.*;

public class Usuarios implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6838418568792849715L;
	private String userName;
	private String password;
	private HashMap<String, Usuario> diccionarioUsuarios = new HashMap<String, Usuario>();
	
	/**
	 * Crea y agrega un nuevo usuario al diccionario
	 * 
	 * @param username : el nombre de usuario del nuevo usuario
	 * @param password : la contraseña del usuario
	 * @param tipoUsuario : si el usuario es cliente o empleado
	 * @param dinero : dinero con el que cuenta el cliente.
	 * @return true si se creo el usuario, false si ya existia
	 */
	public boolean addUsuario(String username, String password, String tipoUsuario, double dinero) {
		if(this.existeUsuario(username)) {
			return false;
		}
		Usuario nuevoUsuario = new Usuario(username, password, tipoUsuario, dinero);
		
		diccionarioUsuarios.put(username.toLowerCase(), nuevoUsuario);
		return true;		
	}
	/**
	 * Determina si el usuario existe o no
	 * 
	 * @param username el nombre del usuario en cuestion
	 * @return true si el usuario existe, false si el usuario no existe
	 * **/
	public boolean existeUsuario(String userName) {
		return this.diccionarioUsuarios.containsKey(userName.toLowerCase());
	}
	/**
	 * Trae un usuario a partir de su username
	 * 
	 * @param username el nombre del usuario en cuestion
	 * @return el usuario cuyo username es ingresado por parametro
	 * **/
	public Usuario getUsuario(String username) {
		return this.diccionarioUsuarios.get(username.toLowerCase());
	}
	/**
	 * 
	 * 
	 * @param username : nombre de usuario
	 * @param password : clave
	 * @param tipoUsuario : si el usuario es cliente o empleado
	 * @param dinero : cantidad de dinero del usuario en el sistema
	 * @return edita un usuario del sistema ya creado.
	 */
	public boolean editarUsuario(String username, String password, String tipoUsuario, double dinero) {
		if(this.existeUsuario(username)) {
			return false;
		}
		Usuario nuevoUsuario = new Usuario(username, password, tipoUsuario, dinero);
		
		diccionarioUsuarios.put(username, nuevoUsuario);
		return true;
	}
	@Override
	public String toString() {
		return "Usuario: userName: " + this.userName + ", password:" + this.password;
	}
}
