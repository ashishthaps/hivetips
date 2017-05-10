{
    "name": "HDIStorageLinkedService",
    "properties": {
        "description": "",
        "hubName": "adf-hive-data-etl_hub",
        "type": "AzureStorage",
        "typeProperties": {
            "connectionString": "DefaultEndpointsProtocol=https;AccountName=micpocsource;AccountKey=**********;EndpointSuffix=core.windows.net"
        }
    }
}
{
    "name": "micHDInsightLinkedService",
    "properties": {
        "hubName": "adf-hive-data-etl_hub",
        "type": "HDInsight",
        "typeProperties": {
            "clusterUri": "https://pocpipeline.azurehdinsight.net",
            "userName": "admin",
            "password": "**********",
            "linkedServiceName": "HDIStorageLinkedService"
        }
    }
}
{
    "name": "hiveinput",
    "properties": {
        "published": false,
        "type": "AzureBlob",
        "linkedServiceName": "HDIStorageLinkedService",
        "typeProperties": {
            "folderPath": "/test/"
        },
        "availability": {
            "frequency": "Month",
            "interval": 1
        },
        "external": true,
        "policy": {}
    }
}

{
    "name": "hiveoutput",
    "properties": {
        "published": false,
        "type": "AzureBlob",
        "linkedServiceName": "HDIStorageLinkedService",
        "typeProperties": {
            "folderPath": "/test/output/"
        },
        "availability": {
            "frequency": "Hour",
            "interval": 1
        },
        "external": false,
        "policy": {}
    }
}

{
    "name": "MicHiveActivityPipeline",
    "properties": {
        "activities": [
            {
                "type": "HDInsightHive",
                "typeProperties": {
                    "script": "select * from hivesampletable"
                },
                "inputs": [
                    {
                        "name": "hiveinput"
                    }
                ],
                "outputs": [
                    {
                        "name": "hiveoutput"
                    }
                ],
                "scheduler": {
                    "frequency": "Hour",
                    "interval": 1
                },
                "name": "MicHiveActivity",
                "linkedServiceName": "micHDInsightLinkedService"
            }
        ],
        "start": "2017-05-10T14:00:00Z",
        "end": "2017-05-10T16:00:00Z",
        "isPaused": false,
        "hubName": "adf-hive-data-etl_hub",
        "pipelineMode": "Scheduled"
    }
}
