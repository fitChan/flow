hello


개발자 채용 과제 [성찬우]
===================

## 환경

##### 서버

`spring-boot` `2.7.4`

`JPA` `2.7.3`

`gradle` `6.8`

`MYSQL` `8.0.16`

<br>
<br>

##### 클라이언트

`ajax/jQuery` `3.4.1`

`thymeleaf` `2.7.3`

## ERD

![flowErid](https://user-images.githubusercontent.com/84306157/195924429-e097c57e-a893-4b07-a166-29ab7b09dd4c.png)

<strong><u> fixed_extension </u></strong>

컬럼 | 의미 | 타입
|---|:---:|---:|
| `id` | PK값 | `Integer` |
| `create_at` | 생성 시간 | `DATETIME` |
| `modified_at` | 수정 시간  | `DATETIME` |
| `flag` | 해당 컬럼 사용 유무 | `BOOLEAN` |
| `names` | 고정 확장자 명 | `STRING` |

<strong><u> custom_extension </u></strong>

컬럼 | 의미 | 타입
|---|:---:|---:|
| `id` | PK값 | `Integer` |
| `create_at` | 생성 시간 | `DATETIME` |
| `modified_at` | 수정 시간 | `DATETIME` |
| `flag` | 해당 컬럼 사용 유무 | `BOOLEAN` |
| `names` | custom 확장자 명 | `STRING` |

## Architecture

![flowarchi](https://user-images.githubusercontent.com/84306157/195933803-5765d627-a203-474b-9104-6d8bf40adf10.png)

## API

<strong><u> fixed_extension </u></strong>

URL | Method | Req |Res |비고
|:---:|:---:|:---|:---|:---:|
| `/fixes/find-true` | `GET` | = | [<br>&nbsp;&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"names:&nbsp;&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"value": "bat"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;&nbsp;},{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"names:&nbsp;&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"value": "com"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;&nbsp;}<br>] |  |  
| `/fixes/update` | `PUT` | {"names": "cpl"} | 200OK |flag = false-> true , true -> false | 

<strong><u> custom_extension </u></strong>

URL | Method |Req |Res |비고
|:---:|:---:|:---|:---|:---:|
| `/customs/find-true` | `GET` | - | [<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "example"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "example"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},{<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "example"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>] | flag=true 이고 list의 limit length = 200 |  
| `/customs/save    ` | `POST` | { "name": "example" } | 1 or 0 (int)  | res[1]: db에 존재하지않음 또는 존재하지만 flag가false res[2]: db에 존재 하지만 flag가 true| 
| `/customs/update` | `PUT` | { "name": "example" } | - |"name": "example" "flag : false " 로 수정| 

## 시퀀스 다이어그램

#### 고정 확장자 출력

![fixidGET](https://user-images.githubusercontent.com/84306157/195940801-2267abbd-1cf0-489a-914c-9bd011131885.png)

#### 고정 확장자 저장 또는 수정
![fixedPut](https://user-images.githubusercontent.com/84306157/195940826-45d05ea2-d3f1-4040-9b72-3e2c775278dc.png)

#### 커스텀 확장자 출력

![CustomGet](https://user-images.githubusercontent.com/84306157/195940843-2104dc57-de0e-4d14-8020-086345c28f99.png)

#### 커스텀 확장자 저장

![CustomSave](https://user-images.githubusercontent.com/84306157/195940865-403ade4b-6ff2-4c39-87e1-90c456c4d57d.png)

#### 커스텀 확장자 수정

![customupdate](https://user-images.githubusercontent.com/84306157/195940879-b6bf38e3-2bc1-419a-9d81-f87e9201552f.png)








