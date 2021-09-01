
package com.incognito.ent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {
    // api path
    static final String url="http://10.0.2.2/ecommapi/";
    private static apicontroller clientobject;
    private static Retrofit retrofit;

    // constructor working
        apicontroller(){
            retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

    // clientobject -> runtime object

        public static synchronized apicontroller getInstance(){
            if(clientobject==null)
                clientobject=new apicontroller();
            return clientobject;
        }

        // it also return apiset function

        apiset getapi(){
            return retrofit.create(apiset.class);
        }
}
