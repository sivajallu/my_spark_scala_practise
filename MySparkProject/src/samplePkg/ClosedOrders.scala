package samplePkg

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession}

import java.io.FileNotFoundException

object ClosedOrders {
  def main(args: Array[String]): Unit = {
    var spark: SparkSession = null
    try {
      Logger.getLogger("org").setLevel(Level.ERROR)

      val sparkConf = new SparkConf();
      sparkConf.set("spark.app.name", "my first application")
      sparkConf.set("spark.master", "local[2]")
      spark = SparkSession.builder().config(sparkConf).getOrCreate()

      val inputFolderName = args(0)
      val outputFolderName = args(1)
      val hdfsLocation = args(2)

      val inputFilePath = s"$hdfsLocation/$inputFolderName"
      val outputFilePath = s"$hdfsLocation/$outputFolderName"

      println(hdfsLocation);
      println(inputFilePath);
      println(outputFilePath);

      /*
      val inputFilePath = s"hdfs://localhost:9000//user/padmaram/$inputFolderName"
      val outputFilePath = s"hdfs://localhost:9000//user/padmaram/$outputFolderName"
      */
      //spark-submit --class samplePkg.ClosedOrders /home/padmaram/IdeaProjects/MySparkProject/out/artifacts/MySparkProject_jar/MySparkProject.jar airflow_input/orders.csv airflow_output hdfs://localhost:9000//user/padmaram

      val orderDf = spark.read
        .format("csv")
        .option("header", true)
        .option("inferSchema", true)
        .option("path", inputFilePath).load()

      orderDf.createOrReplaceTempView("orders")

      val closedOrders = spark.sql("select * from orders where order_status = 'CLOSED'")

      closedOrders.write.format("csv").mode(saveMode = SaveMode.Overwrite).option("path", outputFilePath).save()

      println("successfully copied")
    }
    catch {
      case ex: FileNotFoundException => {
        println(s"File not found")
      }
      case unknown: Exception => {
        println(s"Unknown exception: $unknown")
      }
    }
    finally {
      spark.close()
    }
  }
}
