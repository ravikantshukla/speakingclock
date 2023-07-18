package com.speaking.clock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;

@RestController
public class TimeController {

    @GetMapping("/current-time")
    public String convertTimeToWords() {
        LocalTime currentTime = LocalTime.now();
        String convertedTime = convertToWords(currentTime.getHour(), currentTime.getMinute());
        return "It's " + convertedTime;
    }

    private String convertToWords(int hour, int minute) {
        String[] hourWords = {"midnight", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "noon", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "midnight"};
        String[] minuteWords = {"o'clock", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five", "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine", "thirty", "thirty-one", "thirty-two", "thirty-three", "thirty-four", "thirty-five", "thirty-six", "thirty-seven", "thirty-eight", "thirty-nine", "forty", "forty-one", "forty-two", "forty-three", "forty-four", "forty-five", "forty-six", "forty-seven", "forty-eight", "forty-nine", "fifty", "fifty-one", "fifty-two", "fifty-three", "fifty-four", "fifty-five", "fifty-six", "fifty-seven", "fifty-eight", "fifty-nine"};

        String hourWord = hourWords[hour % 12];
        String minuteWord;

        if (minute == 0) {
            minuteWord = "";
        } else if (minute == 30) {
            minuteWord = "half";
        } else if (minute < 10) {
            minuteWord = "oh " + minuteWords[minute];
        } else if (minute < 20) {
            minuteWord = minuteWords[minute];
        } else {
            int tens = minute / 10;
            int ones = minute % 10;
            minuteWord = minuteWords[18 + tens];
            if (ones != 0) {
                minuteWord += " " + minuteWords[ones];
            }
        }

        return hourWord + " " + minuteWord;
    }
}

