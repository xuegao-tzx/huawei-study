package com.net.jianjia.conventer;

import okhttp3.ResponseBody;
import com.net.jianjia.Converter;

import java.io.IOException;

/**
 * The type Scalar response body converters.
 */
final class ScalarResponseBodyConverters {
    private ScalarResponseBodyConverters() {
    }

    /**
     * The type String response body converter.
     */
    static final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        /**
         * The Instance.
         */
        static final StringResponseBodyConverter INSTANCE = new StringResponseBodyConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    /**
     * The type Boolean response body converter.
     */
    static final class BooleanResponseBodyConverter implements Converter<ResponseBody, Boolean> {
        /**
         * The Instance.
         */
        static final BooleanResponseBodyConverter INSTANCE = new BooleanResponseBodyConverter();

        @Override
        public Boolean convert(ResponseBody value) throws IOException {
            return Boolean.valueOf(value.string());
        }
    }

    /**
     * The type Byte response body converter.
     */
    static final class ByteResponseBodyConverter implements Converter<ResponseBody, Byte> {
        /**
         * The Instance.
         */
        static final ByteResponseBodyConverter INSTANCE = new ByteResponseBodyConverter();

        @Override
        public Byte convert(ResponseBody value) throws IOException {
            return Byte.valueOf(value.string());
        }
    }

    /**
     * The type Character response body converter.
     */
    static final class CharacterResponseBodyConverter implements Converter<ResponseBody, Character> {
        /**
         * The Instance.
         */
        static final CharacterResponseBodyConverter INSTANCE = new CharacterResponseBodyConverter();

        @Override
        public Character convert(ResponseBody value) throws IOException {
            String body = value.string();
            if (body.length() != 1) {
                throw new IOException(
                        "Expected body of length 1 for Character conversion but was " + body.length());
            }
            return body.charAt(0);
        }
    }

    /**
     * The type Double response body converter.
     */
    static final class DoubleResponseBodyConverter implements Converter<ResponseBody, Double> {
        /**
         * The Instance.
         */
        static final DoubleResponseBodyConverter INSTANCE = new DoubleResponseBodyConverter();

        @Override
        public Double convert(ResponseBody value) throws IOException {
            return Double.valueOf(value.string());
        }
    }

    /**
     * The type Float response body converter.
     */
    static final class FloatResponseBodyConverter implements Converter<ResponseBody, Float> {
        /**
         * The Instance.
         */
        static final FloatResponseBodyConverter INSTANCE = new FloatResponseBodyConverter();

        @Override
        public Float convert(ResponseBody value) throws IOException {
            return Float.valueOf(value.string());
        }
    }

    /**
     * The type Integer response body converter.
     */
    static final class IntegerResponseBodyConverter implements Converter<ResponseBody, Integer> {
        /**
         * The Instance.
         */
        static final IntegerResponseBodyConverter INSTANCE = new IntegerResponseBodyConverter();

        @Override
        public Integer convert(ResponseBody value) throws IOException {
            return Integer.valueOf(value.string());
        }
    }

    /**
     * The type Long response body converter.
     */
    static final class LongResponseBodyConverter implements Converter<ResponseBody, Long> {
        /**
         * The Instance.
         */
        static final LongResponseBodyConverter INSTANCE = new LongResponseBodyConverter();

        @Override
        public Long convert(ResponseBody value) throws IOException {
            return Long.valueOf(value.string());
        }
    }

    /**
     * The type Short response body converter.
     */
    static final class ShortResponseBodyConverter implements Converter<ResponseBody, Short> {
        /**
         * The Instance.
         */
        static final ShortResponseBodyConverter INSTANCE = new ShortResponseBodyConverter();

        @Override
        public Short convert(ResponseBody value) throws IOException {
            return Short.valueOf(value.string());
        }
    }
}
