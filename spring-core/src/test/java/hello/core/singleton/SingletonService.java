package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해둔다.
    // 자기 자신을 내부에 private static 으로 가지고 있는 것인데, 이렇게 되면 클래스 레벨에서 올라가기 때문에 딱 한개만 존재하게 됨
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    // (instance 의 참조를 통해 SingletonService 객체 조회)
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private 으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 방지한다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
