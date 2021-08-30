package net.hmanjarres.controller.mapper;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import net.hmanjarres.controller.dto.MovimientoDto;
import net.hmanjarres.model.Cuenta;
import net.hmanjarres.model.Movimiento;

public class MovimientoMapper {
	public static Movimiento toEntity(MovimientoDto movimientoDto) {
		return Movimiento
				.builder()
				.id(movimientoDto.getId())
				.tipo(movimientoDto.getTipo())
				.fecha(movimientoDto.getFecha())
				.valor(movimientoDto.getValor())
				.cuenta(Cuenta.builder().id(movimientoDto.getIdCuenta()).build())
				.build();
	}

	public static List<MovimientoDto> toDto(List<Movimiento> movimientosLista) {
		return movimientosLista.stream().map(item->toDto(item)).collect(Collectors.toList());
	}
	
	public static MovimientoDto toDto(Movimiento movimiento) {
		MovimientoDto movimeintoDto=MovimientoDto.builder().build();
		BeanUtils.copyProperties(movimiento, movimeintoDto);
		return movimeintoDto;
		
	}


}
