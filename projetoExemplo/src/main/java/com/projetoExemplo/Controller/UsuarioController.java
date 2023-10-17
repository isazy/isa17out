package com.projetoExemplo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoExemplo.Service.UsuarioService;
import com.projetoExemplo.entities.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name ="Usuarios", description= "API REST DE GERENCIAMENTO DE USUARIO")
@RestController 
@RequestMapping("/usuarios") 
public class UsuarioController {
	private final UsuarioService usuarioService; 

	@Autowired 
	public UsuarioController(UsuarioService usuarioService) { 
		this.usuarioService = usuarioService; 
	} 

	@GetMapping("/{id}") 
	@Operation( summary = "Localiza usuario por ID")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id){ 
		Usuario usuario = usuarioService.getUsuarioById(id); 
		if (usuario != null) { 
			return ResponseEntity.ok(usuario); 
		} 

		else { 
			return ResponseEntity.notFound().build(); 
		} 

	} 
	@GetMapping("/") 
	@Operation( summary = "localiza usuario por ID")
	public ResponseEntity<List<Usuario>> buscaTodosUsuariosControl(){ 
		List<Usuario> usuario = usuarioService.getAllUsuario(); 
		return ResponseEntity.ok(usuario); 
	} 
	
	@PostMapping("/")
	@Operation(summary = "cadastra um usuario")
	public ResponseEntity<Usuario> salvaUsuariosControl(@RequestBody @Valid Usuario usuario){ 
		Usuario salvaUsuario = usuarioService.salvarUsuario(usuario); 
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario); 
	} 

	@PutMapping("/{id}") 
	@Operation(summary = "alterar um usuario")
	public ResponseEntity<Usuario> alteraUsuariosControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario){ 
		Usuario alteraUsuario = usuarioService.updateUsuario(id, usuario); 
		if (alteraUsuario != null) { 
			return ResponseEntity.ok(usuario); 
		} 

		else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	@DeleteMapping("/{id}") 
	@Operation(summary = "deletar um usuario")
	public ResponseEntity<String> apagaUsuarioControl(@PathVariable Long id){ 
		boolean apagar = usuarioService.deleteUsuario(id); 
		if(apagar) { 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		} 

		else {
			return ResponseEntity.notFound().build(); 
		} 
	} 
} 

