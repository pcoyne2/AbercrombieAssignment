package com.pcoyne.abercrombie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Patrick Coyne on 11/17/2017.
 */

public interface AbercrombieAPI {
    @GET("/personal-pcoyne/abercrombie_json")
    Call<List<ExampleModel>> examples();
}
