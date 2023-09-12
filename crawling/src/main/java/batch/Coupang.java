package batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class Coupang {
    private final String url1 = "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0";
    private final String url2 = "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0";

    private static List<String> crawling(String url) throws IOException {
        List<String> keywordList = new ArrayList<>();


        return keywordList;
    }

    @Scheduled(cron="1 * * * * *")
    public void main(String[] args) {
        try {
            List<String> data1 = crawling(url1);
            List<String> data2 = crawling(url2);

            // 데이터를 출력하거나 원하는 처리를 수행하세요.
            for (String item : data1) {
                log.info(item);
            }
            for (String item : data2) {
                log.info(item);
            }
        } catch (IOException e) {
            log.error("크롤링 중 오류 발생: " + e.getMessage());
        }
    }
}
