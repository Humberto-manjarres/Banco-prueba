package net.hmanjarres.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hmanjarres.commons.BancoExceptions;
import net.hmanjarres.model.Cliente;
import net.hmanjarres.model.Cuenta;
import net.hmanjarres.model.Movimiento;
import net.hmanjarres.repository.ClientesRepository;
import net.hmanjarres.repository.CuentasRepository;
import net.hmanjarres.repository.MovimientosRepository;
import net.hmanjarres.services.ICuentaService;

@Service
public class CuentaService implements ICuentaService{
	
	@Autowired
	private CuentasRepository repoCuentas;
	
	@Autowired
	private ClientesRepository repoClientes;
	
	@Autowired
	private MovimientosRepository repomovimientos;
	
	@Override
	public void create(Cuenta cuenta) {
		Optional<Cliente> optional = repoClientes.findById(cuenta.getCliente().getId());
		if (!optional.isPresent()) {
			throw new BancoExceptions("Cliente NO Existe");
		}
		repoCuentas.save(cuenta);
		
	}

	@Override
	public Cuenta search(int idCuenta) {
		Optional<Cuenta> optionalCuenta = repoCuentas.findById(idCuenta);
		validateExistCuenta(optionalCuenta);
		return optionalCuenta.get();
	}
	
	
	@Override
	public void update(Cuenta cuenta) {
		Optional<Cuenta> optionalCuenta = repoCuentas.findById(cuenta.getId());
		validateExistCuenta(optionalCuenta);
		repoCuentas.save(cuenta);
		
	}
	
	
	@Override
	public void delete(int idCuenta) {
		Optional<Cuenta> optionalCuenta = repoCuentas.findById(idCuenta);
		validateExistCuenta(optionalCuenta);
	
		if (optionalCuenta.get().getSaldo() > 0) {
			throw new BancoExceptions("No se puede eleiminar! la cuenta tiene Saldo.");
		}
		List<Movimiento> listaMovimiento = repomovimientos.findByCuenta_id(idCuenta);
		
		if (!listaMovimiento.isEmpty()) {
			throw new BancoExceptions("No es posible eliminar la cuenta #  "+optionalCuenta.get().getNumero()+" porque tiene movimientos asociados");
		}
		
		repoCuentas.deleteById(idCuenta);
	}
	
	
	private void validateExistCuenta(Optional<Cuenta> c) {
		if (!c.isPresent()) {
			throw new BancoExceptions("Cuenta NO Existe!");
		}
	}

	

	

	
	
	

}
