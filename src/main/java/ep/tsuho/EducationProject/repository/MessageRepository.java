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
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Repository
public class MessageRepository {
    private String fileName = "src/main/resources/static/list.json";

    private File file = new File(fileName);

    private List<Message> list = new ArrayList<>();

    private void Recording(){
        try {
            Writer writer = new FileWriter(fileName, false);
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter prettyWriter = mapper.writer(new DefaultPrettyPrinter());
            prettyWriter.writeValue(writer, list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Save(){
        Recording();
    }

    public void Save(Message message) {
            list.add(message);
            Recording();
    }

    public List<Message> Load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        list = mapper.readValue(file, new TypeReference<List<Message>>() {});
        return list;
    }

    public Message getById(int id){

        for(Message message : list) {
            if(message.getMessageID() == id) {
                return message;
            }
        }
        return null;
    }

    public boolean Delete(int id){
        for (Message message : list) {
            if (message.getMessageID() == id){
                list.remove(message);
                Save();
                return true;
            }
        }
        return false;
    }

   public void Update(Message message, int id){

       for(int i = 0; i < list.size(); i++){
           if(list.get(i).getMessageID() == id){
               list.set(i, message);
               Save();
               break;
           }
       }
    }
   public String Accio() {

        int counter = 0;
        double sum = 0;
        double dispers = 0;
        double accio = 0;
        double[] numbers = new double[list.size()];

        for (Message message : list) {
            if (message.getSize() != 0) {
                sum += message.getSize();
                numbers[counter] = message.getSize();
                counter++;
            }
        }
        double medium = sum / counter;
        sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            double m =(numbers[i] - medium);
            m = (m*m*m*m);
            sum += m;
        }
        sum = sum/numbers.length;

       double sum2 =  0;
       for (int i = 0; i < numbers.length; i++) {
           dispers = (numbers[i] - medium)*(numbers[i] - medium);
           sum2 = sum2 + dispers;
       }
       dispers = sum2/numbers.length;
       double stigma = sqrt(dispers);

       stigma = stigma*stigma*stigma*stigma;

       accio = sum/stigma - 3;

       String str = "Медиана: " + medium + "\nСумма: " + sum + "\nDisp: " + dispers + "\nStigma: " + stigma + "\nAccio: " + accio;

       return str;
    }
}
