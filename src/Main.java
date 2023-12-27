import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String answer = calc(scanner.nextLine());
        System.out.println();
        System.out.println("Output:\n"  + answer);
    }
    public static String calc(String input)
    {
        String[] Data= input.split(" ");
        int firstNumber;
        int secondNumber;
        boolean IsRomanAnswer = Data.length == 3 && IsRoman(Data[0]) && IsRoman(Data[2]);
        if (Data.length == 3 && IsCorrectData(Data[0]) && IsCorrectData(Data[2])) {
            firstNumber = Integer.parseInt(Data[0]);
            secondNumber = Integer.parseInt(Data[2]);
        }
        else if (IsRomanAnswer)
        {
            firstNumber = fromRomanToArabic(Data[0]);
            secondNumber = fromRomanToArabic(Data[2]);
            if (firstNumber == -1 || secondNumber == -1)
                return "throws Exception";
        }
        else{
            return "throws Exception";
        }
        int answer;
        switch(Data[1])
        {
            case "+":
                answer = firstNumber + secondNumber;
                break;
            case "-":
                if (IsRomanAnswer && firstNumber <= secondNumber)
                    return "throws Exception";
                answer = firstNumber - secondNumber;
                break;
            case "*":
                answer = firstNumber *secondNumber;
                break;
            case "/":
                if(IsRomanAnswer && firstNumber % secondNumber != 0)
                    return "throws Exception";
                answer = firstNumber / secondNumber;
                break;
            default:
                return "throws Exception";
        }
        if (IsRomanAnswer)
        {
            return fromArabicToRoman(answer);
        }
        return Integer.toString(answer);
    }
    public static boolean IsCorrectData(String input)
    {
        try {
            int number = Integer.parseInt(input);
            return number >= 0 & number <= 10;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean IsRoman(String input)
    {
        boolean IsNotNormal = input.contains("IIII") || input.contains("IIV") || input.contains("IIX");
        String check = input.replaceAll("I", "");
        check = check.replaceAll("V", "");
        check = check.replaceAll("X", "");
        return !IsNotNormal & check.isEmpty() && (input.contains("I") || input.contains("V") || input.contains("X"));
    }

    public static int fromRomanToArabic(String number)
    {
        int sum = 0;
        char[] Number = number.toCharArray();
        for (int i = Number.length - 1; i >=0; i--)
        {
            boolean IsReduce = i != 0 && Number[i-1] == 'I';
            switch (Number[i])
            {
                case 'I':
                    sum++;
                    break;
                case 'V':
                    if (IsReduce)
                        sum += 3;
                    else sum += 5;
                    break;
                case 'X':
                    if (IsReduce)
                        sum += 8;
                    else sum += 10;
            }
        }
        if (sum > 10)
            return -1;
        return sum;
    }
    public static String fromArabicToRoman(int input)
    {
        String answer = "";
        int units = input % 10;
        int dozens = (input%100 - input%10)/10;
        int hundreds = (input - input%100)/100;
        if (units >0 && units <4)
            answer = RepeatChars('I',units) + answer;
        else if(units == 4)
            answer = "IV" + answer;
        else if(units == 5)
            answer = "V" + answer;
        else if(units == 9)
            answer = "IX" + answer;
        else if (units >5 && units < 9)
            answer = "V" + RepeatChars('I',units - 5) + answer;
        if (dozens >0 && dozens <4)
            answer = RepeatChars('X',dozens) + answer;
        else if(dozens == 4)
            answer = "XL" + answer;
        else if(dozens == 5)
            answer = "L" + answer;
        else if(dozens == 9)
            answer = "IC" + answer;
        else if (dozens >5 && dozens < 9)
            answer = "L" + RepeatChars('X',dozens - 5) + answer;
        if (hundreds == 1)
            answer = "C" + answer;
        return answer;
    }
    public static String RepeatChars(char ch, int amount)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<amount; i++)
            sb.append(ch);
        return sb.toString();
    }

}