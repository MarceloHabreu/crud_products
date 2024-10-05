package api.api_products.entity;

import org.springframework.stereotype.Component;

@Component
public class Message {
    // Attribute
    private String message;

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
