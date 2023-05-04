package cej.boot.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {

    private long mb_num;

    private long mb_status;

    private String mb_id;

    private String mb_pwd;

    private String mb_name;

    private String mb_phone;

    private Date mb_rdate;

    private Date mb_udate;
}
