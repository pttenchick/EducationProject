
package ep.tsuho.EducationProject.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ep.tsuho.EducationProject.model.Message;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Repository
public class MessageRepository {
    private static final String fileName = "src/main/resources/static/list.json";

    private static final File file = new File(fileName);

    public void Save(List<Message> list) throws IOException {
        try {
            Writer writer = new FileWriter(fileName, false);
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter prettyWriter = mapper.writer(new DefaultPrettyPrinter());
            prettyWriter.writeValue(writer, list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Save(Message message) throws IOException {
        try {
            Writer writer = new FileWriter(fileName, true);
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter prettyWriter = mapper.writer(new DefaultPrettyPrinter());
            prettyWriter.writeValue(writer, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Message> Load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        List <Message> messageList = mapper.readValue(file, new TypeReference<List<Message>>() {});
        messageList.forEach(message -> System.out.println(message));
        return messageList;
    }
    public Message getById(int id) throws IOException {

        var list = Load();
        for(Message message : list) {
            if(message.getMessageID() == id) {
                return message;
            }
        }
        return null;
    }

    public boolean Delete(Message inputMessage) throws IOException {
        List<Message> list = Load();
        for (Message message :list)
        {
            if (message.equals(inputMessage)){
                list.remove(message);
                Save(list);
                return true;
            }
        }
        return false;
    }
}
