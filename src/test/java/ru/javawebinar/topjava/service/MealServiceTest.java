package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.USER_MEALS;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
/**
 * Created by Денис on 29.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    @Autowired
    protected MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        MealTestData.init();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        MATCHER.assertEquals(MEAL, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(100010, USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        USER_MEALS.remove(MEAL);
        MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(100010, USER_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<Meal> meals = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 29), LocalDate.of(2015, Month.MAY, 31), USER_ID);
        MATCHER.assertCollectionEquals(USER_MEALS, meals);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), LocalDateTime.of(2015, Month.MAY, 30, 11, 0), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL), meals);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(USER_MEALS, all);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL);
        updated.setCalories(330);
        updated.setDescription("UpdatedDescription");
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        service.update(MEAL, START_SEQ + 100);
    }


    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2016, Month.MAY, 30, 13, 0), "Обед", 4000);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        USER_MEALS.add(newMeal);
        MealTestData.sort();
        MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(USER_ID));
    }

}