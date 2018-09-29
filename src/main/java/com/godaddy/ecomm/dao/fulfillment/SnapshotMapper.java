package com.godaddy.ecomm.dao.fulfillment;

import com.godaddy.ecomm.base.fulfillment.Snapshot;
import com.godaddy.ecomm.base.fulfillment.SnapshotExample;
import com.godaddy.ecomm.dao.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SnapshotMapper extends Mapper {
    int countByExample(SnapshotExample example);

    int deleteByExample(SnapshotExample example);

    int deleteByPrimaryKey(Integer snapshot_id);

    int insert(Snapshot record);

    int insertSelective(Snapshot record);

    List<Snapshot> selectByExample(SnapshotExample example);

    Snapshot selectByPrimaryKey(Integer snapshot_id);

    int updateByExampleSelective(@Param("record") Snapshot record, @Param("example") SnapshotExample example);

    int updateByExample(@Param("record") Snapshot record, @Param("example") SnapshotExample example);

    int updateByPrimaryKeySelective(Snapshot record);

    int updateByPrimaryKey(Snapshot record);
}