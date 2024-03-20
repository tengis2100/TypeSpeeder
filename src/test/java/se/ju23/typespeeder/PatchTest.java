package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @Test
    public void testPatchClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.Patch"); //ändrade filvägen
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Patch class should exist.", e);
        }
    }

    @Test
    public void testPatchProperties() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.Patch");

            Field patchVersion = someClass.getDeclaredField("patchVersion");
            assertNotNull(patchVersion, "Field 'patchVersion' should exist in the Patch class.");
            assertTrue(patchVersion.getType().equals(String.class), "Field 'patchVersion' should be of type String.");

            Field realeaseDateTime = someClass.getDeclaredField("realeaseDateTime");
            realeaseDateTime.setAccessible(true);
            assertNotNull(realeaseDateTime, "Field 'realeaseDateTime' should exist in Patch class.");

            assertTrue(realeaseDateTime.getType().equals(LocalDateTime.class), "Field 'realeaseDateTime' should be of type LocalDateTime.");

            LocalDateTime dateTimeValue = LocalDateTime.now(); //  LocalDateTime värde för att inte få null
            Object instance = someClass.getDeclaredConstructor(LocalDateTime.class).newInstance(dateTimeValue);
            //realeaseDateTime.set(instance, dateTimeValue); // värde på realeaseDateTime fält

            String preformatedDayTime = dateTimeValue.toString().replace('T', ' ');
            preformatedDayTime = preformatedDayTime.substring(0,19);                //ändrade om formatering eftersom det fanns massa siffror efter sekunder och T emellan

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime formattedDateTime = LocalDateTime.parse(preformatedDayTime,formatter);

            Field dataFromInstance = instance.getClass().getDeclaredField("realeaseDateTime");
            dataFromInstance.setAccessible(true);
            assertEquals(formattedDateTime.toString(),dataFromInstance.get(instance).toString(), "'realeaseDateTime' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getRealeaseDateTime");
            assertNotNull(getterMethod, "Getter method for field 'realeaseDateTime' should exist.");


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of Patch.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
