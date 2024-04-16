import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> person = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            person.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long low18Age = person.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println(low18Age);

        List<String> familyOfSoldier = person.stream()
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() <= 27)
                .map(Person::getFamily).collect(Collectors.toList());

        System.out.println(familyOfSoldier);

        List<String> manWorker = person.stream()
                .filter(value -> value.getSex() == Sex.MAN)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily).collect(Collectors.toList());

        System.out.println(manWorker);

        List<String> womanWorker = person.stream()
                .filter(value -> value.getSex() == Sex.WOMAN)
                .filter(value -> value.getEducation() == Education.HIGHER)
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily).collect(Collectors.toList());

        System.out.println(womanWorker);

    }
}