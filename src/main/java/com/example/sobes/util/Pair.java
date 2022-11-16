package com.example.sobes.util;

public final class Pair<S, T> {
    private final S charCounts;
    private final T length;

    private Pair(S charCounts, T length) {
        this.charCounts = charCounts;
        this.length = length;
    }

    public static <S, T> Pair<S, T> of(S charCounts, T length) {
        return new Pair<>(charCounts, length);
    }

    public S getCharCounts() {
        return charCounts;
    }

    public T getLength() {
        return length;
    }
}
