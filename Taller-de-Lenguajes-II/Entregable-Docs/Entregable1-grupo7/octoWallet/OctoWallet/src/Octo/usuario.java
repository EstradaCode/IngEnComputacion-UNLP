package Octo;

import java.util.List;

public class usuario extends persona{
	private String alias;
	private long Cbu;
	private String contrasena;
	private long idUsuario;
	private Tarjeta tarjeta;
	private List<String> PregSeg;
	private List<Integer> tickets;
	
	public usuario(String nom,String ape,String correo,String pais,long tel,String a,long cvu,String contra,long id,Tarjeta target) {
		super(nom,ape,correo,pais,tel);
		this.alias=a;
		this.Cbu=cvu;
		this.contrasena=contra;
		this.idUsuario=id;
		this.tarjeta=target;
	}
public String getAlias() {
	return alias;
}
public void setAlias(String alias) {
	this.alias = alias;
}
public long getCbu() {
	return Cbu;
}
public void setCbu(long cbu) {
	Cbu = cbu;
}
public String getContrasena() {
	return contrasena;
}
public void setContrasena(String contrasena) {
	this.contrasena = contrasena;
}
public long getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(long idUsuario) {
	this.idUsuario = idUsuario;
}
public Tarjeta getTarjeta() {
	return tarjeta;
}
public void setTarjeta(Tarjeta tarjeta) {
	this.tarjeta = tarjeta;
}
}
