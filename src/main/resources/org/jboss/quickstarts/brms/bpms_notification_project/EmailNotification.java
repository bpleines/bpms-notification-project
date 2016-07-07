package org.jboss.quickstarts.brms.bpms_notification_project;

import java.io.Serializable;

public class EmailNotification implements Serializable{

       private static final long serialVersionUID = 1L;
       private String from;
       private String to;
       private String subject;
       private String body;
       private EmailNotificationType type;

       public String getFrom() {
              return from;
       }
       public void setFrom(String from) {
              this.from = from;
       }
       public String getTo() {
              return to;
       }
       public void setTo(String to) {
              this.to = to;
       }
       public String getSubject() {
              return subject;
       }
       public void setSubject(String subject) {
              this.subject = subject;
       }
       public String getBody() {
              return body;
       }
       public void setBody(String body) {
              this.body = body;
       }
       public EmailNotificationType getType() {
              return type;
       }
       public void setType(EmailNotificationType type) {
              this.type = type;
       }     

       @Override
       public String toString() {
              return "EmailNotification [from=" + from + ", to=" + to + ", "
                           + "subject=" + subject + ", body=" + body + ", type=" + type + "]";
       }
}
