import java.util.List;

public class Neuron {

    private List<Double> entries;
    private Integer expectedOutput;

    public Neuron(List<Double> entries, Integer expectedOutput) {
        this.entries = entries;
        this.expectedOutput = expectedOutput;
    }

    public List<Double> getEntries() {
        return entries;
    }

    public void setEntries(List<Double> entries) {
        this.entries = entries;
    }

    public Integer getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(Integer expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
