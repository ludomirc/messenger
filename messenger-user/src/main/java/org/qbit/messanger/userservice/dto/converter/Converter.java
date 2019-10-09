package org.qbit.messanger.userservice.dto.converter;

public interface Converter<S,T> {
    T convert(S source);
}