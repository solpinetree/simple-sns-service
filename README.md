<a name="readme-top"></a>


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h3 align="center">대규모 트래픽을 고려한 간단한 SNS 서비스</h3>

  <p align="center">
<!--     <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a> -->
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

- **JWT와 Bearer 헤더를 통한 인증**

   Bearer 헤더를 통해 클라이언트가 JWT를 서버로 전달하고, 서버는 해당 토큰의 유효성을 검사하여 사용자를 인증합니다. 

- **JwtTokenFilter를 통한 토큰 확인**

  JwtTokenFilter는 토큰의 유효성을 검사하고 사용자 정보를 추출하는 역할을 합니다. 이렇게 분리된 필터를 통해 코드를 모듈화하고 중복을 방지할 수 있었습니다. 보안 로직을 한 곳에 모여있기에 관리 및 업데이트가 용이하기도 했습니다.

- **Redis를 활용한 유저 정보 캐싱**

  Redis는 빠른 읽기와 쓰기 성능을 제공하는 인메모리 데이터 스토어로서, 토큰에서 추출한 유저 정보를 캐싱하기에 적합하다고 판단했습니다. 유저 정보를 Redis에 저장함으로써 데이터베이스에 불필요한 조회를 줄이고 응답 속도를 향상시킬 수 있었습니다. 

- **Redis의 만료 정책**

  Redis에 저장된 유저 정보는 설정된 3일의 만료 기간을 가지며, 이를 통해 불필요한 데이터의 축적을 방지하고 메모리를 효율적으로 관리할 수 있었습니다. 만료된 데이터는 자동으로 삭제되어 데이터 일관성과 보안을 유지할 수 있었습니다.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

&nbsp;
&nbsp;
&nbsp;
&nbsp;

## ERD


![image](https://github.com/solpinetree/simple-sns-service/assets/83967710/045f940f-9cd8-4550-aaec-4718702e283f)

### PostgreSQL 선택 이유
프로젝트에서는 회원 정보와 게시물 정보를 저장하기 위한 데이터베이스가 필요합니다. 특히 사용자(User)가 작성한 게시물(Post)과의 관계가 명확하며, 사용자가 작성한 게시물을 검색하는 등의 작업도 필요합니다. 이러한 요구 사항을 충족하기 위해서 관계형 데이터베이스(RDB)를 선택하는 것이 적절하다고 판단했습니다. <br>

프로젝트가 Heroku에서 배포될 예정이었고, 프로젝트 진행 당시에 Heroku는 무료로 PostgreSQL을 제공했으므로 또 다른 대표적인 RDB인 MySQL 말고 PostgreSQL을 선택하게 되었습니다.

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

