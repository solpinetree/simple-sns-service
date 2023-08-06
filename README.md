<a name="readme-top"></a>


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h3 align="center">대규모 트래픽을 고려한 간단한 SNS 서비스</h3>

  <p align="center">
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
  </p>
</div>

<br/>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#architecture">Architecture</a>
    </li>
    <li>
      <a href="#backend-layers">BackEnd Layers</a>
    </li>
    <li>
      <a href="#erd">ERD</a>
    </li>
    <li>
      <a href="#class-diagram">Class Diagram</a>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

댓글, 좋아요, 알림기능을 포함하는 간단한 text-based sns 어플리케이션 입니다. Caching, server side event, 비동기를 이용해서 데이터를 처리하는 등 대규모 트래픽을 고려했습니다.  

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With
* [![Java][Java]][Java-url]
* [![Junit5][Junit5]][Junit5-url]
* [![SpringBoot][SpringBoot]][SpringBoot-url]
* [![Postgres][Postgres]][Postgres-url]
* [![Git][Git]][Git-url]
* [![Github][Github]][Github-url]
* [![Heroku][Heroku]][Heroku-url]
* [![Redis][Redis]][Redis-url]
* [![Apache Kafka][Kafka]][Kafka-url]

Junit5을 이용한 TDD 방법론으로 서비스를 개발했습니다. `Spring jdbc` 를 이용해서 Postgres DB와 소통했습니다. 유저 정보 캐시를 위해 Redis를 사용했고 서버 안정화와 비동기 처리를 위해 Kafka를 사용했습니다. Spring boot 버전은 2.6.7 버전을 사용했습니다. 

<p align="right">(<a href="#readme-top">back to top</a>)</p>

[Kafka]: https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka
[Kafka-url]: https://kafka.apache.org/
[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://devdocs.io/openjdk~11/
[SpringBoot]: https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[SpringBoot-url]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#legal
[Postgres]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[Postgres-url]: https://www.postgresql.org/
[Heroku]: https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white
[Heroku-url]: https://devcenter.heroku.com/
[Junit5]: https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white
[Junit5-url]: https://junit.org/junit5/
[Redis]: https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white
[Redis-url]: https://redis.com/
[Git]: https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white
[Git-url]: https://git-scm.com/
[Github]: https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white
[Github-url]: https://docs.github.com/en

&nbsp;
&nbsp;

## Architecture
<img width="70%" alt="image" src="https://github.com/solpinetree/simple-sns-service/assets/83967710/61a0c70f-f87f-48fa-9738-9ee7249669b4">

<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;

## BackEnd Layers
![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/c158e364-8a0b-4bda-b79c-218f4378224e)
이 프로젝트는 레이어드 아키텍처 패턴을 따릅니다.
레이어드 아키텍처의 Presentation layer의 역할은 controller 가,
Business layer의 역할은 service, Persistence layer의 역할은 repository가 수행하도록 구성했습니다.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;

## Security Diagram
![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/63dba5cc-bcf1-496e-89e7-db94f3025b3b)


<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;

## ERD

![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/9c3538af-f3c5-4678-aadc-7d6a8ca6b4ad)


<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;

## Class Diagram
![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/7c8aaf16-3544-4b4c-b800-e97732ce0c93)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

