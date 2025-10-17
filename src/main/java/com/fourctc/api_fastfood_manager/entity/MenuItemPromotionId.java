import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MenuItemPromotionId implements Serializable {

    private Integer menuitemid;
    private Integer promotionid;

    // Constructors, getters, setters, equals, hashCode
    public MenuItemPromotionId() {}

    public MenuItemPromotionId(Integer menuitemid, Integer promotionid) {
        this.menuitemid = menuitemid;
        this.promotionid = promotionid;
    }

    public Integer getMenuitemid() {
        return menuitemid;
    }

    public void setMenuitemid(Integer menuitemid) {
        this.menuitemid = menuitemid;
    }

    public Integer getPromotionid() {
        return promotionid;
    }

    public void setPromotionid(Integer promotionid) {
        this.promotionid = promotionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItemPromotionId that = (MenuItemPromotionId) o;

        if (!menuitemid.equals(that.menuitemid)) return false;
        return promotionid.equals(that.promotionid);
    }

    @Override
    public int hashCode() {
        int result = menuitemid.hashCode();
        result = 31 * result + promotionid.hashCode();
        return result;
    }
}
