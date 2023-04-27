package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*; //importante usar static para que funcione correctamente
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;





public class APISteps {

    private static RequestSpecification request;
    private static Response response;
    private static ValidatableResponse json;
    
    @Given("^el usuario que envia un GET request a (.+) URI$")
    public void sendGETRequest(String URI){
        System.out.println("Inicio de Test " + URI);
        request = given().baseUri(URI)
                    .contentType(ContentType.JSON);
    }

    @Then("^el usuario obtiene un (\\d+) status code desde el endPoint: (.*)$")
    public void validaStatusCode(int statusEsperado, String endPoint){
        System.out.println("Fin de Test " + endPoint);
        response = request
                    .when()
                    .get(endPoint);

                    json = response.then().statusCode(statusEsperado);
    }

    @Then("^el usuario valida que obtiene (\\d+) items en el endPoint: (.+)$")
    public void validaCantItems(int itemsEsperados, String endPoint){
        System.out.println("Fin de Test " + endPoint);
        response = request
                    .when()
                    .get(endPoint);

        List<String> jsonResponse = response.jsonPath().getList("$");  //$ para 'todos'
        int itemsObtenidos = jsonResponse.size();

        assertEquals("Se esperan "+itemsEsperados+", pero se obtuvieron "+itemsObtenidos+" items", itemsEsperados, itemsObtenidos);

    }


    @Then("el usuario tiene una lista de {int} usuarios")
    public void valida10Usuarios(int cantUsuarios){
        System.out.println("Fin de Test" + cantUsuarios);
    }


    @Then("^el usuario valida que el nombre (.*) se encuentra en la respuesta del endPoint: (.*)$")
    public void validaCantItems(String nombreEsperado, String endPoint){
        System.out.println("Fin de Test " + endPoint);
        response = request
                    .when()
                    .get(endPoint);

        List<String> jsonResponse = response.jsonPath().getList("username");
        
        

        assertTrue("Se esperan "+nombreEsperado+", pero no se obtuvo en la respuesta", jsonResponse.contains(nombreEsperado));

    }
}
