package net.hmanjarres.model;



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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cuentas")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Double saldo;
	
	@OneToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;
	
	
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", numero=" + numero + ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}
	
	
}
