package br.com.bakingapp.services.recipeService.source;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import br.com.bakingapp.data.model.ErrorResponse;
import br.com.bakingapp.data.model.Resource;
import retrofit2.Response;
import timber.log.Timber;

import static br.com.bakingapp.data.Constants.CONNECTION_MSG_ERROR;
import static br.com.bakingapp.data.Constants.GENERIC_ERROR_CODE;
import static br.com.bakingapp.data.Constants.GENERIC_MSG_ERROR_MESSAGE;
import static br.com.bakingapp.data.Constants.NETWORK_ERROR_CODE;
import static br.com.bakingapp.data.Constants.SERVER_MSG_ERROR;

public class ApiResponse<T> {
    private final String logTag;

    public ApiResponse(String logTag) {
        this.logTag = logTag;
    }

    public Resource<T> getApiOnResponse(Response<T> response) {
        if (response.body() != null) {
            if (response.isSuccessful()) {
                return Resource.success(response.body());
            } else {
                if (response.errorBody() != null) {
                    ErrorResponse error = null;
                    if (response.code() >= 400 && response.code() < GENERIC_ERROR_CODE) {
                        Moshi moshi = new Moshi.Builder().build();
                        JsonAdapter<ErrorResponse> jsonAdapter = moshi.adapter(ErrorResponse.class);
                        try {
                            error = jsonAdapter.fromJson(response.errorBody().string());
                        } catch (IOException e) {
                            Timber.e(logTag, e.getMessage());
                        }
                    } else {
                        error = new ErrorResponse(response.code(),
                                SERVER_MSG_ERROR);
                    }
                    return Resource.error(error);
                }
            }
        }

        return Resource.error(new ErrorResponse(response.code(),
                SERVER_MSG_ERROR));
    }

    public Resource<T> getApiOnFailure(Throwable t) {
        ErrorResponse error;
        if (t instanceof IOException) {
            error = new ErrorResponse(NETWORK_ERROR_CODE,
                    CONNECTION_MSG_ERROR);
        } else {
            error = new ErrorResponse(GENERIC_ERROR_CODE,
                    GENERIC_MSG_ERROR_MESSAGE);
        }
        Timber.e(logTag, t.getMessage());
        return Resource.error(error);
    }
}