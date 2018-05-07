package com.example.contexthierarchy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final DiceRollService diceRollService;

    @Value("${dice.faces}")
    private int diceFaces;

    @GetMapping("/")
    public String index() {
        final StringBuilder sb = new StringBuilder("Distribution:<br>\n");
        for (int score = 1; score <= diceFaces; ++score) {
            sb.append(score).append(": ").append(diceRollService.getDistribution(score)).append("<br>\n");
        }
        sb.append("<br>\n<form action=\"/\" method=\"POST\"><button type=\"submit\">Roll dice!</button></form>");
        return sb.toString();
    }

    @PostMapping("/")
    public RedirectView insertOne() {
        diceRollService.roll();

        return new RedirectView("/");
    }

}
