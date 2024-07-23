package com.example.processors.extensions.java.util.ArrayList;


import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.util.ArrayList;
import java.util.Collection;


@Extension
public class ArrayListExtensions {

    public static <E> Collection<E> chaos(@This ArrayList<E> list) {
        return list.stream()
                .filter(value -> Math.random() > .5)
                .toList();
    }
}

