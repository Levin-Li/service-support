package com.levin.commons.rbac;


/**
 * @author lilw
 */
public interface DataPair<A, B> {

    static <A, B> DataPair<A, B> of(A a, B b) {
        return new DataPair<>() {
            @Override
            public A getA() {
                return a;
            }

            @Override
            public B getB() {
                return b;
            }
        };
    }

    A getA();

    B getB();

}
