package model;

import java.util.Scanner;

public class CharacterCounter {
    // Karakter sayısını sayacak metodu tanımlıyoruz
    public static int countCharacters(String sentence, String sensitivity, char character) {
        int count = 0;
        if (sensitivity.equalsIgnoreCase("Evet")) {
            count = sentence.length() - sentence.replaceAll(String.valueOf(character), "").length();
        } else if (sensitivity.equalsIgnoreCase("Hayır")) {
            count = sentence.toLowerCase().length() - sentence.toLowerCase().replaceAll(String.valueOf(character).toLowerCase(), "").length();
        } else {
            System.out.println("Lütfen geçerli bir cevap giriniz.");
        }
        return count;
    }

    // Ana metod
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Burada kullanıcıdan maksimum karakter sayısını alıyoruz
        System.out.print("Maksimum karakter sayisini belirleyin: ");
        int maxCharacters = scanner.nextInt();
        scanner.nextLine(); 

        // Kullanıcıdan cümleyi alıyoruz
        String sentence;
        do {
            System.out.print("Lutfen bir cumle girin: ");
            sentence = scanner.nextLine();
            if (sentence.length() > maxCharacters) {
                System.out.println("Girdiğiniz cümle maksimum karakter sinirini aşiyor. Lütfen tekrar deneyin.");
            }
        } while (sentence.length() > maxCharacters);

        // Büyük/küçük harf duyarlılığını kontrol ediyoruz
        String sensitivity;
        do {
            System.out.print("Buyuk/kucuk harf duyarliligi aktif olsun mu? (Evet/Hayır): ");
            sensitivity = scanner.nextLine();
        } while (!sensitivity.equalsIgnoreCase("Evet") && !sensitivity.equalsIgnoreCase("Hayır"));

        // Analiz edilecek karakteri alıyoruz
        char character;
        do {
            System.out.print("Analiz etmek icin bir harf girin: ");
            String input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Gecerli bir karakter giriniz.");
                continue;
            }
            character = input.charAt(0);
            break;
        } while (true);

        // Karakter sayısını hesaplayıp ekrana yazdırıyoruz
        int characterCount = countCharacters(sentence, sensitivity, character);
        System.out.println("Girilen cümlede '" + character + "' harfi toplamda " + characterCount + " defa geçmektedir.");
        scanner.close();
    }
}

