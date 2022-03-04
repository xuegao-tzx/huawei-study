package com.net.jianjia;

import com.net.jianjia.http.Streaming;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 默认的数据转换器，如果调用者没有添加{@link Converter}对象，就会使用这个默认的数据转换器
 *
 * @modify&fix 田梓萱
 * @date 2022/2/17
 */
class BuiltInConverters extends Converter.Factory {

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, JianJia jianJia) {
        if (RequestBody.class.isAssignableFrom(Utils.getRawType(type))) {
            return RequestBodyConverter.INSTANCE;
        }
        return null;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, JianJia jianJia) {
        if (type == ResponseBody.class) {
            return Utils.isAnnotationPresent(annotations, Streaming.class) ?
                    StreamingResponseBodyConverter.INSTANCE :
                    BufferingResponseBodyConverter.INSTANCE;
        }
        if (type == Void.class) {
            return VoidResponseBodyConverter.INSTANCE;
        }
        return null;
    }

    /**
     * The type Request body converter.
     */
    static final class RequestBodyConverter implements Converter<RequestBody, RequestBody> {
        /**
         * The Instance.
         */
        static final RequestBodyConverter INSTANCE = new RequestBodyConverter();

        @Override public RequestBody convert(RequestBody value) {
            return value;
        }
    }

    /**
     * The type Streaming response body converter.
     */
    static final class StreamingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {

        /**
         * The Instance.
         */
        static final StreamingResponseBodyConverter INSTANCE = new StreamingResponseBodyConverter();

        @Override
        public ResponseBody convert(ResponseBody value) throws IOException {
            return value;
        }
    }

    /**
     * The type Buffering response body converter.
     */
    static final class BufferingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {

        /**
         * The Instance.
         */
        static final BufferingResponseBodyConverter INSTANCE = new BufferingResponseBodyConverter();

        @Override
        public ResponseBody convert(ResponseBody value) throws IOException {
            try {
                return Utils.buffer(value);
            } finally {
                value.close();
            }
        }
    }

    /**
     * The type Void response body converter.
     */
    static final class VoidResponseBodyConverter implements Converter<ResponseBody, Void> {

        /**
         * The Instance.
         */
        static final VoidResponseBodyConverter INSTANCE = new VoidResponseBodyConverter();

        @Override
        public Void convert(ResponseBody value) throws IOException {
            value.close();
            return null;
        }
    }

    /**
     * The type To string converter.
     */
    static final class ToStringConverter implements Converter<Object, String> {

        /**
         * The Instance.
         */
        static final ToStringConverter INSTANCE = new ToStringConverter();

        /**
         * 直接调用toString方法
         *
         * @param value
         * @return
         * @throws IOException
         */
        @Override
        public String convert(Object value) throws IOException {
            return value.toString();
        }
    }
}
