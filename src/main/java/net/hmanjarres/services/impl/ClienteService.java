package net.hmanjarres.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hmanjarres.commons.BancoExceptions;
import net.hmanjarres.model.Cliente;
import net.hmanjarres.model.Cuenta;
import net.hmanjarres.repository.ClientesRepository;
import net.hmanjarres.repository.CuentasRepository;
import net.hmanjarres.services.IClienteService;

@Service
public class ClienteService implements IClienteService{
	
	@Autowired
	private ClientesRepository repoClientes;
	
	@Autowired
	private CuentasRepository repoCuentas;

	@Override
	public void create(Cliente cliente) {
		repoClientes.save(cliente);
		
	}

	@Override
	public Cliente search(int idCliente) {
		Optional<Cliente> clienteOptional =  getCliente(idCliente);
		validateIsExistClient(clienteOptional);
		return clienteOptional.get();
		
	}

	@Override
	public void update(Cliente cliente) {
		Optional<Cliente> clienteOptional = getCliente(cliente.getId());
		validateIsExistClient(clienteOptional);
		repoClientes.save(cliente);
		
	}
	
	
	@Override
	public void delete(int idCliente) {
		Optional<Cliente> clienteOptional = getCliente(idCliente);
		validateIsExistClient(clienteOptional);
		
		List<Cuenta> listaCuentas = repoCuentas.findByCliente_id(idCliente);
		//listaCuentas.forEach(lista -> System.out.println("CUENTA ASOCIADA-> "+lista));
		
		if (!listaCuentas.isEmpty()) {
			throw new BancoExceptions("No es posible eliminar el Cliente "+clienteOptional.get().getNombre()+" porque tiene Cuentas asociadas");
		}
		
		repoClientes.deleteById(idCliente);
		
	}
	
	
	
	private Optional<Cliente> getCliente(int id) {
		return repoClientes.findById(id);
	}
	
	private void validateIsExistClient(Optional<Cliente> c) {
		if (!c.isPresent()) {
			throw new BancoExceptions("Cliente NO Existe");
		}
	}

	
	
	
}
