package com.example.sobes.service;

import com.example.sobes.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@ApplicationScope
public class ApplicationStringsInfo {
    private final Map<Character, Pair<Integer, Integer>> fullStat = Collections.synchronizedMap(new HashMap<>());

    public Map<Character, Pair<Integer, Integer>> getInfo() {
        return fullStat;
    }

    public void updateInfo(Map<Character, Pair<Integer, Integer>> curStringStat)  {
        for(Map.Entry<Character, Pair<Integer, Integer>> elt : curStringStat.entrySet()) {
            Character ch = elt.getKey();
            Pair<Integer, Integer> prevStat;
            if (fullStat.containsKey(ch)) {
                prevStat = fullStat.get(ch);
                int updateChCount = prevStat.getCharCounts() + elt.getValue().getCharCounts();
                int updateChLen = Math.max(elt.getValue().getLength(), prevStat.getLength());
                prevStat = Pair.of(updateChCount, updateChLen);
            } else {
                prevStat = elt.getValue();
            }
            fullStat.put(ch, prevStat);
        }
    }

}
