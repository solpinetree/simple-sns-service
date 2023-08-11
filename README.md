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
     <li>
      <a href="#api-문서">API 문서</a>
       <ul>
        <li><a href="#swagger">Swagger</a></li>
      </ul>
    </li>
  </ol>

&nbsp;
&nbsp;
&nbsp;
&nbsp;


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

Java11, Spring boot 2.6.7 버전을 사용했습니다.

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
&nbsp;
&nbsp;

## Architecture
<img width="1329" alt="image" src="https://github.com/solpinetree/simple-sns-service/assets/83967710/2b14c817-2703-4830-96ac-b38b389d637f">


<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;
&nbsp;
&nbsp;

## BackEnd Layers
![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/c158e364-8a0b-4bda-b79c-218f4378224e)
이 프로젝트는 레이어드 아키텍처 패턴을 따릅니다. <br>
레이어드 아키텍처의 Presentation layer의 역할은 controller 가 <br>
Business layer의 역할은 service, Persistence layer의 역할은 repository가 수행합니다. <br>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;
&nbsp;
&nbsp;

## Security Diagram
![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/63dba5cc-bcf1-496e-89e7-db94f3025b3b)
Bearer 헤더로 jwt 를 받아서 인증을 합니다. SecurityFilter 에 따로 정의한 JwtTokenFilter를 추가해서 JwtTokenFilter로 토큰을 확인합니다. token에는 유저의 username이 저장되어있습니다. token에서 추출한 username으로 redis나 db(redis에 없다면)에서 userDto를 가져옵니다. 가져온 userDto를 `UsernamePasswordAuthenticationToken`의 principal로 저장해준 뒤 securityContext의 authentication을 방금 정의한 `UsernamePasswordAuthenticationToken`으로 설정해서 사용하도록 했습니다.

Redis에는 (key: username, value: userDto)가 저장되어 있습니다. 만료기간은 3일로 설정했습니다.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;
&nbsp;
&nbsp;

## ERD


![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/045f940f-9cd8-4550-aaec-4718702e283f)


<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;
&nbsp;
&nbsp;

## Class Diagram
![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/7c8aaf16-3544-4b4c-b800-e97732ce0c93)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## API 문서
### Swagger

<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;
&nbsp;
&nbsp;

