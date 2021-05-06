import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    private ItemService itemService;
    @Test
    public void test01() {
        System.out.println("开始");
        TbItem itemById = itemService.getItemById(830972L);
        System.out.println(itemById);
    }
}
