package com.example.sobes.controller;

import com.example.sobes.dto.CharInfo;
import com.example.sobes.service.ApplicationStringsInfo;
import com.example.sobes.service.CurRequestStringInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<CharInfo> getCurStringStat(@RequestParam(defaultValue = "") String str) {
        return stringInfo.parse(str).values();
    }

    @GetMapping("/stat")
    public Iterable<CharInfo.AverageInfo> getFullStat() {
        return applicationStringsInfo.getInfo();
    }

}
