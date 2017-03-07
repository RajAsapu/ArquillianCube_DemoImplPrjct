package stepdef;

import static io.restassured.http.ContentType.JSON;

import java.util.Optional;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractApiDefinitions {

	private ThreadLocal<RequestSpecification> spec = new ThreadLocal<RequestSpecification>() {
		@Override
		protected RequestSpecification initialValue() {
			return RestAssured.with().baseUri(getTargetUrl()).and().contentType(JSON).log().all();
		}
	};

	protected String getTargetUrl() {
		return Optional.ofNullable(System.getenv("Test")).orElse(RestAssured.baseURI);
	}

	protected RequestSpecification spec() {
		return spec.get();
	}

}
