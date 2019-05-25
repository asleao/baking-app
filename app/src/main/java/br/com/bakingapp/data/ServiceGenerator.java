package br.com.bakingapp.data;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.moshi.Moshi;

import br.com.bakingapp.BuildConfig;
import br.com.bakingapp.utils.BigDecimalAdapter;
import br.com.bakingapp.utils.DateAdapter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static br.com.bakingapp.data.Constants.HOST;
import static br.com.bakingapp.data.Constants.SCHEME;


public class ServiceGenerator {


    private static final Moshi moshiFactory = new Moshi.Builder()
            .add(new BigDecimalAdapter())
            .add(new DateAdapter())
            .build();

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(buildUrl())
                    .addConverterFactory(MoshiConverterFactory.create(moshiFactory));

    private static Retrofit retrofit = builder.build();

    private static final HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging) && BuildConfig.DEBUG) {
            httpClient.addNetworkInterceptor(new StethoInterceptor());
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    private static HttpUrl buildUrl() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .build();
    }
}
