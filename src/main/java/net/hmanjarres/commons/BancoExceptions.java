package net.hmanjarres.commons;


public class BancoExceptions extends RuntimeException{
	//el super llama el constructor de RuntimeException
	public BancoExceptions(String msj) {
		super(msj);
	}
	
}
