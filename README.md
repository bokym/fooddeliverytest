
![image](https://user-images.githubusercontent.com/487999/79708354-29074a80-82fa-11ea-80df-0db3962fb453.png)

# LV3 통합실습 및 결과

주제(배달의 민족 마이크로서비스 분석/설계, 구현, 배포/운영)를 구현하여 결과를 정리하였습니다.



# Table of contents

- [LV3 통합실습 및 결과](#lv3-통합실습-및-결과)
  - [서비스 시나리오](#서비스-시나리오)
  - [체크포인트](#체크포인트)
  - [분석/설계](#분석설계)
  - [Microservice Implementation](#microservice-implementation)
    - [Saga (Pub/Sub)](#saga-pubsub)
    - [CQRS](#cqrs)
    - [Compensation/Correlation](#compensationcorrelation)
  - [Microservice Orchestration](#microservice-orchestration)
    - [Deploy to EKS Cluster](#deploy-to-eks-cluster)
    - [Gateway & Service Router 설치](#gateway--service-router-설치)
    - [Autoscale (HPA)](#autoscale-hpa)

# 서비스 시나리오

기능적 요구사항
1. 고객이 메뉴를 선택하여 주문한다.
2. 고객이 선택한 메뉴에 대해 결제한다.
3. 주문이 되면 주문 내역이 입점상점주인에게 주문정보가 전달된다.
4. 상점주는 주문을 수락하거나 거절할 수 있다.
5. 상점주는 요리시작때와 완료 시점에 시스템에 상태를 입력한다.
6. 고객은 아직 요리가 시작되지 않은 주문은 취소할 수 있다.
7. 요리가 완료되면 고객의 지역 인근의 라이더들에 의해 배송건 조회가 가능하다.
8. 라이더가 해당 요리를 Pick한 후, 앱을 통해 통보한다.
9. 고객이 주문상태를 중간중간 조회한다.
10. 라이더의 배달이 끝나면 배송확인 버튼으로 모든 거래가 완료된다.


비기능적 요구사항
1. 장애격리
    1. 상점관리 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다  Async (event-driven), Eventual Consistency
    2. 결제시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다  Circuit breaker, fallback
2. 성능
    1. 고객이 자주 상점관리에서 확인할 수 있는 배달상태를 주문시스템(프론트엔드)에서 확인할 수 있어야 한다  CQRS
    2. 배달상태가 바뀔때마다 카톡 등으로 알림을 줄 수 있어야 한다  Event driven



# 체크포인트
- 대상 마이크로서비스 : 고객, 상점주, 라이더
- Microservice Implementation
    1. Saga (Pub / Sub)
    2. CQRS
    3. Compensation / Correlation
- Microservice Orchestration
    1. Deploy to EKS Cluster
    2. Gateway & Service Router 설치
    3. Autoscale (HPA)



# 분석/설계

## Event Storming 결과
* MSAEz 로 모델링한 이벤트스토밍 결과:  [https://labs.msaez.io/#/storming/fba58f7af9f99d240b78291bdd174345](https://labs.msaez.io/#/storming/0c19758834ef40045add8492612c5c00))
* 대상 마이크로 서비스 customer, store, rider 구현

### 기능적 요구사항에 대한 검증

![Screenshot_20230216_012645_Samsung Notes](https://user-images.githubusercontent.com/125227422/219297514-ae45723a-7afc-46d7-b3f1-d3d562b422a8.jpg)


### 비기능적 요구사항에 대한 검증

![Screenshot_20230216_014100_Samsung Notes](https://user-images.githubusercontent.com/125227422/219297631-e184c6b6-2b3e-49e2-a13c-279461de8234.jpg)



# Microservice Implementation

## Saga (Pub/Sub)
![1](https://user-images.githubusercontent.com/125227422/219299686-9099ada1-8420-4ea2-a6c8-c00ae2dbbef2.png)
Saga패턴으로 ~/orders 뒤에 나열된 정보들이 store에서 확인 가능하다


## CQRS
![2](https://user-images.githubusercontent.com/125227422/219300387-e9e16f43-9080-4604-9e49-083dd059d6b6.png)
주문생성시 레파지토리에 정보들이 저장되어 쉽게 읽어올 수 있다.

## Compensation/Correlation
![3](https://user-images.githubusercontent.com/125227422/219302530-4b434ac1-f62e-4f37-a18f-ae91f88db59f.png)
주문 생성과 취소가 가능하다.


# Microservice Orchestration

## Deploy to EKS Cluster
![4](https://user-images.githubusercontent.com/125227422/219309360-48e6154c-20e2-4954-b76e-c368b9c11e51.png)

## Gateway & Service Router 설치
![5](https://user-images.githubusercontent.com/125227422/219309450-2083dd28-0da1-4111-9bb7-39c3cd867dde.png)

## Autoscale (HPA)
### 부하 전
![6](https://user-images.githubusercontent.com/125227422/219312423-6ca4b9fa-6496-4300-bdf4-e029f3b02aed.png)

### 부하 후
![7](https://user-images.githubusercontent.com/125227422/219315427-c03f057c-001e-44a6-997c-ed45bcc52629.png)

### pod 
![8](https://user-images.githubusercontent.com/125227422/219315610-c290c386-b368-44e5-8232-a8d44da2f05e.png)
