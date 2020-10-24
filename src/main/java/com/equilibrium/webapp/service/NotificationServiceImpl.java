package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Notification;
import com.equilibrium.webapp.domain.repository.CommerceRepository;
import com.equilibrium.webapp.domain.repository.NotificationRepository;
import com.equilibrium.webapp.domain.service.NotificationService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CommerceRepository commerceRepository;

    @Override
    public Page<Notification> getAllNotificationsByCommerceId(Long commerceId, Pageable pageable) {
        return notificationRepository.findByCommerceId(commerceId, pageable); }

    @Override
    public Notification getNotificationByIdAndCommerceId(Long commerceId, Long notificationId) {
        return notificationRepository.findByIdAndCommerceId(notificationId, commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notification not found with Id " + notificationId +
                                " and CommerceId " + commerceId));
    }

    @Override
    public Notification createNotification(Long commerceId, Notification notification) {
        return commerceRepository.findById(commerceId).map(commerce -> {
            notification.setCommerce(commerce);
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Commerce", "Id", commerceId));
    }

    @Override
    public Notification updateNotification(Long commerceId, Long notificationId, Notification notificationRequest) {
        if(!commerceRepository.existsById(commerceId))
            throw new ResourceNotFoundException("Commerce", "Id", commerceId);
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setContent(notificationRequest.getContent());
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Notification", "Id", notificationId));
    }

    @Override
    public ResponseEntity<?> deleteNotification(Long commerceId, Long notificationId) {
        return notificationRepository.findByIdAndCommerceId(notificationId, commerceId).map(notification -> {
            notificationRepository.delete(notification);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Notification not found with Id " + notificationId + " and CommerceId " + commerceId));
    }
}
