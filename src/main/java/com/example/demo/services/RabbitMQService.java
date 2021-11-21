package com.example.demo.services;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Dispositivos;
import com.example.demo.repository.DispositivoRepository;

@Service
public class RabbitMQService {

	@Autowired
	private DispositivoRepository disRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public List<Dispositivos> SaveAll(List<Dispositivos> objs){
		return disRepository.saveAll(objs);
	}
	
	public void MensagemRabbit(String nomeFila, Object mensagem) {
		rabbitTemplate.convertAndSend(nomeFila, mensagem);
	}
	
}
