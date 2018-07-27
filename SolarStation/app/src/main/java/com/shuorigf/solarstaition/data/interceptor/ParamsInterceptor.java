package com.shuorigf.solarstaition.data.interceptor;


import com.shuorigf.solarstaition.data.params.BaseParams;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by clx on 18/2/27.
 */
public class ParamsInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        RequestBody body = null;

        if (request.body() instanceof MultipartBody){
            MultipartBody.Builder newPartBody = new MultipartBody.Builder();
            newPartBody.setType(MultipartBody.FORM);
            MultipartBody oilMultipartBody = (MultipartBody) request.body();
            List<MultipartBody.Part> parts = oilMultipartBody.parts();
            for (int i = 0; i < parts.size(); i++) {
                newPartBody.addPart(parts.get(i));
            }
            if (BaseParams.mUserName != null) {
                newPartBody.addPart(MultipartBody.Part.createFormData(BaseParams.USER_NAME, BaseParams.mUserName));
            }
            if (BaseParams.mClientKey != null) {
                newPartBody.addPart(MultipartBody.Part.createFormData(BaseParams.CLIENT_KEY, BaseParams.mClientKey));
            }
            if (BaseParams.mToken != null) {
                newPartBody.addPart(MultipartBody.Part.createFormData(BaseParams.TOKEN, BaseParams.mToken));
            }
            if (BaseParams.mOs != null) {
                newPartBody.addPart(MultipartBody.Part.createFormData(BaseParams.OS, BaseParams.mOs));
            }
            body = newPartBody.build();
        }else {
            FormBody.Builder newFormBody = new FormBody.Builder();
            if (request.body() instanceof FormBody){
                FormBody oidFormBody = (FormBody) request.body();
                for (int i = 0; i < oidFormBody.size(); i++) {
                    newFormBody.add(oidFormBody.name(i),oidFormBody.value(i));
                }
            }
            if (BaseParams.mUserName != null) {
                newFormBody.add(BaseParams.USER_NAME, BaseParams.mUserName);
            }
            if (BaseParams.mClientKey != null) {
                newFormBody.add(BaseParams.CLIENT_KEY, BaseParams.mClientKey);
            }
            if (BaseParams.mToken != null) {
                newFormBody.add(BaseParams.TOKEN, BaseParams.mToken);
            }
            if (BaseParams.mOs != null) {
                newFormBody.add(BaseParams.OS, BaseParams.mOs);
            }
            body = newFormBody.build();
        }
        if (request.method().equals("POST")){
            builder.method(request.method(),body);
        }else {
            builder.get().post(body);
        }
        request = builder.build();
        return chain.proceed(request);
    }
}
