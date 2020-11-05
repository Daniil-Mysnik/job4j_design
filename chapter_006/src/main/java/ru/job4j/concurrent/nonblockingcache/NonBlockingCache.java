package ru.job4j.concurrent.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private ConcurrentHashMap<Integer, Base> container = new ConcurrentHashMap<>();

    public void add(Base model) {
        container.putIfAbsent(model.getId(), model);
    }

    public boolean update(Base model) {
        if (container.contains(model)) {
            container.computeIfPresent(model.getId(), (key, value) -> {
                if (value.getVersion() != model.getVersion()) {
                    throw new OptimisticException("Incorrect version");
                }
                Base base = new Base(key);
                base.setVersion(value.getVersion() + 1);
                return base;
            });
            return true;
        }
        return false;
    }

    public boolean delete(Base model) {
        if (container.contains(model)) {
            container.remove(model);
            return true;
        }
        return false;
    }

}
