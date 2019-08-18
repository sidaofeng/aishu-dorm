package com.waken.dorm.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
