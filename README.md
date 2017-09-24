# demo-spark-eb-ci

This is a simple demo Java Spark application, with continuous delivery integration, using GitFlow, CircleCI, EB cli and AWS ElasticBeanstalk.

[![CircleCI](https://circleci.com/gh/romajs/demo-spark-eb-ci.svg?style=svg)](https://circleci.com/gh/romajs/demo-spark-eb-ci)

## Featuring

* Spark
* Gradle
* Jar with dependencies (shadowJar)
* AWS ElasticBeanstalk deployment with EB cli
* CircleCI integration with EB cli
* EB cli and GitFlow branch integration

## To do

- [ ] Swagger documentation
- [ ] SwaggerUI
- [ ] Spark Auto-reload
- [ ] eb deploy -l $(gradle properties | grep version | cut -c 10-)

## Deployment

To start deployment this application to your AWS ElasticBeanstalk environments, you will need:

- [Create an AWS account](https://aws.amazon.com/free)
- [Gerenate an AWS security credentials](https://aws.amazon.com/pt/blogs/security/wheres-my-secret-access-key/)
- [Configure this credential into your CircleCI project](https://circleci.com/docs/1.0/environment-variables/)
- [Create your application in AWS ElasticBeanstalk](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/applications.html)

Then by simply making changes and pushing to theese branches (`<master>`, `<release>` and `<develop>`), CircleCI will update your enviroments automatically.

### Continuous integration

Git branch | ElasticBeanstalk enviroment
-----------|----------------------------
master     | demo-spark-eb-ci
release    | demo-spark-eb-ci-hml
develop    | demo-spark-eb-ci-dev

## Development

### Requirements

- Java JDK 8
- Gradle
- EB cli

### Deployment

To deploy to your enviroments manually you will need:

- [Install AWS EB cli into your machine](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install.html)
- [Configure a profile for EB cli into your machine](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-configuration.html )

Then run the eb cli deploy command:

`eb deploy <your-eb-enviroment> --profile <your-eb-cli-profile>`

## Useful commands

Description                        | Command
-----------------------------------|------------------------------------------------------------------------
Build                              | `./gradlew build`  
Build w/ dependencies (shadow/jar) | `./gradlew shadowJar`  
Test                               | `./gradlew test`  
Run                                | `./gradlew run` or `java -jar ./build/libs/demo-spark-eb-ci-shadow.jar`  
ElasticBeanstalk initialization    | `eb init`
Create EB application              | `eb create <your-eb-enviroment>`

## Further configuration

After your environment has been create/deployed, you need to edit your security groups rules

### Single instance

#### `<your-sg-ec2>`

##### Inbound 

Type            | Protocol | Port range | Source          | Description
----------------|----------|------------|-----------------|-------------
Custom TCP Rule | TCP      | 4567       | 0.0.0.0/0       |

#### `<your-sg-elb>`

### Auto-scaling

(with load balancer)

#### `<your-sg-ec2>`

##### Inbound 

Type            | Protocol | Port range | Source          | Description
----------------|----------|------------|-----------------|-------------
Custom TCP Rule | TCP      | 4567       | `<your-sg-elb>` |

#### `<your-sg-elb>`

##### Outbound

Type            | Protocol | Port range | Source          | Description
----------------|----------|------------|-----------------|-------------
Custom TCP Rule | TCP      | 4567       | `<your-sg-ec2>` |

#### `<your-elb>`

##### Listeners

Load Balancer Protocol | Load Balancer Port | Instance Protocol | Instance Port | Cipher | SSL Certificate
-----------------------|--------------------|-------------------|---------------|--------|---------------
HTTP                   | 80                 | HTTP              | 4567          |        |
