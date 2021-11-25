import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class ThreadExample {

    public static void main(String[] args) throws Exception {

        URL otodom = new URL("https://adresowo.pl/domy/krakow/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(otodom.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }
        //System.out.println(stringBuilder.toString());
        in.close();

        Set<String>listOfLinks = new TreeSet<>();
        String content = stringBuilder.toString();


        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("<div class=\"result-photo\" href=\"", i);
            if (i < 0)
                break;

            String substring = content.substring(i + 32);
            String link = substring.split("\">")[0];
            //System.out.println("https://adresowo.pl/domy/krakow"+link);
            String test = "https://adresowo.pl/domy/krakow";

            listOfLinks.add(test+link);



        }
        System.out.println(listOfLinks);
    }
}



        /* String content = stringBuilder.toString();

        int linkIndex = content.indexOf("https://www.otodom.pl/pl/oferta/");
        String substring = content.substring(linkIndex);
        String link = substring.split(".html")[0];
        System.out.println(link);*/

       /* Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 999; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 999; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        });
        t2.setPriority(10);
        t1.setName("t1");
        t2.setName("t2");
        //t1.run("t1");  run in main thread
        t1.start();
        t2.start();*/

