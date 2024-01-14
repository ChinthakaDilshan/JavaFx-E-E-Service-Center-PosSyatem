package dao.custom;

import dao.CrudDao;
import dto.ItemDto;
import entity.Item;

public interface ItemDao extends CrudDao<Item> {
    ItemDto searchItem(String itemCode);
}
