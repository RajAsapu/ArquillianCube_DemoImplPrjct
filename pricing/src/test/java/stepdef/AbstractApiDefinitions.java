package stepdef;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.util.Optional;
import static io.restassured.http.ContentType.JSON;

public abstract class AbstractApiDefinitions  {

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
