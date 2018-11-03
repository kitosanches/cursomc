package com.marcus.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.marcus.cursomc.dto.CategoriaDTO;
import com.marcus.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/categorias")

public class CategoriaResource {
	@Autowired
	private CategoriaService categoriaService;
 @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
	 return ResponseEntity.ok(this.categoriaService.find(id));
	}
 @RequestMapping(method =RequestMethod.POST)
 public ResponseEntity<Void> insert (@RequestBody Categoria obj) {
	 obj = categoriaService.insert(obj);
	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	 return ResponseEntity.created(uri).build();
 }
 @RequestMapping(value="/{id}", method=RequestMethod.PUT)
 public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) throws ObjectNotFoundException {
	 obj.setId(id);
	 obj = categoriaService.update(obj);
	 return ResponseEntity.noContent().build();
 }
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
	 categoriaService.delete(id);
	 return ResponseEntity.noContent().build();
	}
 @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
	 List<Categoria> lista = (this.categoriaService.findAll());
	 List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	 return ResponseEntity.ok().body(listaDTO);
	}
}
