package com.zdnf.bbs.dao;

import com.zdnf.bbs.domain.Perfect;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by 90488 on 2017/5/22.
 */
public interface PerfectDao {
    public boolean add(@Param("Perfect")Perfect perfect);

    public boolean delete(@Param("id")int id);

    public List<Perfect> get_all(@Param("min")int min,@Param("max")int max);

    public int amount();
}
