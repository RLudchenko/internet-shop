package mate.academy.internetshop.service.interfaces;

import java.util.List;

public interface GenericService<T, K> {
    T create(T element);

    T get(K id);

    T update(T element);

    boolean delete(K id);

    List<T> getAll();
}
