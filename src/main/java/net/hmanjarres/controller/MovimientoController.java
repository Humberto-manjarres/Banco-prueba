package net.hmanjarres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hmanjarres.controller.dto.CuentaDto;
import net.hmanjarres.controller.dto.MovimientoDto;

import net.hmanjarres.controller.dto.filtroReporteMovimientoDto;
import net.hmanjarres.controller.mapper.CuentaMapper;
import net.hmanjarres.controller.mapper.MovimientoMapper;

import net.hmanjarres.model.Movimiento;
import net.hmanjarres.services.IMovimientoService;


@RestController
@RequestMapping(value = "api/movimiento")
public class MovimientoController {
	
	@Autowired
	private IMovimientoService serviceMovimiento;
	
	@PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> create(@RequestBody MovimientoDto movimientoDto) {
		
		serviceMovimiento.create(MovimientoMapper.toEntity(movimientoDto));
		
		return new ResponseEntity<String>("Ok" , HttpStatus.OK);
	}
	
	@GetMapping(value = "/reporte",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<CuentaDto>> reporte(@RequestBody filtroReporteMovimientoDto filtroReporteMovimientoDto) {
		
		List<Movimiento> movimientos=serviceMovimiento
				.reporteMovimiento(filtroReporteMovimientoDto.getFechaInicio(),filtroReporteMovimientoDto.getFechaFin(),
						filtroReporteMovimientoDto.getIdCliente());
		
		return new ResponseEntity<>(CuentaMapper.toCuentaDtoList(movimientos),HttpStatus.OK);
	}
	
}
