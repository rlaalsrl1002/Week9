import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 데이터 파일 경로
        String filePath = "data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int totalPeople = 0;
            int obesePeople = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                // 데이터 파일에서 각 사람의 신장(cm)과 체중(kg)을 읽어옴
                String[] data = line.split(" ");
                double height = Double.parseDouble(data[0]) / 100.0; // cm를 m로 변환
                double weight = Double.parseDouble(data[1]);

                // 비만도 수치 계산
                double bmi = weight / (height * height);

                // 비만 여부 확인
                if (bmi >= 25) {
                    obesePeople++;
                }

                totalPeople++;
            }

            // 비만인 사람의 비율 계산
            double obeseRatio = (double) obesePeople / totalPeople * 100;

            // 결과 출력
            System.out.printf("All %d Person\n", totalPeople);
            System.out.printf("Total OverWeight perseon:%d (%.0f%%)\n", obesePeople, obeseRatio);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
