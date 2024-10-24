package com.springbookserver.dao.interfaces;

import com.springbookserver.model.Cart;

public interface CartDao {
    Cart getById(Long id);
    Cart save(Cart cart);
    Cart update(Cart cart);
    void delete(Long id);
}
