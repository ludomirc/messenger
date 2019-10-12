package org.qbit.messanger;

public interface Converter<S,T> {
    T convert(S source);
}
