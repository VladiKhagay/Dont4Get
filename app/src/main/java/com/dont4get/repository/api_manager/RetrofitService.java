//package com.dont4get.repository.api_manager;
//
//import com.google.gson.GsonBuilder;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RetrofitService {
//
//    private static Retrofit instance = null;
//
//
//    public static Retrofit getInstance() {
//        if (instance == null) {
//            instance = new Retrofit.Builder()
//                    .baseUrl(BASE_URL + "/")
//                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
//                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                            .create()))
//                    .build();
//        }
//
//        return instance;
//    }
//}
