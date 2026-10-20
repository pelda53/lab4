public interface Stage<IN, OUT> { OUT process(IN input) throws Exception; }
interface Source<T> { T next() throws Exception; }
interface Sink<T> { void consume(T item) throws Exception; }
