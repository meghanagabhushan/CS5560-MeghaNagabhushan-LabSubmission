/**
  * Created by Megha Nagabhushan on 6/20/2017.
  */


import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Megha on 27-Jan-17.
  */
object SparkPartitioning {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\Users\\Megha Nagabhushan\\Documents\\BDAA\\big_data");

    val sparkConf = new SparkConf().setAppName("SparkPartitioning").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("output")

    val wordRDD = input.flatMap(_.split(" "))
    //Transformation 1

    val y = wordRDD.groupBy(_.charAt(0))
    //Transformation 2

    y.saveAsTextFile("outputFile")
    //Action 1

    val o=y.collect()
    //Action 2


  }

}
