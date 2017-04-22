<h1>How to optimize HDInsight cluster for high priority and low priority jobs? </h1>
If you have a scenario where you want to
-	Always have high capacity for higher priority jobs
-	Low priority job can run with high capacity when there are no high priority job in the queue
-	When a high priority job is running, low priority job gets only fraction of the cluster resource

You can achieve this with Yarn capacity scheduler by doing following

1.	Create a high priority queue with capacity ~70% and max capacity to 100%
2.	Create a low priority queue with capacity ~30% and max capacity to 100%


With these settings, High priority queue queue would always get ~70% of the cluster if it needs it and the second queue would only get a ~30 % of the cluster if the high priority queues have jobs. The low priority queue would still be able to monopolize the cluster but it will take longer when high priority jobs are running.

<h1>How to parse hive queries programmatically </h1>

In java, you can use Hive ParseDriver (https://github.com/apache/hive/blob/master/ql/src/java/org/apache/hadoop/hive/ql/parse/ParseDriver.java) which will give you each statement and give you complete ParseTree.

You need following in your Maven pom.xml

<dependency>
            <groupId>org.apache.hive</groupId>
            <artifactId>hive-exec</artifactId>
            <version>${hive.version}</version>
</dependency>

