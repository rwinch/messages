package sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessagesApplicationTests {

	@Autowired
	TestRestTemplate rest;

	@Test
	public void findOne() {
		Message message = this.rest.getForObject("/messages/{id}", Message.class, 1L);

		assertThat(message.getText()).isEqualTo("Hello World");
	}

	@Test
	public void save() {
		Message toSave = new Message("New");
		Message saved = this.rest.postForObject("/messages", toSave, Message.class);
		assertThat(saved.getText()).isEqualTo(toSave.getText());

		Message message = this.rest.getForObject("/messages/{id}", Message.class, saved.getId());

		assertThat(message.getText()).isEqualTo(saved.getText());
	}
}
