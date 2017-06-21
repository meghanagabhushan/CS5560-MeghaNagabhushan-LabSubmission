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
import java.io.FileWriter;
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

        List<String> personList = new ArrayList();

        //passing the file to the readFile method which reads the entire file and returns a string.
        //String line = readFile("src/main/java/michelle.obama");
            String line = "Michelle: Barack's bitter or better half?\n" +
                    "\n" +
                    "It is less than a month into the general election and Michelle Obama is already in the midst of a makeover.\n" +
                    "\n" +
                    "Michelle Obama\n" +
                    "Michelle Obama could help court the all-important female vote\n" +
                    "\n" +
                    "A tough primary campaign put some dents in her image. She weathered a storm of criticism following a comment she made about her husband's candidacy, saying that \"for the first time in my adult lifetime, I'm really proud of my country, and not just because Barack has done well, but because I think people are hungry for change\".\n" +
                    "\n" +
                    "Immediately, Republicans and her Democratic rivals piled in, including Republican presidential hopeful John McCain's wife Cindy McCain, who has alluded to Mrs Obama's comment on more than one occasion.\n" +
                    "\n" +
                    "The conservative magazine National Review dubbed her \"Mrs Grievance.\" Conservative commentators have called her Barack Obama's \"bitter half.\" Fox News was rebuked for referring to her with the racial slander \"Baby Mama,\" and talk among political pundits escalated about her \"angry\" side.\n" +
                    "\n" +
                    "The politically motivated rumour mills that have plagued Mr Obama have not spared Mrs Obama either. Whisper campaigns on the internet have alluded to racist comments in her past that she says she has never made (and the evidence is solidly in her court, as so far no one has produced any evidence of the comments).\n" +
                    "\n" +
                    "True to herself\n" +
                    "\n" +
                    "Still, the Obama campaign is actively trying to refresh Mrs Obama's image. Appearing on ABC's The View last week, Mrs Obama used the opportunity to address her critics, but also to present a picture that those close to her say is more true to herself than the caricature her critics are painting.\n" +
                    "\n" +
                    "Michelle Obama on The View\n" +
                    "Michelle with her jokey \"fist bump\" on The View\n" +
                    "During the course of the show she was able to talk about her background, growing up in humble circumstances on the South Side of Chicago, attending Princeton for college, and Harvard law school, becoming a wife and a mother.\n" +
                    "\n" +
                    "She addressed her \"proud\" gaffe directly, saying \"I think when I talked about it during my speech, what I was talking about was having a part in the political process. People are just engaged in this election in a way that I haven't seen in a long time and I think everybody has agreed with that, that people are focused, they're coming out.\"\n" +
                    "\n" +
                    "She also appeared this week on the cover of US Weekly Magazine above the title Why Barack Loves Her. The article pointed out that she shops at the US store Target, loved the television show Sex and the City, and included a quote from Mr Obama, who told the magazine, \"Nothing is more important to Michelle than being a good mother.\"\n" +
                    "\n" +
                    "It is no surprise that the Obama campaign chose The View and US Weekly for their re-introduction of Mrs Obama; those outlets are extremely popular among women, who are up for grabs in this election.\n" +
                    "\n" +
                    "Hillary Clinton's loss in the Democratic primary, combined with Republican competition for important female demographics including married and Hispanic women, makes winning women all the more important for Mr Obama.\n" +
                    "\n" +
                    "Modest roots\n" +
                    "\n" +
                    "Mrs Obama could be quite an asset when appealing to these voters.\n" +
                    "\n" +
                    "Her background is another electoral asset. Her roots on Chicago's historically black South Side and the fact that she is a descendant of American slaves served as an important counter-point to early questions among African American voters over whether Mr Obama is \"black enough.\"\n" +
                    "\n" +
                    "Barack Obama\n" +
                    "Michelle could provide a counterpoint to Barack who is more guarded\n" +
                    "\n" +
                    "Her conversations with African Americans about her background and the fact that she has family from South Carolina were a key piece of the Obama primary strategy in the state, and contributed to Mr Obama's win there.\n" +
                    "\n" +
                    "She is also well-positioned as a bold counterpoint to the GOP's favourite charge when it comes to Mr Obama: \"elitism\". Her modest roots in a one-bedroom apartment, with a shift-working father, are anything but \"elite\".\n" +
                    "\n" +
                    "Further, as she said herself on last week's The View, she \"wears her heart on her sleeve,\" a potentially powerful contrast to Mr Obama, who, at times, has come across as more guarded.\n" +
                    "\n" +
                    "She is able to speak for the candidate in a way that no one else can. Mrs Obama has talked about what her husband was like as a younger man, the qualities that she loves about him and the reasons she thinks he should be president.\n" +
                    "\n" +
                    "All this is in the hope that voters can connect to her stories, and increase their trust in him. If she can do that, she will be enormously successful in her role.\n" +
                    "\n" +
                    "The downside to all of this, as the Obama campaign is all too aware, is that she runs the risk of being an enormous liability. If rumours about Mrs Obama being anti-white or unpatriotic persist, they could have a very negative impact on Mr Obama's chance to win the White House.\n" +
                    "\n" +
                    "Not only do these stories steal the spotlight from the message that the campaign is trying to convey, but they contribute to voters' doubts about Mr Obama.\n" +
                    "\n" +
                    "Whether true or completely unfounded, as many of these rumours are, they have a danger of becoming a distraction, and chipping away at Mr Obama's trustworthiness rating, which would, in turn, diminish his ability to win the White House.\n" +
                    "\n" +
                    "At the end of the day, voters vote for the candidate they most want to be president of the United States, and not for his wife. But in the months between now and election day, Michelle Obama can have a huge impact on how voters feel on 4 November.";
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
                if(nameEntityRecognition.equals("PERSON")){
                    personList.add(token.toString());
                }
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
        System.out.println(personList);
        FileWriter writer = new FileWriter("output.txt");
        for(Object str: personList) {
            writer.write((String) str);
        //writer.write((String) str);
        writer.write("\n");
         }
        writer.close();

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
