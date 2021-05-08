package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    //根据父节点id查询树形结构，由于是懒加载，最开始只显示第一级目录，只有点击下级目录的时候
    //才会去查询子节点
    List<EasyUITreeNode> getItemCatList(long parentId);
}
