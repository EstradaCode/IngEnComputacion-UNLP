package Octo;

public class Debito extends Tarjeta {
	private String monedaA;
	private double Saldo;
	
	public Debito(String nom_a,long numT,String fechaS,String fechaV,int numS,boolean esV,double limD,String mon,double S) {
		super(nom_a,numT,fechaS,fechaV,numS,esV,limD);
		this.monedaA=mon;
		this.Saldo=S;
	}
public String getMonedaA() {
	return monedaA;
}
public void setMonedaA(String monedaA) {
	this.monedaA = monedaA;
}
public double getSaldo() {
	return Saldo;
}
public void setSaldo(double saldo) {
	Saldo = saldo;
}
}
