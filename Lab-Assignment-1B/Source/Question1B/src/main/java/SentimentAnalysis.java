/**
 * Created by Mayanka on 20-Jul-15.
 * Reference : https://github.com/shekhargulati/day20-stanford-sentiment-analysis-demo
 */

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class SentimentAnalysis {

    public TweetWithSentiment determineSentiment(String line) {

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(properties);
        int textSentiment = 0;
        if (line != null && line.length() > 0) {
            int longest = 0;
            Annotation annotation = stanfordCoreNLP.process(line);
            for (CoreMap lines : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = lines.get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentimentValue = RNNCoreAnnotations.getPredictedClass(tree);
                String text = lines.toString();
                if (text.length() > longest) {
                    textSentiment = sentimentValue;
                    longest = text.length();
                }

            }
        }
        if (textSentiment == 2 || textSentiment > 4 || textSentiment < 0) {
            return null;
        }

        TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(line, toCss(textSentiment));
        return tweetWithSentiment;

    }
    public int determineSentiment(String line,int i) {
    Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
            StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(properties);
            int textSentiment = 0;
            if (line != null && line.length() > 0) {
                int longest = 0;
                Annotation annotation = stanfordCoreNLP.process(line);
                for (CoreMap lines : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                    Tree tree = lines.get(SentimentCoreAnnotations.AnnotatedTree.class);
                    int sentimentValue = RNNCoreAnnotations.getPredictedClass(tree);
                    String partText = lines.toString();
                    if (partText.length() > longest) {
                         textSentiment = sentimentValue;
                        longest = partText.length();
                    }

                }
            }
            if ( textSentiment == 2 ||  textSentiment > 4 ||  textSentiment < 0) {
                return -1;
            }

            return  textSentiment;


    }

    private String toCss(int sentiment) {
        switch (sentiment) {
            case 0:
                return "sentiment : very negative";
            case 1:
                return "sentiment : negative";
            case 2:
                return "sentiment : neutral";
            case 3:
                return "sentiment : positive";
            case 4:
                return "sentiment : very positive";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        SentimentAnalysis sentimentAnalyzer = new SentimentAnalysis();
        TweetWithSentiment tweetWithSentiment = sentimentAnalyzer
                .determineSentiment("click here for your Sachin Tendulkar personalized digital autograph.");
        System.out.println(tweetWithSentiment);
    }
}
