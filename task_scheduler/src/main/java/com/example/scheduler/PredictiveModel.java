package com.example.scheduler;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class PredictiveModel {
    private SimpleRegression regression;

    public PredictiveModel() {
        this.regression = new SimpleRegression();
    }

    /**
     * Trains the model with historical data (e.g., task size vs. execution time).
     * @param data Array of [task size, execution time] pairs.
     */
    public void train(double[][] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Training data cannot be null or empty");
        }
        regression.addData(data);
    }

    /**
     * Predicts execution time for a given task size.
     * @param input Task size or complexity.
     * @return Predicted execution time in milliseconds.
     */
    public long predict(double input) {
        if (regression.getN() == 0) {
            throw new IllegalStateException("Model must be trained before prediction");
        }
        return Math.round(regression.predict(input));
    }

    /**
     * Returns the number of data points used for training.
     */
    public long getTrainingSize() {
        return regression.getN();
    }
}