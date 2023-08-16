package com.AAGST.CustomerApp.ControllerTest;
import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCardRestControllerTest {

    @Autowired
    TestRestTemplate template;

    CreditCardAddSender c1;
    @BeforeEach
    public void setUp(){
        template=new TestRestTemplate();
        c1 = new CreditCardAddSender(1);
    }

    @Test
    public void addCreditCardTest() throws URISyntaxException{
        URI uri = new URI("http://localhost:8080/CreditCard/add");
        ResponseEntity<CreditCard> response = template.postForEntity(uri,c1,CreditCard.class);
        CreditCard newCreditCard = response.getBody();
        assertEquals(c1.getCustomerId(),newCreditCard.getCustomerId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void updateCreditCardTest1() throws URISyntaxException{

        URI uri = new URI("http://localhost:8080/CreditCard/delete/64db0da20a0a49293b000064/2");
        ResponseEntity<String> response = template.exchange(uri,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Deleted Successfully",response.getBody());
    }
    @Test
    public void updateCreditCardTest2() throws URISyntaxException{

        URI uri = new URI("http://localhost:8080/CreditCard/delete/1212/2");
        ResponseEntity<String> response = template.exchange(uri,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());
    }

    @AfterEach
    public void destroy(){
        template = null;
        c1 = null;
    }
}
