package dvd_rental_film.boundary;

import java.io.IOException;
import java.util.Properties;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Component
public class UrlProperties {

    private Properties props;
    private String customerBase;
    private String filmBase;
    private String storeBase;

    @Value("classpath:urlsLocal.properties")
    private org.springframework.core.io.Resource urlsLocalResource;

    @Value("classpath:urlsDocker.properties")
    private org.springframework.core.io.Resource urlsDockerResource;

    @PostConstruct
    public void init() throws IOException {
        props = new Properties();

        if (urlsLocalResource.exists()) {
            props.load(urlsLocalResource.getInputStream());
        } else if (urlsDockerResource.exists()) {
            props.load(urlsDockerResource.getInputStream());
        }

        customerBase = props.getProperty("customerBase");
        filmBase = props.getProperty("filmBase");
        storeBase = props.getProperty("storeBase");
    }

    public String getCustomerBase() {
        return customerBase;
    }

    public String getFilmBase() {
        return filmBase;
    }

    public String getStoreBase() {
        return storeBase;
    }
}