package com.nico.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/formularioRegistro")
	public String formularioRegistro(Model modelo) {
		modelo.addAttribute("cliente", new Cliente());
		return "formulario-registro";
	}

	@PostMapping("/insertarCliente")
	public String procesarFormularioRegistro(@ModelAttribute("cliente") Cliente cliente) {
		clienteDAO.insertarCliente(cliente);
		return "redirect:/cliente/lista";
	}

	@GetMapping("/formularioActualizacion")
	public String formularioActualizacion(Model modelo, @RequestParam("id") int id) {
		Cliente c = clienteDAO.getCliente(id);
		modelo.addAttribute("cliente", c);
		return "formulario-actualizacion";
	}

	@PostMapping("/actualizarCliente")
	public String procesarFormularioActualizacion(@ModelAttribute("cliente") Cliente cliente) {
		clienteDAO.actualizarCliente(cliente);
		return "redirect:/cliente/lista";
	}

	@GetMapping("/eliminarCliente")
	public String eliminarCliente(@RequestParam("id") int id) {
		clienteDAO.eliminarCliente(id);
		return "redirect:/cliente/lista";
	}

}
