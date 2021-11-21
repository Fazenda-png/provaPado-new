package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Dispositivos;
import com.example.demo.repository.DispositivoRepository;
import com.example.demo.services.exception.ResourceNotFoundException;

@Service
public class DispositivoService {
	
	@Autowired
	private DispositivoRepository disRepository;
	
	public List<Dispositivos> findAll(){
		return disRepository.findAll();
	}
 
	public Dispositivos findById(String Id) {
		Optional<Dispositivos> obj = disRepository.findById(Id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(Id));
	}

	public Dispositivos insert(Dispositivos obj) {
		return disRepository.save(obj);
	}
	
	
	public String mensagem(ResponseEntity<Dispositivos> result) {
		JSONObject newObj = new JSONObject();
		newObj.put("deviceId", result.getBody().getDeviceId());
		newObj.put("mac", result.getBody().getMac());
		return newObj.toString();
	}
}
