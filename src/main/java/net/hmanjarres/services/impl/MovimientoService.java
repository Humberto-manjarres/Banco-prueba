package net.hmanjarres.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hmanjarres.commons.BancoExceptions;
import net.hmanjarres.model.Cuenta;
import net.hmanjarres.model.Movimiento;
import net.hmanjarres.repository.CuentasRepository;
import net.hmanjarres.repository.MovimientosRepository;
import net.hmanjarres.services.IMovimientoService;

@Service
public class MovimientoService implements IMovimientoService {
	
	private static final String MOVIMIENTO_DEBITO = "D";
	private static final String MOVIMIENTO_CREDITO = "C";
	
	@Autowired
	private MovimientosRepository repoMovimiento;
	
	@Autowired
	private CuentasRepository repoCuenta;
	
	@Override
	public void create(Movimiento movimiento) {
		Optional<Cuenta> cuentaOptional = repoCuenta.findById(movimiento.getCuenta().getId());
		if (!cuentaOptional.isPresent()) {
			throw new BancoExceptions("Cuenta No Existe!");
		}
		
		if (movimiento.getValor() <= 0) {
			throw new BancoExceptions("El Movimiento debe ser mayo a 0");
		}
		
		double nuevoSaldo=0;
		if (MOVIMIENTO_DEBITO.equalsIgnoreCase(movimiento.getTipo())) {
			nuevoSaldo = cuentaOptional.get().getSaldo() - movimiento.getValor();
		}else if(MOVIMIENTO_CREDITO.equalsIgnoreCase(movimiento.getTipo())){
			nuevoSaldo = cuentaOptional.get().getSaldo() + movimiento.getValor();
		}else {
			throw new BancoExceptions("Movimiento desconocido, es Credito o Debito");
		}
		
		if (nuevoSaldo < 0) {
			throw new BancoExceptions("Saldo insuficiente!");
		}
		
		repoMovimiento.save(movimiento);
		cuentaOptional.get().setSaldo(nuevoSaldo);
		
		repoCuenta.save(cuentaOptional.get());

	}

	@Override
	public List<Movimiento> reporteMovimiento(Date fechaInicio, Date fechaFin, int idCliente) {
		if (fechaInicio.after(fechaFin)) {
			throw new BancoExceptions("Fecha inicio debe ser menor que la fecha fin!");
		}
		List<Movimiento> listaMovimiento = repoMovimiento.reporteMovimiento(fechaInicio, fechaFin, idCliente);
		return listaMovimiento;
	}

	
}
