package kr.kh.team3final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.team3final.dao.ThumbnailDAO;
import kr.kh.team3final.model.vo.ThumbnailVO;

@Service
public class ThumbnailService {

    @Autowired
    private ThumbnailDAO thumbnailDAO;

    public List<ThumbnailVO> selectThumbnail(String th_table, int th_key) {
        return thumbnailDAO.selectThumbnail(th_table, th_key);
    }
}
