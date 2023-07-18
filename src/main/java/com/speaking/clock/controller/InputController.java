package com.speaking.clock.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InputController {

    @PostMapping("/convert-input")
    public String convertInputToWords(@RequestBody String inputTime) {
        String[] timeParts = inputTime.split(":");
        if (timeParts.length != 2) {
            throw new IllegalArgumentException("Invalid input format. Please provide time in the format 'HH:MM'.");
        }

        try {
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                throw new IllegalArgumentException("Invalid time. Please provide a valid time between 00:00 and 23:59.");
            }

            String convertedTime = convertToWords(hour, minute);
            return "It's " + convertedTime;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input format. Please provide time in numeric format.");
        }
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
