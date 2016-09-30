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
    public static int MEAL_ID;
    public static Meal MEAL;
    public static List<Meal> USER_MEALS;

    public static void init(){
        MEAL_ID = START_SEQ+2;
        MEAL = new Meal(MEAL_ID,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

        USER_MEALS = new ArrayList<>(Arrays.asList(
                MEAL,
                new Meal(MEAL_ID+1,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(MEAL_ID+2,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(MEAL_ID+3,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(MEAL_ID+4,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(MEAL_ID+5,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        ));
        sort();
    }

    public static void sort(){Collections.sort(USER_MEALS, (o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));}

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                    )
    );

}
