import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Question1A {
    public static void main(String args[]) throws IOException {
        // creating a StanfordCoreNLP object and perorming Natural language processing
        // Steps: POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties property = new Properties();
        property.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(property);

        List personList = new ArrayList();

        //passing the file to the readFile method which reads the entire file and returns a string.
        String line = readFile("src/main/java/michelle.obama");

//creating annotation for the text
        Annotation annotation = new Annotation(line);

        // annotating the line
        stanfordCoreNLP.annotate(annotation);

//The CoreMap key for getting the sentences contained by an annotation. This key is typically set only on document annotations.
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                System.out.println("\nThe current token is -> " + token);
                //Breaking the sentence into tokens
                String tokenizedWord = token.get(CoreAnnotations.TextAnnotation.class);
                System.out.println("Text Annotation\t" + token + "->" + tokenizedWord);
                //Lemmatization usually refers to doing things properly with the use of a vocabulary and morphological analysis of words, normally aiming to remove inflectional endings only and to return the base or dictionary form of a word, which is known as the lemma
                String lemmatization = token.get(CoreAnnotations.LemmaAnnotation.class);
                System.out.println("Lemmatization\t" + token + "->" + lemmatization);
                //A Part-Of-Speech Tagger (POS Tagger) is a piece of software that reads text in some language and assigns parts of speech to each word
                String posTags = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                System.out.println("Parts of Speech Tagging\t"+ token + "->" + posTags);
                //Name entity recognition is a subtask of information extraction that seeks to locate and classify named entities in text into pre-defined categories
                String nameEntityRecognition = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println("Name Entity Recognizer\t" + token + "->" + nameEntityRecognition);

                System.out.println("\n\n");
            }

            // displaying parse tree of the sentence
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
            System.out.println("Displaying tree for the current sentence-\n");
            System.out.println(tree);
            // dependencies for the sentence
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            System.out.println("Displaying dependencies for the current sentence-\n");
            System.out.println(dependencies.toString());
            //graph of the sentence
            Map<Integer, CorefChain> graphs =
                    annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
            System.out.println("Displaying graph for the sentence");
            System.out.println(graphs.values().toString());
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
