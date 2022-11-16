package com.example.sobes.service;

import com.example.sobes.dto.CharInfo;
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

    public Map<Character, CharInfo> parse(String str) {
        Map<Character, CharInfo> curStringStat = new HashMap<>();
        if (!StringUtils.hasLength(str)) return curStringStat;
        char prevChar = str.charAt(0);
        Integer reqCount = null;
        curStringStat.put(prevChar, CharInfo.of(prevChar, reqCount, 1, 1));
        int commonCharsLength = 1;
        for (int i = 1, len = str.length(); i < len; i += 1) {
            char ch = str.charAt(i);
            CharInfo charInfo = CharInfo.of(ch, reqCount,0, 1);
            int charCounter = charInfo.getCharCounts();
            commonCharsLength = prevChar == ch ? commonCharsLength + 1 : 1;
            CharInfo updatedCharInfo = CharInfo.of(ch, reqCount,charCounter + 1, commonCharsLength);
            if (curStringStat.containsKey(ch)) {
                CharInfo prevStat = curStringStat.get(ch);
                int updateChCount = prevStat.getCharCounts() + updatedCharInfo.getCharCounts();
                int updateChLen = Math.max(updatedCharInfo.getLength(), prevStat.getLength());
                updatedCharInfo = CharInfo.of(ch, reqCount, updateChCount, updateChLen);
            }
            curStringStat.put(ch, updatedCharInfo);
            prevChar = ch;
        }
        applicationStringsInfo.updateInfo(curStringStat);
        return curStringStat;
    }

}
