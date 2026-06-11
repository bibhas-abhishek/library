// Decorator Design Pattern

package designpatterns.decoratorpattern;

public class DecoratorPattern {

    // Component interface: defines the base behavior that decorators will extend
    interface Coffee {
        double cost();

        String description();
    }

    // Concrete component: the base object that decorators wrap around
    static class PlainCoffee implements Coffee {
        @Override
        public double cost() {
            return 2.00;
        }

        @Override
        public String description() {
            return "Plain coffee";
        }
    }

    // Abstract decorator: holds a reference to a Coffee and delegates to it,
    // allowing subclasses to add behavior before/after delegation
    abstract static class CoffeeDecorator implements Coffee {
        protected Coffee decoratedCoffee;

        CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        @Override
        public double cost() {
            return decoratedCoffee.cost();
        }

        @Override
        public String description() {
            return decoratedCoffee.description();
        }
    }

    // Concrete decorator: adds milk — extends behavior without modifying the original class
    static class MilkDecorator extends CoffeeDecorator {
        MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double cost() {
            return decoratedCoffee.cost() + 0.50;
        }

        @Override
        public String description() {
            return decoratedCoffee.description() + ", Milk";
        }
    }

    // Concrete decorator: adds sugar
    static class SugarDecorator extends CoffeeDecorator {
        SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double cost() {
            return decoratedCoffee.cost() + 0.25;
        }

        @Override
        public String description() {
            return decoratedCoffee.description() + ", Sugar";
        }
    }

    // Concrete decorator: adds whipped cream
    static class WhippedCreamDecorator extends CoffeeDecorator {
        WhippedCreamDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double cost() {
            return decoratedCoffee.cost() + 0.75;
        }

        @Override
        public String description() {
            return decoratedCoffee.description() + ", Whipped Cream";
        }
    }

    public static void main(String[] args) {
        // Start with a plain coffee
        Coffee coffee = new PlainCoffee();
        System.out.println(coffee.description() + " -> $" + coffee.cost());

        // Wrap with milk — decorators stack on top of each other
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.description() + " -> $" + coffee.cost());

        // Add sugar on top of milk+coffee
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.description() + " -> $" + coffee.cost());

        // Add whipped cream — each decorator is independent and composable
        coffee = new WhippedCreamDecorator(coffee);
        System.out.println(coffee.description() + " -> $" + coffee.cost());
    }
}
