package com.tastyExpress.dao;

import com.tastyExpress.model.ContactMessage;

public interface ContactDAO {
    void saveMessage(ContactMessage msg);
}
