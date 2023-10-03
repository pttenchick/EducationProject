package ep.tsuho.EducationProject.controller;


import ep.tsuho.EducationProject.model.Message;
import ep.tsuho.EducationProject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/message/")
public class MainController {
    @Autowired
    private MessageService service;

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") int id){
        Message message = service.GetById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Message> Save(@RequestBody Message message){
        this.service.Save(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("save_all")
    public ResponseEntity<Message> SaveAll(){
        service.SaveList();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete\"{id}\"")
    public ResponseEntity<Boolean> Delete(@PathVariable("id") int id){
        return new ResponseEntity<>(service.Delete(id),HttpStatus.OK);
    }

    @GetMapping("get_all")
    public List<Message> GetAll() throws IOException {
        return service.GetAll();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Message> Update(@PathVariable("id") int id, @RequestBody Message message){
        service.Update(message, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("about")
    public ResponseEntity<String> about () {
        String string = "Исполнитель: Суходунова Татьяна Васильевна\n Вариант 35 \nПредметная область: электронные сообщения";
    return new ResponseEntity<String>(string, HttpStatus.OK);
    }

    @GetMapping("accio")
    public ResponseEntity<String> Accio(){
        return new ResponseEntity<String>(service.Accio(), HttpStatus.OK);
    }
}
