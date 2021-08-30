package net.hmanjarres.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDto {
	private Integer id;
	private String nombre;
	private String direccion;
	private String telefono;
	
	
}
