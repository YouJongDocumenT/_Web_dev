package com.project.ras.controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScrapingService {

    private Map<String, Integer> cumulativeDonations = new HashMap<>();

    @Scheduled(fixedRate = 1000) // 1초마다 실행
    public void scrapeAndStoreDonations() {
        String chatUrl = "https://play.afreecatv.com/roket0829/256370752"; // 아프리카 TV 채팅 페이지 URL

        try {
            Document doc = Jsoup.connect(chatUrl).get();
            Elements messages = doc.select("div.donation-container"); // 메시지를 나타내는 요소 선택

            for (Element message : messages) {
                String nickname = message.select("button.name").text(); // 닉네임 추출
                System.out.println(nickname);
                String donationText = message.select("span.money").text(); // 후원 금액 추출

                // 후원 금액을 숫자로 변환하여 저장
                int donationAmount = parseDonationAmount(donationText);

                // 후원자의 누적 금액 업데이트
                cumulativeDonations.put(nickname, cumulativeDonations.getOrDefault(nickname, 0) + donationAmount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 후원 금액을 파싱하는 메서드
    private int parseDonationAmount(String donationText) {
        // 정규 표현식을 사용하여 숫자만 추출
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(donationText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            return 0;
        }
    }

    // 누적 후원 금액을 반환하는 메서드
    public Map<String, Integer> getCumulativeDonations() {
        return cumulativeDonations;
    }
}
