package com.example.sobes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharInfo {
    private final char ch;
    private final Integer requestCount;
    private final int charCounts;
    private final int length;
    @JsonIgnore
    private final AverageInfo averageInfo;

    private CharInfo(Character ch, Integer requestCount, int charCounts, int length) {
        this.ch = ch;
        this.requestCount = requestCount;
        this.charCounts = charCounts;
        this.length = length;
        this.averageInfo = new AverageInfo(ch, requestCount, charCounts, length);
    }

    public static CharInfo of(Character ch, Integer requestCount, int charCounts, int length) {
        return new CharInfo(ch, requestCount, charCounts, length);
    }
    public int getCharCounts() {
        return charCounts;
    }

    public int getLength() {
        return length;
    }

    public Character getCh() {
        return ch;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public AverageInfo getAverageInfo() {
        return averageInfo;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AverageInfo {
        private final char ch;
        private final Integer requestCount;
        private final int avgCharCounts;
        private final int avgLength;

        public AverageInfo(char ch, Integer requestCount, int charCounts, int length) {
            this.ch = ch;
            this.requestCount = requestCount;
            this.avgCharCounts = requestCount != null ? charCounts / requestCount : charCounts;
            this.avgLength = requestCount != null ? length / requestCount : length;
        }

        public char getCh() {
            return ch;
        }

        public Integer getRequestCount() {
            return requestCount;
        }

        public int getAvgCharCounts() {
            return avgCharCounts;
        }

        public int getAvgLength() {
            return avgLength;
        }
    }
}
