package Tests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import Network.Network;
import java.util.ArrayList;
import Phone.TestPhone;
import Phone.Rubric.*;

public class Test {

    public static int nTests = 12;

    public static void startTests() {
        System.out.println("Test in corso...");
        List<Integer> failedTests = new ArrayList<Integer>();
        for (int i = 1; i <= nTests; i++) {
            try {
                Method m = Test.class.getDeclaredMethod("Test" + i);

                if (!(Boolean) m.invoke(null))
                    failedTests.add(i);
                    
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (0 == failedTests.size())
            System.out.println("Tutti i test sono stati superati");
        else
            for (Integer i : failedTests)
                System.out.println("Test " + i + " fallito");

        Network.getIstance().reset();
    }

    // Test pro rubric search single result
    private static boolean Test1() {
        RubricApp r = new ProRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");

        if (r.search("2").equals("Prova 2\t1231212123\n"))
            return true;
        else
            return false;
    }

    // Test pro rubric search multiple result
    private static boolean Test2() {
        RubricApp r = new ProRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");

        if (r.search("p").equals("Prova 1\t3317978258\nProva 2\t1231212123\nProva 3\t3213211123\n"))
            return true;
        else
            return false;
    }

    // Test pro rubric add and show all
    private static boolean Test3() {
        RubricApp r = new ProRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 1", "1");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");
        r.add("Prova 3", "1");

        if (r.show().equals("Prova 1\t3317978258\nProva 2\t1231212123\nProva 3\t3213211123\n"))
            return true;
        else
            return false;
    }

    // Test pro rubric remove
    private static boolean Test4() {
        RubricApp r = new ProRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 1", "1");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");
        r.add("Prova 3", "1");
        r.remove("P1");
        r.remove("Prova 2");
        r.add("Prova 2", "2");

        if (r.show().equals("Prova 1\t3317978258\nProva 3\t3213211123\nProva 2\t2\n"))
            return true;
        else
            return false;
    }

    // Test pacco rubric search single result
    private static boolean Test5() {
        RubricApp r = new PaccoRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");

        if (r.search("2").equals("Prova 2\t1231212123\n"))
            return true;
        else
            return false;
    }

    // Test pacco rubric search multiple result
    private static boolean Test6() {
        RubricApp r = new PaccoRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");

        if (r.search("p").equals("Prova 1\t3317978258\nProva 2\t1231212123\nProva 3\t3213211123\n"))
            return true;
        else
            return false;
    }

    // Test pacco rubric search add and show all
    private static boolean Test7() {
        RubricApp r = new PaccoRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 1", "1");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");
        r.add("Prova 3", "1");

        if (r.show().equals("Prova 1\t3317978258\nProva 2\t1231212123\nProva 3\t3213211123\n"))
            return true;
        else
            return false;
    }

    // Test pacco rubric remove
    private static boolean Test8() {
        RubricApp r = new PaccoRubricApp();
        r.add("Prova 1", "3317978258");
        r.add("Prova 1", "1");
        r.add("Prova 2", "1231212123");
        r.add("Prova 3", "3213211123");
        r.add("Prova 3", "1");
        r.remove("P1");
        r.remove("Prova 2");
        r.add("Prova 2", "2");

        if (r.show().equals("Prova 1\t3317978258\nProva 3\t3213211123\nProva 2\t2\n"))
            return true;
        else
            return false;
    }

    // Test phone without connection
    private static boolean Test9() {
        TestPhone phone1 = new TestPhone();

        TestPhone phone2 = new TestPhone();

        phone1.sendMessage(phone2.getNumber(), "Messaggio di prova");
        return phone2.lastResult == null;
    }

    // Test phone communication without rubric
    private static boolean Test10() {
        TestPhone phone1 = new TestPhone();
        phone1.connect();
        TestPhone phone2 = new TestPhone();
        phone2.connect();

        phone1.sendMessage(phone2.getNumber(), "Messaggio di prova");

        if (phone2.lastResult == null)
            return false;
        return phone2.lastResult.equals(phone1.getNumber() + "Messaggio di prova");
    }

    // Test phone communication with rubric
    private static boolean Test11() {
        final String nome1 = "Nome1", nome2 = "nome2", messaggio = "Messaggio di prova";

        TestPhone phone1 = new TestPhone();
        phone1.connect();
        TestPhone phone2 = new TestPhone();
        phone2.connect();

        phone1.addRubric(nome2, phone2.getNumber());
        phone2.addRubric(nome1, phone1.getNumber());
        phone1.sendMessage(nome2, messaggio);

        if (phone2.lastResult == null)
            return false;
        return phone2.lastResult.equals(nome1 + messaggio);
    }

    // Test network dhcp system
    private static boolean Test12() {
        TestPhone phone1 = new TestPhone();
        phone1.connect();
        TestPhone phone2 = new TestPhone();
        phone2.connect();
        TestPhone phone3 = new TestPhone();
        phone3.connect();

        phone2.disconnect();

        TestPhone phone4 = new TestPhone();
        phone4.connect();

        phone2.connect();

        return Integer.valueOf(phone3.getNumber()) == Integer.valueOf(phone1.getNumber()) + 2
                && Integer.valueOf(phone4.getNumber()) == Integer.valueOf(phone1.getNumber()) + 3
                && Integer.valueOf(phone2.getNumber()) == Integer.valueOf(phone1.getNumber()) + 4;
    }
}