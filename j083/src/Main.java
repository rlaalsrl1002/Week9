import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "name.txt"; // 파일 이름

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int totalCount = 0;
            String longestName = "";
            String shortestName = "";

            String line;
            while ((line = reader.readLine()) != null) {
                totalCount++;

                // 가장 긴 이름 찾기
                if (longestName.isEmpty() || line.length() > longestName.length()) {
                    longestName = line;
                }

                // 가장 짧은 이름 찾기
                if (shortestName.isEmpty() || line.length() < shortestName.length()) {
                    shortestName = line;
                }
            }

            // 결과 출력
            System.out.println("전체 학부 개수: " + totalCount);
            System.out.println("가장 긴 이름: " + longestName);
            System.out.println("가장 짧은 이름: " + shortestName);

        } catch (IOException e) {
            System.err.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
