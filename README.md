<h1>How to optimize HDInsight cluster for high priority and low priority jobs? </h1>
If you have a scenario where you want to
-	Always have high capacity for higher priority jobs
-	Low priority job can run with high capacity when there are no high priority job in the queue
-	When a high priority job is running, low priority job gets only fraction of the cluster resource

You can achieve this with Yarn capacity scheduler by doing following

1.	Create a high priority queue with capacity ~70% and max capacity to 100%
2.	Create a low priority queue with capacity ~30% and max capacity to 100%


With these settings, High priority queue queue would always get ~70% of the cluster if it needs it and the second queue would only get a ~30 % of the cluster if the high priority queues have jobs. The low priority queue would still be able to optimize the cluster but it will take longer when high priority jobs are running.

<h1>How to parse hive queries programmatically </h1>

In java, you can use Hive ParseDriver (https://github.com/apache/hive/blob/master/ql/src/java/org/apache/hadoop/hive/ql/parse/ParseDriver.java) which will give you each statement and give you complete ParseTree.

You need following in your Maven pom.xml

<dependency>
            <groupId>org.apache.hive</groupId>
            <artifactId>hive-exec</artifactId>
            <version>${hive.version}</version>
</dependency>

</h1> Resource Needs </h1>
Sorting & Partitioning is CPU heavy 
Spilling to disk: Reduce this by adding more memory , adds time to process the data
Serialization & Deserializing - More serializarion and desirialization will add to the CPU cost so avoid them if not needed
Network Traffic
Merging
Lockstep
Partition Multiplier

Problem: 

In some companies the view is a way of life and in fact I have seen many companies have views that are multiple levels deep.  All in the effort to add security or to consolidate logic, which is all good but for the problem of when those views contain joins.  See the problem is if you have a hive view that contains joins that join is executed every time that view is accessed.  If that join requires a shuffle then you are reordering and partitioning that data every time you access that view.  Which in the end is a lot of repeated work that consumed your CPUs that could have been used for something else.



Answer: 

The easy answer is the materialized view.  The idea here is storage is cheaper than time or CPU.  So if your data updates daily and it gets queried 100 times a day, then a materialized view can reduce the shuffle cost by 99%.  If you data changes in a more real time fashion then making materialized view become much harder, but still may have value in some cases.


