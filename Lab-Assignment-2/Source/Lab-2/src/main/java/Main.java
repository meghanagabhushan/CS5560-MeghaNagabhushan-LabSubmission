import edu.stanford.nlp.ling.CoreAnnotations;
import java.io.IOException;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.FileWriter;
import edu.stanford.nlp.pipeline.Annotation;
import java.util.ArrayList;
import java.util.List;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;


/**
 * Created by Megha Nagabhushan on 6/20/2017.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        // creating property object
        Properties properties = new Properties();
        //setting the property
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        //Creating a stanford core nlp class and passing properties object to its constructor
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(properties);

        ArrayList lemmaList = new ArrayList();

        // we will be creating lemma tags for the following text
        String inputString = "It would make for provoking  experience, if Tim was serious about assigning him to manage the battlefield from afar.";

        Annotation annotation = new Annotation(inputString);


        stanfordCoreNLP.annotate(annotation);

        List<CoreMap> text = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap x : text) {
            
            for (CoreLabel token : x.get(CoreAnnotations.TokensAnnotation.class)) {

                System.out.println("The label is\n" + token);
                    //generating lemmas
                    String lemmaAnnotation = token.get(CoreAnnotations.LemmaAnnotation.class);
                    System.out.println("Lemma Generated");
                    System.out.println(token + ":" + lemmaAnnotation);
                    //removing punctuation marks
                    if(!lemmaAnnotation.equals(",") && !lemmaAnnotation.equals(".")) {
                        lemmaList.add(lemmaAnnotation);
                    }

                System.out.println("\n\n");
            }

        }
        //Writing out the lemma list into the file "output"
        FileWriter writer = new FileWriter("output.txt");
        for(Object str: lemmaList) {
            writer.write((String) str);
            writer.write("\n");
        }
        writer.close();

    }
}
