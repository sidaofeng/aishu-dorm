package com.waken.dorm.serviceImpl.basic;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.TreeUtil;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.dao.dorm.BedMapper;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.service.basic.BedService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 宿舍床位 服务实现类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BedServiceImpl extends ServiceImpl<BedMapper, DormBed> implements BedService {
    private final DormMapper dormMapper;
    private final TreeUtil treeUtil;

    @Override
    public int insert(DormBed bed) {
        int result = 0;
        if (bed == null || StringUtils.isEmpty(bed.getDormId())) {
            throw new ServiceException("入参数据为空");
        }
        this.verifyBed(bed);
        if (this.verification(bed) == 0) {
            result = this.baseMapper.insert(bed);
        }
        return result;
    }

    @Override
    public void delete(DeleteForm deleteForm) {
        if (deleteForm == null || deleteForm.getDelIds() == null) {
            throw new ServiceException("入参数据为空");
        }
        if (!deleteForm.getDelIds().isEmpty()) {
            //物理删除
            this.baseMapper.deleteBatchIds(deleteForm.getDelIds());
        }
    }

    @Override
    public int update(DormBed bed) {
        int result = 0;
        if (bed == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(bed) == 0) {
            //宿舍床位编码不能修改
            bed.setCode(null);
            result = this.baseMapper.updateById(bed);
        }
        return result;
    }

    @Override
    public DormBed get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<DormBed> list(String dormId) {
        List<DormBed> bedList = this.baseMapper.selectList(new LambdaQueryWrapper<DormBed>()
                .eq(DormBed::getDormId, dormId)
                .orderByAsc(DormBed::getCode));
        return bedList;
    }

    @Override
    public List<Tree<T>> bedTree() {
        List<Tree<T>> basicTreeList = this.baseMapper.selectBasicTreeList();
        List<Tree<T>> roomTreeList = this.baseMapper.selectDormRoomTreeList();
        List<Tree<T>> bedTreeList = this.baseMapper.selectBedTreeList();
        if (this.handlerRoomTree(roomTreeList, bedTreeList)) {
            basicTreeList.addAll(roomTreeList);
            basicTreeList.addAll(bedTreeList);
        }
        return treeUtil.toTree(basicTreeList, Constant.ROOT);
    }

    private boolean handlerRoomTree(List<Tree<T>> roomTreeList, List<Tree<T>> bedTreeList) {
        if (roomTreeList != null && !roomTreeList.isEmpty()) {
            Map<String, Long> parentIdAndCountMap = bedTreeList.stream()
                    .collect(Collectors.groupingBy(Tree::getParentId, Collectors.counting()));
            roomTreeList.stream().forEach(roomTree -> {
                if (parentIdAndCountMap == null || parentIdAndCountMap.isEmpty()
                        || parentIdAndCountMap.get(roomTree.getId()) == 0) {
                    roomTree.setTitle(roomTree.getTitle() + " (床位已满)");
                    roomTree.setLeaf(true);
                } else {
                    roomTree.setTitle(roomTree.getTitle() + " (床位余" + parentIdAndCountMap.get(roomTree.getId()) + ")");
                }
            });
        }
        return true;
    }

    private void verifyBed(DormBed dormBed) {
        if (dormBed == null) {
            throw new ServiceException("入参数据为空");
        }
        if (dormBed.getCode() <= 0) {
            throw new ServiceException("床位号必须大于 0 ！");
        }
        Dorm dorm = this.dormMapper.selectById(dormBed.getDormId());
        if (dormBed.getCode() > dorm.getBedNum()) {
            throw new ServiceException("床位号必须小于宿舍" + dorm.getCode() + "的床位数量" + dorm.getBedNum());
        }
    }

    private int verification(DormBed bed) {
        if (bed.getName() != null) {
            //验证宿舍床位
            List<DormBed> bedList = this.baseMapper.selectList(new LambdaQueryWrapper<DormBed>()
                    .eq(DormBed::getName, bed.getName())
                    .eq(DormBed::getDormId, bed.getDormId()));
            if (bedList.size() != 0) {
                if (bed.getId() == null) {
                    throw new ServiceException("该名称已经存在");
                } else {
                    for (DormBed campus : bedList) {
                        if (!campus.getId().equals(bed.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("该名称已经存在");
                        }
                    }
                }
            }
        }
        if (bed.getCode() != null) {
            //验证校区名称
            List<DormBed> bedList = this.baseMapper.selectList(new LambdaQueryWrapper<DormBed>()
                    .eq(DormBed::getCode, bed.getCode())
                    .eq(DormBed::getDormId, bed.getDormId()));
            if (bedList.size() != 0) {
                if (bed.getId() == null) {
                    throw new ServiceException("宿舍床位不能重复");
                } else {
                    for (DormBed campus : bedList) {
                        if (!campus.getId().equals(bed.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("宿舍床位不能重复");
                        }
                    }
                }
            }
        }
        return 0;
    }
}
