package com.susy.dormitoryassistant.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知的一个包装类，会一次性的把最新的六个类型的type给塞入到这个里面来
 */
public class NoticeVO {

    private List<Notice> noticeList;

    private Map<String, String> notice;

    public List<Notice> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    public Map<String, String> getNotice() {
        return notice;
    }

    public void setNotice(Map<String, String> notice) {
        this.notice = notice;
    }
}
