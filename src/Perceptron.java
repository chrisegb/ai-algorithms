import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Christian Blanco
 * This is a class for a perceptron, after research in internet I found that there are too complicated classes to do
 * this or people who actually are just matemathicians and do not know how to program correctly, so I created this
 * simple test class to create a simple perceptron.
 */
public class Perceptron {

    private double learningFactor;

    private List<Double> weights;

    private final List<Neuron> neurons;

    /**
     * The constructor of the perceptron waiting only of the "neurons" data.
     * @param neurons List of "neurons" data.
     */
    public Perceptron(List<Neuron> neurons) {
        learningFactor = initializeLearningFactor();
        this.neurons = neurons;
        this.weights = initializeWeights();
    }

    /**
     * It only print the weights rather if they have been processed or not.
     */
    public void printWeights() {
        for (int i = 0; i < weights.size(); i++) {
            String format = String.format("w%d = %f", i, weights.get(i));
            System.out.println(format);
        }
    }

    /**
     * It calculates the final value of the weights after training.
     * @param neurons It processes the wights given randomly in the constructor.
     */
    public void calculateWeights(List<Neuron> neurons) {
        int counter = 0;
        while (counter < neurons.size()) {
            Neuron neuron = neurons.get(counter);
            List<Double> entries = neuron.getEntries();
            double error = calculateError(neuron.getExpectedOutput(), neuron);
            counter = getCounter(counter, entries, error);
        }
    }

    /**
     * Given a new set of entries, it tests if the linear separation was correct.
     * @param testEntries the entries to test.
     * @return the value of the activation function, 1 or -1 that of the two classes to separate.
     */
    public Double test(List<Double> testEntries) {
        double y = weights.get(0);
        for (int i = 0; i < testEntries.size(); i++) {
            y = y + weights.get(i+1) * testEntries.get(i);
        }
        return y;
    }

    /**
     * It activates the value of the activation function to decide the class of the two set to separate.
     * @param y the value of the activation function.
     * @return the type of set of objects separated.
     */
    private double activate(Double y) {
        if (y >= 0)
            return 1;
        return -1;
    }

    /**
     * It creates the activation function multiplying the weights by the entries.
     * @param entries the entries to multiply and sum in the activation function.
     * @return the value of the activation function, 1 or -1.
     */
    private Double activation(List<Double> entries) {
        double y = 0;
        for (int i = 0; i < entries.size(); i++) {
            y = y + weights.get(i) * entries.get(i);
        }
        return activate(y);
    }

    /**
     * It calculates the error depending on the expected output.
     * @param expectedOutput the expected output that we will use to check how much error is in the activation function.
     * @param neuron the neuron data which contains the entries.
     * @return the amount of error, the subtraction between the expected output and the value of the activation function.
     */
    private double calculateError(Integer expectedOutput, Neuron neuron) {
        return expectedOutput - activation(neuron.getEntries());
    }

    /**
     * It sums 1 to the counter that controls the while loop to calculate the weights.
     * It will sum 1 only ig there is no error, if there is error then it will return the counter to 0, and it will
     * recalculate the weights.
     * @param counter the counter inside the while loop of the calculation of weights.
     * @param entries the entries that we will use in order to recalculate the weights if there is error.
     * @param error the error given by the method `calculateError(..)`
     * @return the new value of the counter.
     */
    private int getCounter(Integer counter, List<Double> entries, Double error) {
        if (error == 0) {
            return counter + 1;
        }
        weights = recalculateWeights(entries, error);
        return 0;
    }

    /**
     * It initializes the random value of the learning factor.
     * @return the learning factor to use at the calculus of the activation function.
     */
    private Double initializeLearningFactor() {
        learningFactor = (new Random().nextInt(8) + 2);
        return learningFactor / 10;
    }

    /**
     * It initializes the random value of the weights depending on the amount of entries in the neuron data.
     * @return a List<Double> of the random weights.
     */
    private List<Double> initializeWeights() {
        List<Double> weights = new ArrayList<>();
        for (int i  = 0; i < neurons.get(0).getEntries().size(); i++) {
            weights.add(new Random().nextDouble());
        }
        return weights;
    }

    /**
     * If there is an error when looping to get the correct weights, this method will recalculate the weights.
     * @param entries the entries of the data set.
     * @param error the error in the loop.
     * @return a List<Double> of new recalibrated weights.
     */
    private List<Double> recalculateWeights(List<Double> entries, Double error) {
        List<Double> newWeights = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++) {
            double weight = calibrateWeight(weights.get(i), error, entries.get(i));
            newWeights.add(weight);
        }
        return newWeights;
    }

    /**
     * It calibrates one weight depending on the amount of the error and the value of the entry.
     * @param weight the weight to recalibrate.
     * @param error the error in the loop.
     * @param entry the entry afecting the weight.
     * @return the new calibrated weight.
     */
    private double calibrateWeight(Double weight, Double error, Double entry) {
        return weight + (learningFactor * error * entry);
    }
}
