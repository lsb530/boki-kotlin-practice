# 1. 루틴과 코루틴

## 코루틴

co-routine, 협력하는 루틴

* runBlocking: 일반루틴 세계와 코루틴 세계를 연결(새로운 코루틴 생성)
* launch: 반환값이 없는 코루틴을 만든다
* suspend fun: 다른 suspend fun을 호출할 수 있다
* yield(): 지금 코루틴을 중단하고 다른 코루틴이 실행되도록 한다(스레드를 양보)

## 코루틴을 출력하기 위한 설정

VM Options: `-Dkotlinx.coroutines.debug`

## 루틴, 코루틴 차이 정리

| 비교 | 루틴                     | 코루틴                       |
|----|------------------------|---------------------------|
| 흐름 | 시작되면 끝날 때까지 멈추지 않는다    | 중단되었다가 재개될 수 있다           |
| 정보 | 한 번 끝나면 루틴 내의 정보가 사라진다 | 중단되더라도 루틴 내의 정보가 사라지지 않는다 |


# 2. 스레드와 코루틴

## 프로세스/스레드/코루틴 차이

* 프로세스: 컴퓨터에서 실행되고 있는 프로그램
* 스레드: 프로세스보다 작은 개념. 프로세스에 소속되어 여러 코드를 동시에 실행할 수 있게 해줌
* 프로세스 > 스레드 > 코루틴
* but, 스레드-코루틴을 프로세스-스레드 개념에 빗대기에는 다른 점이 존재
    * 프로세스가 있어야만 스레드가 존재 가능
    * 스레드가 프로세스를 바꿀 수는 없다
    * 코루틴의 코드가 실행되려면, 스레드가 있어야만 한다
    * 하지만, 중단되었다가 재개될 때 다른 스레드에 배정될 수 있다
* context switching에서도 차이 존재
    * 프로세스 context switching
        * CPU CORE에 프로세스를 번갈아 내리고 올려야하기 때문에(모든 메모리가 교체) 비용이 많이 발생
    * 스레드 context switching
        * Heap 메모리를 공유하고, Stack만 교체되므로 Process보다는 비용이 적다
    * 코루틴 context switching
        * 동일 스레드에서 코루틴이 실행되면, 메모리를 전부 공유하므로 스레드보다 context switching cost가 낮음

## 코루틴 context switching 특징

* 하나의 스레드에서도 `동시성`을 확보할 수 있음
    * 동시성: 한 번에 한 가지 일만 할 수 있지만, 아주 빠르게 작업이 전환되어 동시에 하는 것처럼 보이는 것
    * 병렬성: 실제로 2가지 일을 동시에 하는 것(CPU 멀티코어)
* 코루틴은 스스로 자리를 양보할 수 있다(비선점형) / 스레드는 선점형

## 스레드, 코루틴 차이 정리

| 비교    | 스레드                                | 코루틴                                                |
|-------|------------------------------------|----------------------------------------------------|
| 개념    | 프로세스 > 스레드                         | 스레드 > 코루틴                                          |
| 소속    | 한 스레드는 오직 한 프로세스에만 포함              | 한 코루틴의 코드는 여러 스레드에서 실행 가능                          |
| 문맥교환  | context switching 발생시 stack 영역이 교체 | (한 스레드에서 실행하는 경우) context switching 발생시 메모리 교체가 없음 |
| 중단,재개 | OS가 스레드를 강제로 멈추고 다른 스레드를 실행        | 코루틴 스스로가 다른 코루틴에게 양보                               |


# 3. 코루틴 빌더와 Job
## 코루틴 빌더
### `runBlocking`
새로운 코루틴을 만들고, 루틴 세계와 코루틴 세계를 이어준다

### `launch`
* 반환값이 없는 코드를 실행
* 코루틴을 제어할 수 있는 객체 Job을 반환 받음
* 옵션: LAZY, ...

## Job 객체
* start(): 시작
* cancel(): 취소
* join(): 코루틴이 완료될 때까지 대기

### `async`
* 주어진 함수의 실행 결과를 반환할 수 있다(Deferred)
* Deferred: Job을 상속받고, await()을 갖고 있음
* 활용: 여러 API를 동시에 호출하여 소요시간을 최소화 할 수 있음
* 주의사항: CoroutineStart.LAZY 옵션을 사용하면, await() 함수 호출 시 계산결과를 계속 기다림
  * LAZY + start()를 동시에 해주면 해결 가능

# 4. 코루틴의 취소
## 취소에 협조하는 방법1
delay() / yield() 같은</br>
kotlinx.coroutines 패키지의 suspend 함수 사용

## 취소에 협조하는 방법2
코루틴 스스로 본인의 상태를 확인해 취소 요청을 받았으면, `CancellationException` 던지기
* isActive: 현재 코루틴이 활성화되어 있는지, 취소 신호를 받았는지
* Dispatchers.Default: 코루틴을 다른 스레드에 배정

### 주의
코루틴 내부에서 try-catch-finally를 사용할 때 흐름에 영향을 줄 수 있다</br>
EX)`cancel()` 실행시 CancellationException이 발생하는데 catch후에 다시 throw를 하지 않는 경우

# 5. 코루틴의 예외처리와 Job의 상태변화
## 부모-자식 코루틴이 아닌 Root 코루틴 만드는 방법
```kotlin
CoroutineScope(Dispatchers.Default).launch {
    // ..
}
```

## launch와 async의 예외 발생 차이
* launch: 예외가 발생하면, 예외를 출력하고 코루틴이 종료
* async: 예외가 발생해도, 예외를 출력하지 않음. 예외를 확인하려면, await()이 필요

## 예외 전파
* 기본: RootCoroutineScope가 아닌 부모/자식간의 코루틴 스코프였다면, 자식 코루틴에서 발생한 예외는 부모에게 전파
* 전파를 원치 않을 경우

## 예외 핸들링
1. 직관적인 try-catch-finally
2. CoroutineExceptionHandler(예외 발생 이후 에러 로깅/에러 메시지 전송 등에 활용)

## CoroutineExceptionHandler 주의사항
1. launch에만 적용 가능
2. 부모 코루틴이 있으면 동작 X

## 코루틴 취소 예외 정리
1. 발생한 예외가 CancellationException인 경우 취소로 간주하고 부모 코루틴에게 전파 X
2. 그 외 다른 예외가 발생한 경우 실패로 간주하고 부모 코루틴에게 전파 O
3. 다만, 내부적으로는 취소나 실패 모두 "취소됨" 상태로 관리한다

## Job(코루틴)의 Life Cycle
```text
NEW -> ACTIVE -> COMPLETING -> COMPLETED
     (예외발생)↘️  ↙️
        CANCELLING -> CANCELLED
```

# 6. Structured Concurrency
* 부모 - 자식 관계의 코루틴이 한 몸처럼 움직이는 것
* 수많은 코루틴이 유실되거나 누수되지 않도록 보장
* 코드 내의 에러가 유실되지 않고, 적절히 보고될 수 있도록 보장

## 정리
자식 코루틴에서 예외가 발생할 경우, 
Structured Concurrency에 의해 부모 코루틴이 취소되고,
부모 코루틴의 다른 자식 코루틴들도 취소된다.

자식 코루틴에서 예외가 발생하지 않더라도,
부모 코루틴이 취소되면, 자식 코루틴들이 취소된다.

다만, CancellationException은 정상적인 취소로 간주하기 때문에
부모 코루틴에게 전파되지 않고, 부모 코루틴의 다른 자식 코루틴을 취소시키지도 않는다.

# 7. CoroutineScope & CoroutineContext
## CoroutineScope
* `launch`, `async`는 CoroutineScope의 확장함수
  * `runBlocking`: 코루틴<->루틴 세계 연결하며 CoroutineScope을 제공
* 직접 CoroutineScope을 만든다면?
  * `runBlocking`이 필요하지 X
* 해당 클래스에서 사용하던 코루틴을 한 번에 종료시킬 수 있다

## CoroutineContext
코루틴과 관련된 여러가지 데이터를 갖고 있는 객체
Map + Set을 합쳐놓은 형태

## Dispatcher
코루틴이 어떤 스레드에 배정될지를 관리하는 역할
* 종류
1. Dispatchers.Default
   * 가장 기본적인 디스패처, CPU 자원을 많이 쓸 때 권장. 별다른 설정이 없으면 이 디스패처가 사용
2. Dispatchers.IO
   * I/O 작업에 최적화된 디스패치
3. Dispatchers.Main
   * 보통 UI 컴포넌트를 조작히 위한 디스패처
   * 특정 의존성을 갖고 있어야 정상적으로 활용 가능

## ExecutorService to Dispatcher
asCoroutineDispatcher

# 8. Suspending function
* launch의 signature. block: suspending lambda
* 코루틴이 중지되었다가 재개`될 수 있는` 지점(suspension point)
* 여러 비동기 라이브러리를 사용할 수 있도록 도움

## 추가적인 suspend 함수들
1. coroutineScope
   * 추가적인 코루틴을 만들고, 주어진 함수 블록이 바로 실행된다
   * 만들어진 코루틴이 모두 완료되면 다음 코드로 넘어간다
2. withContext
   * coroutineScope과 기본적으로 유사
   * context에 변화를 주는 기능이 추가적으로 존재
3. withTimeout / withTimeoutOrNull
   * coroutineScope과 기본적으로 유사
   * 주어진 시간 안에 새로 생긴 코루틴이 완료되어야 함