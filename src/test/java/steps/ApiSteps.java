package steps;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.ApiHelper;
import models.ApiRequestModel;
import models.ApiResponseModel;
import models.UserRequestModel;
import models.UserResponseModel;
import org.junit.Assert;
import services.getdataService;

import java.util.List;

public class ApiSteps {
    private Response apiResponse;
    List<ApiRequestModel> apiRequestModels;
    ApiResponseModel apiResponseModel;
    List<UserRequestModel> userRequestModels;
    UserResponseModel userResponseModel;

    @When("^User hits the Url$")
    public void userHitsTheUrl() {
        apiResponse = getdataService.getApiData();
        System.out.println(apiResponse.getStatusCode());
    }

    @Then("^User is getting Response$")
    public void userIsGettingResponse() {
        Assert.assertTrue(apiResponse.getStatusCode() == 200);
    }

    @When("^User hits the request$")
    public void userHitsTheRequest(List<ApiRequestModel> apiRequestModels) {
        this.apiRequestModels = apiRequestModels;
    }

    @Then("^User gets created$")
    public void userGetsCreated() {
        apiResponse = getdataService.createApiData(apiRequestModels);
        System.out.println(apiResponse.getStatusCode());
        Assert.assertTrue(apiResponse.getStatusCode() == 201);
    }

    @And("^User is saved in the DB$")
    public void userIsSavedInTheDB() throws Throwable {
        apiResponseModel = ApiHelper.gson().fromJson(apiResponse.body().prettyPrint(),ApiResponseModel.class);
        //Assert.assertTrue(apiResponseModel.getName() != null);
        Assert.assertTrue(apiResponseModel.getId() != null);
        //Assert.assertTrue(apiResponseModel.getJob() != null);
        Assert.assertTrue(apiResponseModel.getCreatedAt() != null);
    }

    @When("^User enters the details$")
    public void userEntersTheDetails(List<UserRequestModel> userRequestModels)  {this.userRequestModels= userRequestModels;}

    @Then("^User gets updated$")
    public void userGetsUpdated()  {
        apiResponse=getdataService.updateApiData(userRequestModels);
        System.out.println(apiResponse.getStatusCode());
        Assert.assertTrue(apiResponse.getStatusCode() == 200);
    }

    @And("^User details gets updated in DB$")
    public void userDeatilsGetsUpdatedInDB() throws Throwable {
       userResponseModel=ApiHelper.gson().fromJson(apiResponse.body().prettyPrint(),UserResponseModel.class);
       //Assert.assertTrue(userResponseModel.getJob() != null);
        //Assert.assertTrue(userResponseModel.getName() != null);
        //Assert.assertTrue(userResponseModel.getUpdatedAt() != null);
    }
}
