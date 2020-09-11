package ru.job4j.collectoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user);
        }
        int changed = 0;
        int deleted = previous.size();
        for (User user : current) {
            if (map.get(user.id) != null) {
                if (!map.get(user.id).equals(user)) {
                    changed++;
                }
                deleted--;
            }
        }
        int added = current.size() - (previous.size() - deleted);
        return new Info(added, changed, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }

}
