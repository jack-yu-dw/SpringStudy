package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // 내부에 @Component 가 있기 때문에 컴포넌트 스캔의 대상이 됨.
public class ItemRepository {

    public static final Map<Long, Item> store = new HashMap<>(); // static 사용.
    // 추가로, 멀티스레드 환경에서 돌아가는 실무에서는 HashMap 사용하면 안됨 (동시성 문제).
    // 여러개가 동시에 store 를 접근하게 되면, 이때 store 는 싱글톤으로 생성되기 때문에 문제 발생할 수 있으므로
    // ConcurrentHashMap 사용해야 함.
    public static long sequence = 0L; // static 사용.
    // 마찬가지로 이것 또한 AtomicLong 등으로 사용해야 함
    // https://codechacha.com/ko/java-atomic-long/

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 사실 여기의 updateParam 에서는 id 가 쓰이지 않기 때문에 그대로 Item 객체를 사용하기 보다는
     * name, price, quantity 로 구성된 별도의 객체를 생성하여 사용하는 것이 좋다.
     * 번거롭게 느껴지더라도 설계상 이렇게 명확하게 나누는 것이 낫다.
     *
     * 예시) ItemParamDto
     */
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
