package com.gevernova.ecommerceorderprocessing;

// Sends notifications via Email
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("EMAIL: " + message);
    }
}

