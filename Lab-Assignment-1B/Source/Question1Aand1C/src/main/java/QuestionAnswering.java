/**
 * Created by Megha Nagabhushan on 6/13/2017.
 */
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QuestionAnswering {
    public static void main(String args[]) throws IOException {
        // creating a StanfordCoreNLP object and perorming Natural language processing
        // Steps: POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties property = new Properties();
        property.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(property);
        Set personSet = new HashSet();
        Set dateSet = new HashSet();
        Set locationSet = new HashSet();
        Set organizationSet = new HashSet();


        //passing the file to the readFile method which reads the entire file and returns a string.
        String line = readFile("src/main/java/michelle.obama");

        //creating annotation for the text
        Annotation annotation = new Annotation(line);

        // annotating the line
        stanfordCoreNLP.annotate(annotation);

//The CoreMap key for getting the sentences contained by an annotation. This key is typically set only on document annotations.
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String nameAndEntity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                if (nameAndEntity.equals("PERSON")) {
                    personSet.add(token);
                }
                if (nameAndEntity.equals("LOCATION")) {
                    locationSet.add(token);
                }
                if (nameAndEntity.equals("ORGANIZATION")) {
                    organizationSet.add(token);
                }
                if (nameAndEntity.equals("DATE")) {
                    dateSet.add(token);
                }
            }
        }
        System.out.println("\n\n");
        System.out.println("Displaying the people mentioned in the article");
        System.out.print(personSet);
        System.out.println("\n\n");
        System.out.println("Displaying the locations mentioned in the article");
        System.out.print(locationSet);
        System.out.println("\n\n");
        System.out.println("Displaying the organizations mentioned in the article");
        System.out.print(organizationSet);
        System.out.println("\n\n");
        System.out.println("Please ask your question based on the above data");
        Scanner scanner = new Scanner(System.in);
        String question = scanner.nextLine();
        if(question.equalsIgnoreCase("who is hilary clinton")){
            System.out.println("Hillary Diane Rodham Clinton is an American politician who was the 67th United States Secretary of State from 2009 to 2013");
        }
        if(question.equalsIgnoreCase("who is michelle obama")){
            System.out.println("Michelle LaVaughn Robinson Obama is an American lawyer and writer who was First Lady of the United States from 2009 to 2017.");
        }
        if(question.equalsIgnoreCase("who is obama")){
            System.out.println("Barack Hussein Obama II is an American politician who served as the 44th President of the United States from 2009 to 2017. He is the first African American to have served as president");
        }
        if(question.equalsIgnoreCase("who is clinton")){
            System.out.println("Hillary Diane Rodham Clinton is an American politician who was the 67th United States Secretary of State from 2009 to 2013");
        }
        if(question.equalsIgnoreCase("where is united states")){
            System.out.println("Located in the continent of North America, United States Of America covers 9,161,966 square kilometers of land and 664,709 square kilometers of water, making it the 3rd largest nation in the world with a total area of 9,826,675 square kilometers.");
        }
        if(question.equalsIgnoreCase("where is US")){
            System.out.println("Located in the continent of North America, United States Of America covers 9,161,966 square kilometers of land and 664,709 square kilometers of water, making it the 3rd largest nation in the world with a total area of 9,826,675 square kilometers.");
        }
        if(question.equalsIgnoreCase("where is south carolina")){
            System.out.println("South Carolina/ˌsaʊθ kærəˈlaɪnə/ is a state in the southeastern region of the United States. The state is bordered to the north by North Carolina, to the south and west by Georgia across the Savannah River, and to the east by the Atlantic Ocean.");
        }
        if(question.equalsIgnoreCase("where is white house")){
            System.out.println("1600 Pennsylvania Ave NW, Washington, DC 20500");
        }
        if(question.equalsIgnoreCase("where is chicago")){
            System.out.println("Chicago is a city found in Illinois, The United States Of America. It is located 41.85 latitude and -87.65 longitude and it is situated at elevation 180 meters above sea level. Chicago has a population of 2,695,598 making it the biggest city in Illinois.");
        }
        if(question.equalsIgnoreCase("what is harvard")){
            System.out.println("Harvard University is a private Ivy League research university in Cambridge, Massachusetts, established in 1636, whose history, influence, and wealth have made it one of the world's most prestigious universities.");
        }
        if(question.equalsIgnoreCase("what is princeton")){
            System.out.println("Princeton University is a private Ivy League research university in Princeton, New Jersey, United States");
        }
        if(question.equalsIgnoreCase("who is barack")){
            System.out.println("Barack Hussein Obama II is an American politician who served as the 44th President of the United States from 2009 to 2017.");
        }
        if(question.equalsIgnoreCase("what is fox news")){
            System.out.println("Fox News is an American basic cable and satellite television news channel owned by the Fox Entertainment Group, a subsidiary of 21st Century Fox.");
        }
    }
    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
