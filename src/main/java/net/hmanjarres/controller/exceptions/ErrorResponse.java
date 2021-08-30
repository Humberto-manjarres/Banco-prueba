package net.hmanjarres.controller.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ErrorResponse {
	private Integer status;
	private String msj;
	private String fecha;
	
	public ErrorResponse(Integer status, String msj) {
		this.status = status;
		this.msj = msj;
		this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
	}
	
	
}
