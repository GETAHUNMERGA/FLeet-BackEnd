package com.FmsProject.services;

import com.FmsProject.mappers.DatabaseMapper;
import com.FmsProject.models.BackupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DatabaseService {

    @Autowired
    private DatabaseMapper mapper;

    public void backup(BackupModel backup) {
        if (Objects.equals(backup.getType(), "differential")){
            System.out.println("Differential Backup");
            mapper.backupWithDifferential(backup.getFilePath());
        }
        else{
            System.out.println("Full Backup");
            mapper.backup(backup.getFilePath());
        }
    }

    public void restore(BackupModel backup) {
        if (Objects.equals(backup.getType(), "differential")){
            System.out.println("Differential Restore");
            mapper.restoreWithDifferential(backup.getFilePath());
        }
        else{
            System.out.println("Full Restore");
            mapper.restore(backup.getFilePath());
        }
    }
}
