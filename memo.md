```java
    public Task updateTask(Long id, Task source) {
//        Task task = getTask(id); // 상위의 메소드에 의존하지 않고, repository에 의존하도록 바꾼다.
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));;
        task.setTitle(source.getTitle());

        return task;
    }

```


- @Transactional
- @GenerateId

- :8080/h2-console


- @Primary의 용도

- @NonNull
- Not annotated parameter overrides @NonNullApi parameter 
  
- TaskNotFoundException 내부 구조


### Throwable 클래스: 예외처리를 할 수 있는 최상위 클래스 
- Exception 과 Error는  Throwable 의 상속을 받는다.
```java
assertThatThrownBy(() -> toyService.deleteToy(1L))
        .isInstanceOf(TaskNotFoundException.class);
```
assertThatThrownBy() 내부에서 exception raise 가 발생하지 않으면 에러가 난다.
즉, exception 이 발생하는 코드를 assertThatThrownBy() 내부의 인자로 넣어야 한다.


#### 잘못 생각한 점
- 직접 만든 tasks 배열은 JpaRepository 에 저장되지 않는다. tasks 는 그저 test code 에서 비교를 위해 사용된 객체일 뿐이다. 
- 클래스가 다르면 @BeforeEach 어노테이션이 작동하지 않는다 => 잘 동작한다


```java
verify(toyJpaRepository.findById(1L)); // 잘못된 방법
// verify() 의 인자로 mock 객체를 넣어줘야 한다. => verify(toyJpaRepository)
```


```java
    @BeforeEach
    void setUp() {
//        toyService = new ToyService(toyJpaRepository); // 바아로 toyJpaRepository is null 터진다.
        toyService = mock(ToyService.class);
        controller = new ToyController(toyService);
        Toy toy = new Toy();

        toy.setName(TOY_NAME);
        toy.setPrice(TOY_PRICE);
        toy.setImageUrl(TOY_IMAGE_URL);
        toy.setMaker(TOY_MAKER);
        toys.add(toy);
    }
```