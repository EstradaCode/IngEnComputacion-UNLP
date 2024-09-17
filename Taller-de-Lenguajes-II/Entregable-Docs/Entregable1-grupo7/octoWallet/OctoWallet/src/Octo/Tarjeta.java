package Octo;

public abstract class Tarjeta {
	private String nom_ape;
	private long num_tarjeta;
	private String fecha_S;
	private String fecha_V;
	private int num_seg;
	private boolean es_v;
	private int cod_v;
	private double limD;
	
	public Tarjeta(String nom_a,long numT,String fechaS,String fechaV,int numS,boolean esV,double limD) {
		this.nom_ape=nom_a;
		this.num_tarjeta=numT;
		this.fecha_S=fechaS;
		this.fecha_V=fechaV;
		this.num_seg=numS;
		this.es_v=esV;
		this.limD=limD;
	}
  public String getNom_ape() {
	return nom_ape;
  }
  public void setNom_ape(String nom_ape) {
	this.nom_ape = nom_ape;
}
  public long getNum_tarjeta() {
	return num_tarjeta;
}
  public void setNum_tarjeta(long num_tarjeta) {
	this.num_tarjeta = num_tarjeta;
}
  public String getFecha_S() {
	return fecha_S;
}
  public void setFecha_S(String fecha_S) {
	this.fecha_S = fecha_S;
}
  public String getFecha_V() {
	return fecha_V;
}
  public void setFecha_V(String fecha_V) {
	this.fecha_V = fecha_V;
}
  public int getNum_seg() {
	return num_seg;
}
  public void setNum_seg(int num_seg) {
	this.num_seg = num_seg;
}
  public double getLimD() {
	return limD;
}
 public void setLimD(double limD) {
	this.limD = limD;
}
 public boolean isEs_v() {
	return es_v;
}
 public void setEs_v(boolean es_v) {
	this.es_v = es_v;
}
}
