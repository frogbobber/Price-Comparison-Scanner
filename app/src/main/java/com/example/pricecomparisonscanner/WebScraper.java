package com.example.pricecomparisonscanner;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import com.example.pricecomparisonscanner.sorter.AmazonExtractor;
import com.example.pricecomparisonscanner.sorter.BestBuyExtractor;
import com.example.pricecomparisonscanner.sorter.TargetExtractor;
import com.example.pricecomparisonscanner.sorter.WalmartExtractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class WebScraper {

    public static AllProductInformation getProductInformation(String name, String upc) throws IOException {
        AllProductInformation p = new AllProductInformation(
                getAmazonProductInformation("https://www.amazon.com/s?k=" + name + "&ref=nb_sb_noss"),
                getWalmartProductInformation("https://www.walmart.com/search/?cat_id=0&query=" + name),
                getTargetProductInformation(name),
                getBestBuyProductInformation("https://www.bestbuy.com/site/searchpage.jsp?st=" + name),    // <-- actually best buy, Target Information: https://www.target.com/s?searchTerm=
                null,
                upc
        );
        return p;
    }

    private static ArrayList<ProductInformation> getAmazonProductInformation(String url) {
        try {
            Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .referrer("https://www.amazon.com/")
                .get();
            return new AmazonExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static ArrayList<ProductInformation> getWalmartProductInformation(String url) {
        try {
            Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml")
                .header("Accept-Language", "en")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header("TE", "Trailers")
                .cookie("s_vi", "[CS]v1|2EEE586F8515EC43-600008C8A14A9DAB[CE]; _pxvid=cf813bbd-ecb2-11ea-a572-0242ac120005; newphoto=Y; _abck=i30nnecz9kgw640b8roj_1771; cart-item-count=0; CID=08a250dd-7d98-47aa-ab80-31a6515bf398; hasCID=1; WMP=4; SPID=ba2a7af7eb2db55a4d0df9955c06dd1fa3bdf8aff733d3ac02231a61dfd02a2449a41d6312ed42cbd3423a6714c3f1ccwmcxo; customer={\"firstName\":\"John\",\"lastNameInitial\":\"L\",\"rememberme\":true}; vtc=STXbT2bvV3RcHP3cTbre8M; DL=87801,,,ip,87801,,; type=REGISTERED; s_pers= om_mv3d=sem_un%3Awlpa_ADID|1593066733231; om_mv7d=sem_un%3Awlpa_ADID|1593412333233; om_mv14d=sem_un%3Awlpa_ADID|1594017133235; om_mv30d=sem_un%3Awlpa_ADID|1595399533238; s_cmpstack=%5B%5B'seo_un'%2C'1608412971692'%5D%5D|1766179371692; s_fid=12A285F6F76F6A8E-1A20FEC01D3C5013|1671485305559; s_v=Y|1608415105562; gpv_p11=Checkout%7Chide%20balance%20details%3A|1608415105577; gpv_p44=Checkout|1608415105583; s_vs=1|1608415105587;; _gcl_au=1.1.1877140486.1611953457; ONEAPP_CUSTOMER=true; GCRT=2d2e51f4-0336-4521-a1cc-3567ccb55c03; hasGCRT=1; wm_mystore=Fe26.2**70ce9b20738c65cf6e7acd333d6b67b777d290f7f82526f3d19db1d9b24111a3*2Oikw9F8x8z--uPHckFc4g*hoA5y6mmPi9ab2VIKrNJYayV5B99xADJ-XR0zBHROAuc5oeLEMb8qlsF89K0I6upU7xx3Ud25x735oTL_8XNNoRhDtqS16a654obcB2r776LnQuWaSOzZDiG_rVgDmAzkliLbaIA21UQsY3r0p86w-uQ6_i7t15PH4zjzp_0qz3G6MHTjEnau6rvkQydjRjMz7lfyASryTrD_Vp4FY4MKTkH4jmtDON5brIUVdhBW6OPE2J4lD8gypN-XN8lKGE8DwU02SlInbCjYnonbo0w5xZIr7MqaqZRXLEz5JabSDYpHtDzyTx-qt7mpNYEnVwiTSQSJ4OeWCPPhg4_84kv5MUDIbjHHSxSHtWSO4krCnaAC0IxG4B4aExcS0cZ3gt6WhteoXUgBSnFp0ByUCCu84gg9aEeIhwjWscpmHNMqo637Lq2t0tcj1QM-sp05JkFMnkWSPeXBndfrrB8BC-RKweafkwOsFjdw8VRi8XEdtSOXL4juVw70VYX9lYz0V2PLmg0yshkro3sUsGRxn1kqJrIPhmBc1rX_psiCJAWnYO3YOWwRBYMDuDV74RVPZIXrEzRK0-PxnZ-UE18t54EWJ0Nbx3RI8qc3uvGrqTzuKniV9ZzHqltwO8tXaftwoH8sMS6oXs6XeftDWezQ-KwzhyWUISW7-DeV6JZjiZcEP5T82CQbQcQZjFwjMc-fp_iPfI1-G13i3OQOqIYLbPgMeclPvTGYoaI0fMGt244YsuSSKcXFEMPn1aT0bJE_OlYvgLL8-bkbxwX55xLF8lBiJPYX1rpjo2bQMaft_4kAh3U2hcZb7htyrYbXKPEILhl2mjgUxha1rIlzcBud9oHyntR-6OOGwgF5tLJNu4LHx2W6W-t0zBPEC50MNnzUf4c6Ulwnx-zRx9O62RWxYOey6KHc1dE1LGhGQ_Tg-Qz-5aE2adsSxoR1QhKOxij5vd_cuSJEIUmfpVQbMraoyN-0r4_2997LMIVWnjW-YURszU**d2ad8d7cc2d97aa8f3fcf13d548160982e3394625735047280bc4110cb4d93dd*OJfRQAPF7mjXZxEOcvOwBcJZzY4Crz75qG89NXxd03Q; brwsr=2d68bef1-7c4c-11eb-8d47-42010a246c43; s_pers_2=gpv_p11=Search Results Search|1593400618914|Search Results Search; useVTC=N|1656513803; ps=4; om_mv3d=aff:adid-:sourceid-imp_2dMT7ZyZQxyLUdywUx0Mo3ZzUkEQiHVWI1mDRA0:wmls-imp_120157:cn-|1615058024589; om_mv7d=aff:adid-:sourceid-imp_2dMT7ZyZQxyLUdywUx0Mo3ZzUkEQiHVWI1mDRA0:wmls-imp_120157:cn-|1615403624591; om_mv14d=aff:adid-:sourceid-imp_2dMT7ZyZQxyLUdywUx0Mo3ZzUkEQiHVWI1mDRA0:wmls-imp_120157:cn-|1616004824592; om_mv30d=aff:adid-:sourceid-imp_2dMT7ZyZQxyLUdywUx0Mo3ZzUkEQiHVWI1mDRA0:wmls-imp_120157:cn-|1617387224593; athrvi=RVI~hd2c43d-h2c5b3fb; TB_Latency_Tracker_100=1; TB_Navigation_Preload_01=1; TBV=7; tb_sw_supported=true; TB_SFOU-100=1; next-day=null|false|true|null|1619370309; location-data=87801:Socorro:NM::8:1|48k;;3.92,13a;;41.1,2rw;;49.39||7|1|; wm_ul_plus=INACTIVE|1619373909440; TB_DC_Flap_Test=0; g=0; bstc=WDlpWIqwVt_TTUiVyQJNSY; mobileweb=0; xpa=CrKQ0|Q4Ycm|VN4MW|YKh-C|_CVhV|dMRDO|tWDgk|x1kw1; exp-ck=VN4MW2dMRDO1tWDgk1x1kw11; xpm=1+1619370309+STXbT2bvV3RcHP3cTbre8M~08a250dd-7d98-47aa-ab80-31a6515bf398+0; auth=MTAyOTYyMDE4ZniZjwa5e5/tj9wCc89PfZq67ezyxdX53fZxljPgiOtF+VdOhlBNfCAknTl+iHM/5KqBAJ7xEIKpFDCQU1vt1ylqZ4a72OpbP7hD+zLzUBt9CdbXS/zpsC5mLA6D+leEFq9riivtxT8okFpOy3evOFxcpmP6tAN6M8HtpAo0UNjIDpbmnzaGkSN/VFUYu9zmmRUp+szyUJWAMhhE/Zu+xCpQBIV+q5odNqvzV1gAYluNn5AblvKQK5lMnBb7WEiFa+Z650cVSgun4L2x7Afw7WwFF4h96GXP+cnt1vlXW3TrDTvTak+Bt5x1saErqsKsAyCkYyoL4kDE1YeMHYrJvXPjlsY9vw+1o2Br5/6z8Pjyj67gmr8R6hDe6b3XPxioyf0gHDSybX6ztF/kSu7u6g==; TS01b0be75=01538efd7c6cfefdc68e4be888310bd6a7dd40db0cc4f0bebbc826dde54ee90ef7bedb4401414c6075fc2a60a0b9eb188b495600b5; TS013ed49a=01538efd7c6cfefdc68e4be888310bd6a7dd40db0cc4f0bebbc826dde54ee90ef7bedb4401414c6075fc2a60a0b9eb188b495600b5; com.wm.reflector=\"reflectorid:0000000000000000000000@lastupd:1619370429035@firstcreate:1617150994704\"; akavpau_p8=1619371045~id=aef7e773573db51dfef5481b35b10c7f")
                .referrer("https://www.walmart.com/")
                .get();
            return new WalmartExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static ArrayList<ProductInformation> getTargetProductInformation(String searchTerm) { //https://www.target.com/s?searchTerm=
        try {
            String url = "https://www.target.com/s?searchTerm=" + searchTerm;
            Document document = Jsoup.connect(url)//"https://www.bestbuy.com/site/searchpage.jsp?st=cat&_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys")
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36")
                    .header("authority", "www.walmart.com")
                    .header("method", "GET")
                    .header("path", "/search/?cat_id=0&query=walmart")
                    .header("scheme", "https")
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("accept-language", "en-US,en;q=0.9")
                    .header("cache-control", "max-age=0")
                    .cookie("s_vi", "visitorId=01778DB72F770201821CD9EAB98CCD03")
                    .cookie("sapphire", "1")
                    .cookie("UserLocation", "33417|26.720|-80.120|FL|US")
                    .cookie("fiatsCookie", "DSI_2427|DSN_West%20Palm%20Beach|DSZ_33401")
                    .cookie("criteo", "{%22criteo%22:%22cZ9_fqR7f5VcN1zagvOko7LT1nTVpYG_%22}")
                    .cookie("gcl_au", "1.1.308001801.1612990333")
                    .cookie("cd_user_id", "1778db7480265e-0844626545548-33e3567-190140-1778db748039f5")
                    .cookie("crl8.fpcuid", "a51406d3-3115-42b0-998a-0ba3f8605563")
                    .cookie("TealeafAkaSid", "LCi4l5kvh-l3FeoNibAFz5nZMxZ0Rqfy")
                    .cookie("brwsr", "652f7655-7c4c-11eb-8d47-42010a246c43")
                    .cookie("kampyleUserSession", "1614795320057")
                    .cookie("kampyleUserSessionsCount", "21")
                    .cookie("kampyleSessionPageCounter", "1")
                    .cookie("kampyleUserPercentile", "36.59870311425224")
                    .cookie(" GuestLocation", "83350|42.760|-113.620|ID|US")
                    .cookie(" adaptiveSessionId", "A6421968255")
                    .cookie(" accessToken", "eyJraWQiOiJlYXMyIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJjOWQ3ZjMwMi0zODQ0LTQ1ZGUtYmQ4NS1iZGMyNGM1NTZiN2QiLCJpc3MiOiJNSTYiLCJleHAiOjE2MTk0MDQ4NTQsImlhdCI6MTYxOTMxODQ1NCwianRpIjoiVEdULjhjZmYwMDg3N2YxZDRjNWJhMTQ3Y2E0Y2Y3NjMyNzMzLWwiLCJza3kiOiJlYXMyIiwic3V0IjoiRyIsImRpZCI6IjQwMWQ0YmI3ZDQ1ZmI4ODkzNjRiODYxZDBjNmQ0NmI4N2VhMDdiNTgzNWQwNjk2M2YxMzVjOTVjZmEwZmE1NDgiLCJzY28iOiJlY29tLm5vbmUsb3BlbmlkIiwiY2xpIjoiZWNvbS13ZWItMS4wLjAiLCJhc2wiOiJMIn0.aGf7zZ2QJSYHNhq3bK1Ko6ZKK42SSqwogEKKCWVscboM6_9WaFEGwSiFxcjO2bsQsiWAu060jOcXMyXkspLB9So-QjYgQYESqHgt8pQrZlep5UhT6qte7T9egQy9kYvZHeDRq-qvFQJtW-VxCe-IMFdp-m7VH4JDy5DmzgQOqHI2zcnKD50RCBdRF-okuAejszuqwVz_V2jXY5_7FQdfUMTSe2a1zRnRm3VwgyNr_X-8xM79Ig7XNCYcv1wtDaTPNSHVz3HocpdC9kHX3gYVkwC2490kcsH-JK2_W3J7vUU_wRGBbzot0cgwD0Kmrtzb3qk8b8GmLLT6IfbxJBIyRQ")
                    .cookie(" idToken", "eyJhbGciOiJub25lIn0.eyJzdWIiOiJjOWQ3ZjMwMi0zODQ0LTQ1ZGUtYmQ4NS1iZGMyNGM1NTZiN2QiLCJpc3MiOiJNSTYiLCJleHAiOjE2MTk0MDQ4NTQsImlhdCI6MTYxOTMxODQ1NCwiYXNzIjoiTCIsInN1dCI6IkciLCJjbGkiOiJlY29tLXdlYi0xLjAuMCIsInBybyI6eyJmbiI6bnVsbCwiZW0iOm51bGwsInBoIjpmYWxzZSwibGVkIjpudWxsLCJsdHkiOmZhbHNlfX0.")
                    .cookie(" refreshToken", "-zXoSo_WS67WgX8ERYDT-jC6PdZlgYBw4WrhdrMH4U2TVjumnHQaDBELX54i3Q-BzWV5Ooy07ARUm8uMeL1E_w")
                    .cookie(" guestType", "G|1619318454000")
                    .cookie(" tlThirdPartyIds", "%7B%22pt%22%3A%22v2%3A3a50b6c08d79dc69a1d49050efd5c8f28e37cfc3ffecc9deca262c05b4a1c3ca%7Cac0ddc84f8986b8d02ce27931e7fcc9c5b319f5e650a373a94f2a4a79beeea3a%22%2C%22adHubTS%22%3A%22Sat%20Apr%2024%202021%2020%3A40%3A56%20GMT-0600%20(Mountain%20Daylight%20Time)%22%7D")
                    .cookie(" ci_pixmgr", "other")
                    .cookie(" targetMobileCookie", "guestLogonId:null~guestDisplayName:null~guestHasVerifiedPhone:false")
                    .cookie(" ffsession", "{%22sessionHash%22:%22c6e9d5b6e68ee1619318453653%22%2C%22sessionHit%22:26%2C%22prevPageType%22:%22search:%20search%20results%22%2C%22prevPageName%22:%22search:%20search%20results%22%2C%22prevPageUrl%22:%22https://www.target.com/s?searchTerm=cat+toy%22%2C%22prevSearchTerm%22:%22cat%20toy%22}")
                    .referrer("http://www.walmart.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute()
                    .parse();
            return new TargetExtractor().extractProducts(document, searchTerm);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static ArrayList<ProductInformation> getBestBuyProductInformation(String url) {
        try {
            //System.out.println(url);
            Document document = Jsoup.connect(url)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml")
                    .header("Accept-Language", "en")
                    .header("Accept-Encoding", "gzip,deflate,sdch")
                    .header("Connection", "keep-alive")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("TE", "Trailers")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36
                    .referrer("https://www.bestbuy.com/")
                    .get();
            return new BestBuyExtractor().extractProducts(document);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
