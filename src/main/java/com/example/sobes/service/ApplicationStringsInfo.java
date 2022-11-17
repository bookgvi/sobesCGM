package com.example.sobes.service;

import com.example.sobes.dto.CharInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//@Component
//@ApplicationScope
public class ApplicationStringsInfo {
    private final Map<Character, CharInfo> fullStat = Collections.synchronizedMap(new HashMap<>());

    public Iterable<CharInfo.AverageInfo> getInfo() {
        return fullStat.values().stream().map(CharInfo::getAverageInfo).collect(Collectors.toUnmodifiableList());
    }

    public void updateInfo(Map<Character, CharInfo> curStringStat)  {
        for(Map.Entry<Character, CharInfo> elt : curStringStat.entrySet()) {
            Character ch = elt.getKey();
            CharInfo prevStat;
            if (fullStat.containsKey(ch)) {
                prevStat = fullStat.get(ch);
                int requestCount = prevStat.getRequestCount() + 1;
                int charCount = prevStat.getCharCounts() + elt.getValue().getCharCounts();
                int charLen = elt.getValue().getLength() + prevStat.getLength();
                prevStat = CharInfo.of(ch, requestCount, charCount, charLen);
            } else {
                prevStat = CharInfo.of(elt.getValue().getCh(), 1, elt.getValue().getCharCounts(), elt.getValue().getLength());
            }
            fullStat.put(ch, prevStat);
        }
    }

}
