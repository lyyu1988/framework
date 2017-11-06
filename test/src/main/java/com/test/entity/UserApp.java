package com.test.entity;

import java.util.Date;

/**
 * UserApp entity. @author MyEclipse Persistence Tools
 */

public class UserApp implements java.io.Serializable {

    private Long id;
    private Long creater;
    private Date createTime;
    private Long updater;
    private Date updateTime;
    private Long deleter;
    private Date deleteTime;
    private Integer isDelete;

    private String username;
    private String password;
    private String phone;
    private String image;
    private String sex;
    private Date birthday;
    private Date lastloginTime;
    private String creditNum;
    private String equipmentNum;
    private String userId;
    private Long channelId;
    private Integer deviceType;
    private String freeNum;
    private String provinceCode;
    private String cityCode;
    private Long provinceId;
    private Long cityId;
    private Long schoolId;
    private Long weiSchoolId;
    private Long weiDepartmentId;
    private Long weiClassId;
    private Integer isArticleSort;
    private String schoolName;
    private Integer silentType;
    private String schoolComplaintCall;

    private String schoolCode;//学校代码
    private String departmentCode;//院系代码
    private String specialitiesCode;//专业代码
    private String classCode;//班级代码
    private String realName;//姓名
    private String email;//邮箱
    private String houseNum;//楼号
    private String identityCard;//身份证号
    private String studentNum;//学号
    private String admissionTicket;//准考证号
    private Integer dataSource;//数据来源 1.注册	2.后台添加	3.智慧迎新 	4.翼讯 5.公众号 6.微校圈注册 7.电竞大赛
    private Integer relatedStatus;//关联微信状态 0.未关联	1.已关联
    private Integer loginCount;

    private String accessToken;//翼讯宽带token

    private String grade;
    private String tag;
    private Integer otherLetterCounts;
    private Integer schoolLetterCounts;
    private Integer commentCounts;
    private Integer praiseCounts;
    private Integer status;
    private String schoolTsnLink;

    // Property accessors
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCreater() {
        return creater;
    }
    public void setCreater(Long creater) {
        this.creater = creater;
    }


    public Long getUpdater() {
        return updater;
    }
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Long getDeleter() {
        return deleter;
    }
    public void setDeleter(Long deleter) {
        this.deleter = deleter;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getDeleteTime() {
        return deleteTime;
    }
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    public String getUsername() {
        return this.username;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getAdmissionTicket() {
        return admissionTicket;
    }

    public void setAdmissionTicket(String admissionTicket) {
        this.admissionTicket = admissionTicket;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getLastloginTime() {
        return this.lastloginTime;
    }

    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }

    public String getCreditNum() {
        return this.creditNum;
    }

    public void setCreditNum(String creditNum) {
        this.creditNum = creditNum;
    }

    public String getEquipmentNum() {
        return this.equipmentNum;
    }

    public void setEquipmentNum(String equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getFreeNum() {
        return this.freeNum;
    }

    public void setFreeNum(String freeNum) {
        this.freeNum = freeNum;
    }

    public Long getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getWeiSchoolId() {
        return this.weiSchoolId;
    }

    public void setWeiSchoolId(Long weiSchoolId) {
        this.weiSchoolId = weiSchoolId;
    }


    public Long getWeiDepartmentId() {
        return this.weiDepartmentId;
    }

    public void setWeiDepartmentId(Long weiDepartmentId) {
        this.weiDepartmentId = weiDepartmentId;
    }


    public String getSpecialitiesCode() {
        return specialitiesCode;
    }

    public void setSpecialitiesCode(String specialitiesCode) {
        this.specialitiesCode = specialitiesCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Long getWeiClassId() {
        return this.weiClassId;
    }

    public void setWeiClassId(Long weiClassId) {
        this.weiClassId = weiClassId;
    }

    public Integer getIsArticleSort() {
        return isArticleSort;
    }

    public void setIsArticleSort(Integer isArticleSort) {
        this.isArticleSort = isArticleSort;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getSilentType() {
        return silentType;
    }

    public void setSilentType(Integer silentType) {
        this.silentType = silentType;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getSchoolComplaintCall() {
        return schoolComplaintCall;
    }

    public void setSchoolComplaintCall(String schoolComplaintCall) {
        this.schoolComplaintCall = schoolComplaintCall;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getOtherLetterCounts() {
        return otherLetterCounts;
    }

    public void setOtherLetterCounts(Integer otherLetterCounts) {
        this.otherLetterCounts = otherLetterCounts;
    }

    public Integer getSchoolLetterCounts() {
        return schoolLetterCounts;
    }

    public void setSchoolLetterCounts(Integer schoolLetterCounts) {
        this.schoolLetterCounts = schoolLetterCounts;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public Integer getPraiseCounts() {
        return praiseCounts;
    }

    public void setPraiseCounts(Integer praiseCounts) {
        this.praiseCounts = praiseCounts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSchoolTsnLink() {
        return schoolTsnLink;
    }

    public void setSchoolTsnLink(String schoolTsnLink) {
        this.schoolTsnLink = schoolTsnLink;
    }

}