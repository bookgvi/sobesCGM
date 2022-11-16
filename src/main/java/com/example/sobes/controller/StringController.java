package com.example.sobes.controller;

import com.example.sobes.util.Pair;
import com.example.sobes.service.ApplicationStringsInfo;
import com.example.sobes.service.CurRequestStringInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/string")
public class StringController {
    private final CurRequestStringInfo stringInfo;
    private final ApplicationStringsInfo applicationStringsInfo;

    public StringController(CurRequestStringInfo stringInfo, ApplicationStringsInfo applicationStringsInfo) {
        this.stringInfo = stringInfo;
        this.applicationStringsInfo = applicationStringsInfo;
    }

    @GetMapping
    public Map<Character, Pair<Integer, Integer>> getCurStringStat(@RequestParam(defaultValue = "") String str) {
        return stringInfo.parse(str);
    }

    @GetMapping("/stat")
    public Map<Character, Pair<Integer, Integer>> getFullStat() {
        return applicationStringsInfo.getInfo();
    }

}
