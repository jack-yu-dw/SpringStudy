package hello.core.order;

public class Order {

    private Long memberId;
    private String itemName;
    private int itemPirce;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPirce, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPirce = itemPirce;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPirce - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPirce() {
        return itemPirce;
    }

    public void setItemPirce(int itemPirce) {
        this.itemPirce = itemPirce;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPirce=" + itemPirce +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
