package net.hmanjarres.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.hmanjarres.commons.BancoExceptions;
import net.hmanjarres.model.Cuenta;
import net.hmanjarres.model.Movimiento;
import net.hmanjarres.repository.CuentasRepository;
import net.hmanjarres.repository.MovimientosRepository;

class MovimientoServiceTest {
	
	@InjectMocks
	private MovimientoService serviceMovimiento;
	
	@Mock
	private CuentasRepository repoCuenta;
	
	@Mock
	private MovimientosRepository repoMovimiento;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void clienteNoExiste() {
	
		Cuenta cuenta = new Cuenta();
		cuenta.setId(8);
		Movimiento movimiento = new Movimiento();
		movimiento.setCuenta(cuenta);
		
		when(repoCuenta.findById(cuenta.getId())).thenReturn(Optional.empty());
		
		Exception ex= assertThrows(BancoExceptions.class,
				()->serviceMovimiento.create(movimiento));
		
		String actual=ex.getMessage();
		String esperado="Cuenta No Existe!";
		
		assertEquals(esperado,actual);
		
	}
	
	@Test
	public void saldoInsuficiente() {
		Cuenta cuenta = new Cuenta();
		cuenta.setId(1);
		cuenta.setNumero("9879879");
		cuenta.setSaldo(50000.0);
		
		Movimiento movimiento = new Movimiento();
		movimiento.setTipo("D");
		movimiento.setValor(100000.0);
		movimiento.setCuenta(cuenta);
		
		
		
		when(repoCuenta.findById(cuenta.getId())).thenReturn(Optional.of(cuenta));
		
		Exception ex= assertThrows(BancoExceptions.class,
				()->serviceMovimiento.create(movimiento));
		
		String actual=ex.getMessage();
		String esperado="Saldo insuficiente!";
		
		assertEquals(esperado,actual);
		
	}
	
	@Test
	public void save() {
		Cuenta cuenta = new Cuenta();
		cuenta.setId(1);
		cuenta.setNumero("9879879");
		cuenta.setSaldo(50000.0);
		
		Movimiento movimiento = new Movimiento();
		movimiento.setTipo("D");
		movimiento.setValor(10000.0);
		movimiento.setCuenta(cuenta);
		
		
		
		when(repoCuenta.findById(cuenta.getId())).thenReturn(Optional.of(cuenta));
		
		serviceMovimiento.create(movimiento);
		verify(repoMovimiento,times(1)).save(movimiento);
		verify(repoCuenta,times(1)).save(cuenta);
	}
	
	@Test
	public void pruebaReporte() {
		Date fechaInicio = new Date();
		Date fechaFin = new Date();
		int idCliente = 3;
		List<Movimiento> listaM = new LinkedList<Movimiento>();
		when(repoMovimiento.reporteMovimiento(fechaInicio, fechaFin, idCliente)).thenReturn(listaM);
		assertEquals(0, listaM.size());
		
	}
	
	@Test
	public void validacionFechas() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechaInicio = formato.parse("23/11/2015");
		Date fechaFin = formato.parse("23/11/2012");
		
		Exception ex= assertThrows(BancoExceptions.class,
				()->serviceMovimiento.reporteMovimiento(fechaInicio, fechaFin, 1));
		

		String actual=ex.getMessage();
		String esperado="Fecha inicio debe ser menor que la fecha fin!";
		assertEquals(esperado, actual);
		
	}

}
