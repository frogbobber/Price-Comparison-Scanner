package com.example.pricecomparisonscanner.analysis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DataProcessor {
    private JSONObject bestListing;
    private double mean;
    private double standardDeviation;
    private double variance;
    private List<Double> priceArray; //just used to make the other calculations more time efficient
    private double median;

    private double iqr;
    private double q1;
    private double q3;

    public DataProcessor(JSONArray jsonArray) throws JSONException {
        initStats(jsonArray);
        this.bestListing = calculateBestListing(jsonArray);
    }

    public DataProcessor(JSONArray jsonArray, boolean includeOutliers, boolean includeShipping) throws JSONException {
        initStats(jsonArray);
        this.bestListing = calculateBestListing(jsonArray, includeOutliers, includeShipping);
    }

    private void initStats(JSONArray jsonArray) throws JSONException {
        this.priceArray = createPriceArray(jsonArray);
        this.mean = calculateMean();
        this.variance = calculateVariance();
        this.standardDeviation = Math.sqrt(variance);
        double[] tmp = calculateMedianAndQuartiles();
        this.q1 = tmp[0];
        this.q3 = tmp[1];
        this.iqr = tmp[2];
        this.median = calculateMedian(priceArray);
    }

    protected List<Double> createPriceArray(JSONArray jsonArray) throws JSONException {
        List<Double> priceArray = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++) {
            priceArray.add(jsonArray.getJSONObject(i).getDouble("price"));
        }
        Collections.sort(priceArray);
        return priceArray;
    }

    protected double calculateMean(){
        double mean = 0.0;
        for(int i = 0; i < priceArray.size(); i++) {
            mean += priceArray.get(i);
        }

        mean /= priceArray.size();
        return mean;
    }

    protected double calculateVariance(){
        double standardDeviation = 0.0;

        for(int i = 0; i < priceArray.size(); i++) {
            standardDeviation += Math.pow(priceArray.get(i) - mean, 2);
        }
        standardDeviation /= priceArray.size();
        return standardDeviation;
    }

    protected double[] calculateMedianAndQuartiles(){
        List<Double> data1;
        List<Double> data2;
        if (priceArray.size() % 2 == 0) {
            data1 = priceArray.subList(0, priceArray.size() / 2);
            data2 = priceArray.subList(priceArray.size() / 2, priceArray.size());
        } else {
            data1 = priceArray.subList(0, priceArray.size() / 2);
            data2 = priceArray.subList(priceArray.size() / 2 + 1, priceArray.size());
        }
        double q1 = calculateMedian(data1);
        double q3 = calculateMedian(data2);
        double iqr = q3 - q1;
        return new double[] {q1, q3, iqr};
    }

    protected double calculateMedian(List<Double> arr){
        if (arr.size() % 2 == 0)
            return (arr.get(arr.size() / 2) + arr.get(arr.size() / 2 - 1)) / 2;
        else
            return arr.get(arr.size() / 2);
    }

    protected JSONObject calculateBestListing(JSONArray jsonArray) throws JSONException {
        return calculateBestListing(jsonArray, false, false);
    }


    /**
     * This could be made more efficient if you can just do a reverse search by price
     * 1. Remove outliers from priceArray
     * 2. Get index 0 of priceArray
     * 3. Reverse search by price
     */
    protected JSONObject calculateBestListing(JSONArray jsonArray, Boolean includeOutliers, Boolean includeShipping) throws JSONException {
        int bestLoc = 0;
        double currentMin = 0.0;
        double currentPrice = 0.0;

        if(!includeOutliers) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(includeShipping) {
                    // todo fix this, shipping don't work
                    currentPrice = jsonObject.getDouble("price") + jsonObject.getDouble("shipping");
                } else {
                    currentPrice = jsonObject.getDouble("price");
                }

                if (i == 0) {
                    currentMin = currentPrice;
                } else {
                    System.out.println(jsonObject.get("price"));
                    if (currentMin > currentPrice) {
                        currentMin = currentPrice;
                        bestLoc = i;
                    }
                }
            }

        } else {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(includeShipping) {
                    currentPrice = jsonObject.getDouble("price") + jsonObject.getDouble("shipping");
                } else{
                    currentPrice = jsonObject.getDouble("price");
                }

                if (i == 0) {
                    if(isOutlier(currentPrice)){
                        currentMin = median;
                    } else {
                        currentMin = currentPrice;
                    }
                } else {
                    System.out.println(jsonObject.get("price"));
                    if (currentMin > currentPrice && !(isOutlier(currentPrice))) {
                        currentMin = currentPrice;
                        bestLoc = i;
                    }
                }
            }
        }

        return jsonArray.getJSONObject(bestLoc);
    }

    private boolean isOutlier(double price){
        if (price < q1 - 1.5 * iqr || price > q3 + 1.5 * iqr){
            return true;
        }
        return false;
    }

    public JSONObject getBestListing() {
        return bestListing;
    }

    public void setBestListing(JSONObject bestListing) {
        this.bestListing = bestListing;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }
    public List<Double> getPriceArray() {
        return priceArray;
    }

    public void setPriceArray(List<Double> priceArray) {
        this.priceArray = priceArray;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getIqr() {
        return iqr;
    }

    public void setIqr(double iqr) {
        this.iqr = iqr;
    }

    public double getQ1() {
        return q1;
    }

    public void setQ1(double q1) {
        this.q1 = q1;
    }

    public double getQ3() {
        return q3;
    }

    public void setQ3(double q3) {
        this.q3 = q3;
    }

}
