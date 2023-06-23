package com.exam.examportal.Controller;

import com.exam.examportal.model.User;
import com.exam.examportal.model.exam.Quiz;
import com.exam.examportal.model.exam.Result;
import com.exam.examportal.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {
    @Autowired
    private ResultService resultservice;

    @PostMapping("/")
    public ResponseEntity<?> addResult(@RequestBody Result result)
    {
        Result result1=this.resultservice.addResult(result);
        return ResponseEntity.ok(result1);
    }

    @GetMapping("/{qid}/{uid}")
    public ResponseEntity<?> getResultByUserAndQuiz(@PathVariable("qid") long qid,@PathVariable("uid") long uid)
    {
        Quiz quiz1=new Quiz();
        quiz1.setQuid(qid);
        User user1=new User();
        user1.setId(uid);
        System.out.println(qid+" "+uid);
        List<Result> lis=(this.resultservice.getResultOfUserAndQuiz(quiz1, user1));
        for(Result r:lis)
        {
            System.out.println(r.getMarksGot());
        }
        return ResponseEntity.ok(lis);

    }

    @GetMapping("/{qid}")
    public ResponseEntity<?> getResultByQuiz(@PathVariable("qid") long qid)
    {
        Quiz quiz1=new Quiz();
        quiz1.setQuid(qid);
        List<Result>lis=this.resultservice.getResultOfQuiz(quiz1);
        return ResponseEntity.ok(lis);

    }
}
