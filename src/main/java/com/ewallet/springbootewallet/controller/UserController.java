package com.ewallet.springbootewallet.controller;


import com.ewallet.springbootewallet.domain.User;
import com.ewallet.springbootewallet.service.TransactionService;
import com.ewallet.springbootewallet.service.UserService;
import com.ewallet.springbootewallet.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TransactionService transactionService;

    @PostMapping("/login")
    public Result<User> loginController(@RequestParam String uname, @RequestParam String password) {
        User user = userService.loginService(uname, password);
        if (user != null) {
            return Result.success(user, "login success");
        } else {
            return Result.error("1", "incorrect username or password");
        }
    }

    @PostMapping("/register")
    public Result<User> registerController(@RequestBody User newUser) {
        User user = userService.registerService(newUser);
        if (user != null) {
            return Result.success(user, "register success");
        } else {
            return Result.error("2", "User already exist");
        }
    }

//    @PostMapping("/transfer")
//    public Result<Integer> transferOutController(@RequestParam String uname, @RequestParam String receiverUname, @RequestParam Double amount) {
//        Integer status1 = userService.transferOutService(uname, amount);
//        Integer status2 = userService.receiveService(receiverUname, amount);
//        return Result.success(status1, "money tranfer out success");
////        if (user1 != null && user2 != null) {
////            return Result.success(user1, "money transfer out success");
////        } else {
////            return Result.error("3", "Money transfer fail");
////        }
//    }

//    @PostMapping("/topup")
//    public Result<Transaction> topUpController(@RequestParam String uname, @RequestParam Double amount) {
//        Transaction transactionRecord = userService.transferToOneService(uname, uname, amount);
//        return Result.success(transactionRecord, "money top-up success");
////        if (user != null) {
////            return Result.success(user, "top up success");
////        } else {
////            return Result.error("4", "top up fail");
////        }
//    }
//
//    @PostMapping("/transactionToOne")
//    public Result<Transaction> transactionToOneController(@RequestParam String uname, @RequestParam String receiverUname, @RequestParam Double amount) {
//        Transaction transactionRecord = userService.transferToOneService(uname, receiverUname, amount);
//        Transaction addedTransaction = transactionService.addOneTransactionService((transactionRecord));
//        return Result.success(addedTransaction, "transaction success");
//    }


}
