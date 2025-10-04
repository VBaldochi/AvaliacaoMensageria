package com.faculdade.pubsub;

import com.faculdade.pubsub.service.ReservaService;
import com.google.cloud.pubsub.v1.*;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Consumer Pub/Sub integrado com Spring Boot para processar reservas de hotelaria
 */
@Component
public class PubSubConsumerSpring implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(PubSubConsumerSpring.class);
    
    @Value("${google.cloud.project-id}")
    private String projectId;
    
    @Value("${google.cloud.pubsub.subscription}")
    private String subscriptionName;
    
    @Autowired
    private ReservaService reservaService;
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("Iniciando PubSub Consumer...");
        logger.info("Project ID: {}", projectId);
        logger.info("Subscription: {}", subscriptionName);
        
        consumeMessages();
    }
    
    public void consumeMessages() {
        ProjectSubscriptionName subscription = ProjectSubscriptionName.of(projectId, subscriptionName);
        
        // Configuração do receiver de mensagens
        MessageReceiver receiver = (PubsubMessage message, AckReplyConsumer consumer) -> {
            try {
                String messageData = message.getData().toStringUtf8();
                logger.info("Mensagem recebida: {}", messageData);
                
                // Processar reserva usando o service
                reservaService.processarReserva(messageData);
                
                // Confirmar processamento da mensagem
                consumer.ack();
                logger.info("Mensagem processada e confirmada com sucesso");
                
            } catch (Exception e) {
                logger.error("Erro ao processar mensagem: {}", e.getMessage(), e);
                // Em caso de erro, nack para reprocessar
                consumer.nack();
            }
        };
        
        Subscriber subscriber = null;
        try {
            // Configurar subscriber
            subscriber = Subscriber.newBuilder(subscription, receiver)
                    .setMaxAckExtensionPeriod(org.threeten.bp.Duration.ofSeconds(600))
                    .build();
            
            logger.info("Iniciando subscriber para subscription: {}", subscription);
            subscriber.startAsync().awaitRunning();
            
            logger.info("Consumer ativo. Aguardando mensagens...");
            logger.info("Pressione Ctrl+C para parar o consumer");
            
            // Aguardar indefinidamente (até ser interrompido)
            while (true) {
                try {
                    Thread.sleep(5000);
                    logger.debug("Consumer ativo - aguardando mensagens...");
                } catch (InterruptedException e) {
                    logger.info("Consumer interrompido");
                    break;
                }
            }
            
        } catch (Exception e) {
            logger.error("Erro no consumer: {}", e.getMessage(), e);
        } finally {
            if (subscriber != null) {
                subscriber.stopAsync();
                try {
                    subscriber.awaitTerminated(30, TimeUnit.SECONDS);
                    logger.info("Subscriber finalizado");
                } catch (TimeoutException e) {
                    logger.warn("Timeout ao finalizar subscriber");
                }
            }
        }
    }
}
