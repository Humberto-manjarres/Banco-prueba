package net.hmanjarres.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import net.hmanjarres.model.Cuenta;

public interface CuentasRepository extends JpaRepository<Cuenta, Integer> {
	List<Cuenta> findByCliente_id(int idCliente);
	
}
