package com.springbookserver.dao.interfaces;

import com.springbookserver.model.User;

public interface UserDao {
    User getById(Long id);
    User save(User user);
    User update(User user);
    void delete(Long id);
}
