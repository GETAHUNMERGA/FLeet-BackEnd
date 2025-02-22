package com.FmsProject.mappers;

import org.apache.ibatis.annotations.Select;

public interface DatabaseMapper {

    @Select("BACKUP DATABASE FleetManagementSystem TO DISK = #{path}")
    void backup(String path);

    @Select("BACKUP DATABASE FleetManagementSystem TO DISK = #{path} WITH DIFFERENTIAL")
    void backupWithDifferential(String path);

    @Select("RESTORE DATABASE FleetManagementSystem FROM DISK = #{path}")
    void restore(String path);

    @Select("RESTORE DATABASE FleetManagementSystem FROM DISK = #{path} WITH NORECOVERY")
    void restoreWithDifferential(String path);

}
