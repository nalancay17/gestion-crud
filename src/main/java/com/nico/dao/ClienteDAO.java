package com.nico.dao;

import java.util.List;

import com.nico.entidad.Cliente;

public interface ClienteDAO {

	public List<Cliente> getClientes();

	public void insertarCliente(Cliente cliente);

}
