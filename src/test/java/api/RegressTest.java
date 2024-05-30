package api;

import api.dto.colors.ColorsData;
import api.dto.user.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RegressTest {

    private final static String URL = "https://reqres.in/";


    @Test
    public void checkAvatarAndIdTest() {

        Specifications.installSpecification(Specifications.requestSpecifications(URL), Specifications.responseSpecificationOK200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
       /* users.forEach(x-> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
*/

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x -> x.getId().toString()).toList();

        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }


    @Test
    public void successRegTest() {
        Specifications.installSpecification(Specifications.requestSpecifications(URL), Specifications.responseSpecificationOK200());
        int id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register register = new Register("eve.holt@reqres.in", "pistol");

        SuccessReg successReg = given()
                .body(register)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessReg.class);

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    public void unSuccessRegTest() {
        Specifications.installSpecification(Specifications.requestSpecifications(URL), Specifications.responseSpecificationError400());
        Register register = new Register("sydney@fife", "");
        BadResponse badResponse = given()
                .body(register)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(BadResponse.class);

        Assertions.assertTrue(badResponse.getError().contains("Missing password"));
    }

    @Test
    public void checkYearsInRightQueue() {
        Specifications.installSpecification(Specifications.requestSpecifications(URL), Specifications.responseSpecificationOK200());
        List<ColorsData> colorDataList = given()
                .when()
                .get("api/unknown")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colorDataList.stream().map(ColorsData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();
        Assertions.assertEquals(sortedYears, years);
    }

    @Test
    public void deleteUser() {
        Specifications.installSpecification(Specifications.requestSpecifications(URL),Specifications.responseSpecificationSpec(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();

    }



}
