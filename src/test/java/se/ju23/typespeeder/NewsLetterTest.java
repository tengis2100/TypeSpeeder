package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class NewsLetterTest {

    @Test
    public void testNewsLetterClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.NewsLetter");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("NewsLetter class should exist.", e);
        }
    }
    @Test
    public void testNewsLetterContentLength() {
        try {
            Class<?> newsLetterClass = Class.forName("se.ju23.typespeeder.NewsLetter");

            // skapade ny instans
            Object instance = newsLetterClass.getDeclaredConstructor().newInstance();

            // sätt in artificiella värdet
            Method setContentMethod = newsLetterClass.getDeclaredMethod("setContent", String.class);
            setContentMethod.invoke(instance, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            // hämta värdet
            Field contentField = newsLetterClass.getDeclaredField("content");
            contentField.setAccessible(true);
            String contentValue = (String) contentField.get(instance);

            System.out.println("Actual content length: " + (contentValue != null ? contentValue.length() : "null"));

            assertNotNull(contentValue, "Content field should not be null.");
            assertTrue(contentValue.length() >= 100, "Content field length should be at least 100 characters.");
            assertTrue(contentValue.length() <= 200, "Content field length should be at most 200 characters.");

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail("Error occurred while testing NewsLetter content field length.", e);
        }
    }


    @Test
    public void testNewsLetterPublishDateTime() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.NewsLetter");

            Field publishDateTime = someClass.getDeclaredField("publishDateTime");
            assertNotNull(publishDateTime, "Field 'publishDateTime' should exist in NewsLetter class.");

            publishDateTime.setAccessible(true); // accessible field

            assertTrue(publishDateTime.getType().equals(LocalDateTime.class), "Field 'publishDateTime' should be of type LocalDateTime.");

            Object instance = someClass.getDeclaredConstructor().newInstance();
            publishDateTime.set(instance, LocalDateTime.now()); // initialiserad publishDateTime fält

            LocalDateTime dateTimeValue = (LocalDateTime) publishDateTime.get(instance);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);

            Field dataFromInstance = instance.getClass().getDeclaredField("publishDateTime");
            dataFromInstance.setAccessible(true);

            // hämta från instans
            LocalDateTime actualDateTime = (LocalDateTime) dataFromInstance.get(instance);
            String actualFormattedDateTime = actualDateTime.format(formatter);

            assertEquals(actualFormattedDateTime, formattedDateTime, "'publishDateTime' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getPublishDateTime");
            assertNotNull(getterMethod, "Getter method for the field 'publishDateTime' should exist.");


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of NewsLetter.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
