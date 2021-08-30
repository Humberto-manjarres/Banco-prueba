package net.hmanjarres.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "movimiento")
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tipo;
	private Date fecha;
	private Double valor;
	
	@OneToOne
	@JoinColumn(name="idcuenta")
	private Cuenta cuenta;

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", tipo=" + tipo + ", fecha=" + fecha + ", valor=" + valor + ", cuenta="
				+ cuenta + "]";
	}
	
	
	
}
