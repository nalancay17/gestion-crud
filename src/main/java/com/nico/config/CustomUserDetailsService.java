package com.nico.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nico.dao.UsuarioDAO;
import com.nico.entidad.Usuario;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDAO.getUsuario(username);
		if (usuario == null)
			throw new UsernameNotFoundException("El usuario ingresado no existe");

		Set<GrantedAuthority> permisos = usuario
				.getPermisos()
				.stream()
				.map(permiso -> new SimpleGrantedAuthority(permiso.getNombre()))
				.collect(Collectors.toSet());

		return new User(username, usuario.getContrasena(), permisos);
	}

}
