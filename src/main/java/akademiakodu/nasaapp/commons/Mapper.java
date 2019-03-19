package akademiakodu.nasaapp.commons;

public interface Mapper<F, T> {

    T map(F from);
    F revers(T from);
}
