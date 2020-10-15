package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private Map<Integer, UserFromStorage> users = new HashMap<>();

    public  boolean add(UserFromStorage user) {
        if (users.containsKey(user.getId())) {
            return false;
        }
        users.put(user.getId(), user);
        return true;
    }

    public  boolean update(UserFromStorage user) {
        return users.replace(user.getId(), users.get(user.getId()), user);
    }

    public synchronized boolean delete(UserFromStorage user) {
        return users.remove(user.getId()) != null;
    }

    public  boolean transfer(int fromId, int toId, int amount) {
        if (users.containsKey(fromId) && users.containsKey(toId) && users.get(fromId).getAmount() >= amount) {
            UserFromStorage userFrom = users.get(fromId);
            UserFromStorage userTo = users.get(toId);
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            return true;
        }
        return false;
    }
}
