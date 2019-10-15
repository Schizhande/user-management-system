package com.schizhande.autorepaircentremanagementsystem.user.event;

import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Data
public class OnChangePasswordEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public OnChangePasswordEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
