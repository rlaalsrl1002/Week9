import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "data.txt"; // 데이터 파일 이름
        int lowercaseCount = 0;
        int uppercaseCount = 0;
        int spaceCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (Character.isLowerCase(ch)) {
                        lowercaseCount++;
                    } else if (Character.isUpperCase(ch)) {
                        uppercaseCount++;
                    } else if (Character.isWhitespace(ch)) {
                        spaceCount++;
                    }
                }
            }

            // 결과 출력
            System.out.println("a-z: " + lowercaseCount);
            System.out.println("A-Z: " + uppercaseCount);
            System.out.println("blank: " + spaceCount);

        } catch (IOException e) {
            System.err.println("error " + e.getMessage());
            e.printStackTrace();
        }
    }
}
