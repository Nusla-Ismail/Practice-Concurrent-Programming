public class Cat {
    private String name;
    int age;


    public Cat(String name, int age){
        this.name = name;
        this.age =age;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void meow(){
        System.out.println("Meow");

    }

}
