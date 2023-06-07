package com.gorest.gotestinfo;

import com.gorest.gorestSteps.UserSteps;
import com.gorest.testbase.UserTestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUD extends UserTestBase {
    static int userID;
    static String name = "Mish" + TestUtils.getRandomValue();
    static String email =  TestUtils.getRandomValue() + "Mish@gmail.com";
    static String gender = "Female" ;

    static  String status ="active";
    @Steps
    UserSteps userTestSteps;

    @Title("This will create a new user")
    @Test
    public void test001()
    {
        ValidatableResponse response = userTestSteps.createUser(name,email,gender,status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @Title("Verify If the user was Added to the application")
    @Test
    public void test002(){
        HashMap<String,Object> UserMap= userTestSteps.getUserById(userID);
        Assert.assertThat(UserMap, hasValue(userID));

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003()
    {
        name = name + TestUtils.getRandomValue();
        userTestSteps.updateTheUser(userID, name, email, gender, status)
                .statusCode(200);
        HashMap<String, ?> userMap = userTestSteps.getUserById(userID);
        Assert.assertThat(userMap, hasValue(name));

    }
    @Title("Delete the student and verify if the student is deleted")
    @Test
    public void test004()
    {
        userTestSteps.deleteUser(userID)
                .statusCode(204);

        userTestSteps.deleteUser(userID)
                .statusCode(404);
    }

}
