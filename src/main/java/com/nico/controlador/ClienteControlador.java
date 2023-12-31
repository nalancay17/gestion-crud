package com.nico.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nico.dao.ClienteDAO;
import com.nico.entidad.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

	@Autowired
	private ClienteDAO clienteDAO;

	@GetMapping("/lista")
	public String listaClientes(Model modelo) {
		List<Cliente> clientes = clienteDAO.getClientes();
		modelo.addAttribute("clientes", clientes);
		return "lista-clientes";
	}

}
