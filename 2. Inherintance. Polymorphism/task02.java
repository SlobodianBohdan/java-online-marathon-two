// Create interface DrinkReceipt with methods String getName() and DrinkReceipt addComponent(String componentName, int componentCount). Create interface DrinkPreparation with method Map<String, Integer> makeDrink() to return a drink components. Create interface Rating with method int getRating().
// Class Caffee contains fields String name, int rating, Map of ingredients and implements interfaces DrinkReceipt, DrinkPreparation and Rating. Method makeDrink() prepare coffee with typically components: {Water=100, Arabica=20}. Espresso and Cappuccino classes extends the Caffee Class and override method makeDrink(). Espresso caffee has 50 g. of Water. Cappuccino caffee has an additional of 50 g. of Milk.
// Create a averageRating() method of the MyUtils class to return a Map with coffee name as key and coffee average rating as value.
// For example, for a given list
// [Espresso [name=Espresso, rating=8], Cappuccino [name=Cappuccino, rating=10], Espresso [name=Espresso, rating=10], Cappuccino [name=Cappuccino, rating=6], Caffee [name=Caffee, rating=6]]
// you should get
// {Espresso=9.00, Cappuccino=8.00, Caffee=6.00}


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface DrinkReceipt {
    String getName();

    DrinkReceipt addComponent(String componentsName, int componentCount);
}

interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}

interface Rating {
    int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    private String name;
    private int rating;
    private Map<String, Integer> ingredients = new HashMap<>();

    public Caffee() {
    }

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

     public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DrinkReceipt addComponent(String componentsName, int componentCount) {
        ingredients.put(componentsName, componentCount);
        return this;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        addComponent("Water", 100);
        addComponent("Arabica", 20);
        return ingredients;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Caffee{" +
                "name='" + name + '\'' +
                ",rating=" + rating +
                '}';
    }
}
class Espresso extends Caffee {
    public Espresso(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        addComponent("Water", 50);
        addComponent("Arabica", 20);
        return getIngredients();
    }
}
class Cappuccino extends Caffee {

    public Cappuccino(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        addComponent("Water", 100);
        addComponent("Arabica", 20);
        addComponent("Milk", 50);
        return getIngredients();
    }
}


class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        Map<String, Double> map = new HashMap<>();
        int countCaffee = 0;
        int countEspresso = 0;
        int countCappuccino = 0;
        double sumaCaffee = 0;
        double sumaEspresso = 0;
        double sumaCappuccino = 0;

        if (coffees.size() == 1){
            Double rating = coffees.get(0).getRating() * 1.0;
            map.put(coffees.get(0).getName(), rating);
            return map;
        }
        if (coffees.isEmpty()){
            return map;
        }
        for (Caffee c : coffees) {
        
            if (c instanceof Espresso) {
                countEspresso++;
                sumaEspresso += c.getRating();
            }else if (c instanceof Cappuccino) {
                countCappuccino ++;
                sumaCappuccino += c.getRating();
            }else {
                countCaffee++;
                sumaCaffee += c.getRating();
            }

        }
        map.put("Caffee", sumaCaffee/countCaffee);
        map.put("Espresso", sumaEspresso/countEspresso);
        map.put("Cappuccino", sumaCappuccino/countCappuccino);

        return map;
    }
}
