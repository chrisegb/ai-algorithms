# AI Algoritms
A set of simple algorithms to use just for educational purposes.


## Perceptron.
In order to use the class please do the next.
1. Initialize a list of Neurons data:
```java
List<Neuron> neurons = new ArrayList<>();
```
2. Feed in the way you want the neuron data in the example inside this project you will find a easy (and maybe bad) way to read a file and feed list instantiating a new Neuron given a list of entries and the expexted value.
```java
Stream<String> lines = FileUtils.readFile("iris_perceptron.data");
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
```
3. Instantiate the Perceptron passing as parameter the list of neurons data.
```java
Perceptron perceptron = new Perceptron(neurons);
```
4. Calculate the weights.
```java
perceptron.calculateWeights(neurons);
```
5. If you want you can print the value of the final calculated and calibrated weights.
```java
perceptron.printWeights();
```
6. Create a list of the entries to test.
```java
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
```
7. Test the linear separation:
```java
Double y = perceptron.test(testEntries);

if (y >= 0) {
    System.out.println("Es setosa");
} else {
    System.out.println("Es versicolor");
}
```
This perceptron tests its functionality using the iris data set that you can find [here](https://archive.ics.uci.edu/ml/datasets/iris).