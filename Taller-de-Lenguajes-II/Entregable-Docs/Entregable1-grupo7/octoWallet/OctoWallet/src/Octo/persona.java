package Octo;

public abstract class persona {
    private String nombre;
    private String apellido;
    private String correo;
    private String pais;
    private long telefono;
    
    public persona(String nom,String ape,String correo,String pais,long tel){
    	this.nombre=nom;
    	this.apellido=ape;
    	this.correo=correo;
    	this.telefono=tel;
    	this.pais=pais;
		
	}
    public String getApellido() {
		return apellido;
	}
    public void setApellido(String apellido) {
		this.apellido = apellido;
	}
    public String getNombre() {
		return nombre;
	}
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    public String getCorreo() {
		return correo;
	}
    public void setCorreo(String correo) {
		this.correo = correo;
	}
    public String getPais() {
		return pais;
	}
    public void setPais(String pais) {
		this.pais = pais;
	}
    public long getTelefono() {
		return telefono;
	}
    public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
}
