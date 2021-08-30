package net.hmanjarres.controller.mapper;


import net.hmanjarres.controller.dto.ClienteDto;
import net.hmanjarres.model.Cliente;

public class ClienteMapper {
	
	public static Cliente toEntity(ClienteDto clienteDto) {
		
		
		return Cliente
				.builder()
				.id(clienteDto.getId())
				.nombre(clienteDto.getNombre())
				.direccion(clienteDto.getDireccion())
				.telefono(clienteDto.getTelefono())
				.build();
	}
	
	public static ClienteDto toDto(Cliente cliente) {
		return ClienteDto
				.builder()
				.id(cliente.getId())
				.nombre(cliente.getNombre())
				.direccion(cliente.getDireccion())
				.telefono(cliente.getTelefono())
				.build();
	}
}
