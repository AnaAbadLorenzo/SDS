package com.sds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.security.JWToken;
import com.sds.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	public IUsuarioService usuarioService;
	
	@Autowired
	public JWToken jwtToken;
	
	/*@PostMapping("/login")
	public ResponseEntity<?> loginUser(String nombreUsuario, String passwdUsuario){
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(nombreUsuario, passwdUsuario));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token = jwtToken.generateToken(auth);
		
		return ResponseEntity.ok(new TokenAuth(token));
	}*/
	
	/*@GetMapping("/listarUsuarios")
	public List<UsuarioE> listarUsuarios(){
		
		List<UsuarioE> listaUsuarios = new ArrayList<UsuarioE>();
		
		listaUsuarios=usuarioService.listarUsuarios();
		
		return listaUsuarios;
	}
	
	@GetMapping("/buscarUsuario")
	
	public UsuarioE buscarUsuario(String email) {
		
		UsuarioE user = null;
		
		user=usuarioService.buscarUsuario(email);
		
		return user;
	}
	
	@PostMapping("/insertarUsuario")
	
	public void insertarUsuario(String dniUsuario, String usuario, String passwdUsuario, Integer borradoUsuario) {
		UsuarioE user = null;
		
		user=usuarioService.buscarUsuario(dniUsuario);
		
		if(user == null) {
			usuarioService.insertarUsuario(dniUsuario, usuario, passwdUsuario, borradoUsuario);
		}else {
			//Error el usuario ya existe
		}
	}
	
	@PutMapping("/modificarUsuario/{idUsuario}")
	public void modificarUsuario(String dniUsuario, String usuario, String passwdUsuario,Integer borradoUsuario, @PathVariable("idUsuario")
				Integer idUsuario) throws BadRequestException{
		UsuarioE user = null;
		
		user = usuarioService.buscarUsuarioId(idUsuario);
		
		if(user == null) {
			throw new BadRequestException("El usuario no existe");
		}else {
			usuarioService.modificarUsuario(dniUsuario,usuario,passwdUsuario,borradoUsuario, idUsuario);
		}
	}
	
	@DeleteMapping("/eliminarUsuario/{idUsuario}")
	
	public void eliminarUsuario( @PathVariable("idUsuario")Integer idUsuario) throws BadRequestException {
		UsuarioE user = null;
		
		user = usuarioService.buscarUsuarioId(idUsuario);
		
		if(user == null) {
			throw new BadRequestException("El usuario no existe");
		}else {
			usuarioService.eliminarUsuario(idUsuario);
		}
	}*/


}
