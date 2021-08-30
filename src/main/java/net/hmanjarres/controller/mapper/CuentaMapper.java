package net.hmanjarres.controller.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;


import net.hmanjarres.controller.dto.CuentaDto;
import net.hmanjarres.controller.dto.MovimientoDto;
import net.hmanjarres.model.Cliente;
import net.hmanjarres.model.Cuenta;
import net.hmanjarres.model.Movimiento;

public class CuentaMapper {
	public static Cuenta toEntity(CuentaDto cuentaDto) {
		return Cuenta 
				.builder()
				.id(cuentaDto.getId())
				.numero(cuentaDto.getNumero())
				.saldo(cuentaDto.getSaldo())
				.cliente(Cliente.builder().id(cuentaDto.getIdCliente()).build())
				.build();
				
	}
	
	public static CuentaDto entityToDto(Cuenta cuenta) {
		return CuentaDto
				.builder()
				.id(cuenta.getId())
				.numero(cuenta.getNumero())
				.saldo(cuenta.getSaldo())
				.idCliente(cuenta.getCliente().getId())
				.build();
		
	}
	
	public static List<CuentaDto> toCuentaDtoList(List<Movimiento> movimientoLista){
		
		Map<Cuenta,List<Movimiento>> gruposCuentas= movimientoLista.stream().collect(Collectors.groupingBy(Movimiento::getCuenta));
		List<CuentaDto> cuentasLista=new ArrayList<CuentaDto>();
		
		for (Map.Entry<Cuenta, List<Movimiento>> entry : gruposCuentas.entrySet()) {
			
			CuentaDto cuentaDto= toDto(entry.getKey());
			List<MovimientoDto> movimientosDtoList=MovimientoMapper.toDto(entry.getValue()); 
			cuentaDto.setMovimientos(movimientosDtoList);
			cuentasLista.add(cuentaDto);
	    }
		
		
		
		return cuentasLista;
		
	}

	private static  CuentaDto toDto(Cuenta cuenta) {
		CuentaDto cuentaDto=CuentaDto.builder().build();
		BeanUtils.copyProperties(cuenta,cuentaDto);
		cuentaDto.setIdCliente(cuenta.getCliente().getId());
		return cuentaDto;
	
	}
}

