package com.waken.dorm.serviceImpl.school;

import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.school.SchoolClass;
import com.waken.dorm.common.entity.school.SchoolClassExample;
import com.waken.dorm.common.entity.school.SchoolClassStudentRel;
import com.waken.dorm.common.entity.school.SchoolClassStudentRelExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.school.EditSchoolClassForm;
import com.waken.dorm.common.form.school.SchoolClassForm;
import com.waken.dorm.common.form.school.SchoolClassTreeForm;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.ShiroUtils;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.UUIDUtils;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.school.SchoolClassView;
import com.waken.dorm.dao.school.SchoolClassMapper;
import com.waken.dorm.dao.school.SchoolClassStudentRelMapper;
import com.waken.dorm.service.school.SchoolClassService;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.service.student.StudentService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @ClassName SchoolClassServiceImpl
 * @Description SchoolClassServiceImpl
 * @Author zhaoRong
 * @Date 2019/4/2 10:56
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class SchoolClassServiceImpl extends BaseServerImpl implements SchoolClassService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SchoolClassMapper schoolClassMapper;
    @Autowired
    SchoolClassStudentRelMapper schoolClassStudentRelMapper;
    @Autowired
    SchoolService schoolService;
    @Autowired
    StudentService studentService;

    /**
     * （保存/修改）学校类别
     *
     * @param editSchoolClassForm
     * @return
     */
    @Transactional
    @Override
    public SchoolClass saveSchoolClass(EditSchoolClassForm editSchoolClassForm) {
        String schoolId = editSchoolClassForm.getSchoolId();
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count = Constant.ZERO;
        if (StringUtils.isEmpty(schoolId)){
            schoolId = schoolService.getSchoolIdByUserId(userId);
            editSchoolClassForm.setSchoolId(schoolId);
        }
        this.editSchoolClassValidate(editSchoolClassForm);
        if (StringUtils.isEmpty(editSchoolClassForm.getPkSchoolClassId())){//新增
            logger.info("service: 新增学校类别开始");
            String pkSchoolClassId = UUIDUtils.getPkUUID();
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setSchoolId(schoolId);
            schoolClass.setPkSchoolClassId(pkSchoolClassId);
            if (StringUtils.isNotEmpty(editSchoolClassForm.getParentId())){
                schoolClass.setParentId(editSchoolClassForm.getParentId());
            }
            schoolClass.setClassNo(getClassNo(editSchoolClassForm.getParentId()));
            schoolClass.setClassName(editSchoolClassForm.getClassName());
            schoolClass.setClassDesc(editSchoolClassForm.getClassDesc());
            schoolClass.setStatus(CodeEnum.ENABLE.getCode());
            schoolClass.setCreateTime(curDate);
            schoolClass.setCreateUserId(userId);
            schoolClass.setLastModifyTime(curDate);
            schoolClass.setLastModifyUserId(userId);
            if (StringUtils.isNotEmpty(editSchoolClassForm.getMemo())){
                schoolClass.setMemo(editSchoolClassForm.getMemo());
            }
            count = schoolClassMapper.insertSelective(schoolClass);
            if (count == Constant.ZERO){
                throw new DormException("新增学校类别个数为 0 个");
            }
            return schoolClass;
        }else {//修改
            logger.info("service: 更新学校类别信息开始");
            SchoolClass schoolClass = schoolClassMapper.selectByPrimaryKey(editSchoolClassForm.getPkSchoolClassId());
            if (schoolClass == null){
                throw new DormException("学校类别id无效!");
            }
            if (StringUtils.isNotEmpty(editSchoolClassForm.getClassName())){
                schoolClass.setClassName(editSchoolClassForm.getClassName());
            }
            if (StringUtils.isNotEmpty(editSchoolClassForm.getClassDesc())){
                schoolClass.setClassDesc(editSchoolClassForm.getClassDesc());
            }
            if (StringUtils.isNotEmpty(editSchoolClassForm.getMemo())){
                schoolClass.setMemo(editSchoolClassForm.getMemo());
            }
            schoolClass.setLastModifyUserId(userId);
            schoolClass.setLastModifyTime(curDate);
            count = schoolClassMapper.updateByPrimaryKeySelective(schoolClass);
            if (count == Constant.ZERO){
                throw new DormException("更新学校类别失败！");
            }
            return schoolClass;
        }
    }

    /**
     * 删除学校类别
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteSchoolClass(DeleteForm deleteForm) {
        logger.info("service: 删除学校类别开始");
        List<String> schoolClassIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        List<String> delClassIdList = this.recursionGetDelIds(schoolClassIds);
        Set<String> delIdsSet = new HashSet<>(delClassIdList);// 去掉重复的id
        delClassIds.clear();
        delClassIdList.addAll(delIdsSet);
        logger.info("需要删除的学校类别id: "+delClassIdList.toString());
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<SchoolClass> schoolClassList = schoolClassMapper.selectByIds(delClassIdList);
            StringBuffer sb = new StringBuffer();
            for (SchoolClass schoolClass : schoolClassList){
                if (CodeEnum.ENABLE.getCode() == schoolClass.getStatus()){
                    sb.append(schoolClass.getClassName());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下学校类别处于生效中："+sb.toString());
            }else {//删除学校类别

                SchoolClassExample example = new SchoolClassExample();
                SchoolClassExample.Criteria criteria = example.createCriteria();
                criteria.andPkSchoolClassIdIn(delClassIdList);
                count = schoolClassMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除学校类别个数为 0 条");
                }
            }
        }else if(CodeEnum.NO.getCode() == delStatus){
            count = schoolClassMapper.batchUpdateStatus(getToUpdateStatusMap(delClassIdList,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 查询学校类别集合
     *
     * @param schoolClassForm
     * @return
     */
    @Override
    public List<SchoolClassView> listSchoolClasses(SchoolClassForm schoolClassForm) {
        logger.info("service: 查询学校类别集合开始");
        if (StringUtils.isEmpty(schoolClassForm.getSchoolId())) {
            String userId = ShiroUtils.getUserId();
            String schoolId = schoolService.getSchoolIdByUserId(userId);
            schoolClassForm.setSchoolId(schoolId);
        }
        List<SchoolClassView> schoolClassViews = schoolClassMapper.listSchoolClasses(schoolClassForm);
        if (schoolClassViews.isEmpty()){
            return new ArrayList<SchoolClassView>();
        }
        return schoolClassViews;
    }

    /**
     * 查询学校类别树集合
     *
     * @param schoolClassTreeForm
     * @return
     */
    @Override
    public List<TreeView> listSchoolClassTree(SchoolClassTreeForm schoolClassTreeForm) {
        logger.info("service: 开始进入学校类别树查询");
        String schoolId = schoolClassTreeForm.getSchoolId();
        if (StringUtils.isEmpty(schoolId)){
            String userId = ShiroUtils.getUserId();
            schoolId = schoolService.getSchoolIdByUserId(userId);
            schoolClassTreeForm.setSchoolId(schoolId);
        }
        List<SchoolClass> schoolClasses = this.selectSchoolClasses(schoolClassTreeForm);
        List<TreeView> schoolClassTreeViewList = new ArrayList<TreeView>();
        for (SchoolClass schoolClass : schoolClasses){
            TreeView schoolClassTreeView = new TreeView();
            if (StringUtils.isEmpty(schoolClass.getParentId())){
                schoolClassTreeView.setPkId(schoolClass.getPkSchoolClassId());
                schoolClassTreeView.setText(schoolClass.getClassName());
                List<TreeView> childTreeViews = this.getChildSchoolClassTreeView(schoolId,schoolClass.getPkSchoolClassId());
                schoolClassTreeView.setNodes(childTreeViews);
                schoolClassTreeViewList.add(schoolClassTreeView);
            }
        }
        return schoolClassTreeViewList;
    }
    /**
     * 通过学生id获取学生
     *
     * @param studentId
     * @return
     */
    @Override
    public String getSchoolIdByStudentId(String studentId) {
        logger.info("service：开始进入通过学生id获取学校信息："+studentId);
        SchoolClassStudentRelExample example = new SchoolClassStudentRelExample();
        SchoolClassStudentRelExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<SchoolClassStudentRel> schoolClassStudentRels = schoolClassStudentRelMapper.selectByExample(example);
        if (schoolClassStudentRels.isEmpty()){
            return Constant.NULL_STRING;
        }else {
            String schoolClassId =  schoolClassStudentRels.get(Constant.ZERO).getSchoolClassId();
            SchoolClassExample classExample = new SchoolClassExample();
            SchoolClassExample.Criteria classCriteria = classExample.createCriteria();
            classCriteria.andPkSchoolClassIdEqualTo(schoolClassId);
            List<SchoolClass> schoolClasses = schoolClassMapper.selectByExample(classExample);
            if (schoolClasses.isEmpty()){
                return Constant.NULL_STRING;
            }else {
                return schoolClasses.get(Constant.ZERO).getSchoolId();
            }
        }
    }

    /**
     * 通过学习类别id批量导入学生（excel）
     *
     * @param multipartFile
     * @param classId
     */
    @Transactional
    @Override
    public void batchImportStudentByClassId(MultipartFile multipartFile, String classId) {
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        List<String> studentIds = studentService.batchAddStudent(multipartFile);
        List<SchoolClassStudentRel> schoolClassStudentRels = new ArrayList<>();
        for (String studentId : studentIds){
            SchoolClassStudentRel schoolClassStudentRel = new SchoolClassStudentRel();
            String pkRelId = UUIDUtils.getPkUUID();
            schoolClassStudentRel.setPkSchoolClassStudentId(pkRelId);
            schoolClassStudentRel.setSchoolClassId(classId);
            schoolClassStudentRel.setStudentId(studentId);
            schoolClassStudentRel.setStatus(CodeEnum.ENABLE.getCode());
            schoolClassStudentRel.setCreateTime(curDate);
            schoolClassStudentRel.setCreateUserId(userId);
            schoolClassStudentRel.setLastModifyTime(curDate);
            schoolClassStudentRel.setLastModifyUserId(userId);
            schoolClassStudentRels.add(schoolClassStudentRel);
        }
        int count = Constant.ZERO;
        count = schoolClassStudentRelMapper.batchImportStudentClassRel(schoolClassStudentRels);
        if (count == Constant.ZERO){
            throw new DormException("批量新增失败个数 0 条！");
        }
    }

    /**
     * 通过主键id查询学校类别信息
     *
     * @param schoolClassTreeForm
     * @return
     */
    @Override
    public SchoolClass selectByPkId(SchoolClassTreeForm schoolClassTreeForm) {
        if (StringUtils.isEmpty(schoolClassTreeForm.getPkSchoolClassId())){
            throw new DormException("学校类别id为空！");
        }
        return schoolClassMapper.selectByPrimaryKey(schoolClassTreeForm.getPkSchoolClassId());
    }
    /**
     * 递归得到子节点
     * @param schoolClassId
     * @return
     */
    private List<TreeView> getChildSchoolClassTreeView(String schoolId,String schoolClassId){
        SchoolClassTreeForm schoolClassTreeForm = new SchoolClassTreeForm();
        schoolClassTreeForm.setParentId(schoolClassId);
        schoolClassTreeForm.setSchoolId(schoolId);
        List<SchoolClass> schoolClasses = this.selectSchoolClasses(schoolClassTreeForm);
        if (schoolClasses.isEmpty()){
            return new ArrayList<TreeView>();
        }else {
            List<TreeView> schoolClassTreeViewList = new ArrayList<TreeView>();
            for (SchoolClass schoolClass : schoolClasses){
                TreeView schoolClassTreeView = new TreeView();
                schoolClassTreeView.setPkId(schoolClass.getPkSchoolClassId());
                schoolClassTreeView.setText(schoolClass.getClassName());
                List<TreeView> childTreeViews = this.getChildSchoolClassTreeView(schoolId,schoolClass.getPkSchoolClassId());
                schoolClassTreeView.setNodes(childTreeViews);
                schoolClassTreeViewList.add(schoolClassTreeView);
            }
            return schoolClassTreeViewList;
        }
    }
    private List<SchoolClass> selectSchoolClasses(SchoolClassTreeForm schoolClassTreeForm){
        SchoolClassExample example = new SchoolClassExample();
        SchoolClassExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(schoolClassTreeForm.getPkSchoolClassId())){
            criteria.andPkSchoolClassIdEqualTo(schoolClassTreeForm.getPkSchoolClassId());
        }
        criteria.andSchoolIdEqualTo(schoolClassTreeForm.getSchoolId());
        if (StringUtils.isNotEmpty(schoolClassTreeForm.getParentId())){
            criteria.andParentIdEqualTo(schoolClassTreeForm.getParentId());
        }
        criteria.andStatusEqualTo(CodeEnum.ENABLE.getCode());
        example.setOrderByClause("class_no asc");
        return schoolClassMapper.selectByExample(example);
    }
    /**
     * 递归得到学校类别子集
     */
    private static List<String> delClassIds = new ArrayList<>();
    private List<String> recursionGetDelIds(List<String> schoolClassIds){
        delClassIds.addAll(schoolClassIds);
        for(String schoolClassId : schoolClassIds){
            List<String> childIds = this.getChildIdByParenId(schoolClassId);
            if (!childIds.isEmpty()){
                this.recursionGetDelIds(childIds);
            }
        }
        return delClassIds;
    }

    /**
     * 通过父级id得到子集
     * @param parentId
     * @return
     */
    private List<String> getChildIdByParenId(String parentId){
        SchoolClassExample example = new SchoolClassExample();
        SchoolClassExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<SchoolClass> schoolClasses = schoolClassMapper.selectByExample(example);
        List<String> childIds = new ArrayList<>();
        if (!schoolClasses.isEmpty()){
            for (SchoolClass schoolClass : schoolClasses){
                childIds.add(schoolClass.getPkSchoolClassId());
            }
        }
        return childIds;
    }

    /**
     * 编辑学校类别验证
     * @param editSchoolClassForm
     */
    private void editSchoolClassValidate(EditSchoolClassForm editSchoolClassForm){
        if (StringUtils.isEmpty(editSchoolClassForm.getPkSchoolClassId())){//新增验证
            if (StringUtils.isEmpty(editSchoolClassForm.getClassName())){
                throw new DormException("学校类别名称为空");
            }
            SchoolClassExample example = new SchoolClassExample();
            SchoolClassExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isEmpty(editSchoolClassForm.getParentId())){
                criteria.andParentIdIsNull();
            }else {
                criteria.andParentIdEqualTo(editSchoolClassForm.getParentId());
            }
            criteria.andClassNameEqualTo(editSchoolClassForm.getClassName());
            criteria.andSchoolIdEqualTo(editSchoolClassForm.getSchoolId());
            List<SchoolClass> schoolClasses = schoolClassMapper.selectByExample(example);
            if (!schoolClasses.isEmpty()){
                throw new DormException("学校类别名称已存在！");
            }
        }else {//修改验证
            if (StringUtils.isNotEmpty(editSchoolClassForm.getClassName())){
                SchoolClass schoolClass = schoolClassMapper.selectByPrimaryKey(editSchoolClassForm.getPkSchoolClassId());
                SchoolClassExample example = new SchoolClassExample();
                SchoolClassExample.Criteria criteria = example.createCriteria();
                if (StringUtils.isEmpty(schoolClass.getParentId())){
                    criteria.andParentIdIsNull();
                }else {
                    criteria.andParentIdEqualTo(schoolClass.getParentId());
                }
                criteria.andClassNameEqualTo(editSchoolClassForm.getClassName());
                criteria.andSchoolIdEqualTo(editSchoolClassForm.getSchoolId());
                List<SchoolClass> schoolClasses = schoolClassMapper.selectByExample(example);
                if (!schoolClasses.isEmpty()){
                    if (!StringUtils.equals(schoolClasses.get(Constant.ZERO).getPkSchoolClassId(),editSchoolClassForm.getPkSchoolClassId())){
                        throw new DormException("学校类别名称已存在！");
                    }
                }
            }
        }
    }

    /**
     * 获取类别序号
     * @param parentId
     * @return
     */
    private int getClassNo(String parentId){
        SchoolClassExample example = new SchoolClassExample();
        SchoolClassExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(parentId)){
            criteria.andParentIdIsNull();
        }else {
            criteria.andParentIdEqualTo(parentId);
        }
        criteria.andStatusEqualTo(CodeEnum.ENABLE.getCode());
        return schoolClassMapper.countByExample(example);
    }
}
