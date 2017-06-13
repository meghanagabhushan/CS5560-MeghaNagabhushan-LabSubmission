object MainClass {

  def main(args: Array[String]) {
    //sentiment Analysis class
    val sentimentAnalysis: SentimentAnalysis = new SentimentAnalysis
    //reading lines from the text file
    val lines = scala.io.Source.fromFile("src\\main\\scala\\michelle.obama").mkString
    //sentiments like - positive or negative is determinedby this method.
    val textSentiment: TweetWithSentiment = sentimentAnalysis.determineSentiment(lines)
    //printing the sentiment
    System.out.println("The sentiment of the file is:"+textSentiment)
  }
}
