package boundary;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@Getter
@ApplicationScoped
public class UrlProperties {
    private Properties props;
    private String customerBase;
    private String filmBase;
    private String storeBase;

    @PostConstruct
    public void init() throws IOException {
        props = new Properties();
        if(this.getClass().getResourceAsStream("/urlsLocal.properties") != null) {
            props.load(this.getClass().getResourceAsStream("/urlsLocal.properties"));   
        } else if(this.getClass().getResourceAsStream("/urlsDocker.properties") != null) {
            props.load(this.getClass().getResourceAsStream("/urlsDocker.properties"));
        }
        customerBase = props.getProperty("customerBase");
        filmBase = props.getProperty("filmBase");
        storeBase = props.getProperty("storeBase");
    }
    
}
