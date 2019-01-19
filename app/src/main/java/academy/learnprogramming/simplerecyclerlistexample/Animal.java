package academy.learnprogramming.simplerecyclerlistexample;

public class Animal {

    private String name;
    private int weight;
    private String info;

    Animal(String n, int w, String i) {

        name = n;
        weight = w;
        info = i;
    }

    Animal(String n, int w) {

        name = n;
        weight = w;
        info = "No info available.";
    }

    public String getName() {
        return name;
    }


    public String getWeight() {
        return Integer.toString(weight);
    }

    public String getInfo() {
        return info;
    }
}
