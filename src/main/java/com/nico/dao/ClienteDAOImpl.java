package com.nico.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nico.entidad.Cliente;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	@Transactional
	public List<Cliente> getClientes() {
		Session session = factory.openSession();
		Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void insertarCliente(Cliente cliente) {
		Session session = factory.openSession();
		session.save(cliente);
	}

}
