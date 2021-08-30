package net.hmanjarres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.hmanjarres.controller.dto.ClienteDto;
import net.hmanjarres.controller.mapper.ClienteMapper;
import net.hmanjarres.services.IClienteService;



@RestController
@RequestMapping(value = "api/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService serviceCliente;
	
	@PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> create(@RequestBody ClienteDto clienteDto) {
		
		serviceCliente.create(ClienteMapper.toEntity(clienteDto));
		
		//cuerpo Ok y cabecera Http.OK
		return new ResponseEntity<String>("Ok" , HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/search/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ClienteDto> search(@PathVariable("id") int idCliente) {
		
		ClienteDto clienteDto = ClienteMapper.toDto(serviceCliente.search(idCliente));
		
		return new ResponseEntity<ClienteDto>(clienteDto , HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> update(@RequestBody ClienteDto clienteDto) {
		
	serviceCliente.update(ClienteMapper.toEntity(clienteDto));
		
		return new ResponseEntity<String>("Ok" , HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> delete(@PathVariable("id") int idCliente) {
		serviceCliente.delete(idCliente);
		
		return new ResponseEntity<String>("Ok" , HttpStatus.OK);
	}

}
