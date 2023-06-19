package ep.tsuho.EducationProject.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Getter
@Setter
public class Message {
    private int messageID;
    private String title;
    private String text;
    private String sender;
    private String serverSender;
    private String recepient;
    private String serverRecipient;
    private String status = "Черновик";
    private String dateMail;
    private double size;

    public Message() throws IOException {

        this.messageID = 0;
        this.size = 0;
        this.title = "empty";
        this.text = "empty";
        this.sender = "empty@empty";
        this.serverSender = "empty.empty";
        this.recepient = "empty@empty";
        this.serverRecipient = "empty.empty";
        this.status = "Черновик";
        this.dateMail = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    }

    public Message(int messageID, double size, String title, String text, String sender, String serverSender, String recepient, String serverRecipient, String status) {

        this.messageID = messageID;
        this.size  = size;
        this.title = title;
        this.text = text;
        this.sender = sender;
        this.serverSender = serverSender;
        this.recepient = recepient;
        this.serverRecipient = serverRecipient;
        this.status = status;
        this.dateMail = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
