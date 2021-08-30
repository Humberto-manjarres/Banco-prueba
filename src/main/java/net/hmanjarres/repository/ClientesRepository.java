package net.hmanjarres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import net.hmanjarres.model.Cliente;

//public interface ClientesRepository extends CrudRepository<Cliente, Integer> {	}
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
	
}
