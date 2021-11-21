package com.example.demo.connection;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.example.demo.constants.Constants;

@Component
public class RabbitMQConnect {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	
	private AmqpAdmin amqp;
	
	public RabbitMQConnect(AmqpAdmin amqp) {
		this.amqp = amqp;
	}
	
	private Queue fila(String nomeFila){
		return new Queue(nomeFila, true, false, false);
	}

	private DirectExchange dirExchange() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding binding(Queue fila, DirectExchange dirExchange) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, dirExchange.getName(), fila.getName(), null );
	}
	
	@PostConstruct
	private void acrescent() {
		Queue Queuedis = this.fila(Constants.FILA_DISPOSITIVO);
		DirectExchange dirExchange = this.dirExchange();
		
		Binding ligacao = this.binding(Queuedis, dirExchange);
		
		this.amqp.declareQueue(Queuedis);
		
		this.amqp.declareExchange(dirExchange);
		
		this.amqp.declareBinding(ligacao);
		
	}
}
