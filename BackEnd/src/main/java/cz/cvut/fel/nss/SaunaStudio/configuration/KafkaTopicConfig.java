package cz.cvut.fel.nss.SaunaStudio.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Konfigurační třída pro nastavení Kafky.
 * Tato třída obsahuje konfiguraci pro vytvoření Kafky, která je použita
 * pro zasílání a přijímání zpráv v rámci aplikace.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Definuje bean pro téma Kafka s názvem "reservation".
     *
     * <p>Téma je použito pro správu rezervací a umožňuje odesílání a přijímání zpráv
     * v rámci tohoto tématu.</p>
     *
     * @return instance {@link NewTopic} s názvem "reservation".
     */
    @Bean
    public NewTopic ReservationTopic() {
        return TopicBuilder.name("reservation")
                .build();
    }
}
