package Utiles;

public class Empresa {
	
	private static Empresa singleton;
	
	private String nombreEmpresa = "El almacen del Grupo 1";
	
	private String cuitEmpresa = "30-17112020-1";
	
	public static Empresa getInstance() {
		
		if(singleton == null) {
			singleton = new Empresa();
		}
		return singleton;
	}
	private Empresa() {
		
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getCuitEmpresa() {
		return cuitEmpresa;
	}
	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}
	
	
}

	
