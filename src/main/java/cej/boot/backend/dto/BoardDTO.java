package cej.boot.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDTO {
    private long bd_num;
    private String bd_id;
    private String bd_title;
    private String bd_content;
    private Date bd_date;
    private Date bd_udate;

    // bd_id 필드의 getter 메소드
    public String getBd_id() {
        return bd_id;
    }

    // bd_id 필드의 setter 메소드
    public void setBd_id(String bd_id) {
        this.bd_id = bd_id;
    }
}




