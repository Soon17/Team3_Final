package kr.kh.team3final.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.kh.team3final.model.vo.ThumbnailVO;

public interface ThumbnailDAO {
  List<ThumbnailVO> selectThumbnail(@Param("th_table") String th_table, @Param("th_key") int th_key);
}
