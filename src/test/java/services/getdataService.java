package services;

import com.jayway.restassured.response.Response;
import helpers.ApiHelper;
import models.ApiRequestModel;
import models.UserRequestModel;

import java.util.List;

public class getdataService extends ApiHelper {
    public static Response getApiData(){
        return getApiUrl().when().get("/api/users?page=2");
    }

    public static Response createApiData(List<ApiRequestModel> apiRequestModels){
        Response createData = null;
        createData= getApiUrl().body(gson().toJson(apiRequestModels.get(0))).log().all().post("/api/users");
        return createData;
    }

    public static  Response updateApiData(List<UserRequestModel> userRequestModels){
        Response updateData = null;
        updateData = getApiUrl().body(gson().toJson(userRequestModels.get(0))).log().all().put("/api/users?page=2");
        return updateData;
    }
}
