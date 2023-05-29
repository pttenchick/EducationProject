package ep.tsuho.EducationProject.service;

import ep.tsuho.EducationProject.model.Message;
import ep.tsuho.EducationProject.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public Message GetById(int id){
        return repository.getById(id);
    }
    public void Save(Message message) {
        repository.Save(message);
    }
    public void SaveList(){
        repository.Save();
    }
    public boolean Delete(Message message){
        return repository.Delete(message);
    }
    public List<Message> GetAll() throws IOException {
        return repository.Load();
    }
    public void Update(Message message, int id){
        repository.Update(message,  id);
    }


}
