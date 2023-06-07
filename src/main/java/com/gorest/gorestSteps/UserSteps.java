package com.gorest.gorestSteps;

import com.gorest.constant.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;
public class UserSteps {
    @Step("Creating User with name : {0},email : {1} , gender:{2} , status : {3}")
    public ValidatableResponse createUser(String name , String email , String gender, String status)
    {
        UserPojo userPojo = UserPojo.getUserPojo(name,email,gender,status);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }
    @Step ("Getting user information with User ID = {0}")
    public HashMap<String,Object> getUserById(int userID){
        HashMap<String, Object> userMap = SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .pathParam("userID",userID)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_Id)
                .then().statusCode(200)
                .extract().path("");
        return userMap;
    }

    @Step("Getting all the user info ")
    public ValidatableResponse getUsertInfo()
    {
        return  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when().get(EndPoints.GET_ALL_USER)
                .then();
    }

//    @Step("Getting the user info with name : {0}")
//    public HashMap<String,Object> getUserInfoByName(String name)
//    {
//        String s1 = "findAll{it.firstName == '";
//        String s2 = "'}.get(0)";
//        return SerenityRest.given()
//                .when()
//                .get(EndPoints.GET_ALL_USER)
//                .then().statusCode(200)
//                .extract()
//                .path(s1+name+s2);
//    }

    @Step("Updating user information with user with userID : {0},name : {1} ,email : {2} ,gender : {3},status : {4}")
    public ValidatableResponse updateTheUser(int userID , String name , String email ,String gender, String status )
    {
        UserPojo userPojo = UserPojo.getUserPojo(name,email,gender,status);
        return SerenityRest.given().log().all()
                .header("Content-Type","application.json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .pathParam("userID",userID)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Deleting user information with userID : {0}")
    public ValidatableResponse deleteUser(int userID)
    {
        return SerenityRest.given().log().all()
                .header("Content-Type","application.json")
                .header("Access","application/json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .pathParam("userID",userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
}
