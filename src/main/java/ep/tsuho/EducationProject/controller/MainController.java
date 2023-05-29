package ep.tsuho.EducationProject.controller;


import ep.tsuho.EducationProject.model.Message;
import ep.tsuho.EducationProject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/message/")
public class MainController {
    @Autowired
    private MessageService service;

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") int id) throws IOException {
        Message message = service.GetById(id);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Message> Save(@RequestBody Message message) throws IOException {
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.service.Save(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("save_all")
    public ResponseEntity<Message> SaveAll(@RequestBody List<Message> list) throws IOException {
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.SaveList(list);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Boolean> Delete(@RequestBody Message message) throws IOException {
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(service.Delete(message),HttpStatus.OK); //Может нужно заменить на коды 2xx
    }

    @GetMapping("get_all")
    public List<Message> GetAll() throws IOException {
        return service.GetAll();
    }

    @PutMapping("update")
    public  Message Update(@RequestBody Message message) throws  IOException{
        service.Update(message);
        return null;
    }
}
