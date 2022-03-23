package com.clearCapital;

public class NameUtility {
    public static void main(String[] args) {

        System.out.println(convertNameToInitials("John Doe"));

    }


    public static String convertNameToInitials(String fullName) {
        fullName = fullName.trim().replaceAll("\\s+", " ");
        if(fullName.length()==0){
            throw new RuntimeException("Please give a valid full name");
        }else if (!fullName.contains(" ")) {
            String firstInitial =""+fullName.charAt(0);
            return firstInitial.toUpperCase()+".";

        } else if (fullName.indexOf(" ") == fullName.lastIndexOf(" ")) {
            int firstSpace = fullName.indexOf(" ");
            String firstName = fullName.substring(0, firstSpace);
            String lastName = fullName.substring(firstSpace + 1);
            String firstInitial =""+firstName.charAt(0);
            String lastInitial = ""+lastName.charAt(0);
            return firstInitial.toUpperCase()+ "." + lastInitial .toUpperCase()+ ".";
        } else {
            int firstSpace = fullName.indexOf(" ");
            int lastSpace = fullName.lastIndexOf(" ");
            String firstName = fullName.substring(0, firstSpace);
            String firstInitial = ""+firstName.charAt(0);
            String middleName = fullName.substring(firstSpace + 1, lastSpace);
            String middleInitial = ""+middleName.charAt(0);
            String lastName = fullName.substring(lastSpace + 1);
            String lastInitial = ""+lastName.charAt(0);

            return firstInitial.toUpperCase() + "." + middleInitial.toUpperCase() + "." + lastInitial.toUpperCase() + ".";

        }
    }
}
