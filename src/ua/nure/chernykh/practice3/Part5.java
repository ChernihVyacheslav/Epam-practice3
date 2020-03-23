package ua.nure.chernykh.practice3;

public class Part5 {

    private static final int ONE_HUNDRED = 100;
    private static final int NINETY = 90;
    private static final int FIFTY = 50;
    private static final int FORTY = 40;

    public static void main(String[] args) {
        for (int i = 1; i <= ONE_HUNDRED; i++) {
            String romanStr = decimal2Roman(i);
            System.out.println(i + " --> " + romanStr + " --> " + roman2Decimal(romanStr));
        }
    }

    public static String decimal2Roman(int x) {
        if (x > 0 && x <= ONE_HUNDRED) {
            StringBuilder sb = new StringBuilder();
            sb.append(getRoman(x));
            if (sb.length() > 0) {
                return sb.toString();
            } else {
                if (x > NINETY) {
                    sb.append(getRoman(NINETY)).append(decimal2Roman(x - NINETY));
                } else if (x > FIFTY) {
                    sb.append(getRoman(FIFTY)).append(decimal2Roman(x - FIFTY));
                } else if (x > FORTY) {
                    sb.append(getRoman(FORTY)).append(decimal2Roman(x - FORTY));
                } else if (x > 10) {
                    sb.append(getRoman(10)).append(decimal2Roman(x - 10));
                } else if (x > 5) {
                    sb.append(getRoman(5)).append(decimal2Roman(x - 5));
                } else {
                    sb.append(getRoman(1)).append(decimal2Roman(x - 1));
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static int roman2Decimal(String s) {
        int result = 0;
        StringBuilder sb = new StringBuilder(s);
        result += getDecimal(s);
        if (result != 0) {
            return result;
        } else {
            while(sb.length() > 0) {
                if(sb.length() > 1 &&
                        getDecimal(Character.toString(sb.charAt(sb.length() - 2))) <
                                getDecimal(Character.toString(sb.charAt(sb.length() - 1)))) {
                    result += getDecimal(sb.substring(sb.length() - 2, sb.length()));
                    sb.delete(sb.length() - 2, sb.length());
                } else {
                    result += getDecimal(sb.substring(sb.length() - 1, sb.length()));
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
        }
        return result;
    }

    private static String getRoman(int decimal) {
        String s = "";
        switch(decimal) {
            case 1:
                s = "I";
                break;
            case 4:
                s = "IV";
                break;
            case 5:
                s = "V";
                break;
            case 9:
                s = "IX";
                break;
            case 10:
                s = "X";
                break;
            case FORTY:
                s = "XL";
                break;
            case FIFTY:
                s = "L";
                break;
            case NINETY:
                s = "XC";
                break;
            case ONE_HUNDRED:
                s = "C";
                break;
            default:
                s = "";
                break;
        }
        return s;
    }

    private static int getDecimal(String roman) {
        int result = 0;
        switch(roman) {
            case "I":
                result = 1;
                break;
            case "IV":
                result = 4;
                break;
            case "V":
                result = 5;
                break;
            case "IX":
                result = 9;
                break;
            case "X":
                result = 10;
                break;
            case "XL":
                result = FORTY;
                break;
            case "L":
                result = FIFTY;
                break;
            case "XC":
                result = NINETY;
                break;
            case "C":
                result = ONE_HUNDRED;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

}