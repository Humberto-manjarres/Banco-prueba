package net.hmanjarres.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.hmanjarres.model.Movimiento;

public interface MovimientosRepository extends JpaRepository<Movimiento, Integer> {
	
	@Query(value = "SELECT mov.* "
			+ "from movimiento mov, cuentas cuen, clientes cli "
			+ "WHERE cli.id = cuen.idCliente and cuen.id = mov.idCuenta and cli.id = :idCliente "
			+ "and mov.fecha BETWEEN :fechaInicio and :fechaFin ",nativeQuery = true)
	List<Movimiento> reporteMovimiento(@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin,@Param("idCliente") int idCliente);
	
	List<Movimiento> findByCuenta_id(int idCuenta);
}
