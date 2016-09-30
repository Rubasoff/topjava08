package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int MEAL_ID = START_SEQ+2;
    public static final Meal MEAL = new Meal(MEAL_ID,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
   // public static Map<Meal, Integer> USER_MEALS = new TreeMap<>();
  /*  static
    {
        USER_MEALS.put(MEAL, 100000);
        USER_MEALS.put(new Meal(MEAL_ID+1,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000), 100000);
        USER_MEALS.put(new Meal(MEAL_ID+2,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500), 100000);
        USER_MEALS.put(new Meal(MEAL_ID+3,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000), 100000);
        USER_MEALS.put(new Meal(MEAL_ID+4,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500), 100000);
        USER_MEALS.put(new Meal(MEAL_ID+5,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510), 100000);
        USER_MEALS.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey((Meal m1, Meal m2) -> m1.getDateTime().compareTo(m2.getDateTime())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }*/

    public static List<Meal> USER_MEALS = new ArrayList<>(Arrays.asList(
            MEAL,
            new Meal(MEAL_ID+1,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(MEAL_ID+2,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(MEAL_ID+3,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(MEAL_ID+4,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(MEAL_ID+5,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    ));

    static {
        Collections.sort(USER_MEALS, (o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
    }
    public static final List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 18, 0), "Ужин", 1500)
    );

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                    )
    );

}
