package net.hmanjarres.services;



import net.hmanjarres.model.Cuenta;


public interface ICuentaService {

		void create (Cuenta cuenta);
		Cuenta search(int idCuenta);
		void update(Cuenta cuenta);
		void delete(int idCuenta);
}
