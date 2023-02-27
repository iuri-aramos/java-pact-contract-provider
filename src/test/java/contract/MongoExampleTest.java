package contract;

import com.pact.abinbev.BeerApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = {BeerApplication.class})
@ExtendWith(SpringExtension.class)
public class MongoExampleTest {
	@Test
	public void example(@Autowired final MongoTemplate mongoTemplate) {
		Assertions.assertThat(mongoTemplate.getDb()).isNotNull();
	}
}