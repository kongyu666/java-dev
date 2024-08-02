# Java8 和 Spark  SpringBoot2

## 打包运行Spark On YARN

配置hive文件

> 如果Spark配置了Hive，则需要将**hive-site.xml**配置文件拷贝到$SPARK_HOME/conf下

```shell
ln -s $HIVE_HOME/conf/hive-site.xml $SPARK_HOME/conf/hive-site.xml
```

提交任务到yarn（客户端模式），适用于开发测试

> 传入参数指定需要运行的程序**spark.sql**，对应MySparkStart的配置

```shell
spark-submit \
    --master yarn \
    --deploy-mode client \
    MySparkOnSpring-1.0.jar spark.sql
```

提交任务到yarn（集群模式），适用于生产

> 传入参数指定需要运行的程序**spark.sql**，对应MySparkStart的配置

```shell
spark-submit \
    --master yarn \
    --name APP_Spark_on_Spring_Cluster \
    --deploy-mode cluster \
    MySparkOnSpring-1.0.jar spark.sql
```
