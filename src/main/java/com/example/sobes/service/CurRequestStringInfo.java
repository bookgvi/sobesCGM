package com.example.sobes.service;

import com.example.sobes.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

@Component
@RequestScope
public class CurRequestStringInfo {
    private final ApplicationStringsInfo applicationStringsInfo;

    public CurRequestStringInfo(ApplicationStringsInfo applicationStringsInfo) {
        this.applicationStringsInfo = applicationStringsInfo;
    }

    public Map<Character, Pair<Integer, Integer>> parse(String str) {
        Map<Character, Pair<Integer, Integer>> curStringStat = new HashMap<>();
        if (!StringUtils.hasLength(str)) return curStringStat;
        char prevChar = str.charAt(0);
        curStringStat.put(prevChar, Pair.of(1, 1));
        int commonCharsLength = 1;
        for (int i = 1, len = str.length(); i < len; i += 1) {
            char ch = str.charAt(i);
            Pair<Integer, Integer> charInfo = Pair.of(0, 1);
            int charCounter = charInfo.getCharCounts();
            commonCharsLength = prevChar == ch ? commonCharsLength + 1 : 1;
            Pair<Integer, Integer> updatedCharInfo = Pair.of(charCounter + 1, commonCharsLength);
            if (curStringStat.containsKey(ch)) {
                Pair<Integer, Integer> prevStat = curStringStat.get(ch);
                int updateChCount = prevStat.getCharCounts() + updatedCharInfo.getCharCounts();
                int updateChLen = Math.max(updatedCharInfo.getLength(), prevStat.getLength());
                updatedCharInfo = Pair.of(updateChCount, updateChLen);
            }
            curStringStat.put(ch, updatedCharInfo);
            prevChar = ch;
        }
        applicationStringsInfo.updateInfo(curStringStat);
        return curStringStat;
    }

}
