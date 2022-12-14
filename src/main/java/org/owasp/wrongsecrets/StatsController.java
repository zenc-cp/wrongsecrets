package org.owasp.wrongsecrets;

import org.owasp.wrongsecrets.canaries.CanaryCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {

    @Autowired
    private CanaryCounter canaryCounter;
    @Autowired
    private SessionConfiguration sessionConfiguration;

    @Value("${hints_enabled}")
    private boolean hintsEnabled;
    @Value("${reason_enabled}")
    private boolean reasonEnabled;
    @Value("${ctf_enabled}")
    private boolean ctfModeEnabled;

    @Value("${canarytokenURLs}")
    private String[] canaryTokenURLs;

    @GetMapping("/stats")
    public String getStats(Model model) {
        model.addAttribute("canaryCounter", canaryCounter.getTotalCount());
        model.addAttribute("sessioncounter", sessionConfiguration.getCounter());
        model.addAttribute("lastCanaryToken", canaryCounter.getLastToken());
        model.addAttribute("canarytokenURLs", canaryTokenURLs);
        model.addAttribute("hintsEnabled", hintsEnabled);
        model.addAttribute("reasonEnabled", reasonEnabled);
        model.addAttribute("ctfModeEnabled",ctfModeEnabled);
        return "stats";
    }
}
