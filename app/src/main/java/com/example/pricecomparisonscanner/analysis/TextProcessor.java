package com.example.pricecomparisonscanner.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    public TextProcessor() {

    }

    public boolean isMultiCount(String input){
        Pattern pattern = Pattern.compile("[0-9] c(ou)?nt |[0-9](-| )pack|pack(-| )of(-| )[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();
        System.out.println(matchFound);
        return matchFound;
    }

}
