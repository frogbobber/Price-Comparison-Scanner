package com.example.pricecomparisonscanner.analysis;

import com.example.pricecomparisonscanner.information.AllProductInformation;
import com.example.pricecomparisonscanner.information.ProductInformation;
import android.content.Context;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

public class DataProcessorTest extends TestCase {

    public AllProductInformation initInfo(){
        ArrayList<ProductInformation> amazonProducts = new ArrayList<>();
        amazonProducts.add(new ProductInformation("www.amazon.com", "amazonProduct1", "$1.50"));
        amazonProducts.add(new ProductInformation("www.amazon.com", "amazonProduct2", "$1.77"));

        ArrayList<ProductInformation> walmartProducts = new ArrayList<>();
        walmartProducts.add(new ProductInformation("www.walmart.com", "walmartProduct1 3 Count", "$2.50"));
        walmartProducts.add(new ProductInformation("www.walmart.com", "walmartProduct2", "$3.00"));

        ArrayList<ProductInformation> targetProducts = new ArrayList<>();
        targetProducts.add(new ProductInformation("www.target.com", "targetProduct1", "$4.00"));
        targetProducts.add(new ProductInformation("www.target.com", "targetProduct2", "$1.22"));

        ArrayList<ProductInformation> bestbuyProducts = new ArrayList<>();
        bestbuyProducts.add(new ProductInformation("www.bestbuy.com", "bestbuyProduct1", "$0.99"));
        bestbuyProducts.add(new ProductInformation("www.bestbuy.com", "bestbuyProduct2", "$1.99"));

        ArrayList<ProductInformation> upciteProducts = new ArrayList<>();
        upciteProducts.add(new ProductInformation("www.upcite.com", "upciteProduct1", "$7.00"));
        upciteProducts.add(new ProductInformation("www.upcite.com", "upciteProduct2", "$6.99"));


        AllProductInformation ret = new AllProductInformation(
                amazonProducts,
                walmartProducts,
                targetProducts,
                bestbuyProducts,
                upciteProducts,
                "testUPC"
        );
        return ret;
    }

    @Test
    public void testRemoveMultiples() throws JSONException, CloneNotSupportedException {
        AllProductInformation productInformation = initInfo();
        AllProductInformation productInformationBackup = initInfo();
        DataProcessor dp = new DataProcessor(productInformation);
        dp.removeMultiples(productInformation);
        assertNotSame(productInformation, productInformationBackup);
    }

    @Test
    public void testCreatePriceArray() throws JSONException, CloneNotSupportedException {
        AllProductInformation productInformation = initInfo();
        DataProcessor dp = new DataProcessor(productInformation);
        List<Double> priceArray = dp.createPriceArray(productInformation);
        assertTrue(priceArray.size() == 10);
    }

    @Test
    public void testCalculateMean() throws JSONException, CloneNotSupportedException {
        AllProductInformation productInformation = initInfo();
        DataProcessor dp = new DataProcessor(productInformation);
        assertEquals(dp.getMean(), 3.096);
    }

    @Test
    public void testCalculateVariance() throws JSONException, CloneNotSupportedException {
        AllProductInformation productInformation = initInfo();
        DataProcessor dp = new DataProcessor(productInformation);
        assertEquals(dp.getVariance(), 4.506944);
    }

    @Test
    public void testCalculateMedianAndQuartiles() throws JSONException, CloneNotSupportedException {
        AllProductInformation productInformation = initInfo();
        DataProcessor dp = new DataProcessor(productInformation);
        assertEquals(dp.getQ1(), 1.5);
        assertEquals(dp.getQ3(), 4.0);
        assertEquals(dp.getMedian(), 2.245);
        assertEquals(dp.getIqr(), 2.5);

    }

    @Test
    public void testCalculateBestListing() throws JSONException, CloneNotSupportedException {
        AllProductInformation productInformation = initInfo();
        DataProcessor dp = new DataProcessor(productInformation);
        assertEquals(dp.getBestListing().getName(), "bestbuyProduct1");
        assertEquals(dp.getBestListing().getUrl(), "www.bestbuy.com");
        assertEquals(dp.getBestListing().getPrice(), "$0.99");

    }
}