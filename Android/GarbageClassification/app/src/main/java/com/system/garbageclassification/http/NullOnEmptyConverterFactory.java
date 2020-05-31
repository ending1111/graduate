package com.system.garbageclassification.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Retrofit配置所需的类
 */

public class NullOnEmptyConverterFactory extends Converter.Factory {
    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, Object> responseBodyObjectConverter = retrofit.nextResponseBodyConverter(this, type, annotations);
        return (Converter<ResponseBody, Object>) value -> value.contentLength() == 0 ? null : responseBodyObjectConverter.convert(value);
    }
}
