package net.hmanjarres.controller.dto;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CuentaDto {
	private Integer id;
	private String numero;
	private Double saldo;
	private List<MovimientoDto> movimientos;
	private Integer idCliente;
}
