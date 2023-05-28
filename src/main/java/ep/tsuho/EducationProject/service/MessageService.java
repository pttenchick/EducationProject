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

    public Message GetById(int id) throws IOException {
        return repository.getById(id);
    }
    public void Save(Message message) throws IOException {
        repository.Save(message);
    }
    public void SaveList(List<Message> messageList) throws IOException {
        repository.Save(messageList);
    }
    public void Delete(int id) throws IOException {
        repository.Delete(id);
    }
    public List<Message> GetAll() throws IOException {
        return repository.Load();
    }


}
