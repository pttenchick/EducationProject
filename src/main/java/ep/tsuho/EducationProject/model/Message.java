package ep.tsuho.EducationProject.model;


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
    static int counter = 1; //Как сделатьтак, чтобы его значение не сбрасывалось после окончания? Можно записывать значение счетчика в отдельный файл
    private String title;
    private String text;
    private String sender;
    private String serverSender;
    private String recepient;
    private String serverRecipient;
    private String status = "Черновик";
    private String dateMail = null;

    public Message() throws IOException {

        // counter = (int) fileReader.read();

        this.messageID = counter++;
        this.title = "empty";
        this.text = "empty";
        this.sender = "empty@empty";
        this.serverSender = "empty.empty";
        this.recepient = "empty@empty";
        this.serverRecipient = "empty.empty";
        this.status = "Черновик";
        this.dateMail = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        //fileWriter.write(counter);
    }

    public Message(int messageID, String title, String text, String sender, String serverSender, String recepient, String serverRecipient, String status) {
        this.messageID = messageID;
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
