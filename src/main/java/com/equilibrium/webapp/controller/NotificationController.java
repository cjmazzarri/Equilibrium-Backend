package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Notification;
import com.equilibrium.webapp.domain.service.NotificationService;
import com.equilibrium.webapp.resource.NotificationResource;
import com.equilibrium.webapp.resource.SaveNotificationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Notifications", description = "Notifications API")
@RestController
@RequestMapping("/api/commerces/{commerceId}")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/notifications")
    public Page<NotificationResource> getAllNotificationsByCommerceId(
            @PathVariable(name = "commerceId") Long commerceId, Pageable pageable){
        Page<Notification> notificationPage = notificationService.getAllNotificationsByCommerceId(commerceId, pageable);
        List<NotificationResource> resources = notificationPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/notifications/{notificationId}")
    public NotificationResource getNotificationByIdAndCommerceId(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "notificationId") Long notificationId){
        return convertToResource(notificationService.getNotificationByIdAndCommerceId(commerceId, notificationId));
    }

    @PostMapping("/notifications")
    public NotificationResource createNotification(
            @PathVariable(name = "commerceId") Long commerceId,
            @Valid @RequestBody SaveNotificationResource resource){
        return convertToResource(notificationService.createNotification(commerceId,
                convertToEntity(resource)));
    }

    @PutMapping("/notifications/{notificationId}")
    public NotificationResource updateNotification(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "notificationId") Long notificationId,
            @Valid @RequestBody SaveNotificationResource resource){
        return convertToResource(notificationService.updateNotification(commerceId, notificationId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<?> deleteNotification(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "notificationId") Long notificationId){
        return notificationService.deleteNotification(commerceId, notificationId);
    }

    private Notification convertToEntity(SaveNotificationResource resource) {
        return mapper.map(resource, Notification.class);
    }

    private NotificationResource convertToResource(Notification entity) {
        return mapper.map(entity, NotificationResource.class);
    }
}
