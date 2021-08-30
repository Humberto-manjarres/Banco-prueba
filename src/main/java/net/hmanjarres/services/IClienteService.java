package net.hmanjarres.services;

import net.hmanjarres.model.Cliente;

public interface IClienteService {
	void create(Cliente cliente);
	Cliente search(int idCliente);
	void update(Cliente cliente);
	void delete(int idCliente);
}
