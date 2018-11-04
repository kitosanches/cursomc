package com.marcus.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcus.cursomc.domain.Categoria;
import com.marcus.cursomc.domain.Cliente;
import com.marcus.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	@RequestMapping(value="/{id}", method =RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		 return ResponseEntity.ok(this.clienteService.find(id));
		}
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll(){
		 return ResponseEntity.ok(this.clienteService.findAll());
		}
	
	 @RequestMapping(method =RequestMethod.POST)
	 public ResponseEntity<Void> insert (@RequestBody Cliente obj) {
		 obj = clienteService.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	 }

}
