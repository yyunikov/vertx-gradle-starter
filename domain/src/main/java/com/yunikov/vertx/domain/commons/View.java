package com.yunikov.vertx.domain.commons;

@FunctionalInterface
public interface View<T> {
    T print();
}
