package contract;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.pact.abinbev.data.domain.Beer;
import com.pact.abinbev.data.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.pact.abinbev.BeerApplication;
import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("BeerService")
@PactBroker
@IgnoreNoPactsToVerify(ignoreIoErrors = "true")
public class BeerServicePactTest {

	@LocalServerPort
	private int port;

	@Autowired
	private BeerRepository beerRepository;

	@BeforeEach
	void setup(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationSpringProvider.class)
	void pactVerificationTestTemplate(PactVerificationContext context) {
		context.verifyInteraction();
	}
	@State(value = "beer exists", action = StateChangeAction.SETUP)
	void productsExists() {
		beerRepository.deleteAll();
		beerRepository.saveAll(Arrays.asList(
						new Beer("63c18997cf5ec173590cc15a", "BHRAMA", "AMBEV", new BigDecimal(2.52).setScale(2, BigDecimal.ROUND_UP), "Test"),
						new Beer("e00111fc959f11eda1eb0242", "SKOL", "AMBEV", new BigDecimal(3.22).setScale(2, BigDecimal.ROUND_UP), "Test")
		));
	}

	@State(value = "no beer exists", action = StateChangeAction.SETUP)
	void noBeerExists() {
		beerRepository.deleteAll();
	}
}
