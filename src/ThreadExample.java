import java.io.*;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample {

    public static void main(String[] args) throws Exception {


        ExecutorService executorService = Executors.newFixedThreadPool(30);

        long start = System.currentTimeMillis();


        URL adresowo = new URL("https://adresowo.pl/domy/krakow/");
        //"https://adresowo.pl/domy/krakow/"
        BufferedReader in = new BufferedReader(
                new InputStreamReader(adresowo.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }


        Set<String>setOfLinks = new TreeSet<>();
        String content = stringBuilder.toString();


        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("<div class=\"result-photo\" href=\"", i);
            if (i < 0)
                break;

            String substring = content.substring(i + 32);
            String link = substring.split("\">")[0];
            //System.out.println("https://adresowo.pl/domy/krakow"+link);
            String http = "https://adresowo.pl";
            setOfLinks.add(http+link);

        }

        for (int i = 0; i < setOfLinks.size(); i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    readWebsite(setOfLinks.toArray()[finalI].toString(), finalI + ".html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            );
        }
        executorService.shutdown();
        System.out.println(setOfLinks);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

// This method read link and write into the file
    public static void readWebsite(String link, String fileName) throws IOException {

        URL adresowo = new URL(link);
        //"https://adresowo.pl/domy/krakow/"
        BufferedReader in = new BufferedReader(
                new InputStreamReader(adresowo.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }
        //System.out.println(stringBuilder.toString());
        in.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
        bw.write(stringBuilder.toString());
        bw.close();
    }
}

