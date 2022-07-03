import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Stream<String> lines = FileUtils.readFile("iris_perceptron.data");

        List<Neuron> neurons = new ArrayList<>();
        lines.forEach(line -> {
            String[] split = line.split(",");
            String irisType = split[split.length - 1];

            int expectedOutput = 0;
            if (irisType.equalsIgnoreCase("iris-setosa")) {
                expectedOutput = 1;
            } else {
                expectedOutput = -1;
            }

            String s = split[0];
            Double e1 = Double.valueOf(s);
            Double e2 = Double.valueOf(split[1]);
            Double e3 = Double.valueOf(split[2]);
            Double e4 = Double.valueOf(split[3]);
            Neuron neuron = new Neuron(Arrays.asList(1D, e1, e2, e3, e4), expectedOutput);
            neurons.add(neuron);
        });

        Perceptron perceptron = new Perceptron(neurons);
        perceptron.calculateWeights(neurons);
        perceptron.printWeights();

        Scanner scanner = new Scanner(System.in);
        double entry1 = scanner.nextDouble();
        double entry2 = scanner.nextDouble();
        double entry3 = scanner.nextDouble();
        double entry4 = scanner.nextDouble();

        List<Double> testEntries = new ArrayList<>();
        testEntries.add(entry1);
        testEntries.add(entry2);
        testEntries.add(entry3);
        testEntries.add(entry4);

        Double y = perceptron.test(testEntries);

        if (y >= 0) {
            System.out.println("Es setosa");
        } else {
            System.out.println("Es versicolor");
        }
    }
}
