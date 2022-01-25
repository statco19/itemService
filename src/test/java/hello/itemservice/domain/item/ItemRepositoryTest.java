package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);
        itemRepository.save(item);

        Item savedItem = itemRepository.findById(item.getId());

        assertThat(savedItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        Item item = new Item("itemA", 10000, 10);
        itemRepository.save(item);

        Item savedItem = itemRepository.findById(item.getId());
        Long itemId = savedItem.getId();

        Item updateParam = new Item("itemB", 20000, 30);
        itemRepository.update(itemId, updateParam);

        Item foundItem = itemRepository.findById(itemId);

        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}