// Create IntUserInputRetriever interface here
@FunctionalInterface
public interface IntUserInputRetriever<T> {
    public T produceOutputOnIntUserInput(int selection) throws IllegalArgumentException;
}