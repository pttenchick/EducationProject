package ep.tsuho.EducationProject.controller;


import ep.tsuho.EducationProject.model.Message;
import ep.tsuho.EducationProject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/message/")
public class RestController {

    @Autowired
    private MessageService service;

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") int id) throws IOException {
       Message message = service.GetById(id);
       if(message == null) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("save/")
    public ResponseEntity<Message> Save(@RequestParam("id") int id, Message message) throws IOException {
        if(message == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.service.Save(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("save_all")
    public ResponseEntity<Message> SaveAll(List<Message> list) throws IOException {
        if(list == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.SaveList(list);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("delete{id}")
    public ResponseEntity<Message> Delete(int id) throws IOException {
        Message message = service.GetById(id);
        if(message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.Delete(id);
        return new ResponseEntity<>(HttpStatus.GONE); //Может нужно заменить на коды 2xx
    }
    @GetMapping("get_all")
    public ResponseEntity<Message> GetAll() throws IOException {
        List<Message> list = service.GetAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Может другой код нужен?
        }
        return new ResponseEntity<Message>((MultiValueMap<String, String>) list, HttpStatus.OK);// ПЕРЕДЕЛАТЬ!!!!!!!!!
    }


}
