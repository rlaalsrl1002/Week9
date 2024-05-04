import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 파일에서 숫자를 읽어옴
        String filename = "data.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            String[] numbers = line.split("\\s+");

            // 최대값과 최소값 초기화
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            // 숫자 배열을 순회하면서 최대값과 최소값 찾기
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                max = Math.max(max, num);
                min = Math.min(min, num);
            }

            // 결과 출력
            System.out.println("Max: " + max);
            System.out.println("Min: " + min);
        } catch (IOException e) {
            System.err.println("파일 읽기 실패: " + e.getMessage());
        }
    }
}
