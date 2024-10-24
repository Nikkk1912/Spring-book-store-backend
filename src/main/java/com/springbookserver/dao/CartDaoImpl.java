package com.springbookserver.dao;

import com.springbookserver.dao.interfaces.CartDao;
import com.springbookserver.exeption_handling.exceptions.CartNotFoundException;
import com.springbookserver.model.Cart;
import com.springbookserver.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartDaoImpl implements CartDao {

    private CartRepository cartRepository;

    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.delete(getById(id));
    }
}
