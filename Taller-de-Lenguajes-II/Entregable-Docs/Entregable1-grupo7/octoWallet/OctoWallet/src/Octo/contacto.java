package Octo;

public class contacto {
    private String nombre;
    private String Alias;
    private long cbu;
    private String nomB;
    
    public contacto(String nom,String alias,long cbu,String nomB) {
    	this.nombre=nom;
    	this.Alias=alias;
    	this.cbu=cbu;
    	this.nomB=nomB;
    }
    public String getAlias() {
		return Alias;
	}
    public void setAlias(String alias) {
		Alias = alias;
	}
    public long getCbu() {
		return cbu;
	}
    public void setCbu(long cbu) {
		this.cbu = cbu;
	}
    public String getNombre() {
		return nombre;
	}
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    public String getNomB() {
		return nomB;
	}
    public void setNomB(String nomB) {
		this.nomB = nomB;
	}
} 
