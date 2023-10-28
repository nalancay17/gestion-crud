package com.nico.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nico.entidad.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	@Transactional
	public Usuario getUsuario(String nombreUsuario) {
		Session session = factory.getCurrentSession();
		Usuario u = session.get(Usuario.class, nombreUsuario);
		return u;
	}

}
