package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    Page<Notification> getAllNotificationsByCommerceId(Long commerceId, Pageable pageable);
    Notification getNotificationByIdAndCommerceId(Long commerceId, Long notificationId);
    Notification createNotification(Long commerceId, Notification notification);
    Notification updateNotification(Long commerceId, Long notificationId, Notification notificationRequest);
    ResponseEntity<?> deleteNotification(Long commerceId, Long notificationId);
}
