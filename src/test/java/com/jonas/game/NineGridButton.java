package com.jonas.game;

public class NineGridButton {
    static String[] nineGrid = {
            ",.", "abc", "def",
            "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz",
            "#", " ", "/"};
    public static void main(String[] args) {
        String input = "#2222333/224433";//"#12222/223334#1";
        boolean isNumberType = true;
        int index = 0;
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if ('#' == input.charAt(i)) {
                if (!isNumberType) {
                    int ix = Integer.parseInt("" + input.charAt(index));
                    stringBuilder.append(nineGrid[ix - 1].charAt(count - 1));
                }
                isNumberType = !isNumberType;
                index = i + 1;
                continue;
            }
            if (isNumberType) {
                if (input.charAt(i) != '/') {
                    stringBuilder.append(input.charAt(i));
                }
            } else {
                int ix = Integer.parseInt("" + input.charAt(index));
                if (input.charAt(index) != input.charAt(i)) {
                    stringBuilder.append(nineGrid[ix - 1].charAt(count - 1));
                    if (input.charAt(i) == '/') {
                        index = i + 1;
                        count = 0;
                    } else {
                        index = i;
                        if (i == input.length() - 1) {
                            ix = Integer.parseInt("" + input.charAt(index));
                            stringBuilder.append(nineGrid[ix - 1].charAt(count - 1));
                        }
                    }
                } else {
                    count = (nineGrid[ix - 1].length() == count) ? 1 : (count + 1);
                    if (i == input.length() - 1) {
                        stringBuilder.append(nineGrid[ix - 1].charAt(count - 1));
                    }
                }
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
