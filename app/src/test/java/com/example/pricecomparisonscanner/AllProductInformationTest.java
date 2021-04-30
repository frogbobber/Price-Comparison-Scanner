package com.example.pricecomparisonscanner;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AllProductInformationTest {
    @Test
    public void allProduct_isCorrect() {
        ArrayList<ProductInformation> amazonProducts = new ArrayList<>();
        amazonProducts.add(new ProductInformation("www.amazon1.com", "amazonProduct1", "amazonPrice1"));
        amazonProducts.add(new ProductInformation("www.amazon2.com", "amazonProduct2", "amazonPrice2"));
        amazonProducts.add(new ProductInformation("www.amazon3.com", "amazonProduct3", "amazonPrice3"));

        ArrayList<ProductInformation> walmartProducts = new ArrayList<>();
        walmartProducts.add(new ProductInformation("www.walmart1.com", "walmartProduct1", "walmartPrice1"));
        walmartProducts.add(new ProductInformation("www.walmart2.com", "walmartProduct2", "walmartPrice2"));
        walmartProducts.add(new ProductInformation("www.walmart3.com", "walmartProduct3", "walmartPrice3"));

        ArrayList<ProductInformation> targetProducts = new ArrayList<>();
        targetProducts.add(new ProductInformation("www.target1.com", "targetProduct1", "targetPrice1"));
        targetProducts.add(new ProductInformation("www.target2.com", "targetProduct2", "targetPrice2"));
        targetProducts.add(new ProductInformation("www.target3.com", "targetProduct3", "targetPrice3"));

        ArrayList<ProductInformation> bestbuyProducts = new ArrayList<>();
        bestbuyProducts.add(new ProductInformation("www.bestbuy1.com", "bestbuyProduct1", "bestbuyPrice1"));
        bestbuyProducts.add(new ProductInformation("www.bestbuy2.com", "bestbuyProduct2", "bestbuyPrice2"));
        bestbuyProducts.add(new ProductInformation("www.bestbuy3.com", "bestbuyProduct3", "bestbuyPrice3"));

        ArrayList<ProductInformation> upciteProducts = new ArrayList<>();
        upciteProducts.add(new ProductInformation("www.upcite1.com", "upciteProduct1", "upcitePrice1"));
        upciteProducts.add(new ProductInformation("www.upcite2.com", "upciteProduct2", "upcitePrice2"));
        upciteProducts.add(new ProductInformation("www.upcite3.com", "upciteProduct3", "upcitePrice3"));


        AllProductInformation expected = new AllProductInformation(
                amazonProducts,
                walmartProducts,
                targetProducts,
                bestbuyProducts,
                upciteProducts,
                "testUPC"
        );

        ArrayList<ProductInformation> amazonLong = new ArrayList<>();
        amazonLong.add(new ProductInformation("www.amazon1.com", "amazonProduct1", "amazonPrice1"));
        amazonLong.add(new ProductInformation("www.amazon2.com", "amazonProduct2", "amazonPrice2"));
        amazonLong.add(new ProductInformation("www.amazon3.com", "amazonProduct3", "amazonPrice3"));
        amazonLong.add(new ProductInformation("www.amazon4.com", "amazonProduct4", "amazonPrice4"));
        amazonLong.add(new ProductInformation("www.amazon5.com", "amazonProduct5", "amazonPrice5"));

        ArrayList<ProductInformation> walmartLong = new ArrayList<>();
        walmartLong.add(new ProductInformation("www.walmart1.com", "walmartProduct1", "walmartPrice1"));
        walmartLong.add(new ProductInformation("www.walmart2.com", "walmartProduct2", "walmartPrice2"));
        walmartLong.add(new ProductInformation("www.walmart3.com", "walmartProduct3", "walmartPrice3"));
        walmartLong.add(new ProductInformation("www.walmart4.com", "walmartProduct4", "walmartPrice4"));
        walmartLong.add(new ProductInformation("www.walmart5.com", "walmartProduct5", "walmartPrice5"));

        ArrayList<ProductInformation> targetLong = new ArrayList<>();
        targetLong.add(new ProductInformation("www.target1.com", "targetProduct1", "targetPrice1"));
        targetLong.add(new ProductInformation("www.target2.com", "targetProduct2", "targetPrice2"));
        targetLong.add(new ProductInformation("www.target3.com", "targetProduct3", "targetPrice3"));
        targetLong.add(new ProductInformation("www.target4.com", "targetProduct4", "targetPrice4"));
        targetLong.add(new ProductInformation("www.target5.com", "targetProduct5", "targetPrice5"));

        ArrayList<ProductInformation> bestbuyLong = new ArrayList<>();
        bestbuyLong.add(new ProductInformation("www.bestbuy1.com", "bestbuyProduct1", "bestbuyPrice1"));
        bestbuyLong.add(new ProductInformation("www.bestbuy2.com", "bestbuyProduct2", "bestbuyPrice2"));
        bestbuyLong.add(new ProductInformation("www.bestbuy3.com", "bestbuyProduct3", "bestbuyPrice3"));
        bestbuyLong.add(new ProductInformation("www.bestbuy4.com", "bestbuyProduct4", "bestbuyPrice4"));
        bestbuyLong.add(new ProductInformation("www.bestbuy5.com", "bestbuyProduct5", "bestbuyPrice5"));

        ArrayList<ProductInformation> upciteLong = new ArrayList<>();
        upciteLong.add(new ProductInformation("www.upcite1.com", "upciteProduct1", "upcitePrice1"));
        upciteLong.add(new ProductInformation("www.upcite2.com", "upciteProduct2", "upcitePrice2"));
        upciteLong.add(new ProductInformation("www.upcite3.com", "upciteProduct3", "upcitePrice3"));
        upciteLong.add(new ProductInformation("www.upcite4.com", "upciteProduct4", "upcitePrice4"));
        upciteLong.add(new ProductInformation("www.upcite5.com", "upciteProduct5", "upcitePrice5"));

        AllProductInformation toBeModified = new AllProductInformation(
                amazonLong,
                walmartLong,
                targetLong,
                bestbuyLong,
                upciteLong,
                "testUPC"
        );

        assertEquals(expected.toString(), new AllProductInformation(3, toBeModified).toString());
    }
}