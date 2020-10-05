public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize");
    }

    public static void info() {
        int mb = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();

        System.out.println("Used Memory: " + (runtime.totalMemory() - runtime.freeMemory() / mb));
        System.out.println("Free memory: " + runtime.freeMemory() / mb);
        System.out.println("Total memory " + runtime.totalMemory() / mb);
        System.out.println("Max memory " + runtime.maxMemory() / mb);
    }

    //Set VM options -Xmx10m
    public static void main(String[] args) {
        info();
        System.out.println(System.lineSeparator());
        for (int i = 0; i < 50; i++) {
            User user = new User("name");
            if (i % 2 == 0) {
                user = null;
            }
        }
        info();
    }

}
