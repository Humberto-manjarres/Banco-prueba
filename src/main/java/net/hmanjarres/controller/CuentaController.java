package net.hmanjarres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.hmanjarres.controller.dto.CuentaDto;

import net.hmanjarres.controller.mapper.CuentaMapper;

import net.hmanjarres.services.ICuentaService;

@RestController
@RequestMapping(value = "api/cuenta")
public class CuentaController {
	
	@Autowired
	private ICuentaService serviceCuenta;
	
	@PostMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> create(@RequestBody CuentaDto cuentaDto) {
		serviceCuenta.create(CuentaMapper.toEntity(cuentaDto));
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/search/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<CuentaDto> search(@PathVariable("id") int idCuenta) {
		
		CuentaDto cuentaDto = CuentaMapper.entityToDto(serviceCuenta.search(idCuenta));
		
		return new ResponseEntity<CuentaDto>(cuentaDto , HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> update(@RequestBody CuentaDto cuentaDto) {
		
		serviceCuenta.update(CuentaMapper.toEntity(cuentaDto));
		
		return new ResponseEntity<String>("Ok" , HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> delete(@PathVariable("id") int idCliente) {
		serviceCuenta.delete(idCliente);
		return new ResponseEntity<String>("Ok" , HttpStatus.OK);
	}
	
	
}


