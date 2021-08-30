package net.hmanjarres.services;



import java.util.Date;
import java.util.List;

import net.hmanjarres.model.Movimiento;

public interface IMovimientoService {
	void create(Movimiento movimiento);
	List<Movimiento> reporteMovimiento(Date fechaInicio, Date fechaFin, int idCliente);
}
