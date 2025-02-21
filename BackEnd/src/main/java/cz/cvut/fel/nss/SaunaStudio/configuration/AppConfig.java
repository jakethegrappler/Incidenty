package cz.cvut.fel.nss.SaunaStudio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Konfigurační třída pro nastavení aplikace.
 * Tato třída obsahuje konfiguraci pro přístup k databázi, Elasticsearch a šifrování hesel.
 *
 * <p>Třída je označena jako konfigurační třída Springu, která aktivuje
 * JPA a Elasticsearch repozitáře a poskytuje definici bean pro šifrování hesel.</p>
 */
@EnableJpaRepositories(basePackages = "cz.cvut.fel.nss.SaunaStudio.dao")
@EnableElasticsearchRepositories(basePackages = "cz.cvut.fel.nss.SaunaStudio.elasticsearch")
@Configuration
public class AppConfig {

    /**
     * Definuje bean pro šifrování hesel pomocí algoritmu BCrypt.
     *
     * @return instance {@link PasswordEncoder} pro šifrování hesel.
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
