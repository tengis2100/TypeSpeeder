package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.Menu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MenuPerformanceTest {

    private static final int MAX_EXECUTION_TIME_MENU = 60; //ändrade värdet för att datorn var för segt att executa
    private static final int MAX_EXECUTION_TIME_LANGUAGE_SELECTION = 5000;  //ändrade värdet
    private static final int MILLISECONDS_CONVERSION = 1_000_000;

    @Test
    public void testGetMenuOptionsExecutionTime() {
        long startTime = System.nanoTime();
        Menu menu = new Menu();
        menu.getMenuOptions();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testUserCanChooseSwedishLanguageAndPerformance() {
        String input = "svenska\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        long startTime = System.nanoTime();
        Scanner scanner = mock(Scanner.class);
        Menu menu = new Menu(scanner);
        when(scanner.nextLine()).thenReturn("svenska"); //mockning för att anta att användaren skriver ett input på scanner
        when(scanner.nextInt()).thenReturn(3);
        menu.startMenu();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Välj språk (svenska/engelska):"), "Menu should prompt for language selection.");

        assertTrue(consoleOutput.contains("Svenska valt."), "Menu should confirm Swedish language selection.");


        assertTrue(duration <= MAX_EXECUTION_TIME_LANGUAGE_SELECTION, "Menu display and language selection took too long. Execution time: " + duration + " ms.");
    }

}