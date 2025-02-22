package com.FmsProject.controllers;

import com.FmsProject.models.BackupModel;
import com.FmsProject.response.MessageResponse;
import com.FmsProject.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseService dbService;

    @PostMapping("/backup")
    public ResponseEntity<MessageResponse> backup(@RequestBody BackupModel backup) {

        String message;

        try {
            dbService.backup(backup);

            message = "Database Backed Up Successfully.";
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {

            message = "Could not Backup Database: " + ". Error: " + e.getMessage();
            System.out.println(e.getCause());

            String cause = "com.microsoft.sqlserver.jdbc.SQLServerException: Cannot open backup device '"
                    + backup.getFilePath() + "'. Operating system error 3(The system cannot find the path specified.).";
            String cause2 = "com.microsoft.sqlserver.jdbc.SQLServerException: Cannot open backup device '"
                    + backup.getFilePath() + "'. Operating system error 5(Access is denied.).";
            if (Objects.equals(e.getCause().toString(), cause)) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Path not found"));
            } else if (Objects.equals(e.getCause().toString(), cause2)) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Access denied"));
            } else {

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
            }
        }
    }

    @PostMapping("/restore")
    public ResponseEntity<MessageResponse> restore(@RequestBody BackupModel backup) {

        String message;

        try {
            dbService.restore(backup);

            message = "Database Restored Successfully.";
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {

            message = "Could not Restore Database: " + ". Error: " + e.getMessage();
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }
}
