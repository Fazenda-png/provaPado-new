package com.example.demo.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.constants.Constants;
import com.example.demo.domain.Dispositivos;
import com.example.demo.services.DispositivoService;
import com.example.demo.services.RabbitMQService;

@RestController
@RequestMapping(value = "/")
public class DispositivoResource {

	@Autowired
	private DispositivoService disService;
	
	@Autowired
	private RabbitMQService mqService;

	@GetMapping(value = "/listar")
	public ResponseEntity<List<Dispositivos>> findAll() {
		List<Dispositivos> list = disService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/listar/deviceId/{id}")
	public ResponseEntity<Dispositivos> findById(@PathVariable String id) {
		Dispositivos obj = disService.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insert(@RequestBody Dispositivos obj) {
		obj = disService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(obj.getDeviceId()).toUri();
		ResponseEntity<Dispositivos> result = ResponseEntity.created(uri).body(obj);
		String objNew = disService.mensagem(result);
		return new ResponseEntity<String>(objNew, HttpStatus.CREATED);
	}

	@PostMapping(value = "/registrar/async")
	public ResponseEntity<List<Dispositivos>> SaveAll(@RequestBody List<Dispositivos> objs) {
		objs = mqService.SaveAll(objs);
		mqService.MensagemRabbit(Constants.FILA_DISPOSITIVO, objs);
		return new ResponseEntity<List<Dispositivos>>(HttpStatus.ACCEPTED);
	}

}
