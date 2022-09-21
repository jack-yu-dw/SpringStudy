package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data   // 이렇게 @Data 를 써서 한번에 처리하려는 것은 상당히 위험할 수 있다.
@Getter @Setter // 이렇게 필요한 부분을 꺼내서 쓰도록 하자.
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
