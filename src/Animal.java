abstract class Animal {
    private String animalType;
    public abstract void sounds();

    // Compile-time polymorphism (overloading)
    public String legs(String name, int numOfLegs) {
        return name + " has " + numOfLegs + " legs.";
    }

    public String legs(String name, int numOfLegs, int age) {
        return name + " has " + numOfLegs + " legs. And it is " + age + " years old.";
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }


}


class Dog extends Animal {
    @Override
    public void sounds() {
        System.out.println("Dogs bark");
        String ans = legs("Tommy", 4);
        System.out.println(ans);
        setAnimalType("Mammal");
        String animalType = getAnimalType();
        System.out.println(animalType);
    }
}

class Bird extends Animal {
    @Override
    public void sounds() {
        System.out.println("Birds chirp");
        String ans = legs("Chirpy", 2);
        System.out.println(ans);
        setAnimalType("Aves");
        String animalType = getAnimalType();
        System.out.println(animalType);
    }
}

class Reptile extends Animal {
    @Override
    public void sounds() {
        System.out.println("Reptile rolls");
        String ans = legs("Peter", 4, 5); // Reptile is 5 years old
        System.out.println(ans);
        setAnimalType("Reptiles");
        String animalType = getAnimalType();
        System.out.println(animalType);
    }
}

